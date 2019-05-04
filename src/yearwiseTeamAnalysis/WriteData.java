package yearwiseTeamAnalysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class WriteData {

	public static void write(ArrayList<String> team, HashMap<Integer, HashMap> hm_fours,
			HashMap<Integer, HashMap> hm_six, HashMap<Integer, HashMap> hm_score, ArrayList years,
			HashMap<String, String> mid_year) throws IOException {

		//System.out.println(mid_year.size());

		// File objects for files
		File file_deliveries = new File(
				"C:\\Users\\NipunGupta\\eclipse-workspace\\Coding Problem IPL\\files\\deliveries.csv");
		File file_matches = new File(
				"C:\\Users\\NipunGupta\\eclipse-workspace\\Coding Problem IPL\\files\\matches.csv");

		// Buffered Reader objects for respective files

		// Pattern object for the files
		Pattern p = Pattern.compile(",");

		// Reading the first line of matches.csv
		String line, line2, line3;

		// String array to store the components of each record of the file
		String[] s_filerecords;

		int id = 0;

		ArrayList<ArrayList> arr = new ArrayList<ArrayList>();
		ArrayList<String> arr1 = new ArrayList<String>();

		int fours = 0, six = 0, score = 0;

		String yr, tm, mid;

		//ArrayList to store years where the data of all teams has been considered
		ArrayList<String> yrs = new ArrayList<String>();
		
		//ArrayList to store 8 teams of a particular year
		ArrayList<String> tms = new ArrayList<String>();

		HashMap<Integer, ArrayList> year_data = new HashMap<Integer, ArrayList>();

		int count = 1;

		boolean present = false;

		BufferedReader matches_reader = new BufferedReader(new FileReader(file_matches));

		line = matches_reader.readLine();

		line = matches_reader.readLine();

		s_filerecords = p.split(line);

		mid = s_filerecords[0];
		yr = s_filerecords[1];
		tm = s_filerecords[4];//Single team at a time

		// System.out.println(
		// "YEAR" + "\t" + "TEAM_NAME" + "\t" + "FOURS_COUNT" + "\t" + "SIXES_COUNT" +
		// "\t" + "TOTAL_SCORE");

		outer: while (year_data.size() <= years.size()) {

			// System.out.println("year_data size " + year_data.size());

			try {

				if (present == false || count == 1) {

					matches_reader = new BufferedReader(new FileReader(file_matches));

					line = matches_reader.readLine();

					line = matches_reader.readLine();

					line2 = line;

					s_filerecords = p.split(line);

					mid = s_filerecords[0];
					yr = s_filerecords[1];
					tm = s_filerecords[4];

				}

				if (year_data.containsKey(Integer.parseInt(yr)) & tms.size() <= 7) {

					// System.out.println("Great");

					if (!((year_data.get(Integer.parseInt(yr))).contains(tm))) {

						present = false;
						count = 1;

						while (line != null) {

							if (count < 635) {

								if ((hm_fours.get(Integer.parseInt(mid)).containsKey(tm)
										&& ((int) hm_fours.get(Integer.parseInt(mid)).get(tm) != 0))
										&& ((mid_year.get(mid)).equals(yr))) {

									//From here
									
									if (!(!(yr.equals(s_filerecords[1]))
											|| (!(tm.equals(s_filerecords[4]) || (tm.equals(s_filerecords[5])))))) {

										fours = fours + Integer
												.parseInt((hm_fours.get((Integer.parseInt(mid))).get(tm)).toString());
										six = six + Integer
												.parseInt((hm_six.get((Integer.parseInt(mid))).get(tm)).toString());
										score = score + Integer
												.parseInt((hm_score.get((Integer.parseInt(mid))).get(tm)).toString());

									}
								}
								count++;

								line = matches_reader.readLine();
								if (line != null) {
									s_filerecords = p.split(line);

									mid = s_filerecords[0];
								} else {
									break;
								}

							} else {
								break;
							}

						}

						arr1.add(tm);
						arr1.add((Integer.valueOf(fours)).toString());
						arr1.add((Integer.valueOf(six)).toString());
						arr1.add((Integer.valueOf(score)).toString());

						System.out.println(yr + "\t" + tm + "\t" + fours + "\t" + six + "\t" + score);

						year_data.put(Integer.parseInt(yr), arr1);

						tms.add(tm);
						yrs.add(yr);

						yr = null;
						tm = null;
						fours = 0;
						six = 0;
						score = 0;

						//present = false;

						continue outer;

					} else {

						tm = s_filerecords[5];

						if (!(year_data.get(Integer.parseInt(yr)).contains(tm))) {

							present = false;

							count = 1;

							while (line != null) {

								if (count < 635) {

									if ((hm_fours.get(Integer.parseInt(mid)).containsKey(tm)
											&& ((int) hm_fours.get(Integer.parseInt(mid)).get(tm) != 0))
											&& ((mid_year.get(mid)).equals(yr))) {

										if (!(!(yr.equals(s_filerecords[1]))
												|| (!(tm.equals(s_filerecords[4]) || (tm.equals(s_filerecords[5])))))) {

											fours = fours + Integer.parseInt(
													(hm_fours.get((Integer.parseInt(mid))).get(tm)).toString());
											six = six + Integer
													.parseInt((hm_six.get((Integer.parseInt(mid))).get(tm)).toString());
											score = score + Integer.parseInt(
													(hm_score.get((Integer.parseInt(mid))).get(tm)).toString());

										}
									}
									count++;
									line = matches_reader.readLine();

									if (line != null) {
										s_filerecords = p.split(line);

										mid = s_filerecords[0];
									} else {
										break;
									}

								} else {
									break;
								}

							}

							arr1.add(tm);
							arr1.add((Integer.valueOf(fours)).toString());
							arr1.add((Integer.valueOf(six)).toString());
							arr1.add((Integer.valueOf(score)).toString());

							System.out.println(yr + "\t" + tm + "\t" + fours + "\t" + six + "\t" + score);

							year_data.put(Integer.parseInt(yr), arr1);

							tms.add(tm);
							yrs.add(yr);

							yr = null;
							tm = null;
							fours = 0;
							six = 0;
							score = 0;

							continue outer;// last line

						}

						line = matches_reader.readLine();
						s_filerecords = p.split(line);
						mid = s_filerecords[0];
						yr = s_filerecords[1];
						tm = s_filerecords[4];
						present = true;

						continue outer;

					}

				} else {

					if (tms.size() > 7) {
						tms = new ArrayList();
					}

					if (year_data.containsKey(Integer.parseInt(yr))) {
						year_data.put(Integer.parseInt(yr), arr1);

						/*
						 * line = matches_reader.readLine(); s_filerecords = p.split(line); yr =
						 * s_filerecords[1];
						 */

					} else {
						// System.out.println(arr1 + "Hello");
						while (line != null) {

							// Checking if the ArrayList yrs contains the present value of yr
							if (!yrs.contains(yr)) {

								year_data.put(Integer.parseInt(yr), arr1);

								continue outer;

							}

							line = matches_reader.readLine();
							s_filerecords = p.split(line);
							yr = s_filerecords[1];

						}
					}

				}

			} catch (NullPointerException e) {
				// System.out.println("Exception");
				break;
			}

		}

		matches_reader.close();

	}

}
