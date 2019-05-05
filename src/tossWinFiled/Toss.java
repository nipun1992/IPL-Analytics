package tossWinFiled;

/*
Top 4 teams which elected to field first after winning toss in the year 2016 and 2017.  

Output Expected: YEAR TEAM COUNT */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Toss {

	public static void print() throws IOException {

		// Fetching the username of the user account
		String username = System.getProperty("user.name");

		/*
		 * Creating File object for matches.csv file. Pass the path of the file as
		 * argument
		 */

		// Passed path of the file as Argument. reset the path if FileNotFound Exception
		// occurs
		File file_matches = new File(
				"C:\\Users\\" + username + "\\eclipse-workspace\\Coding Problem IPL\\files\\matches.csv");

		// Buffered Reader objects for respective files
		BufferedReader matches_reader;

		// Pattern object for the files
		Pattern p = Pattern.compile(",");

		String line, tm;

		// String array to store the components of each record of the file
		String[] s_filerecords;

		// For counting number of times team winning the toss opted to field
		int toss_wins = 0;

		// HashMap object to store match year and toss counts corresponding to each team
		// for that year
		HashMap<String, HashMap> year_team_toss = new HashMap<String, HashMap>();

		// HashMap object to store team and toss counts for a year
		HashMap<String, Integer> team_toss = new HashMap<String, Integer>();

		String yr, yr1;

		int ipl_editions = 2;

		// ArrayList object to store toss winning teams for a year
		ArrayList<String> teams = new ArrayList<String>();

		// ArrayList object to store years that have been calculated with
		ArrayList<String> yrs = new ArrayList<String>();

		matches_reader = new BufferedReader(new FileReader(file_matches));
		line = matches_reader.readLine();
		line = matches_reader.readLine();
		s_filerecords = p.split(line);
		yr = s_filerecords[1];
		tm = s_filerecords[6];

		yr1 = yr;

		try {
			outer: while (year_team_toss.size() < 2) {

				if (teams.size() > 0 || year_team_toss.size() > 0) {

					matches_reader = new BufferedReader(new FileReader(file_matches));
					line = matches_reader.readLine();
					line = matches_reader.readLine();
					s_filerecords = p.split(line);
					yr1 = s_filerecords[1];
					tm = s_filerecords[6];
				}

				while (yrs.contains(yr1)) {

					line = matches_reader.readLine();
					s_filerecords = p.split(line);
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

						line = matches_reader.readLine();

						s_filerecords = p.split(line);

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

									line = matches_reader.readLine();

									s_filerecords = p.split(line);

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

									line = matches_reader.readLine();

									s_filerecords = p.split(line);

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

					line = matches_reader.readLine();

					if (line != null) {
						s_filerecords = p.split(line);
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

		System.out
				.println("Count of teams which elected to field first after winning toss in the year 2017 and 2016. ");

		System.out.println();
		System.out.println();

		System.out.println("Year" + "\t" + "Teams and corresponding toss counts for opting to field");

		System.out.println();

		System.out.println("2017 " + "\t" + year_team_toss.get("2017"));

		System.out.println();

		System.out.println("2016 " + "\t" + year_team_toss.get("2016"));

		team_toss = null;

		teams = null;

		yrs = null;

		p = null;

		matches_reader.close();

	}

}
