package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import economy.BestEconomyBowlers;
import threads.YearWiseBowlerStats;
import tossWinFiled.Toss;
import yearwiseTeamAnalysis.ReadFiles;
import yearwiseTeamAnalysis.WriteData;

public class Client {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		long startTime = System.nanoTime();

		/*
		 * Answer 1 : Gathered data for year 2017 and 2016
		 */
		// Client.tossField();

		/*
		 * Answer 2 : Partially Solved. Gathered data completely for the year 2017,
		 * gathered data partially for years 2008, 2011 and 2016
		 */
		// Client.yearwiseTeamData();

		/*
		 * Answer 3 :
		 */
		Client.bestEconomy();

		Thread.yield();

		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) + " ns");
	}

	// Method for Answer 1
	public static void tossField() throws IOException {

		Toss.print();

	}

	// Method for Answer 2
	public static void yearwiseTeamData() throws IOException {
		ArrayList obj = new ArrayList();

		obj = ReadFiles.files();

		WriteData.write((ArrayList) obj.get(0), (HashMap) obj.get(1), (HashMap) obj.get(2), (HashMap) obj.get(3),
				(ArrayList) obj.get(4), (HashMap) obj.get(5));

	}

	// Method for Answer 3
	public static void bestEconomy() throws IOException {

		BufferedReader[] br = BestEconomyBowlers.reader();

		HashMap<String, String> mid_year = BestEconomyBowlers.read(br);

		// System.out.println((BestEconomyBowlers.bowlerEconomy(br,
		// mid_year)).get("498"));

		BestEconomyBowlers beb = new BestEconomyBowlers();

		YearWiseBowlerStats t08 = new YearWiseBowlerStats("2008");
		t08.bestbowlers = beb;
		t08.mid_year = mid_year;
		t08.mid_bowler = (BestEconomyBowlers.bowlerEconomy(br, mid_year));
		t08.bowlers = BestEconomyBowlers.bowlers;

		t08.start();

		/*
		 * try { t08.join(); } catch (InterruptedException e) {
		 * System.out.println("Interrupted Exception"); }
		 */
	}

}
