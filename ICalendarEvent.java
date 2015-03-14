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
    String version = "";
    String classification = "";
    String summary = "";
    String location = "";
    String DTStart = "";
    String DTEnd = "";
    String timeZone = "";
    int priority = 9;
    
    public ICalendarEvent() {
        version = "2.0";
    }
    public String getVersion() {
        return version;
    }
    public void setClassification(String c) {
        if (c.equals("private") || c.equals("confidential")) {
            classification = c;
        }
        else {
            classification = "public";
        }
    }
    public String getClassification() {
        return classification;
    }
    public void setLocation(String l) {
        location = l;
    }
    public String getLocation() {
        return location;
    }
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
    public int getPriority() {
        return priority;
    }
    public void setSummary(String s) {
        summary = s;
    }
    public String getSummary() {
        return summary;
    }
    public void setDTStart(String m, String t) {
        DTStart = m;
        DTStart = DTStart.concat("T");
        DTStart = DTStart.concat(t);
    }
    public String getDTStart() {
        return DTStart;
    }
    public void setDTEnd(String m, String t) {
        DTEnd = m;
        DTEnd = m.concat("T");
        DTEnd = DTEnd.concat(t);
    }
    public String getDTEnd() {
        return DTEnd;
    }
    public void setTimeZone(String t) {
        timeZone = t;
    }
    public String getTimeZone() {
        return timeZone;
    }
}
