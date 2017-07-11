package com.cooksys.ftd.assignments.file;

import com.cooksys.ftd.assignments.file.model.Contact;
import com.cooksys.ftd.assignments.file.model.Instructor;
import com.cooksys.ftd.assignments.file.model.Session;
import com.cooksys.ftd.assignments.file.model.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * Creates a {@link Student} object using the given studentContactFile.
     * The studentContactFile should be an XML file containing the marshaled form of a
     * {@link Contact} object.
     *
     * @param studentContactFile the XML file to use
     * @param jaxb the JAXB context to use
     * @return a {@link Student} object built using the {@link Contact} data in the given file
     */
    public static Student readStudent(File studentContactFile, JAXBContext jaxb) {
    	Student student = new Student();
    	try {
			Unmarshaller Stanley = jaxb.createUnmarshaller();
			Contact contact = (Contact) Stanley.unmarshal(studentContactFile);
			//System.out.println(contact);
			student.setContact(contact);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return student;
    }

    /**
     * Creates a list of {@link Student} objects using the given directory of student contact files.
     *
     * @param studentDirectory the directory of student contact files to use
     * @param jaxb the JAXB context to use
     * @return a list of {@link Student} objects built using the contact files in the given directory
     */
    public static List<Student> readStudents(File studentDirectory, JAXBContext jaxb) {
    	List<Student> studentList = new ArrayList<Student>();
    	for (File file: studentDirectory.listFiles()) {
    		studentList.add(readStudent(file,jaxb));
    	}
        return studentList; // TODO
    }

    /**
     * Creates an {@link Instructor} object using the given instructorContactFile.
     * The instructorContactFile should be an XML file containing the marshaled form of a
     * {@link Contact} object.
     *
     * @param instructorContactFile the XML file to use
     * @param jaxb the JAXB context to use
     * @return an {@link Instructor} object built using the {@link Contact} data in the given file
     */
    public static Instructor readInstructor(File instructorContactFile, JAXBContext jaxb) {
    	Instructor instructor = new Instructor();
    	try {
			Unmarshaller Stanley = jaxb.createUnmarshaller();
			Contact contact = (Contact) Stanley.unmarshal(instructorContactFile);
			instructor.setContact(contact);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return instructor;
    }

    /**
     * Creates a {@link Session} object using the given rootDirectory. A {@link Session}
     * root directory is named after the location of the {@link Session}, and contains a directory named
     * after the start date of the {@link Session}. The start date directory in turn contains a directory named
     * `students`, which contains contact files for the students in the session. The start date directory
     * also contains an instructor contact file named `instructor.xml`.
     *
     * @param rootDirectory the root directory of the session data, named after the session location
     * @param jaxb the JAXB context to use
     * @return a {@link Session} object built from the data in the given directory
     */
    public static Session readSession(File rootDirectory, JAXBContext jaxb) {
    	File[] files = rootDirectory.listFiles();
    	File[] files2 = files[0].listFiles();
    	File[] files3 = files2[0].listFiles();
    	String location = files[0].getName();
    	String Date = files2[0].getName();
    	File padawons = files3[0];
    	File instruct = files3[1];
    	for (File file : files3) {
    		if (file.isDirectory()) {
    			padawons = file;
    		} else {
    			instruct = file;
    		}
    	}
    	List<Student> studentList = readStudents(padawons, jaxb);
    	Instructor instructor = readInstructor(instruct, jaxb);
    	Session session = new Session();
    	session.setLocation(location);
    	session.setStartDate(Date);
    	session.setInstructor(instructor);
    	session.setStudents(studentList);
    	return session;
    }

    /**
     * Writes a given session to a given XML file
     *
     * @param session the session to write to the given file
     * @param sessionFile the file to which the session is to be written
     * @param jaxb the JAXB context to use
     */
    public static void writeSession(Session session, File sessionFile, JAXBContext jaxb) {
    	try {
			Marshaller Stan = jaxb.createMarshaller();
			Stan.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			Stan.marshal(session, sessionFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Main Method Execution Steps:
     * 1. Configure JAXB for the classes in the com.cooksys.serialization.assignment.model package
     * 2. Read a session object from the <project-root>/input/memphis/ directory using the methods defined above
     * 3. Write the session object to the <project-root>/output/session.xml file.
     *
     * JAXB Annotations and Configuration:
     * You will have to add JAXB annotations to the classes in the com.cooksys.serialization.assignment.model package
     *
     * Check the XML files in the <project-root>/input/ directory to determine how to configure the {@link Contact}
     *  JAXB annotations
     *
     * The {@link Session} object should marshal to look like the following:
     *      <session location="..." start-date="...">
     *           <instructor>
     *               <contact>...</contact>
     *           </instructor>
     *           <students>
     *               ...
     *               <student>
     *                   <contact>...</contact>
     *               </student>
     *               ...
     *           </students>
     *      </session>
     */
    public static void main(String[] args) {
    	File input = new File("input");
    	File output = new File("output\\session.xml");
    	Session session = null;
    	try {
			JAXBContext jaxbCon = JAXBContext.newInstance( Contact.class);
			JAXBContext jaxbCon2 = JAXBContext.newInstance( Session.class);

			session = readSession(input, jaxbCon);
			writeSession(session, output, jaxbCon2);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
}
