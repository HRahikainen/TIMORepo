package timo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Xml2DataBuilder {
	
	private static String readURLToString(URL url) throws Exception{
    	BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine, total="";
        
        while((inputLine = in.readLine()) != null) {
            total += inputLine+"\n";
        }
        in.close();
        return total;
    }
}
