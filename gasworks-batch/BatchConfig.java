package com.example.gasworks.config;

import com.example.gasworks.model.InputData;
import com.example.gasworks.processor.SampleItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class BatchConfig {

    @Bean
    public ItemReader<InputData> reader() {
        return new ListItemReader<>(List.of(
                new InputData(1L, "Shinjuku Plant", 1500),
                new InputData(2L, "Shibuya Station", 2800),
                new InputData(3L, "Yokohama Port", 900)
        ));
    }

    @Bean
    public ItemWriter<String> writer() {
        return chunk -> {
            System.out.println(">>> Writing batch chunk...");
            chunk.forEach(System.out::println);
        };
    }

    @Bean
    public Step sampleStep(JobRepository jobRepository, 
                          PlatformTransactionManager transactionManager,
                          ItemReader<InputData> reader, 
                          SampleItemProcessor processor, 
                          ItemWriter<String> writer) {
        return new StepBuilder("sampleStep", jobRepository)
                .<InputData, String>chunk(2, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public Job gasworksSampleJob(JobRepository jobRepository, Step sampleStep) {
        return new JobBuilder("gasworksSampleJob", jobRepository)
                .start(sampleStep)
                .build();
    }
}