package Utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class XmlParser {

    /**
     * Address lookup function - returns a full address from a given postcode or partial address
     * Uses Google Maps Geocoding API
     * @param postcode
     * @return full address
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws TransformerException
     */
    public String addressFromPostCode (String postcode) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/xml?address="+postcode+"&key=AIzaSyB2kViW5AL4Q1vGaik53tkw6Ve1ZXdT2vg");
        Document doc = generateDoc(url);
        NodeList addressList = doc.getElementsByTagName("formatted_address");
        try{
            return addressList.item(0).getFirstChild().getTextContent();
        }catch (NullPointerException e){
            return postcode.toUpperCase();
        }
    }

    /**
     * Returns duration for a proposed journey
     * @param origin of pickup
     * @param destination (the drop off location)
     * @return the duration if calculated
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws TransformerException
     */
    public String getJourneyDuration (String origin, String destination) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        origin = origin.replaceAll("\\s", "+");
        destination = destination.replaceAll("\\s", "+");
        URL url = new URL("https://maps.googleapis.com/maps/api/directions/xml?origin="+origin+"&destination="+destination+"&key=AIzaSyB2kViW5AL4Q1vGaik53tkw6Ve1ZXdT2vg");
        Document doc = generateDoc(url);
        NodeList journeyList = doc.getElementsByTagName("text");
        try{
            return journeyList.item(journeyList.getLength()-2).getFirstChild().getTextContent();
        }catch (NullPointerException e){
            return "Not Calculated";
        }catch (ArrayIndexOutOfBoundsException e){
            return "Not Calculated";
        }
    }

    /**
     * Builds Document from URL
     * @param url
     * @return document
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    private Document generateDoc(URL url) throws IOException, ParserConfigurationException, SAXException {
        URLConnection conn = url.openConnection();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return  builder.parse(conn.getInputStream());
    }

}