package com.varela.open;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * Created by lance on 2016/1/28.
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class BatchTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job csvJob;


    @Test
    public void run() {
        try {
            long startTime = System.currentTimeMillis();
            JobParametersBuilder builder = new JobParametersBuilder();
            builder.addString("inputFile", "file:E://data.csv");
            builder.addString("outputFile", "file:E://1.csv");

            JobParameters jobParameters = builder.toJobParameters();
            JobExecution jobExecution = jobLauncher.run(csvJob, jobParameters);
            System.out.println("耗时:" + (System.currentTimeMillis() - startTime)+"==》异常："+jobExecution);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }


    }


}
