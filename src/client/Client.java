package client;

import java.io.IOException;

import economy.BestEconomyYearly;
import tossWinFiled.Toss;
import yearwiseTeamAnalysis.TeamStats;

public class Client {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// long startTime = System.nanoTime();

		/*
		 * Note : The questions are mentioned in the location Coding Problem
		 * IPL/files/CODING PROBLEM JVD201901.pdf
		 */

		/*
		 * Answer 1 : Gathered data for year 2017 and 2016
		 */
		// Client.tossField();

		// Answer 2 : In progress

		TeamStats.yearwiseTeamData();

		/*
		 * Answer 3 : Gathered economy for all bowlers in each year
		 */
		// BestEconomyYearly.bestEconomy();

		/*
		 * long endTime = System.nanoTime();
		 * 
		 * System.out.println("Took " + (endTime - startTime) + " ns");
		 */
	}

	// Method for Answer 1
	public static void tossField() throws IOException {

		Toss.print();

	}

	/*
	 * // Method for Answer 2 public static void yearwiseTeamData() throws
	 * IOException { ArrayList obj = new ArrayList();
	 * 
	 * ReadFiles.reader();
	 * 
	 * ReadFiles.read();
	 * 
	 * ReadFiles.yearAndTeams();
	 * 
	 * ReadFiles.files();
	 * 
	 * ReadFiles.result();
	 * 
	 * }
	 */

}
