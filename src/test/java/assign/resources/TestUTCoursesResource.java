package assign.resources;

import assign.services.EavesdropService;
import assign.domain.Error;

import static org.junit.Assert.*;  
import static org.mockito.Mockito.*;

import javax.ws.rs.core.StreamingOutput;

import org.junit.Before;
import org.junit.Test;

public class TestUTCoursesResource {
	
	
	EavesdropService mockService;
	Error error;
	UTCoursesResource mockResource = null;
	
	final String URL = "http://localhost:8080/assignment3/myeavesdrop/projects/";
	final String URLworking = "http://localhost:8080/assignment3/myeavesdrop/projects/non-existent-project/meetings";
	final String URLbroken = "http://localhost:8080/assignment3/myeavesdrop/projects/solum_team_meeting/meetings";
	
	@Before
	public void setUp() {	
		mockService = EavesdropService.getEavesdrop();
		error = new Error ();
		mockResource = new UTCoursesResource();
	}
	//Meeting
	
	//EavesdropService
	@Test
	public void testhelloEaves() {
		String reply = mockService.getHello();
		assertEquals("Hello from Eavesdrop service.", reply);
	}
	
	//UTCoursesResource
	@Test
	public void testhelloEaves2() {
		String reply = mockResource.helloEaves();
		assertEquals("Hello from Eavesdrop service.", reply);
	}
	@Test
	public void testHelloWorld() {
		String reply = mockResource.helloWorld();
		assertEquals("Hello world", reply);
	}
	@Test
	public void testError () throws Exception {
		System.out.println("ERRRROR -- " + error.getMessage());
		String output = mockResource.getURL();
		System.out.println("OUTPUT1 }}}\n" + output.toString());
		assertEquals ("http://eavesdrop.openstack.org/meetings", output.toString());
	}
	@Test
	public void testprojects () throws Exception {
		StreamingOutput output = mockResource.getAllProjects();
		assertNotNull (output.toString());
	}


}
