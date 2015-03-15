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
		getEvent(scan, event);
		saveFile(event);
	}
	
	/**
	 * Get event info
	 * @param scan - user input
	 * @param event - calendar event object
	 */
	public static void getEvent(Scanner scan, ICalendarEvent event) {
		System.out.print("Name of Event: ");
		event.setSummary(scan.nextLine().trim());
		System.out.print("Location of Event: ");
		event.setLocation(scan.nextLine().trim());
		System.out.print("Start date of Event (yyyymmdd): ");
		String startmonth = scan.nextLine().trim();
		System.out.print("Start Time of Event in military time (hhmmss): ");
		String startTime = scan.nextLine().trim();
		event.setDTStart(startmonth, startTime);
		System.out.print("End date of Event (yyyymmdd): ");
		String endmonth = scan.nextLine().trim();
		System.out.print("End Time of Event in military time (hhmmss): ");
		String endTime = scan.nextLine().trim();
		event.setDTEnd(endmonth, endTime);
		System.out.print("Timezone of Event: ");
		event.setTimeZone(scan.nextLine().trim());
		System.out.print("Priority of Event (low, medium, high): ");
		event.setPriority(scan.nextLine().trim());
		System.out.print("Visibility of Event (public, private, confidential): ");
		event.setClassification(scan.nextLine().trim());
	}
	
	/**
	 * Save calendar event to a .ics file
	 * @param event - calendar event object
	 */
	public static void saveFile(ICalendarEvent event) throws IOException {
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
		System.out.print("\nThe .ics file for your event has been created.");
	}
}
