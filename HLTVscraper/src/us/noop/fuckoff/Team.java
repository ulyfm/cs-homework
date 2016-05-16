package us.noop.fuckoff;

public class Team {
	private double ELO;
	private String TeamName;

	public Team(String name) {
		TeamName = name;
		ELO = 2000;
	}

	public double expectedOutcome(Team t2) {
		return 1 / (Math.pow(10, (-1 * GetELO() + t2.GetELO()) / 400) + 1);

	}

	public void win(Team t2) {
		// int c = getC(t2);
		if (!this.equals(t2)) {
			double expectedOutcome = expectedOutcome(t2);
			// System.out.println("EO=" + expectedOutcome);
			double c = 30;
			double change = c * (1 - expectedOutcome);
			SetELO(GetELO() + change);
			t2.SetELO(t2.GetELO() - change);
			//System.out.println(ELO);
		}
	}
	public void tie(Team t2) {
		double expectedOutcome = expectedOutcome(t2);
		double c = 30;
		double change = c * (0.5 - expectedOutcome);
		SetELO(GetELO() + change);
		t2.SetELO(t2.GetELO() + change);
	}
	public String toString() {
		return TeamName + " :" + GetELO();

	}
	public double GetELO() {
		return ELO;
	}
	public void SetELO(double newELO) {
		ELO = newELO;
	}
}
