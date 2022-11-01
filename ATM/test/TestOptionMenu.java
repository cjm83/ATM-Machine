package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import src.Account;
import src.OptionMenu;

class TestOptionMenu {

	OptionMenu optionMenu = new OptionMenu();
	Account acc = new Account(1, 1, 100, 100);
	
	/*
	 * I cannot seem to find a way to test many sections of this file.
	 * They rely on too many dependencies within the file for passing
	 * input that seems to fail without solution. In light of this,
	 * we test what we can.
	 * 
	 */ 
	
	@Test
	void testGetSaving() {
		InputStream sysInBackup = System.in;
		PrintStream sysOutBackup = System.out;
		ByteArrayInputStream in = new ByteArrayInputStream(("a" + System.lineSeparator() +
				"1" + System.lineSeparator() +
				"5").getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setIn(in);
		System.setOut(new PrintStream(out));
		optionMenu.getSaving(acc, System.in, System.out);
		System.setOut(sysOutBackup);
		System.setIn(sysInBackup);
		assertTrue(out.toString().contains("Invalid Choice."));
		assertTrue(out.toString().contains("Savings Account Balance:"));
	}
	
	@Test
	void testGetChecking() {
		InputStream sysInBackup = System.in;
		PrintStream sysOutBackup = System.out;
		ByteArrayInputStream in = new ByteArrayInputStream(("a" + System.lineSeparator() +
				"1" + System.lineSeparator() +
				"5").getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setIn(in);
		System.setOut(new PrintStream(out));
		optionMenu.getChecking(acc, System.in, System.out);
		System.setOut(sysOutBackup);
		System.setIn(sysInBackup);
		assertTrue(out.toString().contains("Invalid Choice."));
		assertTrue(out.toString().contains("Checkings Account Balance:"));
	}
	
	@Test
	void testGetAccountType() {
		InputStream sysInBackup = System.in;
		PrintStream sysOutBackup = System.out;
		ByteArrayInputStream in = new ByteArrayInputStream(("a" + System.lineSeparator() +
				"3").getBytes());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		System.setIn(in);
		System.setOut(new PrintStream(out));
		optionMenu.getAccountType(acc, System.in, System.out);
		System.setOut(sysOutBackup);
		System.setIn(sysInBackup);
		assertTrue(out.toString().contains("Invalid Choice."));
	}
	
}
