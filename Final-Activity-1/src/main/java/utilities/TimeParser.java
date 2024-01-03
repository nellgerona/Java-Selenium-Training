package utilities;

import java.util.InputMismatchException;

public class TimeParser {
	private static String hour;
	private static String minute;
	private static String period;

	/*
	 * ARGUMENT
	 * value: FORMAT HH:MM AM/PM (sample -- 08:02 PM)
	 * 
	 * */
	public TimeParser(String value) {
		if(value == null || value == "") {
			throw new Error("Value is either null or blank");
		}

		parser(value);
	}

	private static void parser(String timeValue) {
		try {
			String[] temp1 = timeValue.split(":");
			String[] temp2 = temp1[1].split(" ");
			int length = 2; 						// desired length after padding

			hour = String.format("%0" + length + "d", Integer.parseInt(temp1[0]));
			minute = String.format("%0" + length + "d", Integer.parseInt(temp2[0]));
			period = temp2[1].toUpperCase();
		}
		catch(InputMismatchException ex) {
			System.out.println("Input is: " + timeValue);
		}
	}

	public String getHour() {
		return hour;
	}

	public String getMinute() {
		return minute;
	}

	public String getPeriod() {
		return period;
	}
}
