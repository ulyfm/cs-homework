package us.noop.hltv;

import java.util.Date;

public class MatchObject {
	protected Date time;
	protected String team1, team2, map;
	protected int t1rounds, t2rounds;
	protected int id;
	
	public Date getTime(){
		return time;
	}
	public String getTeam1(){
		return team1;
	}
	public String getTeam2(){
		return team2;
	}
	public int getTeam1Rounds(){
		return t1rounds;
	}
	public int getTeam2Rounds(){
		return t2rounds;
	}
	public String getMap(){
		return map;
	}
	
	public int getId(){
		return id;
	}
	
	public String toString(){
		return "[" + time + "] " + map + " -> " + team1 + "\t" + t1rounds + ":" + t2rounds + "\t" + team2 + " (id: " + id + ")";
	}
}
