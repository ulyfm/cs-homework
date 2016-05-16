package us.noop.fuckoff;

//View Team as structure as average of 5 player ELOs?
public class Player extends Team{
	private double ELO;
	public Player(String name) {
		super(name);
		ELO = 2000;
	}
	public double GetELO() {
		return ELO;
	}
	public void SetELO(double ELO) {
		this.ELO = ELO;
	}

}
