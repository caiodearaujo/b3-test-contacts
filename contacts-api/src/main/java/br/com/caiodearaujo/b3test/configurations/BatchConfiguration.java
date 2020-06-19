package br.com.caiodearaujo.b3test.configurations;

import br.com.caiodearaujo.b3test.services.FileImportService;
import br.com.caiodearaujo.b3test.services.UserService;
import br.com.caiodearaujo.b3test.tasks.FileImportTasklet;
import br.com.caiodearaujo.b3test.tasks.FileSearchTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    protected static final String FILE_SEARCH_TASK = "fileSearchTask";
    protected static final String FILE_IMPORT_TASK = "fileImportTask";
    protected static final String FILE_SEARCH_IMPORT_JOB = "fileSearchAndImportJob";
    protected static final String FILE_SEARCH_IMPORT_TRIGGER = "fileSearchAndImportTrigger";

    @Value("${custom.file-path}")
    private String path;

    private UserService userService;
    private FileImportService fileImportService;
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                              FileImportService fileImportService, UserService userService) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.fileImportService = fileImportService;
        this.userService = userService;
    }

    @Bean
    public Step fileSearch() {
        return stepBuilderFactory.get(FILE_SEARCH_TASK)
                .tasklet(new FileSearchTasklet(path, fileImportService))
                .build();
    }

    @Bean
    public Step fileImport() {
        return stepBuilderFactory.get(FILE_IMPORT_TASK)
                .tasklet(new FileImportTasklet(fileImportService, userService))
                .build();
    }

    @Bean(name = FILE_SEARCH_IMPORT_JOB)
    public Job fileSarchJob() {
        return jobBuilderFactory.get(FILE_SEARCH_IMPORT_JOB)
                .incrementer(new RunIdIncrementer())
                .start(fileSearch())
                .next(fileImport())
                .build();
    }

}
