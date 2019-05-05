package yearwiseTeamAnalysis;

/*
List total number of fours, sixes, total score with respect to team and year.    

Output Expected:     YEAR TEAM_NAME FOURS_COUNT SIXES_COUNT TOTAL_SCORE */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Pattern;

public class ReadFiles {

	// Method to read the files and return objects containing data params about the
	// matches
	public static ArrayList files() throws IOException {

		/*
		 * File objects for files. Passed path of the file as Argument. Reset the path
		 * if FileNotFound Exception occurs
		 */
		File file_deliveries = new File(
				"C:\\Users\\NipunGupta\\eclipse-workspace\\Coding Problem IPL\\files\\deliveries.csv");
		File file_matches = new File(
				"C:\\Users\\NipunGupta\\eclipse-workspace\\Coding Problem IPL\\files\\matches.csv");

		// Buffered Reader objects for respective files
		BufferedReader deliveries_reader = new BufferedReader(new FileReader(file_deliveries));
		BufferedReader matches_reader = new BufferedReader(new FileReader(file_matches));

		// Pattern object for the files
		Pattern p = Pattern.compile(",");

		// Reading the first line of matches.csv
		String line = matches_reader.readLine();

		// String array to store the components of each record of the file
		String[] s_filerecords;

		// HashMap object to store match id and year
		HashMap<String, String> mid_year = new HashMap<String, String>();

		// HashMap object to store match id and team batting first
		HashMap<String, String> mid_team1 = new HashMap<String, String>();

		while (line != null) {
			line = matches_reader.readLine();

			try {
				s_filerecords = p.split(line);

				// System.out.println(s_filerecords[0] + "\t" + s_filerecords[1]);

				// Inserting match id and year
				mid_year.put(s_filerecords[0], s_filerecords[1]);

				// System.out.println(mid_year);

				// Inserting match id and batting team
				mid_team1.put(s_filerecords[0], s_filerecords[4]);

			} catch (NullPointerException e) {
			}

		}

		// System.out.println(mid_year.size());

		// Collecting all the years
		Collection<String> years_set = mid_year.values();

		int j = 0;

		ArrayList years = new ArrayList();

		// Storing the years in the array
		for (String s : years_set) {
			if (!years.contains(s)) {
				years.add(s);
			}

		}

		years_set = null;

		// Sorting the array Arrays.sort(years);
		Collections.sort(years);

		// Creating Collection object to store names of teams
		Collection<String> teams_set = mid_team1.values();

		// Creating HashSet object to store names of teams
		ArrayList<String> teams = new ArrayList<String>();

		// Adding teams to the HashSet
		for (String s : teams_set) {
			if (!teams.contains(s))
				teams.add(s);
		}

		// Creating HashMaps to store names and number of fours
		HashMap<String, Integer> team_fours = new HashMap<String, Integer>();

		// Creating HashMaps to store names and number of sixes
		HashMap<String, Integer> team_six = new HashMap<String, Integer>();

		// Creating HashMaps to store names and runs
		HashMap<String, Integer> team_score = new HashMap<String, Integer>();

		// Creating HashMaps to store match id and team_fours HashMap
		HashMap<Integer, HashMap> id_teamfours = new HashMap<Integer, HashMap>();

		// Creating HashMaps to store match id and team_six HashMap
		HashMap<Integer, HashMap> id_teamsix = new HashMap<Integer, HashMap>();

		// Creating HashMaps to store match id and team_score HashMap
		HashMap<Integer, HashMap> id_teamscore = new HashMap<Integer, HashMap>();

		// ArrayList to store respective objects
		ArrayList objects = new ArrayList();

		deliveries_reader = new BufferedReader(new FileReader(file_deliveries));
		matches_reader = new BufferedReader(new FileReader(file_matches));

		line = deliveries_reader.readLine();

		int fours = 0, six = 0, runs = 0;

		String team = new String();

		line = deliveries_reader.readLine();

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
				line = deliveries_reader.readLine();
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

		/*
		 * System.out.println(teams); System.out.println(years.size());
		 * System.out.println();
		 */

		objects.add(teams);
		objects.add(id_teamfours);
		objects.add(id_teamsix);
		objects.add(id_teamscore);
		objects.add(years);
		objects.add(mid_year);

		// System.out.println(mid_year.size());

		deliveries_reader.close();
		matches_reader.close();

		file_deliveries = null;
		file_matches = null;

		return objects;

	}

}
