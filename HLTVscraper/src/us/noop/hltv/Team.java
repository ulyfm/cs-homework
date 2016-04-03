package us.noop.hltv;

public class Team {
	public double ELO;
	private String TeamName;

	public Team(String name) {
		TeamName = name;
		ELO = 2000;
	}

	public double expectedOutcome(Team t2) {
		return 1 / (Math.pow(10, (-1 * Math.abs(ELO - t2.ELO)) / 400) + 1);

	}

	public void win(Team t2, int bestOfX) {
		// int c = getC(t2);
		double expectedOutcome = expectedOutcome(t2);
		System.out.println("EO=" + expectedOutcome);
		//BO1 c = 20 BO3 c = 30 BO5 c = 40 unless 3 maps played in BO5
		double c = 20 + (10 * ((bestOfX -(bestOfX % 2))/2));
		ELO += c * (1 - expectedOutcome);
		lose(t2, expectedOutcome, c);
	}

	public void lose(Team t2, double ev, double c) {
		t2.ELO += c * (0 - ev);

	}
	public String toString(){
		return TeamName + ":" + ELO;
		
	}
}
