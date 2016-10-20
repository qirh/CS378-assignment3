package assign.resources;

import assign.services.EavesdropService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class TestUTCoursesResource {
	EavesdropService mockservice = mock(EavesdropService.class);
	UTCoursesResource mockresource = new UTCoursesResource();
	
	final String URL = "http://localhost:8080/assignment4/myeavesdrop/projects/";
	final String URLworking = "http://localhost:8080/assignment4/myeavesdrop/projects/non-existent-project/meetings";
	final String URLbroken = "http://localhost:8080/assignment4/myeavesdrop/projects/solum_team_meeting/meetings";
	

}
