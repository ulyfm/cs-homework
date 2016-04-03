package us.noop.hltv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Test {

	public static void main(String[] args) {
		HashMap<String, Team> teams = new HashMap<String, Team>();
		ArrayList<MatchObject> games = null;
		try {
			games = HLTVScraper.get(10);
		} catch (IOException e) {
			System.err.println("uly fix ur shit");
			e.printStackTrace();
			System.exit(1);
		}
		Team winner;
		Team loser;
		for (MatchObject m : games) {
			if (teams.containsKey(m.getTeam1())) {
				winner = teams.get(m.getTeam1());
			} else {
				winner = new Team(m.getTeam1());
				teams.put(m.getTeam1(), winner);
			}

			if (teams.containsKey(m.getTeam2())) {
				loser = teams.get(m.getTeam2());
			} else {
				loser = new Team(m.getTeam2());
				teams.put(m.getTeam2(), loser);
			}
			String map = m.getMap();
			int box = 1;
			if(m.getMap().contains("" + 2))
				box = 2;
			if (m.getMap().contains("" + 3))
				box = 3;
			if (m.getMap().contains("" + 5))
				box = 5;
			if (m.getTeam1Rounds() > m.getTeam2Rounds())
				teams.get(m.getTeam1()).win(teams.get(m.getTeam2()), box);
			if (m.getTeam1Rounds() < m.getTeam2Rounds())
				teams.get(m.getTeam2()).win(teams.get(m.getTeam1()), box);
			if(m.getTeam1Rounds() == m.getTeam2Rounds()) {
				teams.get(m.getTeam1()).tie(teams.get(m.getTeam2()), box);
			}

		}
		System.out.println(teams.values());
	}

}
