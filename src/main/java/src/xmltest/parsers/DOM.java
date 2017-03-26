package src.xmltest.parsers;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DOM {
    private Document document;
    private Node root;
    private NodeList studentList;

    public DOM() throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse("file.xml");
        root = document.getFirstChild();
        studentList = document.getElementsByTagName("student");


    }

    public void updateParameter(int pos, String paramName, String content) throws TransformerException {
        Node student = studentList.item(pos);
        NodeList studentParams = student.getChildNodes();
        for (int j = 0; j < studentParams.getLength(); j++) {
            Node node = studentParams.item(j);
            if (node.getNodeName().equals(paramName)) {
                node.setTextContent(content);
            }
        }

    }


    public void deleteParameter(int pos, String paramType) throws TransformerException {
        Node student = studentList.item(pos);
        NodeList studentParams = student.getChildNodes();
        for (int j = 0; j < studentParams.getLength(); j++) {
            Node node = studentParams.item(j);
            if (node.getNodeName().equals(paramType)) {
                student.removeChild(node);
            }
        }


    }

    public void addParameter(int pos, String paramName, String content) throws TransformerException {
        Node student = studentList.item(pos);
        Element additionElement = document.createElement(paramName);
        additionElement.setTextContent(content);
        student.appendChild(additionElement);

    }

    public void updateXml() throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File("file.xml"));
        transformer.transform(domSource, streamResult);
    }
}
