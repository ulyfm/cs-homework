package us.noop.hltvV2;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HLTVOddsScraper {
	private String teamA,teamB;
	private URL u;
	public HLTVOddsScraper(String teamA, String teamB, URL u) {
		this.teamA = teamA;
		this.teamB = teamB;
		this.u = u;
	}
	public HashMap<String, String> getOdds () {
		Elements el;
		try {
			Document d = Jsoup.connect(u.toString()).userAgent("Chrome").get();
			el = d.getElementsByAttribute("text-align");
		} catch (IOException e) {
			System.err.println("no connection for URL" );
			e.printStackTrace();
		}
		
		return null;
		
	}
	

}
