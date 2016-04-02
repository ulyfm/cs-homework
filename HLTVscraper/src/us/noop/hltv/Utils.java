package us.noop.hltv;

public class Utils {
	public static int monthToInt(String month){
		String[] months = {"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
		int i = 0;
		for(int j = 0; j < months.length; ++j){
			if(months[j].equalsIgnoreCase(month)){
				i = j;
			}
		}
		return i;
	}
}
