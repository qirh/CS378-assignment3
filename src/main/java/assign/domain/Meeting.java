package assign.domain;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "meeting")
@XmlAccessorType(XmlAccessType.FIELD)
public class Meeting {
	
	@XmlElement
	private String year;
	
	@XmlElement(name = "link")
	private Set<String> links;
	
	public Meeting() {
		links = new HashSet<String>();
	}
	public void setYear(String year) {
		if( year.matches("^\\d+$") )
			this.year = year;
		else
			this.year = "-1";
	}
	public String getYear() {
		return year;
	}
	public Set<String> getLink() {
        return links;
    }
    public void addLink(String link) {
        this.links.add(link);
    }
    public String toString(){
    	return year;
    }
}