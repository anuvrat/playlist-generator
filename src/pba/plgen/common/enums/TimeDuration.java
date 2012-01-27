package pba.plgen.common.enums;

/**
 * The timeline for which top tracks are to be evaluated
 * 
 * @author AnuvratSingh
 */
public enum TimeDuration {
	LAST_7_DAYS("Last 7 Days"), LAST_3_MONTHS("Last 3 Months"), LAST_6_MONTHS("Last 6 Months"), LAST_12_MONTHS(
	        "Last 12 Months"), OVERALL("Overall");

	private String m_value;

	private TimeDuration(String value) {
		m_value = value;
	}

	public String toString() {
		return m_value;
	}

	/**
	 * @param duration The string value of duration
	 * @return The TimeDuration enum for the specified string value. If no match, then return TimeDuration.OVERALL
	 */
	public static TimeDuration getTimeDuration(String duration) {
		for (TimeDuration timeDuration : TimeDuration.values())
			if (timeDuration.toString().equalsIgnoreCase(duration))
				return timeDuration;

		return TimeDuration.OVERALL;
	}
}
