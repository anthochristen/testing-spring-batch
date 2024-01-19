package org.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.PlatformTransactionManager;

@SpringBootApplication
@EnableBatchProcessing
//@ImportResource("classpath:launch-context.xml") -- Use this to fix
public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Main.class);
        app.setSources(Collections.singleton("classpath:launch-context.xml"));
        System.exit(SpringApplication.exit(app.run(args)));
    }

    @Bean
    @JobScope
    public ItemReader<String> reader() {
        return new ItemReader<String>() {
            public static final String DEFAULT_LIST = "A,B,C,D,E";
            private List<String> inputList = new LinkedList(Arrays.asList(DEFAULT_LIST.split(",")));

            @Override
            public String read() {
                String company = null;
                if (!this.inputList.isEmpty()) {
                    company = this.inputList.remove(0);
                }
                LOGGER.info(company == null ? "Nothing to read" : "Read an alphabet:" + "using instance {}", this);
                return company;
            }
        };
    }

    @Bean
    public ItemProcessor<String, String> processor() {
        return String::toUpperCase;
    }

    @Bean
    public ItemWriter<String> writer() {
        return items -> LOGGER.info("Wrote data: {}", items);
    }
}