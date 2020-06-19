package br.com.caiodearaujo.b3test.tasks;

import br.com.caiodearaujo.b3test.dto.UserDTO;
import br.com.caiodearaujo.b3test.entities.FileImport;
import br.com.caiodearaujo.b3test.exceptions.InvalidUserDataException;
import br.com.caiodearaujo.b3test.exceptions.UserAlreadyExistsException;
import br.com.caiodearaujo.b3test.services.FileImportService;
import br.com.caiodearaujo.b3test.services.UserService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class FileImportTasklet implements Tasklet {

    private FileImportService fileImportService;
    private UserService userService;

    public FileImportTasklet(FileImportService fileImportService, UserService userService) {
        this.fileImportService = fileImportService;
        this.userService = userService;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        String dateFormated = LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
        log.info("Starting import files pending at {}", dateFormated);
        fileImportService.findAllNotImported().forEach(this::importProcess);
        return RepeatStatus.FINISHED;
    }

    private void importProcess(FileImport fileImport) {
        try {
            this.fileImportService.initImport(fileImport);
            BufferedReader fileReader = Files.newBufferedReader(Paths.get(fileImport.getFileName()));

            CsvToBean<UserDTO> csvToBean = new CsvToBeanBuilder<UserDTO>(fileReader)
                    .withType(UserDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(';')
                    .build();

            for (UserDTO userDTO : csvToBean) {
                try {
                    userService.save(userDTO);
                } catch (InvalidUserDataException | UserAlreadyExistsException e) {
                    log.warn(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileImport.setProcessed(true);
            fileImport.setFinishProcess(new Date());
            this.fileImportService.save(fileImport);
        }
    }

}
