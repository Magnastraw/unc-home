package src.xmltest.parsers;


import src.xmltest.content.Student;
import src.xmltest.content.Students;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXB {
    private Students students;
    public JAXB() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Students.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        students = (Students) unmarshaller.unmarshal( new File("jaxb.xml") );

        for(Student student : students.getStudents()) {
            System.out.println(student);
        }
    }

    public void updateParameter(int pos, String paramName, String content){
        if(paramName.equals("id")){
            students.getStudents().get(pos).setId(Integer.valueOf(content));
        } else if (paramName.equals("firstName")){
            students.getStudents().get(pos).setFirstName(content);
        } else if (paramName.equals("lastName")) {
            students.getStudents().get(pos).setLastName(content);
        } else if (paramName.equals("birthDate")) {
            students.getStudents().get(pos).setBirthDate(content);
        } else if (paramName.equals("course")){
            students.getStudents().get(pos).setCourse(content);
        }
    }

    public void updateXml() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Students.class);
        Marshaller marshaller= context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(students, new File("jaxb.xml"));
    }
}
