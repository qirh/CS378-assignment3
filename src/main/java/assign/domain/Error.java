package assign.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "output")
@XmlAccessorType(XmlAccessType.FIELD)
public class Error {
	
	@XmlElement
	private String error;
	
	public Error() {
		this.error = "Project xxx does not exist";
	}
	public Error(String message) {
		this.error = "Project " + message + " does not exist";
	}
	public String getMessage() {
		return error;
	}
	public void setMessage(String message) {
		this.error = message;
	}
	public void setProject(String message) {
		error.replace("xxx", message);
	}
    public String toString(){
    	return error;
    }
}