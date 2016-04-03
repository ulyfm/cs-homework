package us.noop;

import java.io.IOException;

import us.noop.hltv.HLTVScraper;

public class Start {
	public static void main(String... args){
		try {
			HLTVScraper.get(365);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
