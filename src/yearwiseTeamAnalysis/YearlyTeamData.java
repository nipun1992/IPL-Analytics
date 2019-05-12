package yearwiseTeamAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Pattern;

public class YearlyTeamData {
	// Declaring array of BufferedReader objects
	public static BufferedReader[] br;

	// Declaring HashMap of match ids and their corresponding year
	public static HashMap<String, String> mid_year;

	public static File file_deliveries;
	public static File file_matches;

	// Pattern object for the files
	public static Pattern p = Pattern.compile(",");

	public static ArrayList<String> teams;

	public static ArrayList<String> years;

	// Creating HashMaps to store match id and team_fours HashMap
	public static HashMap<Integer, HashMap> id_teamfours;

	// Creating HashMaps to store match id and team_six HashMap
	public static HashMap<Integer, HashMap> id_teamsix;

	// Creating HashMaps to store match id and team_score HashMap
	public static HashMap<Integer, HashMap> id_teamscore;

	// Method to get BufferedReaders for each file
	public static void reader() throws FileNotFoundException {

		br = new BufferedReader[2];

		/*
		 * File objects for files. Passed path of the file as Argument. Reset the path
		 * if FileNotFound Exception occurs
		 */
		file_deliveries = new File(
				"C:\\Users\\NipunGupta\\eclipse-workspace\\Coding Problem IPL\\files\\deliveries.csv");
		file_matches = new File("C:\\Users\\NipunGupta\\eclipse-workspace\\Coding Problem IPL\\files\\matches.csv");

		// Buffered Reader objects for respective files
		BufferedReader reader_matches = new BufferedReader(new FileReader(file_matches));
		BufferedReader reader_delivieries = new BufferedReader(new FileReader(file_deliveries));

		br[0] = reader_matches;
		br[1] = reader_delivieries;

	}

	// Method to get HashMap of match id and year
	public static void read() throws IOException {

		// Reading the first line of matches.csv
		String line = br[0].readLine();

		// String array to store the components of each record of the file
		String[] s_filerecords;

		// HashMap object to store match id and year
		mid_year = new HashMap<String, String>();

		while (line != null) {
			line = br[0].readLine();

			try {
				s_filerecords = p.split(line);

				// Inserting match id and year
				mid_year.put(s_filerecords[0], s_filerecords[1]);

			} catch (NullPointerException e) {
			}

		}

	}

	// Method to fetch years and names of teams
	public static void yearAndTeams() throws IOException {

		// br[1] = new BufferedReader(new FileReader(file_deliveries));
		br[0] = new BufferedReader(new FileReader(file_matches));

		// Reading the first line of matches.csv
		String line = br[0].readLine();

		// String array to store the components of each record of the file
		String[] s_filerecords;

		// HashMap object to store match id and team batting first
		HashMap<String, String> mid_team1 = new HashMap<String, String>();

		while (line != null) {
			line = br[0].readLine();

			try {
				s_filerecords = p.split(line);

				// Inserting match id and batting team
				mid_team1.put(s_filerecords[0], s_filerecords[4]);

			} catch (NullPointerException e) {
			}

		}

		// Collecting all the years
		Collection<String> years_set = mid_year.values();

		years = new ArrayList<String>();

		// Storing the years in the array
		for (String s : years_set) {
			if (!years.contains(s)) {
				years.add(s);
			}

		}

		// Sorting the array Arrays.sort(years);
		Collections.sort(years);

		// Creating Collection object to store names of teams
		Collection<String> teams_set = mid_team1.values();

		// Creating HashSet object to store names of teams
		teams = new ArrayList<String>();

		// Adding teams to the HashSet
		for (String s : teams_set) {
			if (!teams.contains(s))
				teams.add(s);
		}

		years_set = null;
		mid_team1 = null;

	}

