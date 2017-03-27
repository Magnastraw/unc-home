package src.xmltest.parsers;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;

public class SAX extends DefaultHandler {
    private String currentElement;
    private String paramName;
    private String content;
    private boolean add = false;
    private boolean update = false;
    private boolean delete = false;
    private boolean deleteElement = false;
    private int elementPos = -1;
    private int pos;
    private XMLStreamWriter out;

    public SAX() throws FileNotFoundException, UnsupportedEncodingException, XMLStreamException {
        OutputStream outputStream = new FileOutputStream(new File("sax_new.xml"));
        out = XMLOutputFactory.newInstance().createXMLStreamWriter(
                new OutputStreamWriter(outputStream, "utf-8"));
        out.writeStartDocument();
    }

    public void updateParameter(int pos, String paramName, String content) {
        this.update = false;
        this.paramName = paramName;
        this.content = content;
        this.pos = pos;
    }

    public void addParameter(int pos, String paramName, String content) {
        this.add = true;
        this.paramName = paramName;
        this.content = content;
        this.pos = pos;
    }

    public void deleteParameter(int pos, String paramName) {
        this.delete = true;
        this.pos = pos;
        this.paramName = paramName;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse");
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;
        if (currentElement.equals("student")) {
            elementPos++;
        }
        if (delete) {
            if (elementPos == pos) {
                if (qName.equals(paramName)) {
                    deleteElement = true;
                    System.out.print("123");
                }
            }

        }

        if (!deleteElement) {
            try {
                out.writeStartElement(qName);
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
            super.startElement(uri, localName, qName, attributes);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (add) {
            if (elementPos == pos) {
                if (qName.equals("student")) {
                    try {
                        out.writeStartElement(paramName);
                        out.writeCharacters(String.valueOf(content.toCharArray()));
                        out.writeEndElement();

                    } catch (XMLStreamException e) {
                        e.printStackTrace();
                    }
                }
            }
            add = false;
        }
        if (!deleteElement) {
            try {
                out.writeEndElement();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
            currentElement = "";
            super.endElement(uri, localName, qName);
        } else {
            deleteElement=false;
        }

    }

    @Override
    public void endDocument() throws SAXException {
        try {
            out.writeEndDocument();
            out.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        super.endDocument();

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (!currentElement.equals("students") || !currentElement.equals("student") || !currentElement.equals("")) {
            if (update) {
                if (elementPos == pos) {
                    if (currentElement.equals(paramName)) {
                        ch = content.toCharArray();
                        start = 0;
                        length = ch.length;
                    }
                }
                update = false;
            }
            if(!deleteElement){
                try {
                    out.writeCharacters(String.valueOf(ch, start, length));
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
                super.characters(ch, start, length);
            }
        }


    }
}
