package parseLib.date;





import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class parseDate {

	private String times = "";// the time(s) to be read

	private ArrayList<String> timeArray = new ArrayList<String>();
	// private String rawTime = "";

	private String dateToCheck = "";

	private String[] months = { "jan", "feb", "mar", "apr", "may", "jun",
			"jul", "aug", "sep", "nov", "dec" };

	private String date = "";
	private String TZD = "";
	private String time = "";
	private String result = "";

	// Defaults to look for a text file
	public parseDate(String inFile) {
		times = inFile;
		System.out.println("file imported: " + times);
	}
	public parseDate() {
		times = "dates.txt";
		System.out.println("file imported: " + times);
	}

	public void readText() {
		String rawTime = "";
		System.out.println("Reading from: " + times);
		if(times.contains(".txt")){
			File file = new File(times);
			try {
	
				Scanner read = new Scanner(file);
				int i = 0;
	
				while (read.hasNextLine()) {
					rawTime = read.nextLine();
					timeArray.add(rawTime);
					// System.out.println(timeArray.get(i));
					i++;
				}
				read.close();
			} catch (FileNotFoundException e) {
				System.err.println("No File Found:" + e.getMessage());
			}
		}
		else{
			timeArray.add(times);
		}

	}

	public void parseAndRec() {
		/*
		 * reading through the array to parse dates one by one
		 */

		for (int i = 0; i < timeArray.size(); i++) {

			dateToCheck = timeArray.get(i);
			// System.out.println(dateToCheck);
			/*
			 * resolved date if(there are 2 .'s /'s or -'s) run resolve slash
			 * and set all date resolved date = rd+ returnvalue+-
			 */

			if (timeArray.get(i).toLowerCase().contains("jan")
					|| timeArray.get(i).toLowerCase().contains("feb")
					|| timeArray.get(i).toLowerCase().contains("mar")
					|| timeArray.get(i).toLowerCase().contains("apr")
					|| timeArray.get(i).toLowerCase().contains("may")
					|| timeArray.get(i).toLowerCase().contains("jun")
					|| timeArray.get(i).toLowerCase().contains("jul")
					|| timeArray.get(i).toLowerCase().contains("aug")
					|| timeArray.get(i).toLowerCase().contains("sep")
					|| timeArray.get(i).toLowerCase().contains("nov")
					|| timeArray.get(i).toLowerCase().contains("dec")) {

				date = resolveDate(timeArray.get(i));
			}

			else if (timeArray.get(i).indexOf("/") >= 0) {

				date = resolveSlashDate(timeArray.get(i));
			}
			else{
				date = resolveDateLeadYear(timeArray.get(i));
			}

			if (timeArray.get(i).indexOf("PM") >= 0) {
				if(date != ""){
					time = "T" + resolveTime(timeArray.get(i));
				}
				else{
					time = resolveTime(timeArray.get(i));
				}
			}
			else if(timeArray.get(i).indexOf(":") >= 0){
				
				if(date != ""){
					time = "T" + resolveTimeStandard(timeArray.get(i));
				}
				else{
					time = resolveTimeStandard(timeArray.get(i));
				}
			}

			if (timeArray.get(i).indexOf("GMT") >= 0
					|| timeArray.get(i).indexOf('-') >= 0
					|| timeArray.get(i).indexOf('-') >= 0) {

				TZD = resolveTZD(timeArray.get(i));
			}

			/*
			 * if contains(Month name ex mar: resolve date)
			 */
			result = date + time + TZD;
			System.out.println(i + ": " + result);
			date = "";
			time = "";
			TZD = "";
		}
	}

	// resolveDate Currently resolves dates that contain the month word
	// (ex:MARCH)
	public String resolveDate(String fixDate) {

		String newDate = "";
		String lowerDate = fixDate.toLowerCase();

		for (int i = 0; i < months.length; i++) {
			if (lowerDate.contains(months[i])) {
				newDate = newDate + (i + 1);
				if (i <= 9) {
					newDate = "0" + newDate;
				}
				break;
			}
		}

		lowerDate = lowerDate.replaceAll("[0-9]*:[0-9]*", "");
		lowerDate = lowerDate.replaceAll("[A-Za-z]", "");
		lowerDate = lowerDate.replaceAll(",", "");
		lowerDate = lowerDate.replaceAll("-[0-9]*", "");
		lowerDate = lowerDate.replaceAll("/+[0-9]*", "");
		lowerDate = lowerDate.replaceAll(" ", "");
		//System.out.println(lowerDate);
		String storeDay = "";
		String storeYear = "";
		//System.out.println(lowerDate.length());
		if(lowerDate.length()<2){
			storeDay=lowerDate;
			System.out.println(storeDay);
		}
		else if(lowerDate.length()>4){
			//System.out.println(lowerDate.length());
			for (int i = 0; i < lowerDate.length(); i++) {
	
				if (i > 1) {
					storeYear = storeYear + lowerDate.charAt(i);
				} else {
					//storeDay = storeDay + lowerDate.charAt(i);
				}
			}
		}
		else{
			storeYear=lowerDate;
		}
		if (storeYear == "" ) {
			newDate =newDate+ "-" + storeDay;
			//System.out.println("hello");
		} 
		else if(storeDay == ""){
			newDate =storeYear+ "-" + newDate;
			//System.out.println("hello1");
		}
		else {
			newDate = storeYear + "-" + newDate + "-" + storeDay;
			//System.out.println("hello2");
		}
		// System.out.println(lowerDate);
		return newDate;
	}

	public String resolveSlashDate(String fixDate) {
		String newDate = "";
		String lowerDate = fixDate.toLowerCase();
		// System.out.println(lowerDate);
		lowerDate = lowerDate.replaceAll("[0-9]*:[0-9]*", "");
		lowerDate = lowerDate.replaceAll("[A-Za-z]", "");
		lowerDate = lowerDate.replaceAll(",", "");
		// lowerDate = lowerDate.replaceAll("-[0-9]*", "");
		// lowerDate = lowerDate.replaceAll("/+[0-9]*", "");
		// System.out.println(lowerDate);
		lowerDate = lowerDate.replaceAll("[/]", "");
		lowerDate = lowerDate.replaceAll(" ", "");
		String storeMonth = "";
		String storeDay = "";
		String storeYear = "";
		// if the date is written with the day leading and is 7 digits
		if (lowerDate.length() == 7) {
			for (int i = 0; i < lowerDate.length(); i++) {
				if (i == 0) {
					storeDay = storeDay + "0" + lowerDate.charAt(i);
				} else if (i > 0 && i <= 2) {
					storeMonth = storeMonth + lowerDate.charAt(i);
					;
				} else {
					storeYear = storeYear + lowerDate.charAt(i);
				}
			}
		}
		/*
		 * if its 8 digits check the leading number to see if its a 0 if it is
		 * assume its the month (it could be leading as the day as well but
		 * looking at other references it is assumed to be the month )
		 */
		else {
			if ((lowerDate.charAt(0) == '0' || lowerDate.charAt(0) == '1')
					&& Character.getNumericValue(lowerDate.charAt(1)) <= 2) {
				for (int i = 0; i < lowerDate.length(); i++) {
					if (i < 2) {
						storeDay = storeDay + lowerDate.charAt(i);
					} else if (i < 5 && i > 2) {
						storeMonth = storeMonth + lowerDate.charAt(i);
					} else {
						storeYear = storeYear + lowerDate.charAt(i);
					}
				}
			} else {
				for (int i = 0; i < lowerDate.length(); i++) {
					if (i < 2) {
						storeMonth = storeMonth + lowerDate.charAt(i);
					} else if (i > 1 && i < 4) {
						storeDay = storeDay + lowerDate.charAt(i);
					} else {
						storeYear = storeYear + lowerDate.charAt(i);
					}
				}
			}
		}

		newDate = storeYear + "-" + storeDay+ "-" + storeMonth;
		// System.out.println(lowerDate);
		return newDate;
	}

	public String resolveDateLeadYear(String fixDate){
		String newDate=fixDate;;
		String resolvedDate="";
		String newDay="";
		String newMonth="";
		String newYear="";
		newDate = newDate.replaceAll("[0-9]*:[0-9]*", "");
		newDate = newDate.replaceAll("[A-Za-z]", "");
		newDate = newDate.replaceAll(",", "");
		//newDate = newDate.replaceAll("-[0-9]*", "");
		//newDate = newDate.replaceAll("/+[0-9]*", "");
		newDate = newDate.replaceAll("[-]", "");
		// System.out.println(lowerDate);
		newDate = newDate.replaceAll("[/]", "");
		newDate = newDate.replaceAll(" ", "");
		newDate = newDate.replaceAll("[.]", "");
		//resolvedDate=newDate;
		for(int i = 0 ; i < newDate.length();i++){
			if(i<4){
				newYear=newYear + newDate.charAt(i);
			}
			else if(i<6){
				newMonth=newMonth + newDate.charAt(i);
			}
			else{
				newDay=newDay + newDate.charAt(i);
			}
		}
		if(newYear=="" && newMonth=="" && newDay==""){
			resolvedDate="";
		}
		else{
		resolvedDate=newYear+ "-" + newMonth + "-" + newDay;
		}
		return resolvedDate;
	}
	
	/*
	 * using regex to narrow down the time I was able to then locate the leading
	 * number and alter it so it followed iso8601 standards. Then I concatenated
	 * it with the rest of the time back into a String to be returned.
	 */
	public String resolveTime(String fixTime) {

		// System.out.println("resolvingTime");
		// Resolves any time that uses pm
		int[] timeArray = { 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 };
		String resolvedTime = "";
		String newTime = "";
		String timeRegex = "([0-9]:[0-9][0-9])";
		Pattern checkRegex = Pattern.compile(timeRegex);
		Matcher matchRegex = checkRegex.matcher(fixTime);
		while (matchRegex.find()) {
			if (matchRegex.group().length() != 0) {
				resolvedTime = matchRegex.group().trim();
				// System.out.println(matchRegex.group().trim());
			}
		}
		int trigger = 0;
		for (int i = 0; i < resolvedTime.length(); i++) {

			if (trigger == 0) {
				for (int j = 0; j < timeArray.length; j++) {
					// System.out.println(timeArray[j]+"");
					if (Character.getNumericValue(resolvedTime.charAt(i)) == j) {
						// System.out.println(timeArray[j]+"");
						newTime = newTime + timeArray[j];
						// System.out.println(newTime);
						trigger = 1;
					}
				}
			} else {
				newTime = newTime + resolvedTime.charAt(i);
				// System.out.println(newTime);
			}
		}
		int colonCount = 0;
		int secondCount = 0;
		for (int k = 0; k < fixTime.length(); k++) {
			if (fixTime.charAt(k) == ':') {
				colonCount++;
			}
			if ((secondCount <= 2 && colonCount == 2)) {
				newTime = newTime + fixTime.charAt(k);
				secondCount++;

			}

		}
		return newTime;
	}

	public String resolveTimeStandard(String fixTime) {
		String newTime="";
		String standardTime="";
		String timeRegex = "([0-9][0-9]:[0-9][0-9](:[0-9][0-9]?))";
		Pattern checkRegex = Pattern.compile(timeRegex);
		Matcher matchRegex = checkRegex.matcher(fixTime);
		while (matchRegex.find()) {
			if (matchRegex.group().length() != 0) {
				newTime = matchRegex.group().trim();
				// System.out.println(matchRegex.group().trim());
			}
		}
		standardTime= newTime;
		return standardTime;
		
	
	}
	// resovesTZDNotation (FIX: ADD the colon)
	public String resolveTZD(String fixTZD) {
		String newTZD = "";
		String resolvedTZD="";
		if (fixTZD.contains("GMT")) {
			newTZD = "+0000";
			return newTZD;
		} else {
			int checkTrailingNum = 0;
			// int checkOccur=0;//used to check the occurrence of -/+

			for (int i = 0; i < fixTZD.length(); i++) {
				if (fixTZD.charAt(i) == '-' || fixTZD.charAt(i) == '+') {
					newTZD = newTZD + fixTZD.charAt(i);
					checkTrailingNum++;
				} else if (checkTrailingNum >= 1 && checkTrailingNum < 5
						&& fixTZD.charAt(i) != ':') {
					newTZD = newTZD + fixTZD.charAt(i);
					checkTrailingNum++;
				}
				// prevents occurrences of multiple -'s from messing up the code
				if ((fixTZD.charAt(i) == '-' || fixTZD.charAt(i) == '+')
						&& checkTrailingNum >= 4) {
					for (int j = 0; j < newTZD.length(); j++) {
						if (j == 0) {
							continue;
						} else if (j >= 1
								&& Character.isDigit(fixTZD.charAt(i))) {
							continue;
						} else {
							newTZD = "";
							checkTrailingNum = 0;
						}
					}
				}
			}
		}
		for(int i=0;i<newTZD.length();i++){
			if(i<=4){
				resolvedTZD=resolvedTZD+newTZD.charAt(i);
			}
			
		}
		return resolvedTZD;
	}

}

