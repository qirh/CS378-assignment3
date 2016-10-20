package assign.domain;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "projects")
@XmlAccessorType(XmlAccessType.FIELD)
public class Projects {
	   
    private List<Project> projects;
    
    public Projects(){
    	projects = new ArrayList<Project>();
    }
    
    public List<Project> getProjects() {
    	return projects;
    }
    public void addProject(Project project) {
    	if( ! projects.contains(project) )
    		projects.add(project);
    }
    public void clearProjects() {
    	projects.clear();
    }
}