package us.noop.hltvV2;

public class LCSTeam extends Team {
	private Player[] players;

	public LCSTeam(String name, Player p1, Player p2, Player p3, Player p4,
			Player p5) {
		super(name);
		players = new Player[] { p1, p2, p3, p4, p5 };
	}

	public void SetELO(double NewELO) {
		double gap = GetELO() - NewELO;
		for (Player p : players) {
			p.SetELO(p.GetELO() - gap);
		}
	}

	public double GetELO() {
		double sum = 0.0;
		for (Player p : players) {
			sum += p.GetELO();
		}
		return sum / 5.0;
	}
}
