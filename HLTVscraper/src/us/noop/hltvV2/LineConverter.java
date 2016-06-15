package us.noop.hltvV2;
import java.util.Scanner;

import javax.swing.JApplet;

public class LineConverter{
	public static void main(String[] argv) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Team x Line odds:");
		double t1 = 1/sc.nextDouble();
		System.out.println("Team Y Line odds:");
		double t2 = 1/sc.nextDouble();
		double t3 = t1 + t2;
		double mult = 1/t3;
		t1*=mult;
		t2*=mult;
		System.out.println(t1 +" " +t2);
	}
}
