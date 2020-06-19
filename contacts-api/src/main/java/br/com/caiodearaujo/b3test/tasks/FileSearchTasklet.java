package br.com.caiodearaujo.b3test.tasks;

import br.com.caiodearaujo.b3test.entities.FileImport;
import br.com.caiodearaujo.b3test.services.FileImportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class FileSearchTasklet implements Tasklet {

    private String path;
    private FileImportService fileImportService;

    public FileSearchTasklet(String path, FileImportService fileImportService) {
        this.path = path;
        this.fileImportService = fileImportService;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("Initializing search for new files at {}",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("h:m:ss a")));
        try {
            String[] filesName;

            File file = new File(path);

            filesName = file.list((dir, name) -> name.endsWith(".csv"));

            for (String fileName : filesName) {
                fileImportService.saveIfNews(new FileImport(path.concat(fileName)));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return RepeatStatus.FINISHED;
    }

}
