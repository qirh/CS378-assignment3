package assign.resources;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import assign.domain.Error;
import assign.domain.Project;
import assign.domain.Projects;
import assign.domain.Meeting;
import assign.domain.Meetings;
import assign.services.EavesdropService;

@Path("/myeavesdrop")
public class UTCoursesResource {
	
	//	http://localhost:8080/assignment3/myeavesdrop/projects/
	//URL's don't terminate with a '/'
	private final String URL = "http://eavesdrop.openstack.org/meetings";
	Projects projects = new Projects();
	Meetings meetings = new Meetings();
	private EavesdropService ser = null;

	public UTCoursesResource() {
		startService();
	}
	
	/* EAVESDROP	*/
	void setEavesdropService(EavesdropService ser){
		this.ser = ser;
	}
	String getURL(){
		return URL;
	}
	EavesdropService startService(){
		ser =  EavesdropService.getEavesdrop();
		return ser;
	}
	@GET
	@Path("/eavesdrophelloworld")
	@Produces("text/html")
	public String helloEaves() {
		return ser.getHello();		
	}
	@GET
	@Path("/helloworld")
	@Produces("text/html")
	public String helloWorld() {
		return "Hello world";		
	}
	@GET
	@Path("/projects")
	@Produces("application/xml")
	public StreamingOutput getAllProjects() throws IOException {
		Document doc = ser.getDoc(URL+"/");
		final Elements elems = ser.getElements(doc);
		System.out.println("-- and elems:\n " + elems.text());
		 
		return new StreamingOutput() {
			public void write(OutputStream outputStream) throws IOException, WebApplicationException {
				for(Element e : elems){
	        	Project project = new Project();
		        System.out.println("element: '" + e.text() + "'");
		        project.setName(e.text());
		        project.addLink(URL + "/" + e.text());
		        System.out.println(project.equals(null));
		      	projects.addProject(project);
		        }
				outputProject(outputStream, projects);
		        projects.clearProjects();
	         }
	     };
	}	
	@GET
	@Path("/projects/{meeting}/meetings")
	@Produces("application/xml")
	public StreamingOutput getMeetings(@PathParam("meeting") final String x)  {
		try{
			Document doc = ser.getDoc(URL+"/"+x);
			final Elements elems = ser.getElements(doc);
			 
			return new StreamingOutput() {
		         public void write(OutputStream outputStream) throws IOException, WebApplicationException {
		        	 for(Element e : elems){
		        		Meeting meeting = new Meeting();
		        		meeting.setYear(e.text());
		        		meeting.addLink(URL + "/" + x + "/" + e.text());
		        		meetings.addMeeting(meeting);
		        	 }
		        	 if(meetings.isEmpty())
		        		 outputError(outputStream, new Error(x));
		        	 else
		        		 outputMeeting(outputStream, meetings);
		        	 meetings.clearMeetings();
		         }
		     };
		}
		catch (IOException|WebApplicationException i){
			return new StreamingOutput() {
		         public void write(OutputStream outputStream) throws IOException, WebApplicationException {
		        	outputError(outputStream, new Error(x));
		         }
		     };
		}
	}
	protected void outputError(OutputStream os, Error error) throws IOException {
		
		try { 
			JAXBContext jaxbContext = JAXBContext.newInstance(Error.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(error, os);
		} 
		catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}
	protected void outputMeeting(OutputStream os, Meetings meetings) throws IOException {
		
		try { 
			JAXBContext jaxbContext = JAXBContext.newInstance(Meetings.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(meetings, os);
		} 
		catch (JAXBException jaxb) {
			jaxb.printStackTrace();
			throw new WebApplicationException();
		}
	}
	protected void outputProject(OutputStream os, Projects projects) throws IOException {
		System.out.println("-- UTCoursesResource outputProject()");
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
}