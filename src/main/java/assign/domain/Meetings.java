package assign.domain;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

@XmlRootElement(name = "meetings")
@XmlAccessorType(XmlAccessType.FIELD)
public class Meetings {
	
	@XmlElement(name= "meeting")
    private List<Meeting> meetings;
    
    public Meetings(){
    	meetings = new ArrayList<Meeting>();
    }
    public List<Meeting> getMeetings() {
    	return meetings;
    }
    public void addMeeting(Meeting meeting) {
    	if( ! meetings.contains(meeting) )
    		meetings.add(meeting);
    }
    public boolean isEmpty() {
    	return meetings.isEmpty();
    }
    public void clearMeetings() {
    	meetings.clear();
    }
}