package timo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class LogHandler {
	/**
	 * Handles both writing and reading user action logs.
	 * Saves and restores the Packages in Storage.
	 * */
	private static final String filepath = "./postActionLog.txt";
	
	public static void writeLog(ObservableList<String> observableList) {
		// Writes log as text to file
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
	
	
	public static void writePackages(ArrayList<Package> packageList) {
		// Writes Package objects from Storage to file 
		try{
	         FileOutputStream fos= new FileOutputStream("PackageListFile");
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(packageList);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	            ioe.printStackTrace();
	       }
	}
	
	@SuppressWarnings("unchecked")
	public static void readPackages(){
		// Reads Package objects from file to Storage 
		 try
	        {
	            FileInputStream fis = new FileInputStream("PackageListFile");
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            Storage.getInstance().getPackages().addAll((ArrayList<Package>) ois.readObject());
	            ois.close();
	            fis.close();
	         }catch(IOException ioe){
	             ioe.printStackTrace();
	         }catch(ClassNotFoundException c){
	             System.out.println("Class not found");
	             c.printStackTrace();
	         }
	}
	
	public static void writeLogStrings(ObservableList<String> observableList) {
		// Writes individual log tab cell contents as String for ease of recovery
		try{
			ArrayList<String> tmpArrList = new ArrayList<String>();
			for (String s : observableList) {
				tmpArrList.add(s);
			}
	         FileOutputStream fos= new FileOutputStream("LogListFile");
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(tmpArrList);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	            ioe.printStackTrace();
	       }catch(NullPointerException e) {
			e.printStackTrace();
	       }
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> readLogStrings(){
		ArrayList<String> tmpArrList = new ArrayList<String>();
		 try{
            FileInputStream fis = new FileInputStream("LogListFile");
            ObjectInputStream ois = new ObjectInputStream(fis);	 
            tmpArrList = (ArrayList<String>) ois.readObject();
            ois.close();
            fis.close();
	        }catch(IOException ioe){
	             ioe.printStackTrace();
	        }catch(ClassNotFoundException c){
	             System.out.println("Class not found");
	             c.printStackTrace();
	        }
		 return tmpArrList;
	}
	
	
	
	
	
	
	
	
	
}
