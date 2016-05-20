package us.noop.fuckoff;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		/*LCSTeam[] t = {
				new LCSTeam("1", new Player("1"), new Player("1"), new Player(
						"1"), new Player("1"), new Player("1")),
				new LCSTeam("2", new Player("1"), new Player("1"), new Player(
						"1"), new Player("1"), new Player("1")), new LCSTeam("3", new Player("1"), new Player("1"), new Player("1"), new Player("1"),new Player("1")), };
		*/
		Team[] t = {
				new Team("1"), new Team("2"), new Team ("3")
		};
		Random r = new Random();
		int amounttrue = 0;
		int amountfalse = 0;
		for (int i = 0; i < 100000; i++) {
			double ra = r.nextDouble();
			if(r.nextBoolean()) {
				t[1].win(t[2]);
				amounttrue++;
			}
			else{
				t[2].win(t[1]);
				amountfalse++;
			}
		}
		System.out.println((t[0].GetELO() + t[1].GetELO() + t[2].GetELO()) / 3 + "");
		System.out.println(t[0] + " " + t[1] + " " + t[2]);
		System.out.println(t[1].expectedOutcome(t[2]));
		System.out.println(amounttrue + " " + amountfalse);
	}
}