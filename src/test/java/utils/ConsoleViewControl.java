package utils;

import java.io.PrintStream;

public class ConsoleViewControl {

	static PrintStream originalOut = System.out;
	static PrintStream originalErr = System.err;

	static PrintStream dummyStream = new PrintStream(new java.io.OutputStream() {
		public void write(int b) {
		}
	});

	public static void enablePrintingInConsole() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	public static void disablePrintingInConsole() {

		System.setOut(dummyStream);
		System.setErr(dummyStream);
	}
}
