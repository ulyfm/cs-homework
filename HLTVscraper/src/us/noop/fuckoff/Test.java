package us.noop.fuckoff;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		LCSTeam[] t = {
				new LCSTeam("1", new Player("1"), new Player("1"), new Player(
						"1"), new Player("1"), new Player("1")),
				new LCSTeam("2", new Player("1"), new Player("1"), new Player(
						"1"), new Player("1"), new Player("1")), new LCSTeam("3", new Player("1"), new Player("1"), new Player("1"), new Player("1"),new Player("1")), };
		Random r = new Random();
		for (int i = 0; i < 1000000; i++) {
			t[i % 3].win(t[r.nextInt(3)]);
		}
		System.out.println((t[0].GetELO() + t[1].GetELO() + t[2].GetELO()) / 3 + "");
		System.out.println(t[0] + " " + t[1] + " " + t[2]);
	}
}