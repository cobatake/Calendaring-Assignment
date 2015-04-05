import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;


public class FreeTimeEventTest {

	@Test
	public void testGetFileArray() {
		FreeTimeEvent event = new FreeTimeEvent();
		event.getFileArray();
		ArrayList<String> fileNames = event.getFileNames();
		assertEquals("Studying for finals.ics", fileNames.get(0));
		assertEquals("Running.ics", fileNames.get(1));
	}

	@Test
	public void testFindBusyTimes() {
		try {
			FreeTimeEvent event = new FreeTimeEvent();
			event.getFileArray();
			event.findBusyTimes();
			ArrayList<TimeBlock> bTimes = event.getBusyTimes();
			assertEquals("20150508", bTimes.get(0).getStartDate());
			assertEquals("050000", bTimes.get(0).getStartTime());
			assertEquals("20150508", bTimes.get(0).getEndDate());
			assertEquals("060000", bTimes.get(0).getEndTime());
			assertEquals("Pacific/Honolulu", bTimes.get(0).getTimezone());
			assertEquals("20150508", bTimes.get(1).getStartDate());
			assertEquals("180000", bTimes.get(1).getStartTime());
			assertEquals("20150508", bTimes.get(1).getEndDate());
			assertEquals("220000", bTimes.get(1).getEndTime());
			assertEquals("Pacific/Honolulu", bTimes.get(1).getTimezone());
		}
		catch (IOException ioexception) {
			System.out.print(ioexception.getMessage());
		}
	}

	@Test
	public void testFindFreeTimes() {
		try {
			FreeTimeEvent event = new FreeTimeEvent();
			event.getFileArray();
			event.findBusyTimes();
			event.findFreeTimes();
			ArrayList<TimeBlock> fTimes = event.getFreeTimes();
			assertEquals("Pacific/Honolulu", event.getTimeZone());
			assertEquals("20150508", fTimes.get(0).getStartDate());
			assertEquals("000000", fTimes.get(0).getStartTime());
			assertEquals("20150508", fTimes.get(0).getEndDate());
			assertEquals("050000", fTimes.get(0).getEndTime());
			assertEquals("20150508", fTimes.get(1).getStartDate());
			assertEquals("060000", fTimes.get(1).getStartTime());
			assertEquals("20150508", fTimes.get(1).getEndDate());
			assertEquals("180000", fTimes.get(1).getEndTime());
			assertEquals("20150508", fTimes.get(2).getStartDate());
			assertEquals("220000", fTimes.get(2).getStartTime());
			assertEquals("20150508", fTimes.get(2).getEndDate());
			assertEquals("235959", fTimes.get(2).getEndTime());
		}
		catch (IOException ioexception) {
			System.out.print(ioexception.getMessage());
		}
	}

	@Test
	public void testGetFreeTimeEvents() {
		try {
			FreeTimeEvent event = new FreeTimeEvent();
			event.getFileArray();
			event.findBusyTimes();
			event.findFreeTimes();
			event.getFreeTimeEvents();

			ArrayList<ICalendarEvent> fEvents = event.getFreeTime();
			assertEquals("2.0", fEvents.get(0).getVersion());
			assertEquals("public", fEvents.get(0).getClassification());
			assertEquals("FreeTime0", fEvents.get(0).getSummary());
			assertEquals("None", fEvents.get(0).getLocation());
			assertEquals("20150508T000000", fEvents.get(0).getDTStart());
			assertEquals("20150508T050000", fEvents.get(0).getDTEnd());
			assertEquals("Pacific/Honolulu", fEvents.get(0).getTimeZone());
			assertEquals(1, fEvents.get(0).getPriority());
			assertEquals("2.0", fEvents.get(1).getVersion());
			assertEquals("public", fEvents.get(1).getClassification());
			assertEquals("FreeTime1", fEvents.get(1).getSummary());
			assertEquals("None", fEvents.get(1).getLocation());
			assertEquals("20150508T060000", fEvents.get(1).getDTStart());
			assertEquals("20150508T180000", fEvents.get(1).getDTEnd());
			assertEquals("Pacific/Honolulu", fEvents.get(1).getTimeZone());
			assertEquals(1, fEvents.get(1).getPriority());
			assertEquals("2.0", fEvents.get(2).getVersion());
			assertEquals("public", fEvents.get(2).getClassification());
			assertEquals("FreeTime2", fEvents.get(2).getSummary());
			assertEquals("None", fEvents.get(2).getLocation());
			assertEquals("20150508T220000", fEvents.get(2).getDTStart());
			assertEquals("20150508T235959", fEvents.get(2).getDTEnd());
			assertEquals("Pacific/Honolulu", fEvents.get(2).getTimeZone());
			assertEquals(1, fEvents.get(2).getPriority());
		}
		catch (IOException ioexception) {
			System.out.print(ioexception.getMessage());
		}

	}
}
