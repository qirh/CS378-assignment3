package assign.domain;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "project")
@XmlAccessorType(XmlAccessType.FIELD)
public class Project {
	
	@XmlElement
	private String project;
	
	@XmlElement(name = "link")
	private Set<String> links;
	
	public Project() {
		links = new HashSet<String>();
	}
	public Project(String project) {
		this.project = project;
	}
	public String getName() {
		return project;
	}
	public void setName(String name) {
		this.project = name;
	}
	public Set<String> getLink() {
        return links;
    }
    public void addLink(String link) {
        this.links.add(link);
    }
    public String toString(){
    	return project;
    }
}