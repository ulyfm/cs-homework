package us.noop.fuckoff;

public class Team {
	public double ELO;
	private String TeamName;

	public Team(String name) {
		TeamName = name;
		ELO = 2000;
	}

	public double expectedOutcome(Team t2) {
		return 1 / (Math.pow(10, (-ELO + t2.ELO) / 400) + 1);

	}

	public void win(Team t2) {
		// int c = getC(t2);
		if (!this.equals(t2)) {
			double expectedOutcome = expectedOutcome(t2);
			// System.out.println("EO=" + expectedOutcome);
			double c = 30;
			double change = c * (1 - expectedOutcome);
			ELO += change;
			t2.ELO -= change;
			//System.out.println(ELO);
		}
	}
	public String toString() {
		return TeamName + " :" + ELO;

	}
}
