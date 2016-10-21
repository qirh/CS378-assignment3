package assign.resources;

import assign.services.EavesdropService;
import assign.domain.Error;
import assign.domain.Meeting;
import assign.domain.Meetings;

import static org.junit.Assert.assertNull;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.StreamingOutput;

import org.junit.Before;
import org.junit.Test;

public class TestUTCoursesResource {
	
	
	EavesdropService mockService;
	Error error;
	Meeting meeting;
	Meetings meetings;
	UTCoursesResource mockResource = null;
	
	final String URL = "http://localhost:8080/assignment3/myeavesdrop/projects/";
	final String URLworking = "http://localhost:8080/assignment3/myeavesdrop/projects/non-existent-project/meetings";
	final String URLbroken = "http://localhost:8080/assignment3/myeavesdrop/projects/solum_team_meeting/meetings";
	final String[] URLs = {"http://localhost:8080/assignment3/myeavesdrop/projects/",
						   "http://localhost:8080/assignment3/myeavesdrop/projects/solum_team_meeting/meetings",
						   "http://localhost:8080/assignment3/myeavesdrop/projects/3rd_party_ci/meetings",
						   "http://localhost:8080/assignment3/myeavesdrop/projects/non-existent-project/meetings"
						  };
	@Before
	public void setUp() {	
		mockService = EavesdropService.getEavesdrop();
		error = new Error();
		meeting = new Meeting();
		meetings = new Meetings();
		mockResource = new UTCoursesResource();
	}
	
	//Error
	@Test
	public void testErrorGetMessage () throws Exception {
		String output = error.getMessage();
		assertEquals ("Project xxx does not exist", output.toString());
	}
	@Test
	public void testErrorSetProject () throws Exception {
		String output = error.getMessage();
		assertEquals ("Project xxx does not exist", output.toString());
		error.setProject("non-existent-project");
		output = error.getMessage();
		assertEquals ("Project non-existent-project does not exist", output.toString());
	}
	@Test
	public void testErrorSetMessage () throws Exception {
		error.setMessage("test message");
		String output = error.getMessage();
		assertEquals ("test message", output.toString());
	}
	
	//Meeting
	@Test
	public void testMeetingGetYear () throws Exception {
		
		assertNull (meeting.getYear());
		
		meeting.setYear("2016");
		
		assertEquals ("2016", meeting.getYear());
	}
	public void testMeetingSetYear () throws Exception {
		
		meeting.setYear("adsad");
		assertEquals ("-1", meeting.getYear());
		
		meeting.setYear("2014");
		assertEquals ("2014", meeting.getYear());
	}
	
	//Meetings
	@Test
	public void testMeetingsGetMeetings () throws Exception {
			
		assertNotNull (meetings.getMeetings());
			
		meetings.addMeeting(meeting);
		assertEquals (meeting, meetings.getMeetings().get(0));
		meetings.clearMeetings();
	}
	@Test
	public void testMeetingsAddMeetings () throws Exception {
			
		meetings.addMeeting(meeting);
		assertEquals (meeting, meetings.getMeetings().get(0));
	}
	
	//EavesdropService
	@Test
	public void testEavesdropServiceGetHello() {
		String reply = mockService.getHello();
		assertEquals("Hello from Eavesdrop service.", reply);
	}
	
	//UTCoursesResource
	@Test
	public void testUTCoursesResourceGetURL () throws Exception {
		String output = mockResource.getURL();
		assertEquals ("http://eavesdrop.openstack.org/meetings", output.toString());
	}
	@Test
	public void testUTCoursesResourceHelloEaves() {
		String reply = mockResource.helloEaves();
		assertEquals("Hello from Eavesdrop service.", reply);
	}
	@Test
	public void testUTCoursesResourceHelloWorld() {
		String reply = mockResource.helloWorld();
		assertEquals("Hello world", reply);
	}
	@Test
	public void testUTCoursesResourceGetAllProjects () throws Exception {
		StreamingOutput output = mockResource.getAllProjects();
		assertNotNull (output.toString());
	}
}
