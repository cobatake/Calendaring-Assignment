public class TimeBlock implements Comparable<TimeBlock> {
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;
	private String timezone;

	/**
	 * Constructor for timeBlock
	 */
	TimeBlock() {
		this.startDate = null;
		this.endDate = null;
		this.startTime = null;
		this.endTime = null;
		this.timezone = null;
	}

	public String getStartDate() {
		return startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public String getStartTime() {
		return startTime;
	}


	public String getEndTime() {
		return endTime;
	}


	public String getTimezone() {
		return timezone;
	}

	/**
	 * Set start date and time
	 * @param DTStart - string containing start date and time
	 */
	public void setdtStart(String DTStart) {
		this.startDate = DTStart.substring(0, 8);
		this.startTime = DTStart.substring(9, DTStart.length());
	}

	/**
	 * Set end date and time
	 * @param DTEnd - string containing end date and time
	 */
	public void setdtEnd(String DTEnd) {
		this.endDate = DTEnd.substring(0, 8);
		this.endTime = DTEnd.substring(9, DTEnd.length());
	}

	/**
	 * Set time zone
	 * @param TZID - string containing time zone
	 */
	public void setTimezone(String TZID) {
		this.timezone = TZID;
	}

	@Override
	public int compareTo(TimeBlock block) {
		return Integer.parseInt(this.startTime) - Integer.parseInt(block.startTime);
	}

}
