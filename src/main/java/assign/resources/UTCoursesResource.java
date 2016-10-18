package assign.resources;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import assign.domain.Project;
import assign.domain.Projects;
import assign.services.EavesdropService;

@Path("/myeavesdrop")
public class UTCoursesResource {
	
	EavesdropService eavesdropService;
	final String URL = "http://localhost:8080/assignment3/myeavesdrop/projects/";
	final String ext = "meetings";
	private Project project = new Project();
	private Projects projects = new Projects();
	private EavesdropService ser = null;

	public UTCoursesResource() {
		System.out.println("-- UTCoursesResource constructor");
		startService();
	}
	
	/* EAVESDROP	*/
	void setEavesdropService(EavesdropService ser){
		this.ser = ser;
	}
	EavesdropService startService(){
		ser =  EavesdropService.getEavesdrop();
		return ser;
	}
	@GET
	@Path("/eavesdrophelloworld")
	@Produces("text/html")
	public String helloEaves() {
		System.out.println("-- UTCoursesResource helloEaves");
		return ser.getHello();		
	}
	
	@GET
	@Path("/helloworld")
	@Produces("text/html")
	public String helloWorld() {
		System.out.println("-- UTCoursesResource helloWorld");
		return "Hello world";		
	}
	@GET
	@Path("/projects")
	@Produces("application/xml")
	public String getAllProjects() throws IOException {
		Document doc = ser.getDoc(URL);
		Elements elems = ser.getProjects(doc);
		elems.addAll(ser.getProjects(doc));
		return ser.getData(elems);
		/*
		final Projects projects = new Projects();
		projects.setProjects(new HashSet<String>());
		projects.getProjects().add("%23heat");
		projects.getProjects().add("%23dox");		
			    
	    return new StreamingOutput() {
	         public void write(OutputStream outputStream) throws IOException, WebApplicationException {
	            outputCourses(outputStream, projects);
	         }
	      };
	    */	    
	}	
	public StreamingOutput getProject() throws Exception {
		final Project heat = new Project();
		heat.setName("%23heat");
		heat.setLink(new ArrayList<String>());
		heat.getLink().add("l1");
		heat.getLink().add("l2");		
			    
	    return new StreamingOutput() {
	         public void write(OutputStream outputStream) throws IOException, WebApplicationException {
	            outputCourses(outputStream, heat);
	         }
	      };	    
	}
	protected void outputCourses(OutputStream os, Projects projects) throws IOException {
		try { 
			JAXBContext jaxbContext = JAXBContext.newInstance(Projects.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(projects, os);
		} catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}	
	
	protected void outputCourses(OutputStream os, Project project) throws IOException {
		try { 
			JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(project, os);
		} catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}
}