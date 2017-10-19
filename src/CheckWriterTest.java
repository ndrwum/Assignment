import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
	public void testAmt() {
		String amtinWords = "Thirty four dollars only";
		ByteArrayInputStream in = new ByteArrayInputStream("34".getBytes());
		System.setIn(in);
		try {
			CheckWriter.getInput();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(amtinWords, outContent.toString());
	}

	@Test
	public void testAmtwithDec() {
		String amtinWords = "Thirty four dollars and 12/100";
		ByteArrayInputStream in = new ByteArrayInputStream("34.12".getBytes());
		System.setIn(in);
		try {
			CheckWriter.getInput();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(amtinWords, outContent.toString());
	}

	@Test
	public void testwithNoNumbersinFrontofDec() {
		String amtinWords = "Zero dollars and 12/100";
		ByteArrayInputStream in = new ByteArrayInputStream(".12".getBytes());
		System.setIn(in);
		try {
			CheckWriter.getInput();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(amtinWords, outContent.toString());
	}

	@Test
	public void testwrongCentFormat() {
		ByteArrayInputStream in = new ByteArrayInputStream(".123".getBytes());
		System.setIn(in);
		try {
			CheckWriter.getInput();
			fail("Didn't throw");
		} catch (MyException e) {
		}
	}

}
