package org.example;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.PassThroughLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

/**
 * Writer that extends Spring's {@link FlatFileItemWriter}
 */
@Component("writer")
public class Writer implements ItemWriter<String> {

	public void write(List<? extends String> items) {
		LOGGER.info("Wrote data: {}", items);
	}
	private static final Logger LOGGER = LoggerFactory.getLogger(Writer.class);

}
