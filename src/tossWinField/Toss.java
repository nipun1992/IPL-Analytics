package tossWinField;

/*
Top 4 teams which elected to field first after winning toss in the year 2016 and 2017. 
 

Output Expected: YEAR TEAM COUNT */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Toss {

	static BufferedReader reader_matches;

	// public static File file_matches;
	public static File file_deliveries, file_matches;

	static HashMap<String, HashMap> year_team_toss;

	// Method to get BufferedReaders for each file
	public static void reader() throws FileNotFoundException {

		/*
		 * File objects for files. Passed path of the file as Argument. Reset the path
		 * if FileNotFound Exception occurs
		 */
		file_matches = new File("C:\\Users\\NipunGupta\\eclipse-workspace\\Coding Problem IPL\\files\\matches.csv");

		// Buffered Reader objects for respective files
		reader_matches = new BufferedReader(new FileReader(file_matches));

	}

	public static void result() throws IOException {

		// Pattern object for the files
		Pattern pattern = Pattern.compile(",");

		String line, tm;

		// String array to store the components of each record of the file
		String[] s_filerecords;

		// For counting number of times team winning the toss opted to field
		int toss_wins = 0;

		// HashMap object to store match year and toss counts corresponding to each team
		// for that year
		year_team_toss = new HashMap<String, HashMap>();

		// HashMap object to store team and toss counts for a year
		HashMap<String, Integer> team_toss = new HashMap<String, Integer>();

		String yr, yr1;

		// ArrayList object to store toss winning teams for a year
		ArrayList<String> teams = new ArrayList<String>();

		// ArrayList object to store years that have been calculated with
		ArrayList<String> yrs = new ArrayList<String>();

		line = reader_matches.readLine();
		line = reader_matches.readLine();
		s_filerecords = pattern.split(line);
		yr = s_filerecords[1];
		tm = s_filerecords[6];

		yr1 = yr;

		try {
			while (year_team_toss.size() < 2) {

				if (teams.size() > 0 || year_team_toss.size() > 0) {

					reader_matches = new BufferedReader(new FileReader(file_matches));
					line = reader_matches.readLine();
					line = reader_matches.readLine();
					s_filerecords = pattern.split(line);
					yr1 = s_filerecords[1];
					tm = s_filerecords[6];
				}

				while (yrs.contains(yr1)) {

					line = reader_matches.readLine();
					s_filerecords = pattern.split(line);
					yr1 = s_filerecords[1];
					tm = s_filerecords[6];

					if (!yrs.contains(yr1)) {

						yr = yr1;
						break;
					}
				}

				if (!teams.contains(tm)) {

					teams.add(tm);
				} else {

					while (teams.contains(tm)) {

						line = reader_matches.readLine();

						s_filerecords = pattern.split(line);

						yr1 = s_filerecords[1];

						tm = s_filerecords[6];

					}
				}

				if (!teams.contains(tm)) {

					teams.add(tm);
				}

				toss_wins = 0;

				yr = yr1;

				inner: while (line != null) {

					if (!yrs.contains(yr)) {

						if (yr.equals("2017")) {

							if (!team_toss.containsKey(tm)) {

								if (tm.equals(s_filerecords[6])) {

									if (s_filerecords[7].equals("field")) {

										toss_wins = toss_wins + 1;
									}
								}

							} else {
								if (team_toss.size() < 8) {

									line = reader_matches.readLine();

									s_filerecords = pattern.split(line);

									yr = s_filerecords[1];
									continue inner;
								} else
									break inner;
							}

						} else if (yr.equals("2016") && year_team_toss.size() > 0) {

							if (!team_toss.containsKey(tm)) {

								if (tm.equals(s_filerecords[6])) {

									if (s_filerecords[7].equals("field")) {

										toss_wins = toss_wins + 1;
									}
								}
							}

							else if (year_team_toss.size() > 0) {
								if (team_toss.size() < 8) {

									line = reader_matches.readLine();

									s_filerecords = pattern.split(line);

									yr = s_filerecords[1];
									continue inner;
								} else
									break inner;
							}

						} else if (!(yr.equals("2017") || yr.equals("2016"))) {

							if (!yrs.contains(yr)) {
								yrs.add(yr);
								yr1 = yr;

							}
						}

					}

					line = reader_matches.readLine();

					if (line != null) {
						s_filerecords = pattern.split(line);
						yr = s_filerecords[1];
					}
				}

				if (toss_wins > 0) {
					team_toss.put(tm, toss_wins);

				}

				if (team_toss.size() % 8 == 0) {

					if (!yrs.contains(yr1)) {
						yrs.add(yr1);
					}

					teams = new ArrayList<String>();

					year_team_toss.put(yr1, team_toss);

					team_toss = new HashMap<String, Integer>();

				}
			}

		} catch (Exception e) {
			System.out.println("Exception");
		}

		/*
		 * System.out
		 * .println("Count of teams which elected to field first after winning toss in the year 2017 and 2016. "
		 * );
		 * 
		 * System.out.println(); System.out.println();
		 * 
		 * System.out.println("Year" + "\t" +
		 * "Teams and corresponding toss counts for opting to field");
		 * 
		 * System.out.println();
		 * 
		 * System.out.println("2017 " + "\t" + year_team_toss.get("2017"));
		 * 
		 * System.out.println();
		 * 
		 * System.out.println("2016 " + "\t" + year_team_toss.get("2016"));
		 * 
		 * team_toss = null;
		 * 
		 * teams = null;
		 * 
		 * yrs = null;
		 * 
		 * pattern = null;
		 * 
		 * reader_matches.close();
		 */
	}

	public static void print() {
		System.out
				.println("Count of teams which elected to field first after winning toss in the year 2017 and 2016. ");

		System.out.println();
		System.out.println();

		System.out.println("Year" + "\t" + "Teams and corresponding toss counts for opting to field");

		System.out.println();

		System.out.println("2017 " + "\t" + year_team_toss.get("2017"));

		System.out.println();

		System.out.println("2016 " + "\t" + year_team_toss.get("2016"));

		try {
			reader_matches.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
