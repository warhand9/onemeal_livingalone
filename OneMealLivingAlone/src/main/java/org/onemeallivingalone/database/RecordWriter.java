package org.onemeallivingalone.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class RecordWriter {

	private final Logger logger = LoggerFactory.getLogger("omla-data-driver");

	private final ObjectMapper mapper = new ObjectMapper()
			.registerModule(new Jdk8Module())
			.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
			.registerModule(new JavaTimeModule())
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			.enable(SerializationFeature.INDENT_OUTPUT);

	private final FileManager fileManager;

	public RecordWriter(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public void writeJsonRecords(Collection<? extends Object> objs, Class<? extends Object> c, String dirName)
			throws IOException {
		logger.debug("Starting writing records: {}", c.getSimpleName());

		// Convert to a JSON tree
		String content = null;
		try {
			List<JsonNode> jsons = new ArrayList<>();
			for (Object obj : objs) {
				jsons.add(mapper.valueToTree(obj));
			}
			content = mapper.writeValueAsString(jsons);
		} catch (IllegalArgumentException e) {
			throw e;
		}

		// Write to the file
		String fileName = String.format("%s/%s.json", dirName, c.getSimpleName());
		try {
			fileManager.writeToFile(fileName, content);
		} catch (IOException e) {
			throw e;
		}

		logger.debug("Done writing records: {}", c.getSimpleName());
	}

}
