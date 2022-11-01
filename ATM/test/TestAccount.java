package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import src.Account;

class TestAccount {

	private Account testAccount1 = new Account(1,1234);
	private Account testAccount2 = new Account(1,1234, 100, 100);
	
	@Test
	void testCalcCheckingWithdraw() {
		testAccount1.calcCheckingWithdraw(100);
		testAccount2.calcCheckingWithdraw(50);
		assertTrue(testAccount1.getCheckingBalance() == -100);
		assertTrue(testAccount2.getCheckingBalance() == 50);
	}
	
	@Test
	void testCalcSavingWithdraw() {
		testAccount1.calcSavingWithdraw(100);
		testAccount2.calcSavingWithdraw(50);
		assertTrue(testAccount1.getSavingBalance() == -100);
		assertTrue(testAccount2.getSavingBalance() == 50);
	}
	
	@Test
	void testCalcSavingDeposit() {
		testAccount1.calcSavingDeposit(100);
		testAccount2.calcSavingDeposit(50);
		assertTrue(testAccount1.getSavingBalance() == 100);
		assertTrue(testAccount2.getSavingBalance() == 150);
	}
	
	@Test
	void testCalcCheckingDeposit() {
		testAccount1.calcCheckingDeposit(100);
		testAccount2.calcCheckingDeposit(50);
		assertTrue(testAccount1.getCheckingBalance() == 100);
		assertTrue(testAccount2.getCheckingBalance() == 150);
	}
	
	@Test
	void testCalcCheckingTransfer() {
		testAccount1.calcCheckTransfer(100);
		assertTrue(testAccount1.getCheckingBalance() == -100);
		assertTrue(testAccount1.getSavingBalance() == 100);
	}
	
	@Test
	void testSavingCheckingTransfer() {
		testAccount1.calcSavingTransfer(100);
		assertTrue(testAccount1.getCheckingBalance() == 100);
		assertTrue(testAccount1.getSavingBalance() == -100);
	}
	
