package src.xmltest;


import org.xml.sax.SAXException;
import src.xmltest.parsers.JAXB;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, IOException, TransformerException, SAXException, JAXBException {
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
//        StreamResult result = new StreamResult(new File("file.xml"));
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
            //        DOM dom = new DOM();
//        dom.updateParameter(1,"firstName","Lirik");
//        dom.addParameter(2,"age","15");
//        dom.deleteParameter(3,"firstName");
//        dom.updateXml();
        } else if (input.equals("2")) {
            //        SAXParserFactory factory = SAXParserFactory.newInstance();
//        SAXParser parser = factory.newSAXParser();
//        InputStream xmlData = new FileInputStream("file.xml");
//        SAX sax =new SAX();
//        sax.updateParameter(1,"id","1");
//        parser.parse(xmlData,sax);
        } else if (input.equals("3")) {
            JAXB jaxb = new JAXB();
            jaxb.updateParameter(1, "firstName", null);
            jaxb.updateXml();
        }


    }
}
