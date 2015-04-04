import java.io.FileWriter;
import java.io.IOException;

public class FreeTimeDriver {
	public static void main(String[] args) throws IOException {
		FreeTimeEvent freeTime = new FreeTimeEvent();
		freeTime.getFileArray();

		if (freeTime.getFileNames() != null) {
			
			freeTime.findBusyTimes();
			freeTime.findFreeTimes();
			freeTime.getFreeTimeEvents();
			
			for (int i = 0; i < freeTime.getFreeTime().size(); i++) {
				saveFile(freeTime.getFreeTime().get(i));
			}

			System.out.println("Free time events have been created.");
		}
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
		}
}
