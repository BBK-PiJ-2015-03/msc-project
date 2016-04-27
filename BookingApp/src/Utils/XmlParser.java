package Utils;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class XmlParser {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        XmlParser x = new XmlParser();
        Scanner s = new Scanner(System.in);
        String t = s.next();
        System.out.println(x.addressFromPostCode(t));
    }
    public String addressFromPostCode (String postcode) throws IOException, ParserConfigurationException, SAXException, TransformerException {
        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/xml?address="+postcode+"&key=AIzaSyB2kViW5AL4Q1vGaik53tkw6Ve1ZXdT2vg");
        URLConnection conn = url.openConnection();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(conn.getInputStream());
        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer xform = tfactory.newTransformer();
        NodeList movieList = doc.getElementsByTagName("formatted_address");
        try{
            return movieList.item(0).getFirstChild().getTextContent();
        }catch (NullPointerException e){
            return postcode.toUpperCase();
        }
    }
}