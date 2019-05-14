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

	// public static File file_matches;
	public static File file_deliveries, file_matches;

	// Pattern object for the files
	public static Pattern p = Pattern.compile(",");

	public HashMap<String, Integer> team_score, team_six, team_fours;
	// public HashMap<String, Integer> team_six;
	// public HashMap<String, Integer> team_fours;

	public ArrayList<String> tm;

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

	public void files(String year) throws IOException {

		BufferedReader br_temp = new BufferedReader(new FileReader(file_deliveries));

		String line = br_temp.readLine();

		try {
			line = br_temp.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int fours = 0, six = 0, runs = 0;

		String mid, team, s_filerecords[];

		tm = new ArrayList<String>();

		// Creating HashMaps to store names and number of fours
		team_fours = new HashMap<String, Integer>();

		// Creating HashMaps to store names and number of sixes
		team_six = new HashMap<String, Integer>();

		// Creating HashMaps to store names and runs
		team_score = new HashMap<String, Integer>();

		while (line != null) {

			s_filerecords = p.split(line);
			mid = s_filerecords[0];

			try {
				if ((mid_year.get(mid)).equals(year)) {

					team = s_filerecords[2];

					if (!tm.contains(team)) {

						tm.add(team);

						runs = 0;
						fours = 0;
						six = 0;

						runs = Integer.parseInt(s_filerecords[15]);

						if (s_filerecords[13].equals("4")) {
							fours = 1;
						} else if (s_filerecords[13].equals("6")) {
							six = 1;
						}

						team_score.put(team, runs);

						team_fours.put(team, fours);

						team_six.put(team, six);

					} else {

						if (!(s_filerecords[15].equals("0"))) {
							runs = team_score.get(team) + Integer.parseInt(s_filerecords[15]);
							team_score.put(team, runs);

							if (s_filerecords[13].equals("4")) {
								fours = team_fours.get(team) + 1;
								team_fours.put(team, fours);
							} else if (s_filerecords[13].equals("6")) {
								six = team_six.get(team) + 1;
								team_six.put(team, six);
							}
						}

					}

				}
			} catch (Exception e) {

			}

			line = br_temp.readLine();

		}

	}

	public void result(String year) {

		for (int i = 0; i < tm.size(); i++) {
			System.out.println(year + "\t" + tm.get(i) + "\t" + team_fours.get(tm.get(i)) + "\t"
					+ team_six.get(tm.get(i)) + "\t" + team_score.get(tm.get(i)));
		}
		System.out.println();
		System.out.println();
	}

}
