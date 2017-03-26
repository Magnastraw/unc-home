package src.xmltest.parsers;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class SAX extends DefaultHandler  {
    private String currentElement;
    private String paramName;
    private String content;
    private int elementPos=-1;
    private int pos;


    public void updateParameter(int pos, String paramName, String content){
        this.paramName = paramName;
        this.content = content;
        this.pos = pos;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse");
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
     //  System.out.println(currentElement);
        currentElement=qName;
        if(currentElement.equals("student")){
            elementPos++;
        }
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      //  System.out.println(currentElement);
        currentElement="";
        super.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(currentElement.equals("student")){
            System.out.println("        "+String.valueOf(ch));

        }
        if(elementPos==pos){
            if(currentElement.equals(paramName)){
                ch = content.toCharArray();
                start=0;
                length =ch.length;
                System.out.println(ch);
            }
        }

        super.characters(ch, start, length);
    }
}
