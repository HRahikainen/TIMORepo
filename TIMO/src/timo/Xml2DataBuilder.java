package timo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
public class Xml2DataBuilder {
	/**
	 * Static class for parsing SmartPost objects from XML
	 */
	private static Document doc;
	
	private static ArrayList<SmartPost> getPostData() {
		// Assemble SmartPosts from document tree
		ArrayList<SmartPost> spList = new ArrayList<SmartPost>();
        NodeList nodes = doc.getElementsByTagName("place");
        for(int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            Element e = (Element) node;
            SmartPost sp = new SmartPost(getTagText("code", e), 
            		getTagText("city", e), getTagText("address", e), 
            		getTagText("availability", e), getTagText("postoffice", e), 
            		getTagText("lat", e), getTagText("lng", e));
            spList.add(sp);
        }
        return spList;
    }
	
	public static ArrayList<SmartPost> parsePostData() {
		// Fetch, prepare and parse data for end use
		ArrayList<SmartPost> spList = new ArrayList<SmartPost>();
		try {
			String dataString = fetchData();
			docPreparator(dataString);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        spList = getPostData();
        return spList;
	}
	
	private static String fetchData() throws Exception {
        URL url = new URL("http://smartpost.ee/fi_apt.xml");
        return readURLToString(url); 
    }
	
	private static String readURLToString(URL url) throws Exception{
    	BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine, total="";
        
        while((inputLine = in.readLine()) != null) {
            total += inputLine+"\n";
        }
        in.close();
        return total;
    }
	
	private static String getTagText(String tag, Element e) {
        return e.getElementsByTagName(tag).item(0).getTextContent();
    }
	
	private static void docPreparator(String dataString) {
    	try {
    		
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(new InputSource(new StringReader(dataString)));
            doc.getDocumentElement().normalize();
            
        } catch (ParserConfigurationException e) {
            System.err.println("Caught ParserConfigurationException!");
        } catch (IOException e) {
            System.err.println("Caught IOException!");
        } catch (SAXException e) {
            System.err.println("Caught SAXException!");
        }
    }
}
