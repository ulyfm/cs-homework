package us.noop.fuckoff;

import java.util.Scanner;

public class KellyC {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Payout for 1$?(under csgolounge odds)");
		double payout = sc.nextDouble();
		System.out.println("Winning probability?");
		double win = sc.nextDouble();
		System.out.println("bet " +((payout * win) - 1) /(payout - 1));
	}

}