	@Test
	void testGetCheckingWithdrawInput() {
		InputStream sysInBackup = System.in;
		PrintStream sysOutBackup = System.out;
		ByteArrayInputStream in = new ByteArrayInputStream(("a" + System.lineSeparator() +
				"1000" + System.lineSeparator() +
				"100").getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setIn(in);
		System.setOut(new PrintStream(out));
		testAccount2.getCheckingWithdrawInput(System.in, System.out);
		System.setOut(sysOutBackup);
		System.setIn(sysInBackup);
		assertTrue(out.toString().contains("Invalid Choice"));
		assertTrue(out.toString().contains("Balance Cannot be Negative."));
		assertTrue(out.toString().contains("Current Checkings Account Balance: $0.00"));
		assertTrue(testAccount2.getCheckingBalance() == 0);
	}
	
	@Test
	void testGetSavingWithdrawInput() {
		InputStream sysInBackup = System.in;
		PrintStream sysOutBackup = System.out;
		ByteArrayInputStream in = new ByteArrayInputStream(("a" + System.lineSeparator() +
				"1000" + System.lineSeparator() +
				"100").getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setIn(in);
		System.setOut(new PrintStream(out));
		testAccount2.getsavingWithdrawInput(System.in, System.out);
		System.setOut(sysOutBackup);
		System.setIn(sysInBackup);
		assertTrue(out.toString().contains("Invalid Choice."));
		assertTrue(out.toString().contains("Balance Cannot Be Negative."));
		assertTrue(out.toString().contains("Current Savings Account Balance: $0.00"));
		assertTrue(testAccount2.getSavingBalance() == 0);
	}

	@Test
	void testGetCheckingDepositInput() {
		InputStream sysInBackup = System.in;
		PrintStream sysOutBackup = System.out;
		ByteArrayInputStream in = new ByteArrayInputStream(("a" + System.lineSeparator() +
				"-1000" + System.lineSeparator() +
				"100").getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setIn(in);
		System.setOut(new PrintStream(out));
		testAccount2.getCheckingDepositInput(System.in, System.out);
		System.setOut(sysOutBackup);
		System.setIn(sysInBackup);
		assertTrue(out.toString().contains("Invalid Choice."));
		assertTrue(out.toString().contains("Balance Cannot Be Negative."));
		assertTrue(out.toString().contains("Current Checking Account Balance: $200.00"));
		assertTrue(testAccount2.getCheckingBalance() == 200.00);
	}
	
	@Test
	void testGetSavingDepositInput() {
		InputStream sysInBackup = System.in;
		PrintStream sysOutBackup = System.out;
		ByteArrayInputStream in = new ByteArrayInputStream(("a" + System.lineSeparator() +
				"-1000" + System.lineSeparator() +
				"100").getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setIn(in);
		System.setOut(new PrintStream(out));
		testAccount2.getSavingDepositInput(System.in, System.out);
		System.setOut(sysOutBackup);
		System.setIn(sysInBackup);
		assertTrue(out.toString().contains("Invalid Choice."));
		assertTrue(out.toString().contains("Balance Cannot Be Negative."));
		assertTrue(out.toString().contains("Current Savings Account Balance: $200.00"));
		assertTrue(testAccount2.getSavingBalance() == 200.00);
	}
	
	@Test
	void testGetTransferInputChecking() {
		InputStream sysInBackup = System.in;
		PrintStream sysOutBackup = System.out;
		ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() +
				"1000" + System.lineSeparator() +
				"1" + System.lineSeparator() +
				"100").getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setIn(in);
		System.setOut(new PrintStream(out));
		testAccount2.getTransferInput("Checkings", System.in, System.out);
		System.setOut(sysOutBackup);
		System.setIn(sysInBackup);
		assertTrue(out.toString().contains("Balance Cannot Be Negative."));
		assertTrue(out.toString().contains("Current Checkings Account Balance: $0.00"));
		assertTrue(out.toString().contains("Current Savings Account Balance: $200.00"));
		assertTrue(testAccount2.getCheckingBalance() == 0.00);
		assertTrue(testAccount2.getSavingBalance() == 200.00);
		in = new ByteArrayInputStream(("3" + System.lineSeparator()
		+  "2").getBytes());
		out = new ByteArrayOutputStream();
		System.setIn(in);
		System.setOut(new PrintStream(out));
		testAccount2.getTransferInput("Checkings", System.in, System.out);
		assertTrue(out.toString().contains("Invalid Choice."));
		System.setOut(sysOutBackup);
		System.setIn(sysInBackup);
	}
	
	@Test
	void testGetTransferInputSaving() {
		InputStream sysInBackup = System.in;
		PrintStream sysOutBackup = System.out;
		ByteArrayInputStream in = new ByteArrayInputStream(("1" + System.lineSeparator() +
				"1000" + System.lineSeparator() +
				"1" + System.lineSeparator() +
				"100").getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setIn(in);
		System.setOut(new PrintStream(out));
		testAccount2.getTransferInput("Savings", System.in, System.out);
		System.setOut(sysOutBackup);
		System.setIn(sysInBackup);
		assertTrue(out.toString().contains("Current Checkings Account Balance: $200.00"));
		assertTrue(out.toString().contains("Current Savings Account Balance: $0.00"));
		assertTrue(testAccount2.getCheckingBalance() == 200.00);
		assertTrue(testAccount2.getSavingBalance() == 0.00);
		in = new ByteArrayInputStream(("3" + System.lineSeparator()
									+  "2").getBytes());
		out = new ByteArrayOutputStream();
		System.setIn(in);
		System.setOut(new PrintStream(out));
		testAccount2.getTransferInput("Savings", System.in, System.out);
		assertTrue(out.toString().contains("Invalid Choice."));
		System.setOut(sysOutBackup);
		System.setIn(sysInBackup);
	}
	
}
