package org.onemeallivingalone.database;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.onemeallivingalone.account.Account;
import org.onemeallivingalone.account.AdminAccount;
import org.onemeallivingalone.account.CustomerAccount;
import org.onemeallivingalone.item.Food;
import org.onemeallivingalone.item.FoodReview;
import org.onemeallivingalone.item.Ingredient;

public class RecordReaderWriterTest {

	private static String TEST_RECORDS_DIR = "test-data/record-reader-writer";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Change logging level
		org.apache.logging.log4j.core.config.Configurator.setLevel("", org.apache.logging.log4j.Level.WARN);
		org.apache.logging.log4j.core.config.Configurator.setLevel("omla-data-driver",
				org.apache.logging.log4j.Level.TRACE);
	}

	private Collection<Account> generateTestSetAccounts() {
		List<Account> items = new ArrayList<>();
		items.add(new AdminAccount("abcde", "abcde!12@$#"));
		items.add(new CustomerAccount("fghsl", "abcde!12@$#", "aaa@bbb.net"));
		{
			CustomerAccount account1 = new CustomerAccount("wefwg", "abcde!12@$#", "bbb@ccc.net");
			account1.addPeronsalFavoriteFood(10);
			account1.addPeronsalFavoriteFood(20);
			account1.addPersonalIngredient(100);
			account1.addPersonalIngredient(200);
			account1.addFoodReview(1000);
			account1.addFoodReview(2000);
			items.add(account1);
		}
		return items;
	}
	
	private Collection<Ingredient> generateTestSetIngredients() {
		List<Ingredient> items = new ArrayList<>();
		items.add(new Ingredient(123, "mario", 100000));
		items.add(new Ingredient(456, "sonic", 500));
		return items;
	}
	
	private Collection<Food> generateTestSetFoods() {
		List<Food> items = new ArrayList<>();
		items.add(new Food(1111, "nayeon", 22, "rabbit"));
		items.add(new Food(2222, "nabong", 950922, "matnae"));
		{
			Food food1 = new Food(3333, "nasung", 2015, "juice");
			List<Integer> ingreIds = food1.getIngredients();
			ingreIds.add(123456789);
			ingreIds.add(987654321);
			List<Integer> reviewIds = food1.getReviews();
			reviewIds.add(1010101);
			reviewIds.add(1101101);
			items.add(food1);
		}
		return items;
	}
	
	private Collection<FoodReview> generateTestSetFoodReviews() {
		List<FoodReview> items = new ArrayList<>();
		items.add(new FoodReview(123, "mario", 100000, 10, "aaaa"));
		items.add(new FoodReview(456, "sonic", 500, 5, "bbbb"));
		return items;
	}
	
	@Test
	public void writeAndReadAccountsTest() throws IOException {
		writeAndReadTest(generateTestSetAccounts(), Account.class);
	}

	@Test
	public void writeAndReadIngredientsTest() throws IOException {
		writeAndReadTest(generateTestSetIngredients(), Ingredient.class);
	}

	@Test
	public void writeAndReadFoodsTest() throws IOException {
		writeAndReadTest(generateTestSetFoods(), Food.class);
	}

	@Test
	public void writeAndReadFoodReviewsTest() throws IOException {
		writeAndReadTest(generateTestSetFoodReviews(), FoodReview.class);
	}

	private void writeAndReadTest(Collection<? extends Object> items, Class<? extends Object> c) throws IOException {
		Collection<Object> itemsRead = null;
		FileManager fileManager = new FileManager();
		RecordWriter recordWriter = new RecordWriter(fileManager);
		RecordReader recordReader = new RecordReader(fileManager);
		
		// Read and write with an empty test set
		recordWriter.writeJsonRecords(Collections.emptyList(), c, TEST_RECORDS_DIR);
		itemsRead = recordReader.readJsonRecords(c, TEST_RECORDS_DIR);
		assertEquals(Collections.emptyList(), itemsRead);

		// Read and write with a non-empty test set
		recordWriter.writeJsonRecords(items, c, TEST_RECORDS_DIR);
		itemsRead = recordReader.readJsonRecords(c, TEST_RECORDS_DIR);
		assertEquals(items, itemsRead);
	}

}
