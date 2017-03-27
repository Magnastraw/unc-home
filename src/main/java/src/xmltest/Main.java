package src.xmltest;


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import src.xmltest.parsers.DOM;
import src.xmltest.parsers.JAXB;
import src.xmltest.parsers.SAX;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXSource;
import java.io.*;

public class Main {
    private static Parser myParser;
    private static String input;

    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException, JAXBException, XMLStreamException {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document document = builder.newDocument();
//        Element students = document.createElement("students");
//        Element student = document.createElement("student");
//        Element id = document.createElement("id");
//        Element firstName = document.createElement("firstName");
//        Element lastName = document.createElement("lastName");
//        Element birthDate = document.createElement("birthDate");
//
//        document.appendChild(students);
//        students.appendChild(student);
//        student.appendChild(id);
//        student.appendChild(firstName);
//        student.appendChild(lastName);
//        student.appendChild(birthDate);
//        id.appendChild(document.createTextNode("1"));
//        firstName.appendChild(document.createTextNode("Salim"));
//        lastName.appendChild(document.createTextNode("Ivanov"));
//        birthDate.appendChild(document.createTextNode("13.12.1992"));
//
//        TransformerFactory transformerFactory = TransformerFactory.newInstance();
//        Transformer transformer = transformerFactory.newTransformer();
//        DOMSource source = new DOMSource(document);
//        StreamResult result = new StreamResult(new File("dom.xml"));
//
//        // Output to console for testing
//        //StreamResult result = new StreamResult(System.out);
//
//        transformer.transform(source, result);
//
//        System.out.println("File saved!");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.println("Input 1-DOM, 2-SAX, 3-JAXB:");
            input = bufferedReader.readLine();
            switch (input) {
                case "1":
                    myParser = new DOM();
                    break;
                case "2":
                    myParser = new SAX();
                    break;
                case "3":
                    myParser = new JAXB();
                    System.out.println("Only update available.");
                    break;
                default:
                    System.out.println("Input error");
                    break;
            }
            System.out.println("Input 1-update, 2-add, 3-delete:");
            input = bufferedReader.readLine();
            System.out.println("Input element position(1,2,3 etc.), tag name, tag text");
            int pos = Integer.valueOf(bufferedReader.readLine());
            String tagName = bufferedReader.readLine();
            String tagText;
            switch (input) {
                case "1":
                    tagText = bufferedReader.readLine();
                    myParser.updateParameter(pos, tagName, tagText);
                    break;
                case "2":
                    tagText = bufferedReader.readLine();
                    myParser.addParameter(pos, tagName, tagText);
                    break;
                case "3":
                    myParser.deleteParameter(pos, tagName);
                    break;
                default:
                    System.out.println("Input error");
                    break;
            }
            if (myParser instanceof SAX) {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = factory.newSAXParser();
                InputStream xmlData = new FileInputStream("sax.xml");
                parser.parse(xmlData, (DefaultHandler) myParser);
            }
            myParser.updateXml();
            System.out.println("Type 'exit' to exit.");
            input = bufferedReader.readLine();
        }while (!input.equals("exit"));

    }
}