	// Method to return objects containing data params about the matches
	public static void files() throws IOException {

		// Creating HashMaps to store names and number of fours
		HashMap<String, Integer> team_fours = new HashMap<String, Integer>();

		// Creating HashMaps to store names and number of sixes
		HashMap<String, Integer> team_six = new HashMap<String, Integer>();

		// Creating HashMaps to store names and runs
		HashMap<String, Integer> team_score = new HashMap<String, Integer>();

		// Creating HashMaps to store match id and team_fours HashMap
		id_teamfours = new HashMap<Integer, HashMap>();

		// Creating HashMaps to store match id and team_six HashMap
		id_teamsix = new HashMap<Integer, HashMap>();

		// Creating HashMaps to store match id and team_score HashMap
		id_teamscore = new HashMap<Integer, HashMap>();

		br[1] = new BufferedReader(new FileReader(file_deliveries));

		String line = br[1].readLine();
		line = br[1].readLine();

		int fours = 0, six = 0, runs = 0;

		String team = new String();

		String s_filerecords[];

		s_filerecords = p.split(line);

		String mid = s_filerecords[0];
		team = s_filerecords[2];

		while (line != null) {

			try {

				if ((mid.equals(s_filerecords[0]))) {

					if ((team.equals(s_filerecords[2]))) {

						runs = runs + Integer.parseInt(s_filerecords[15]);

						if (Integer.parseInt(s_filerecords[15]) == 4) {
							fours = fours + 1;
						} else if (Integer.parseInt(s_filerecords[15]) == 6) {
							six = six + 1;
						}

						if (team_fours.size() <= 2) {
							team_fours.put(team, fours);
							team_six.put(team, six);
							team_score.put(team, runs);
						}
					} else {

						id_teamfours.put(Integer.parseInt(mid), team_fours);
						id_teamsix.put(Integer.parseInt(mid), team_six);
						id_teamscore.put(Integer.parseInt(mid), team_score);

						team = s_filerecords[2];
						runs = 0;
						fours = 0;
						six = 0;

						runs = runs + Integer.parseInt(s_filerecords[15]);

						if (Integer.parseInt(s_filerecords[15]) == 4) {
							fours = fours + 1;
						} else if (Integer.parseInt(s_filerecords[15]) == 6) {
							six = six + 1;
						}

						if (team_fours.size() <= 2) {
							team_fours.put(team, fours);
							team_six.put(team, six);
							team_score.put(team, runs);
						}
					}
				} else {

					mid = s_filerecords[0];
					team = s_filerecords[2];
					runs = 0;
					fours = 0;
					six = 0;

					team_fours.put(team, fours);
					team_six.put(team, six);
					team_score.put(team, runs);

					id_teamfours.put(Integer.parseInt(mid), team_fours);
					id_teamsix.put(Integer.parseInt(mid), team_six);
					id_teamscore.put(Integer.parseInt(mid), team_score);

					team_fours = new HashMap<String, Integer>();
					team_six = new HashMap<String, Integer>();
					team_score = new HashMap<String, Integer>();

				}
				line = br[1].readLine();
				s_filerecords = p.split(line);

			} catch (NullPointerException e) {

			}

		}

		team_fours = null;
		team_six = null;
		team_score = null;

		mid = null;
		team = null;
		line = null;

		br[1].close();
		br[0].close();

		file_deliveries = null;
		file_matches = null;

	}

	public static void result() {

		String year = "2017";

		HashMap<String, Integer> team_four, team_six, team_score;

		ArrayList<String> team = new ArrayList<String>();
		ArrayList<Integer> four = new ArrayList<Integer>();
		ArrayList<Integer> sixes = new ArrayList<Integer>();
		ArrayList<Integer> scores = new ArrayList<Integer>();

		int fours, six, score, index;

		for (int i = 0; i < mid_year.size(); i++) {
			if ((mid_year.get(((i + 1) + ""))).equals(year)) {
				team_four = id_teamfours.get((i + 1));
				team_six = id_teamsix.get((i + 1));
				team_score = id_teamscore.get((i + 1));

				/*
				 * System.out.println(team_four); System.out.println(team_score);
				 * System.out.println(team_six);
				 */

				for (int j = 0; j < teams.size(); j++) {
					if (team_score.containsKey(teams.get(j))) {

						if (!team.contains(teams.get(j))) {
							fours = team_four.get(teams.get(j));
							six = team_six.get(teams.get(j));
							score = team_score.get(teams.get(j));

							team.add(teams.get(j));
							four.add(fours);
							sixes.add(six);
							scores.add(score);

						} else {

							index = team.indexOf(teams.get(j));

							fours = team_four.get(teams.get(j));
							six = team_six.get(teams.get(j));
							score = team_score.get(teams.get(j));

							fours = fours + four.get(index);
							six = six + sixes.get(index);
							score = score + scores.get(index);

							four.remove(index);
							four.add(index, fours);

							sixes.remove(index);
							sixes.add(index, six);

							scores.remove(index);
							scores.add(index, score);

						}

					}
				}

			}
		}

		for (int i = 0; i < team.size(); i++) {

			System.out.println(team.get(i) + "\t" + four.get(i) + "\t" + sixes.get(i) + "\t" + scores.get(i));
		}
	}
}
