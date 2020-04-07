import java.util.Scanner;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class ReadFile {

	public static void main( String [] args ) throws IOException
	{
		Scanner				input = new Scanner(System.in);
		String				fileName = null;
		InputStream 		istream = null;
		int					i = 0;

		
		if(args.length != 1) {
			System.out.println("Please enter a single file name:");
			fileName = input.nextLine();
		} else {
			fileName = args[0];
		}
		try {
			istream = new FileInputStream(fileName);
			while((i = istream.read()) != -1) {
				System.out.print((char)i);
			}
			istream.close();
		} catch (FileNotFoundException ex) {
			System.out.println("file " + fileName + " not found");
		}
	}
	
	// Ref: https://www.tutorialspoint.com/java/io/inputstream_read.htm
	// Ref: https://www.programiz.com/java-programming/basic-input-output
}