package com.jungbo.servlet.centric.config;
import java.util.HashMap;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
public class ReadXML extends DefaultHandler{
        private String match = "";
        private HashMap<String,String> resourceMap;
		private String key;
        private String value;
        public ReadXML(){
                resourceMap = new HashMap<String,String>();
        }
        public HashMap<String,String> setResourceMap(InputStream is) 
        throws IOException, SAXException, ParserConfigurationException{
                InputSource xmlIn = new InputSource(is);
                SAXParserFactory factory = SAXParserFactory.newInstance();
                factory.newSAXParser().parse(xmlIn, this);
                return resourceMap;
        }
        public void startElement(String uri, String localName,
         String qName, Attributes attributes) throws SAXException{
                match += "/" + qName;
        }
        public void characters(char[] ch, int start, int length){
                String chString = new String(ch, start, length);
                if (match.trim().equals("/resources/resource/key")) {
                        key = chString;
                } else if (match.trim().equals("/resources/resource/value")) {
                        value = chString;
                }
        }
        public void endElement(String uri, String localName, String qName) throws SAXException{
                if (match.trim().equals("/resources/resource") ){
                        resourceMap.put(key, value);
                }
                int slash = match.lastIndexOf('/');
                match = (slash > 0) ?  match.substring(0, slash) : "";
        }
}