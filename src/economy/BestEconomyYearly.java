package economy;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import threads.YearWiseBowlerStats;

public class BestEconomyYearly {
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
		System.out.println();

		// Loop to start the threads inside array
		for (int i = 0; i < threads_array.length; i++) {
			threads_array[i].start();

		}

		scan.close();

	}

}
