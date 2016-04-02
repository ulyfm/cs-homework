package us.noop.hltv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Scraper {
	protected ArrayList<MatchObject> scrape(int numDaysAgo) throws IOException{
		
		ArrayList<MatchObject> ret = new ArrayList<MatchObject>();
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, 0 - numDaysAgo);
		System.out.println("Finding matches from after (inc)... " + c.getTime());
		
		Date d = c.getTime();
		Date current = new Date();
		
		int indexNum = 0;
		
		FIRST:
		while(d.before(current)){
			Document doc = Jsoup.connect("http://www.hltv.org/results/" + indexNum + "/").userAgent("Mozilla").get();
			
			Elements un = doc.getElementsByClass("centerFade").first().children();
			
			for(int i = 0; i < un.size(); ++i){
				String str = un.get(i).className();
				//System.out.println(str);
				if(str.trim().equals("")) continue;
				else if(str.startsWith("matchListDateBox")){
					String a = un.get(i).ownText();
					String[] vals = a.split(", ")[1].split(" ");
					Calendar cla = Calendar.getInstance();
					cla.set(Calendar.MONTH, Utils.monthToInt(vals[0]));
					cla.set(Calendar.DAY_OF_MONTH, Integer.parseInt(vals[1].replaceAll("[^\\d.]", "")));
					cla.set(Calendar.YEAR, Integer.parseInt(vals[2]));
					
					Calendar jays = Calendar.getInstance();
					jays.setTime(current);
					
					if(jays.get(Calendar.DAY_OF_YEAR) != cla.get(Calendar.DAY_OF_YEAR)){
						current = cla.getTime();
					
						if(!d.before(current)) break FIRST;
						System.out.println("--- " + current + "---");
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
					
					mo.id = Integer.parseInt(yee.get(4).children().first().attr("href").substring(7).split("-")[0]);
					
					System.out.println(mo);
					
					ret.add(mo);
				}
			}
			
			indexNum += 50;
		}
		
		return ret;
	}
}
