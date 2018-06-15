package org.onemeallivingalone.account;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountKindTest {
	
	private static String rightId = "abc_0";
	private static String rightPassword = "1q2w3e4r!";
	private static String rightEmail = "emiya@muljomdao.com";
	
	/**
	 * Tests a wrong password with alphabets only
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAlphabetsOnlyPasswordAdmin() {
		new AdminAccount(rightId, "abcdefgh");
	}
	
	/**
	 * Tests a wrong password with digits only
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDigitsOnlyPasswordAdmin() {
		new AdminAccount(rightId, "01234567");
	}
	
	/**
	 * Tests a right tuple of right ID, password, and email
	 */
	@Test
	public void testLegalIdAndPasswordAndEmailCustomer() {
		assertNotNull(new CustomerAccount(rightId, rightPassword, rightEmail));
	}
	
	/**
	 * Tests a wrong password with special characters only
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSpcecialCharactersOnlyPasswordAdmin() {
		new AdminAccount(rightId, "!@#$%^&");
	}
	
	/**
	 * Tests a wrong email containing prohibited characters
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongCharactersEmailCustomer() {
		new CustomerAccount(rightId, rightPassword, rightId + "@abcd!!.com");
	}
	
	/**
	 * Tests a wrong ID with prohibited characters 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongCharactersIdAdmin() {
		new AdminAccount("a안녕하세요", rightPassword);
	}
	
	/**
	 * Tests a wrong email with the strange domain
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongDomainEmailCustomer() {
		new CustomerAccount(rightId, rightPassword, rightId + "@abcd");
	}
	
	/**
	 * Tests a wrong ID starting with a non-alphabet
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongFirstCharacterIdAdmin() {
		new AdminAccount("1abcde", rightPassword);
	}
	
	/**
	 * Tests a wrong ID with 4 characters
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLengthIdAdmin() {
		new AdminAccount("abcd", rightPassword);
	}
	
	/**
	 * Tests a wrong password with 7 characters
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongLengthPasswordAdmin() {
		new AdminAccount(rightId, "abcd12!");
	}
	
}
