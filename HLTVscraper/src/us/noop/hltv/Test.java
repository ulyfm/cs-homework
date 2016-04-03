package us.noop.hltv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Test {

	public static void main(String[] args) {
		HashMap<String, Team> teams = new HashMap<String, Team>();
		ArrayList<MatchObject> games = null;
		try {
			games = HLTVScraper.get(40);
		} catch (IOException e) {
			System.err.println("uly fix ur shit");
			e.printStackTrace();
			System.exit(1);
		}
		Team winner;
		Team loser;
		for (MatchObject m : games) {
			if (teams.containsKey(m.getWinner())) {
				winner = teams.get(m.getWinner());
			} else {
				winner = new Team(m.getWinner());
				teams.put(m.getWinner(), winner);
			}

			if (teams.containsKey(m.getLoser())) {
				loser = teams.get(m.getLoser());
			} else {
				loser = new Team(m.getLoser());
				teams.put(m.getLoser(), loser);
			}
			teams.get(m.getWinner()).win(teams.get(m.getLoser()), 1);

		}
		System.out.println(teams.values());
	}

}
