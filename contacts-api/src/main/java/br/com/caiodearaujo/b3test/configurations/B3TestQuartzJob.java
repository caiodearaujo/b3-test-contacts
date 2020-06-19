package br.com.caiodearaujo.b3test.configurations;

import lombok.Data;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.UUID;

@Data
public class B3TestQuartzJob extends QuartzJobBean {

    private String jobName;
    private JobLocator jobLocator;
    private JobLauncher jobLauncher;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Job job = jobLocator.getJob(getJobName());
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("JobID", UUID.randomUUID().toString())
                    .toJobParameters();

            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            //TODO
            e.printStackTrace();
        }
    }

}
