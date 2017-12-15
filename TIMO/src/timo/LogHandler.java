package timo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LogHandler {
	private static final String filepath = "./postActionLog.txt";
	public static ArrayList<String> readLog() {
		ArrayList<String> logList = new ArrayList<String>();
		try {
			BufferedReader in; 
			String inputLine;
			
			in = new BufferedReader(new FileReader(filepath));
			while((inputLine = in.readLine()) != null) {
    			logList.add(inputLine);
			}
			in.close();
			
		} catch (IOException ioe) {
			System.out.println("File not found! Starting from scratch..");
		}
		return logList;
		
	}
	public static void writeLog(ArrayList<String> logList) {
		
		try {
    			BufferedWriter out;
    			out = new BufferedWriter(new FileWriter(filepath));
    			// Write log tab contents here
    			for(String s : logList) {
    				out.write(s);
    			}
    			out.close();
		} catch (IOException ex) {
    			System.out.println("Error while writing log...");
		}
	}
}
