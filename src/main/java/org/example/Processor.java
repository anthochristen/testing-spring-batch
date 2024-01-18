package org.example;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Simple {@link ItemProcessor} implementation whose business logic is to convert values to uppercase. A CAL transaction
 * is logged for each item processed.
 */
@Component("processor")
public class Processor implements ItemProcessor<String, String> {
	@Override
	public String process(String item) {
		return item.toUpperCase();
	}

}
