/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public void findFreeTime(String filename) {
        //times to compare with busy times...
        String startOfDay = "000000";
        String endOfDay = "235959";
        
        //read file of list of .ics files
        File file1 = new File(filename);
        Scanner readFile = null;
        try {
            readFile = new Scanner(file1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ICalendarEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner readTemp = null;
        String start = "000000";
        String end = "235959";
        String day = "20000101";
        String tz = "";
        List<String> times = new ArrayList<String>();
        //Find start and end times for each event and add to arraylist.
        //Assumption: all events occur on same day, no checks
        while (readFile.hasNextLine()) {
            String line = readFile.nextLine();
            File tempFile = new File(line);
            try {
                readTemp = new Scanner(tempFile);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ICalendarEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (readTemp.hasNextLine()) {
                String tempLine = readTemp.nextLine();
                if (tempLine.contains("DTSTART")) {
                    start = tempLine.substring(tempLine.length()-6);
                    day = tempLine.substring(tempLine.length()-15, tempLine.length()-6);
                    tz = tempLine.substring(tempLine.indexOf("=") + 1, tempLine.indexOf(":"));
                }
                else if (tempLine.contains("DTEND")) {
                    end = tempLine.substring(tempLine.length()-6);
                }
                
            }
            times.add(start);
            times.add(end);
        }
        //Sort times (assuming no events overlap)
        Collections.sort(times);
        
        //Find free time between events
        String freeStart = "";
        String freeEnd = "";
        List<String> freetimes = new ArrayList<String>();
        for (int i = 0; i <= times.size(); i++) {
            if (i == times.size()) {
                freeStart = startOfDay;
                freeEnd = endOfDay;
                i = times.size();
            }
            else {
                freeStart = startOfDay;
                freeEnd = times.get(i);
                startOfDay = times.get(i+1);
            }
	    if (!freeStart.equals(freeEnd)) {
            	freetimes.add(freeStart);
            	freetimes.add(freeEnd);
	    }
            i++;
        }
        
        //Create .ics files for "free time"
        for (int i = 0; i < freetimes.size()-1; i++) {
            FileWriter icsFile = null;
            try {
                icsFile = new FileWriter("freetime" + i/2 + ".ics");
                icsFile.write("BEGIN:VCALENDAR");
                icsFile.write("\nVERSION:" + this.version);
                icsFile.write("\nBEGIN:VEVENT");
                icsFile.write("\nCLASS:public");
                icsFile.write("\nSUMMARY:Free Time");
                icsFile.write("\nDTSTART;TZID=" + tz + ":" + day + freetimes.get(i));
                icsFile.write("\nDTEND;TZID=" + tz + ":" + day + freetimes.get(i+1));
                icsFile.write("\nEND:VEVENT");
                icsFile.write("\nEND:VCALENDAR");
                i++;
            } catch (IOException ex) {
                Logger.getLogger(ICalendarEvent.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    icsFile.close();
                } catch (IOException ex) {
                    Logger.getLogger(ICalendarEvent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }
    }
}
