package org.onemeallivingalone.database;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.onemeallivingalone.account.Account;
import org.onemeallivingalone.account.AdminAccount;
import org.onemeallivingalone.account.CustomerAccount;

public class RecordReaderWriterTest {

	private static String TEST_RECORDS_DIR = "test-data";

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

	@Test
	public void writeAndReadAccountsTest() throws IOException {
		Collection<Account> items = generateTestSetAccounts();

		FileManager fileManager = new FileManager();
		RecordWriter recordWriter = new RecordWriter(fileManager);
		recordWriter.writeJsonRecords(items, Account.class, TEST_RECORDS_DIR);

		RecordReader recordReader = new RecordReader(fileManager);
		Collection<Object> itemsRead = recordReader.readJsonRecords(Account.class, TEST_RECORDS_DIR);

		assertEquals(items, itemsRead);
	}

}
