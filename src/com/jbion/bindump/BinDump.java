package com.jbion.bindump;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * A simple program to display the bits of a binary file. Useful to debug encoding
 * programs.
 * 
 * @author <a href="mailto:joffrey.bion@gmail.com">Joffrey Bion</a>
 */
public class BinDump {

	private static int nbBytesPrinted = 0;

	/**
	 * Prints the binary content of the specified file.
	 * 
	 * @param args
	 *            The name of the file to dump. Can be followed by the number of
	 *            bytes to display per line.
	 */
	public static void main(String[] args) {
		if (args.length == 1) {
			readAndPrint(args[0], 1);
		} else if (args.length > 1) {
			try {
				readAndPrint(args[0], Integer.valueOf(args[1]));
			} catch (NumberFormatException e) {
				printUsage();
			}
		} else {
			printUsage();
		}
	}

	private static void printUsage() {
		System.out.println("Usage: java " + BinDump.class.getSimpleName()
				+ " filename [nbBytesPerLine]");
	}

	/**
	 * Reads the specified binary file and prints its binary content.
	 * 
	 * @param inputFileName
	 *            The path to the file to dump.
	 * @param bytesPerLine
	 *            The number of bytes to print per line.
	 */
	private static void readAndPrint(String inputFileName, int bytesPerLine) {
		System.out.println("Reading in binary file named : " + inputFileName);
		File file = new File(inputFileName);
		try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
			int totalBytesRead = 0;
			System.out.println("File size: " + file.length());
			byte[] buff = new byte[1];
			while (totalBytesRead < file.length()) {
				// input.read() returns -1, 0, or more :
				int bytesRead = input.read(buff, 0, 1);
				if (bytesRead > 0) {
					totalBytesRead = totalBytesRead + bytesRead;
					print(buff[0], bytesPerLine);
				}
			}
			if (totalBytesRead % bytesPerLine != 0) {
				System.out.println();
			}
			System.out.println("Num bytes read: " + totalBytesRead);
		} catch (FileNotFoundException ex) {
			System.out.println("File '" + inputFileName + "' not found.");
		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Prints the specified byte, and adds a new line if needed.
	 * 
	 * @param b
	 *            The byte to print.
	 * @param bytesPerLine
	 *            The number of bytes to print per line.
	 */
	private static void print(Byte b, int bytesPerLine) {
		int i = b;
		String res = Integer.toBinaryString(i < 0 ? i + 256 : i);
		while (res.length() < 8) {
			res = "0" + res;
		}
		System.out.print(res);
		nbBytesPrinted++;
		if (nbBytesPrinted % bytesPerLine == 0) {
			System.out.println();
		}
	}
}
