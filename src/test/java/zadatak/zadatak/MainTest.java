package zadatak.zadatak;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import zadatak.zadatak.Main.Position;

public class MainTest {

	@Test
	public void testMain() {
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		final Main tester = new Main();
		final String[] args = new String[1];
		String expectedOutput;

		// map without obstruction
		expectedOutput = "END";
		args[0] = "false";
		tester.main(args);
		assertEquals(expectedOutput, outContent.toString().replaceAll("\\s+", ""));

		// map with obstruction
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		expectedOutput = "START";
		args[0] = "true";
		tester.main(args);
		assertEquals(expectedOutput, outContent.toString().replaceAll("\\s+", ""));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMainException() {
		final Main tester = new Main();
		String[] args = new String[10];

		// test number of arguments
		args[0] = "true";
		args[1] = "false";
		tester.main(args);
		// test invalid argument
		args = null;
		args[0] = "test";
		tester.main(args);
	}

	@Test
	public void testDriveFromStartToEnd() {
		final Main tester = new Main();
		final DrivingModule drivingModule = new DrivingModule();

		// map without obstruction
		drivingModule.defineMap(false);
		assertEquals("Position must be END", Position.END, tester.driveFromStartToEnd(drivingModule));

		// map with obstruction
		drivingModule.defineMap(true);
		assertEquals("Position must be START", Position.START, tester.driveFromStartToEnd(drivingModule));
	}

}
