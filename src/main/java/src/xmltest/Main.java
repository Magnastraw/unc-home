package src.xmltest;


import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
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
        System.out.println("Input 1-DOM, 2-SAX, 3-JAXB:");
        String input = bufferedReader.readLine();
        if (input.equals("1")) {
            DOM dom = new DOM();
            System.out.println("Input 1-update, 2-add, 3-delete:");
            input = bufferedReader.readLine();
            System.out.println("Input element position(1,2,3 etc.), tag name, tag text");
            int pos = Integer.valueOf(bufferedReader.readLine());
            String tagName = bufferedReader.readLine();
            String tagText = bufferedReader.readLine();
            switch (input){
                case "1":
                    dom.updateParameter(pos,tagName,tagText);
                    break;
                case "2":
                    dom.addParameter(pos,tagName,tagText);
                    break;
                case "3":
                    dom.deleteParameter(pos,tagName);
                    break;
                default:
                    System.out.println("Input error");
                    break;
            }
            dom.updateXml();
        } else if (input.equals("2")) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            InputStream xmlData = new FileInputStream("sax.xml");
            SAX sax = new SAX();
            sax.deleteParameter(1,"id");
            parser.parse(xmlData, sax);
        } else if (input.equals("3")) {
            JAXB jaxb = new JAXB();
            jaxb.updateParameter(1, "firstName", "Ddd");
            jaxb.updateXml();
        }


    }
}
