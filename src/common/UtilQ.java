package common; // Declare the package name

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import org.xml.sax.SAXException;

public class UtilQ extends UtilC { // Define a class named UtilQ that extends UtilC

    // Define a static method
    public static String Q(String id) throws ParserConfigurationException, IOException, SAXException {
        NodeList n; // Declare a NodeList
        Element e = null; // Declare an Element

        try {
            
            n = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new File("src/config/EmployeeQuery.xml"))
                    .getElementsByTagName("query");

            // Iterate through the list of 'query' elements
            for (int x = 0; x < n.getLength(); x++) {
                e = (Element) n.item(x); // Get the current element
                if (e.getAttribute("id").equals(id)) // Check if the 'id' attribute matches the provided 'id'
                    break; // Exit the loop
            }
        } catch (ParserConfigurationException | IOException | SAXException e1) {
            e1.printStackTrace(); 
            throw e1; 
        }

        // Check if a matching element was found
        if (e != null) {
            return e.getTextContent().trim(); 
        } else {
            return ""; 
        }
    }
}
