/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author chanelsantiago
 */
public class UserEvent {
    static String[] timezone1 = {"Africa", "America", "Antarctic", "Atlantic", "Europe", "Indian", "Pacific"};
    static String[] africa = {"Abidjan", "Accra", "Addis_Ababa", "Algiers", "Asmara"};
    public static void main (String args[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        ICalendarEvent event = new ICalendarEvent();
        System.out.print("Name of Event: ");
        event.setSummary(scan.nextLine());
        System.out.print("Location of Event: ");
        event.setLocation(scan.nextLine());
        System.out.print("Date of Event(yyyymmdd): ");
        String month = scan.nextLine();
        System.out.print("Start Time of Event(hhmmss): ");
        String startTime = scan.nextLine();
        event.setDTStart(month, startTime);
        System.out.print("End Time of Event(hhmmss): ");
        String endTime = scan.nextLine();
        event.setDTEnd(month, endTime);
        System.out.print("Timezone of Event: ");
        event.setTimeZone(scan.nextLine());
        System.out.print("Priority of Event: ");
        event.setPriority(scan.nextLine());
        System.out.print("Visibility of Event(public, private, confidential): ");
        event.setClassification(scan.nextLine());
        
        FileWriter icsFile = new FileWriter(event.getSummary() + ".ics");
        icsFile.write("BEGIN:VCALENDAR");
        icsFile.write("\nVERSION:" + event.getVersion());
        icsFile.write("\nBEGIN:VEVENT");
        icsFile.write("\nCLASS:" + event.getClassification());
        icsFile.write("\nLOCATION:" + event.getLocation());
        icsFile.write("\nPRIORITY:" + event.getPriority());
        icsFile.write("\nSUMMARY:" + event.getSummary());
        icsFile.write("\nDTSTART;TZID=" + event.getTimeZone() + ":" + event.getDTStart());
        icsFile.write("\nDTEND;TZID=" + event.getTimeZone() + ":" + event.getDTEnd());
        icsFile.write("\nEND:VEVENT");
        icsFile.write("\nEND:VCALENDAR");
        
        icsFile.close();
    }
}
