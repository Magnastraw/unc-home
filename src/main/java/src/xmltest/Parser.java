package src.xmltest;


import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;

public interface Parser {
    void addParameter(int pos,String paramName,String content) throws TransformerException;
    void updateParameter(int pos,String paramName,String content) throws TransformerException;
    void deleteParameter(int pos,String paramName) throws TransformerException;
    void updateXml() throws JAXBException, TransformerException;

}
