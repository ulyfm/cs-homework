package us.noop.hltv;

import java.io.IOException;
import java.util.ArrayList;


public class HLTVScraper {
	
	public static ArrayList<MatchObject> get(int numDaysAgo) throws IOException{
		return new Scraper().scrape(numDaysAgo);
	}
	
}
