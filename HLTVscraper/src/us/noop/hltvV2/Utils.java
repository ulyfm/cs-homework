package us.noop.hltvV2;

public class Utils {
	public static int monthToInt(String month){
		String[] months = {"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};
		int i = 0;
		for(int j = 0; j < months.length; ++j){
			if(months[j].equalsIgnoreCase(month)){
				i = j;
			}
		}
		return i + 1;
	}
	public static String CSGLtoHLTVStr(String csgl){
		csgl = csgl.toLowerCase();
		if(csgl.equals("na'vi"))
			return "natus vincere";
		return csgl;
	}
}
