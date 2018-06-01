package org.onemeallivingalone.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.onemeallivingalone.account.Account;
import org.onemeallivingalone.account.AccountList;
import org.onemeallivingalone.item.Food;
import org.onemeallivingalone.item.FoodList;
import org.onemeallivingalone.item.FoodReview;
import org.onemeallivingalone.item.FoodReviewList;
import org.onemeallivingalone.item.Ingredient;
import org.onemeallivingalone.item.IngredientList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class RecordReader {

	private final Logger logger = LoggerFactory.getLogger("omla-data-driver");

	private final ObjectMapper mapper = new ObjectMapper()
			.registerModule(new Jdk8Module())
			.registerModule(new ParameterNamesModule(JsonCreator.Mode.PROPERTIES))
			.registerModule(new JavaTimeModule())
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

	private final FileManager fileManager;

	public RecordReader(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	public Collection<Object> readJsonRecords(Class<? extends Object> c, String dirName) throws IOException {
		logger.debug("Starting reading records: {}", c.getSimpleName());

		// Read from the file
		String fileName = String.format("%s/%s.json", dirName, c.getSimpleName());
		String content = null;
		try {
			content = fileManager.readFromFile(fileName);
		} catch (IOException e) {
			throw e;
		}

		// Convert to an array of objects
		List<Object> records = new ArrayList<>();
		JsonNode contentJson = mapper.readTree(content);
		if (!contentJson.isArray()) {
			throw new IOException("The JSON content is not an array!");
		}
		for (JsonNode objJson : (ArrayNode) contentJson) {
			try {
				Object obj = mapper.treeToValue(objJson, c);
				records.add(obj);
			} catch (JsonProcessingException e) {
				logger.error("Cannot parse an object in JSON: {}", c.getSimpleName(), e);
			}
		}

		logger.debug("Done reading records: {}", c.getSimpleName());

		return records;
	}

	public void readRecordsToLists() throws IOException {
		try {
			Collection<Object> itemsRead;
			itemsRead = readJsonRecords(Account.class, FileManager.RECORDS_DIR);
			AccountList.loadAllStatic(itemsRead);
			itemsRead = readJsonRecords(Food.class, FileManager.RECORDS_DIR);
			FoodList.getInstance().loadAll(itemsRead);
			itemsRead = readJsonRecords(Ingredient.class, FileManager.RECORDS_DIR);
			IngredientList.getInstance().loadAll(itemsRead);
			itemsRead = readJsonRecords(FoodReview.class, FileManager.RECORDS_DIR);
			FoodReviewList.getInstance().loadAll(itemsRead);
		} catch (IOException e) {
			throw e;
		}
	}

}
