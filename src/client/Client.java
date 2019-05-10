package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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

		// Array of BuferedReader objects
		BufferedReader[] br = BestEconomyBowlers.reader();

		// HashMap object to store match id and corresponding year
		HashMap<String, String> mid_year = BestEconomyBowlers.read(br);

		// Creating a Scanner class object to accept input
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the number of ipl seasons");

		// int variable to store number of ipl seasons to consider
		int seasons = scan.nextInt();

		// Array of YearWiseBowlerStats objects
		YearWiseBowlerStats[] threads_array = new YearWiseBowlerStats[seasons];

		// Declaring YearWiseBowlerStats variable
		YearWiseBowlerStats t;

		// Loop to accept year of ipl season
		for (int i = 0; i < seasons; i++) {

			// Instantiating BestEconomyBowlers class
			BestEconomyBowlers beb = new BestEconomyBowlers();

			System.out.println("Enter the year");
			String year = scan.next();

			// Instantiating thread for the year entered and assinging respective values to
			// its instance variables
			t = new YearWiseBowlerStats(year, beb);
			t.setName("thread-" + year);
			t.bestbowlers = beb;
			t.mid_year = mid_year;
			t.mid_bowler = (BestEconomyBowlers.bowlerEconomy(br, mid_year));
			t.bowlers = BestEconomyBowlers.bowlers;

			// Inserting thread object in the array
			threads_array[i] = t;

		}

		System.out.println();
		System.out.println(Data );
		
		// Loop to start the threads inside array
		for (int i = 0; i < threads_array.length; i++) {
			threads_array[i].start();

		}

	}

}
