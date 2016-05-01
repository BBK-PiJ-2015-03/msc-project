/**
 * Testing for XMLParser
 * Tests rely on Google Maps API working.
 */

import Utils.XmlParser;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class XMLParserTest {
    private XmlParser x = new XmlParser();


    @Test
    public void testAddressLookupFromPostcodeInput() throws ParserConfigurationException, TransformerException, SAXException, IOException {
        String expected = "The Vale, London NW11 8SJ, UK";
        String result = x.addressFromPostCode("NW118SJ");
        assertEquals("Address lookup for NW11 8SJ",expected, result);
    }

    @Test
    public void testGetJourneyDuration() throws ParserConfigurationException, TransformerException, SAXException, IOException {
        String expected = "38 mins";
        String result = x.getJourneyDuration("NW118SJ", "E58AU");
        assertEquals("Duration from NW11 8SJ to E5 8AU",expected, result);
    }

}
