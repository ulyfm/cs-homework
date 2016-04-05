package us.noop.fuckoff;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.LocalDate;

public class Start {
	ArrayList<MatchObject> matches = new ArrayList<MatchObject>();

	public static void main(String... args) {
		new Start();
	}

	public Start() {
		System.out.println("CS Betting Tool");
		System.out.println("version " + Constants.VERSION);
		try {
			File f = new File("matches");

			if (!f.exists()) {
				System.out.println("Downloading database for first time...");
				try {
					ArrayList<MatchObject> ar = HLTVScraper.get(3);

					PrintStream p = new PrintStream(new File("matches"));
					matches = new ArrayList<MatchObject>();
					for (MatchObject mo : ar) {
						p.println(mo.time.toString() + ";;" + mo.id + ";;" + mo.map + ";;" + mo.team1 + ";;" + mo.team2
								+ ";;" + mo.t1rounds + ";;" + mo.t2rounds);
						matches.add(mo);
					}
					p.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Loading saved database...");
				Scanner s = new Scanner(new File("matches"));
				while (s.hasNextLine()) {
					String g = s.nextLine();
					String[] a = g.split(";;");
					MatchObject mo = new MatchObject();
					mo.time = LocalDate.parse(a[0]);

					mo.id = Integer.parseInt(a[1]);
					mo.map = a[2];
					mo.team1 = a[3];
					mo.team2 = a[4];
					mo.t1rounds = Integer.parseInt(a[5]);
					mo.t2rounds = Integer.parseInt(a[6]);

					matches.add(mo);
				}
				
				
				
				System.out.println("Downloading new matches...");
				LocalDate today = new LocalDate(DateTimeZone.forID("CET"));

				LocalDate last = matches.get(0).time;
				System.out.println(matches.size() + " matches in database, " + last.toString() + " is the most recent date.");
				

				int days = Days.daysBetween(last, today).getDays();

				System.out.println("Downloading from " + (days - 1) + " days ago.");
				ArrayList<MatchObject> recent = HLTVScraper.get(days);
				
				for (int i = 0; i < matches.size(); ++i) {
					for (MatchObject m : recent) {
						if (matches.get(i).id == m.id) {
							matches.remove(i);
						}
					}
				}

				for (int j = recent.size() - 1; j >= 0; --j) {
					matches.add(0, recent.get(j));
				}
				
				System.out.println("Saving database...");
				PrintStream p = new PrintStream(new File("matches"));
				for (MatchObject mo : matches) {
					p.println(mo.time.toString() + ";;" + mo.id + ";;" + mo.map + ";;" + mo.team1 + ";;" + mo.team2
							+ ";;" + mo.t1rounds + ";;" + mo.t2rounds);
				}
				p.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.print("> ");

			String command = sc.nextLine();

			respond(command);
		}
	}

	private void respond(String command) {
		if (command.equals("displaydb")) {
			for (MatchObject mo : matches) {
				System.out.println(mo.toString());
			}
		} else if (command.equals("exit") || command.equals("quit")) {
			System.exit(1);
		}else{
			System.out.println("unrecognized command");
		}
	}
}
