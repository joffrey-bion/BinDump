import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Bin2txt {

    public static void main(String[] args) {
        if (args.length == 1) {
            readAndPrint(args[0], 1);
        } else if (args.length > 1) {
            readAndPrint(args[0], Integer.valueOf(args[1]));
        }
    }

    private static int redress(Byte b) {
        int i = b;
        return i < 0 ? i + 256 : i;
    }

    /** Read the given binary file, and return its contents as a byte array. */
    static byte[] readAndPrint(String aInputFileName, int bytesPerLine) {
        System.out.println("Reading in binary file named : " + aInputFileName);
        File file = new File(aInputFileName);
        System.out.println("File size: " + file.length());
        byte[] result = new byte[1];
        try {
            InputStream input = null;
            try {
                int totalBytesRead = 0;
                input = new BufferedInputStream(new FileInputStream(file));
                while (totalBytesRead < file.length()) {
                    // input.read() returns -1, 0, or more :
                    int bytesRead = input.read(result, 0, 1);
                    if (bytesRead > 0) {
                        totalBytesRead = totalBytesRead + bytesRead;
                        print(result[0], bytesPerLine);
                    }
                }
                if (totalBytesRead % bytesPerLine != 0) {
                    System.out.println();
                }
                System.out.println("Num bytes read: " + totalBytesRead);
            } finally {
                System.out.println("Closing input stream.");
                if (input != null)
                    input.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return result;
    }
    
    static int nbBytesPrinted = 0;
    static void print(Byte b, int bytesPerLine) {
        String res = Integer.toBinaryString(redress(b));
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
