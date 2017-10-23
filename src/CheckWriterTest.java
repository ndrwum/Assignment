import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CheckWriterTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}
 
	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void testAmt() throws ParseException {
		String amtinWords = "Thirty four dollars only";
		try {
			CheckWriter.Print("34", "n");;
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(amtinWords, outContent.toString());
	}

	@Test
	public void testAmtwithDec() throws ParseException {
		String amtinWords = "Thirty four dollars and 12/100";
		try {
			CheckWriter.Print("34.12", "n");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(amtinWords, outContent.toString());
	}

	@Test
	public void testwithNoNumbersinFrontofDec() throws ParseException {
		String amtinWords = "Zero dollars and 12/100";
		try {
			CheckWriter.Print(".12", "n");
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(amtinWords, outContent.toString());
	}

	@Test
	public void testwrongCentFormat() throws ParseException {
		try {
			CheckWriter.Print(",123", "y");
			fail("Didn't throw");
		} catch (MyException e) {
		}
	}
	@Test
	public void testInputformat() throws Exception {
		try {
			CheckWriter.Print("k.k", "n");
			fail("Didn't throw");
		} catch (NumberFormatException e) {
		}
	}
	@Test
	public void testwSpace() throws ParseException {
		String amtinWords = "Thirty four dollars and 90/100";
		try {
			CheckWriter.Print("3 4.9", "n");;
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(amtinWords, outContent.toString());
	}
	@Test
	public void otherTests() throws ParseException {
		String amtinWords = "Twelve dollars only";
		try {
			CheckWriter.Print(".12", "y");;
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(amtinWords, outContent.toString());
	}
}
