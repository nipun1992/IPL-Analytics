package economy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class BestEconomyBowlers {

	// Creating an ArrayList to store names of players
	public static ArrayList<String> bowlers;

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

		bowlers = new ArrayList<String>();

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

					// If the bowler considered the latest has already been accounted for the
					// current
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

					if (!bowlers.contains(bowler_const)) {
						bowlers.add(bowler_const);
					}

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

				// If bowler_runsBalls HashMap already consists of the bowler i.e. the bowler
				// has already been considered
				if (bowler_runsBalls.containsKey(bowler_const)) {

					runs = runs + Integer.parseInt(((bowler_runsBalls.get(bowler_const)).get(0)).toString());

					balls = balls + Integer.parseInt(((bowler_runsBalls.get(bowler_const)).get(1)).toString());

				}

				// Storing the runs and balls in the ArrayList
				runs_balls.add(runs);
				runs_balls.add(balls);

				bowler_runsBalls.put(bowler_const, runs_balls);

				if (!bowlers.contains(bowler_const)) {
					bowlers.add(bowler_const);
				}

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

				if (!bowlers.contains(bowler_const)) {
					bowlers.add(bowler_const);
				}

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
			HashMap<String, HashMap> mid_bowler, String year, ArrayList<String> bowlers) {

		// Creating ArrayList to store players that have been considered
		ArrayList<String> players = new ArrayList<String>();

		// Creating HashMap to store bowler names and their statistics
		HashMap<String, ArrayList> bowlers_stats = new HashMap<String, ArrayList>();

		// Creating ArrayList to store runs and balls
		ArrayList<Integer> runs_balls = new ArrayList<Integer>();

		// Creating ArrayList to store the runs and balls for the current match id
		ArrayList<Integer> new_runs_balls = new ArrayList<Integer>();

		int count = 1, runs, balls;

		int total_bowlers = bowlers.size();
		int total_matches = mid_year.size();

		while (count <= total_matches) {
			if ((mid_year.get(count + "")).equals(year)) {

				for (int i = 0; i < total_bowlers; i++) {

					runs = 0;
					balls = 0;

					try {
						if ((mid_bowler.get(count + "")).containsKey(bowlers.get(i + 1))) {

							if (!players.contains(bowlers.get(i + 1))) {
								players.add(bowlers.get(i + 1));
								bowlers_stats.put(bowlers.get(i + 1),
										((ArrayList) ((mid_bowler.get(count + "")).get(bowlers.get(i + 1)))));

							} else {
								runs_balls = bowlers_stats.get(bowlers.get(i + 1));
								runs = runs_balls.get(0);
								balls = runs_balls.get(1);
								new_runs_balls = (ArrayList) (mid_bowler.get(count + "")).get(bowlers.get(i + 1));

								// Adding the runs and balls of the bowler for latest match to the total runs
								// and total balls respectively
								runs = runs + new_runs_balls.get(0);
								balls = balls + new_runs_balls.get(1);

								// Removing the previous values of balls and runs from the ArrayList
								runs_balls.remove(1);
								runs_balls.remove(0);

								// Inserting the latest value of runs and balls of the current bowler
								runs_balls.add(runs);
								runs_balls.add(balls);

								// Inserting the current bowler and his statistics into the HashMap
								bowlers_stats.put(bowlers.get(i + 1), runs_balls);

								// Resetting runs and balls to 0
								runs = 0;
								balls = 0;
							}
						}
					} catch (Exception e) {
						// TODO: handle exception
					}

				}

			}
			// Incrementing count variable
			count++;
		}

		return bowlers_stats;

	}

}