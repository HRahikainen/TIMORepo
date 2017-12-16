package timo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class LogHandler {
	private static final String filepath = "./postActionLog.txt";
	public static ArrayList<String> readLog() {
		ArrayList<String> logList = new ArrayList<String>();
		try {
			BufferedReader in; 
			String inputLine;
			String outputString = "";
			
			in = new BufferedReader(new FileReader(filepath));
			int i = 0;
			while((inputLine = in.readLine()) != null) {
				if(i < 5) {
					outputString += inputLine + "\n";
				}
				if(i++ == 5){
					logList.add(outputString);
					i = 0;
					outputString = "";
				}
			}
			logList.add(outputString);
			in.close();
			
		} catch (IOException ioe) {
			System.out.println("File not found! Starting from scratch..");
		}
		return logList;
		
	}
	public static void writeLog(ObservableList<String> observableList) {
		
		try {
    			BufferedWriter out;
    			out = new BufferedWriter(new FileWriter(filepath));
    			// Write log tab contents here
    			for(String s : observableList) {
    				out.write(s + "\n");
    			}
    			out.close();
		} catch (IOException ex) {
    			System.out.println("Error while writing log...");
		}
	}
}
