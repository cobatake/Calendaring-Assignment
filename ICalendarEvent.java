/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;

/**
 *
 * @author chanelsantiago
 */
public class ICalendarEvent {
	private String version;
	private String classification;
	private String summary;
	private String location;
	private String DTStart;
	private String DTEnd;
	private String timeZone;
	private int priority;
	
	/**
	 * Constructor for calendar
	 */
    public ICalendarEvent() {
        version = "2.0";
        priority = 9;
    }
    
    /**
	 * Get version
	 * @return - version of the calendar
	 */
    public String getVersion() {
        return version;
    }
    
	/**
	 * Set classification of calendar event
	 * @param c - private or confidential
	 */
    public void setClassification(String c) {
        if (c.equals("private") || c.equals("confidential")) {
            classification = c;
        }
        else {
            classification = "public";
        }
    }
    
    /**
	 * Get classification of calendar event
	 * @return - classification of the calendar event
	 */
    public String getClassification() {
        return classification;
    }
    
	/**
	 * Set the location of the calendar event
	 * @param l - location of calendar event
	 */
    public void setLocation(String l) {
        location = l;
    }
    
	/**
	 * Get the location of the calendar event
	 * @return - location of calendar event
	 */
    public String getLocation() {
        return location;
    }
    
	/**
	 * Set priority
	 * @param p - priority of the calendar event
	 */
    public void setPriority(String p) {
        try {
            if (Integer.parseInt(p) < 10 && Integer.parseInt(p) > 0) {
                priority = Integer.parseInt(p);
            }
        }
        catch (NumberFormatException e) {
            if (p.equals("high")) {
                priority = 1;
            }
            else if (p.equals("medium")) {
                priority = 5;
            }
            else {
                priority = 9;
            }
        }
    }
    
	/**
	 * Get priority of the calendar event
	 * @return - priority of the calendar event
	 */
    public int getPriority() {
        return priority;
    }
    
	/**
	 * Set summary of the calendar event
	 * @param s - summary of the calendar event
	 */
    public void setSummary(String s) {
        summary = s;
    }
    	
	/**
	 * Get summary
	 * @return - summary of the calendar event
	 */
    public String getSummary() {
        return summary;
    }
    
    /**
	 * Set start date and time of calendar event
	 * @param m - date of event
	 * @param t - time of event
	 */
    public void setDTStart(String m, String t) {
        DTStart = m;
        DTStart = DTStart.concat("T");
        DTStart = DTStart.concat(t);
    }
    
	/**
	 * Get start date and time of calendar event
	 * @return - start date and time
	 */
    public String getDTStart() {
        return DTStart;
    }
    
	/**
	 * Set end date and time of calendar event
	 * @param m - date of event
	 * @param t - time of event
	 */
    public void setDTEnd(String m, String t) {
        DTEnd = m;
        DTEnd = m.concat("T");
        DTEnd = DTEnd.concat(t);
    }
    
	/**
	 * Get end date and time of calendar event
	 * @return - end date and time
	 */
    public String getDTEnd() {
        return DTEnd;
    }

	/**
	 * Set time zone of calendar event
	 * @param t - time zone of calendar event
	 */
    public void setTimeZone(String t) {
        timeZone = t;
    }
    
	/**
	 * Get time zone of calendar event
	 * @return - time zone of calendar event
	 */
    public String getTimeZone() {
        return timeZone;
    }
}
