package timo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LogHandler {
	public String readLog(String filepath) {
		String output = "";
		
		try {
			BufferedReader in; 
			String inputLine;
			
			in = new BufferedReader(new FileReader(filepath));
			while((inputLine = in.readLine()) != null) {
    			output += inputLine + "\n";
			}
			in.close();
			
		} catch (IOException ioe) {
			System.out.println("File not found!");
		}
		return output;
		
	}
	public void writeLog(String outFile) {
		
		try {
				
    			BufferedWriter out;
    			out = new BufferedWriter(new FileWriter(outFile));
    			// Write log tab contents here
    			/*while(() {
    				out.write(inputLine);
            		out.newLine();
    			}*/
    			out.close();
		} catch (IOException ex) {
    			System.out.println("File not found!");
		}
	}
}
