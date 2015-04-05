import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;

public class FreeTimeEvent {

	private ArrayList<String> fileNames;
	private ArrayList<TimeBlock> busyTimes;
	private ArrayList<TimeBlock> freeTimes;
	private ArrayList<ICalendarEvent> freeTimeEvents;
	private String Date;
	private String TimeZone;

	/**
	 *  Constructor for FreeTimeEvent
	 */
	public FreeTimeEvent() {
		this.fileNames = new ArrayList<String>();
		this.busyTimes = new ArrayList<TimeBlock>();
		this.freeTimes = new ArrayList<TimeBlock>();
		this.freeTimeEvents = new ArrayList<ICalendarEvent>();
		this.Date = null;
		this.TimeZone = null;
	}

	/**
	 *  Get file names for scheduled events
	 * @return array of file names
	 */
	public ArrayList<String> getFileNames(){
		return fileNames;
	}

	/**
	 *  Get blocks of time for scheduled events
	 * @return array of blocks of time
	 */
	public ArrayList<TimeBlock> getBusyTimes(){
		return busyTimes;
	}
	
	/**
	 *  Get blocks of free time for scheduled events
	 * @return array of blocks of free time
	 */
	public ArrayList<TimeBlock> getFreeTimes(){
		return freeTimes;
	}
	
	
	/**
	 * Get free time events
	 * @return array of calendar events for all free times
	 */
	public ArrayList<ICalendarEvent> getFreeTime(){
		return freeTimeEvents;
	}

	/**
	 *  Get date for events
	 * @return array of blocks of time
	 */
	public String getDate(){
		return Date;
	}
	
	/**
	 *  Get time zone for events
	 * @return array of blocks of free time
	 */
	public String getTimeZone(){
		return TimeZone;
	}
	
	/**
	 * Get event files
	 */
	public void getFileArray() {
		Scanner input = new Scanner(System.in);

		System.out.print("Enter .ics file name including the extension (enter d when done): ");
		String file = input.nextLine().trim();

		while((!file.equals("d")) && (!file.equals("D"))) {

			File eventFile = new File(file);
			String extension = file.substring(file.lastIndexOf("."), file.length());

			if(!extension.equals(".ics")){
				System.out.print("Not a valid file\n");			
			}

			else if(!eventFile.exists() || eventFile.isDirectory()) {
				System.out.print("File does not exist\n");		
			}

			else{
				fileNames.add(file);
				System.out.print(file + " was added successfully\n");
			}

			System.out.print("Enter .ics file name including the extension (enter d when done): ");
			file = input.nextLine().trim();
		}
	}

	/**
	 *  Find busy times during the day
	 * @throws IOException - if dates not the same or time zones not matching
	 */
	public void findBusyTimes() throws IOException {

		for(int i=0; i<fileNames.size(); i++) {
			File file = new File(fileNames.get(i));
			Scanner input = new Scanner(file);
			busyTimes.add(new TimeBlock());

			while(input.hasNext()) {
				String nextLine = input.nextLine();
				String delimiter = "[:]+";
				String[] tokens = nextLine.split(delimiter);

				if(tokens[0].equals("DTSTART")) {
					busyTimes.get(i).setdtStart(tokens[1]);
				}

				if(tokens[0].equals("DTEND")) {
					busyTimes.get(i).setdtEnd(tokens[1]);
				}

				if(tokens[0].equals("TZID")) {
					busyTimes.get(i).setTimezone(tokens[1]);
				}		
			}

			input.close();
		}

		TimeBlock checkEvent;
		Date = busyTimes.get(0).getStartDate();
		TimeZone = busyTimes.get(0).getTimezone();

		for(int i=0; i<busyTimes.size(); i++) {
			checkEvent = busyTimes.get(i);

			if(!checkEvent.getStartDate().equals(Date) || !checkEvent.getEndDate().equals(Date) || !checkEvent.getTimezone().equals(TimeZone)) {
				throw new IOException("The event is not on the same day or time zones did not match.");
			}

			Collections.sort(busyTimes);
		}	
	}

	/**
	 * Find free times during the day
	 */
	public void findFreeTimes() {
		int j = 0;	

		if(Integer.parseInt(busyTimes.get(0).getStartTime()) != Integer.parseInt("000000")) {
			freeTimes.add(new TimeBlock());
			freeTimes.get(j).setdtStart(Date+"T000000");
			freeTimes.get(j).setdtEnd(Date+"T"+busyTimes.get(0).getStartTime());
			j++;
		}

		for(int i=1; i<busyTimes.size(); i++) {
			if(Integer.parseInt(busyTimes.get(i).getStartTime()) > Integer.parseInt(busyTimes.get(i-1).getEndTime())) {
				freeTimes.add(new TimeBlock());
				freeTimes.get(j).setdtStart(Date+"T"+busyTimes.get(i-1).getEndTime());
				freeTimes.get(j).setdtEnd(Date+"T"+busyTimes.get(i).getStartTime());
				j++;
			}
		}

		if(Integer.parseInt(busyTimes.get(busyTimes.size()-1).getEndTime()) != Integer.parseInt("235959")) {
			freeTimes.add(new TimeBlock());
			freeTimes.get(j).setdtStart(Date+"T"+busyTimes.get(busyTimes.size()-1).getEndTime());
			freeTimes.get(j).setdtEnd(Date+"T235959");
		}
	}

	/**
	 * Set free time events
	 */
	public void getFreeTimeEvents() {
		for(int i=0; i<freeTimes.size(); i++) {
			ICalendarEvent freeTimeEvent = new ICalendarEvent();

			freeTimeEvent.setSummary("FreeTime"+i);
			freeTimeEvent.setLocation("None");
			freeTimeEvent.setDTStart(Date, freeTimes.get(i).getStartTime());
			freeTimeEvent.setDTEnd(Date, freeTimes.get(i).getEndTime());
			freeTimeEvent.setTimeZone(TimeZone);
			freeTimeEvent.setPriority("high");
			freeTimeEvent.setClassification("public");

			freeTimeEvents.add(freeTimeEvent);
		}
	}

}
