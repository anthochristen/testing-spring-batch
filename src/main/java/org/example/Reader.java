package org.example;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Simple {@link ItemReader} implementation that reads alphabets from a comma-delimited list of strings.
 */
//@Component
//@StepScope
public class Reader implements ItemReader<String> {

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

	private static final Logger LOGGER = LoggerFactory.getLogger(Reader.class);

}