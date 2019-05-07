package economy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class BestEconomyBowlers {

	// Method to get BufferedReaders for each file
	public static BufferedReader[] reader() throws FileNotFoundException {

		BufferedReader[] br = new BufferedReader[2];

		// Buffered Reader objects for respective files
		BufferedReader file_matches = new BufferedReader(
				new FileReader("C:\\Users\\NipunGupta\\eclipse-workspace\\Coding Problem IPL\\files\\matches.csv"));
		BufferedReader file_delivieries = new BufferedReader(
				new FileReader("C:\\Users\\NipunGupta\\eclipse-workspace\\Coding Problem IPL\\files\\deliveries.csv"));

		br[0] = file_matches;
		br[1] = file_delivieries;

		return br;

	}

	// Method to get HashMap of match id and year
	public static HashMap<String, String> read(BufferedReader[] br) throws IOException {

		// Pattern object for the files
		Pattern p = Pattern.compile(",");

		// Reading the first line of matches.csv
		String line = br[0].readLine();

		// String array to store the components of each record of the file
		String[] s_filerecords;

		// HashMap object to store match id and year
		HashMap<String, String> mid_year = new HashMap<String, String>();

		while (line != null) {
			line = br[0].readLine();

			try {
				s_filerecords = p.split(line);

				// Inserting match id and year
				mid_year.put(s_filerecords[0], s_filerecords[1]);

			} catch (NullPointerException e) {
			}

		}

		return mid_year;

	}

	public static HashMap<String, HashMap> bowlerEconomy(BufferedReader[] br, HashMap<String, String> mid_year)
			throws IOException {

		// Created a new BufferedReader object
		br[1] = new BufferedReader(
				new FileReader("C:\\Users\\NipunGupta\\eclipse-workspace\\Coding Problem IPL\\files\\deliveries.csv"));

		// Pattern object for the files
		Pattern p = Pattern.compile(",");

		// Reading the first line of matches.csv
		String line;

		// String array to store the components of each record of the file
		String[] s_filerecords;

		// reading the lines
		line = br[1].readLine();
		line = br[1].readLine();

		// Spliting the present line
		s_filerecords = p.split(line);

		// HashMap object to store runs and total balls bowled by a particular bowler
		// for a particular match id
		ArrayList<Integer> runs_balls = new ArrayList<Integer>();

		// HashMap object to store bowler name and runs_balls HashMap object for a
		// particular bowler for a particular match id
		HashMap<String, ArrayList> bowler_runsBalls = new HashMap<String, ArrayList>();

		// HashMap object to store bowler name and runs_balls HashMap object for a
		// particular bowler for a particular match id
		HashMap<String, HashMap> mid_bowler = new HashMap<String, HashMap>();

		HashMap<String, String> year_bowlerRuns = new HashMap<String, String>();

		// int variables to count runs conceded and balls bowled
		int runs = 0, balls = 0;

		// String variables for match id, ball_number and bowler for each line of the
		// file
		String mid, ball_no, bowler;

		// String variables for setting match id, ball_number and bowler constant for a
		// particular bowler of a match id
		String mid_const, ball_no_const, bowler_const;

		// Assigning the match id, ball number and bowler name to the String variables
		mid = s_filerecords[0];
		ball_no = s_filerecords[5];
		bowler = s_filerecords[7];

		//
		mid_const = mid;
		ball_no_const = ball_no;
		bowler_const = bowler;

		// While loop will until the reader completes reading the file
		while (line != null) {

			// checking if the previous match id is same as current match id
			if (mid_const.equals(mid)) {

				// Checking if the previous bowler is same as the current bowler
				if (bowler_const.equals(bowler)) {

					try {

						// Adding the runs conceded by the current bowler
						runs = runs + Integer.parseInt(s_filerecords[8]) + Integer.parseInt(s_filerecords[11])
								+ Integer.parseInt(s_filerecords[12]) + Integer.parseInt(s_filerecords[13]);

						// Adding the legal deliveries bowled by the bowler
						if (Integer.parseInt(ball_no) <= 6) {
							balls = balls + 1;
						}

					} catch (NullPointerException e) {
					}
				} else {

					// If the bowler considered the latest has already been account in the current
					// year
					if (bowler_runsBalls.containsKey(bowler_const)) {

						runs = runs + Integer.parseInt(((bowler_runsBalls.get(bowler_const)).get(0)).toString());

						balls = balls + Integer.parseInt(((bowler_runsBalls.get(bowler_const)).get(1)).toString());

					}

					// Storing the runs and balls in the ArrayList
					runs_balls.add(runs);
					runs_balls.add(balls);

					// Inserting the bowler name and ArrayList into the HashMap
					bowler_runsBalls.put(bowler_const, runs_balls);

					// Resetting the balls and runs to 0
					runs = 0;
					balls = 0;

					// Creating a new ArrayList
					runs_balls = new ArrayList<Integer>();

					ball_no_const = ball_no;
					bowler_const = bowler;

					continue;

				}
			} else {

				if (bowler_runsBalls.containsKey(bowler_const)) {

					runs = runs + Integer.parseInt(((bowler_runsBalls.get(bowler_const)).get(0)).toString());

					balls = balls + Integer.parseInt(((bowler_runsBalls.get(bowler_const)).get(1)).toString());

				}

				// Storing the runs and balls in the ArrayList
				runs_balls.add(runs);
				runs_balls.add(balls);

				bowler_runsBalls.put(bowler_const, runs_balls);

				mid_bowler.put(mid_const, bowler_runsBalls);

				bowler_runsBalls = new HashMap<String, ArrayList>();

				runs = 0;
				balls = 0;

				runs_balls = new ArrayList<Integer>();

				mid_const = mid;
				ball_no_const = ball_no;
				bowler_const = bowler;

				continue;

			}

			line = br[1].readLine();

			if (line != null) {
				s_filerecords = p.split(line);
			} else {

				if (bowler_runsBalls.containsKey(bowler_const)) {

					runs = runs + Integer.parseInt(((bowler_runsBalls.get(bowler_const)).get(0)).toString());

					balls = balls + Integer.parseInt(((bowler_runsBalls.get(bowler_const)).get(1)).toString());

				}

				// Storing the runs and balls in the ArrayList
				runs_balls.add(runs);
				runs_balls.add(balls);

				bowler_runsBalls.put(bowler_const, runs_balls);

				mid_bowler.put(mid_const, bowler_runsBalls);

				break;
			}
			mid = s_filerecords[0];
			ball_no = s_filerecords[5];
			bowler = s_filerecords[7];

		}

		return mid_bowler;

	}

	public HashMap<String, ArrayList> yearbowlerStats(HashMap<String, String> mid_year,
			HashMap<String, HashMap> mid_bowler, String year) {

		ArrayList<String> players = new ArrayList<String>();

		
		
		HashMap<String, ArrayList> bowlers_stats = new HashMap<String, ArrayList>();

		int count = 0;
		// for (int i = 0; i < mid_year.size(); i++) {
		while (count < mid_year.size()) {
			if ((mid_year.get(count + 1 + "")).equals(year)) {

				
				
				bowlers_stats.putAll(mid_bowler.get(count + 1 + ""));

			}
			count++;
		}

		return bowlers_stats;

	}

}