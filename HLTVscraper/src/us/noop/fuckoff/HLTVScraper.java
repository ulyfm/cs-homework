package us.noop.fuckoff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HLTVScraper {
	
	public static ArrayList<MatchObject> get(int numDaysAgo) throws IOException{
		return new HLTVScraper().scrape(numDaysAgo);
	}
	
	protected ArrayList<MatchObject> scrape(int numDaysAgo) throws IOException{
		
		ArrayList<MatchObject> ret = new ArrayList<MatchObject>();
		
		LocalDate d = new LocalDate(DateTimeZone.forID("CET")).minusDays(numDaysAgo);
		LocalDate current = new LocalDate(DateTimeZone.forID("CET"));
		
		int indexNum = 0;
		
		FIRST:
		while(!d.isAfter(current)){
			Document doc = Jsoup.connect("http://www.hltv.org/results/" + indexNum + "/").userAgent("Mozilla").get();
			
			Elements un = doc.getElementsByClass("centerFade").first().children();
			
			for(int i = 0; i < un.size(); ++i){
				String str = un.get(i).className();
				if(str.trim().equals("")) continue;
				else if(str.startsWith("matchListDateBox")){
					String a = un.get(i).ownText();
					String[] vals = a.split(", ")[1].split(" ");
					
					LocalDate dat = new LocalDate(Integer.parseInt(vals[2]), Utils.monthToInt(vals[0]), Integer.parseInt(vals[1].replaceAll("[^\\d.]", "")));
					LocalDate jays = new LocalDate(current.year().get(), current.monthOfYear().get(), current.dayOfMonth().get());
					
					
					if(!dat.isEqual(jays)){
						current = dat;
						
						if(d.isAfter(current)) break FIRST;
					}
				}else if(str.startsWith("matchListBox")){
					
					MatchObject mo = new MatchObject();
					mo.time = current;
					
					Elements yee = un.get(i).children().first().children();
					mo.map = yee.get(0).ownText();
					
					mo.team1 = yee.get(1).children().first().ownText();
					
					mo.t1rounds = Integer.parseInt(yee.get(2).children().get(0).ownText());
					mo.t2rounds = Integer.parseInt(yee.get(2).children().get(1).ownText());
					
					mo.team2 = yee.get(3).children().first().ownText();
					
					try{
					mo.id = Integer.parseInt(yee.get(4).children().first().attr("href").substring(7).split("-")[0]);
					}catch(Exception e){
						mo.id = -1;
					}
					System.out.println(mo);
					
					ret.add(mo);
				}
			}
			
			indexNum += 50;
		}
		
		return ret;
	}
}
