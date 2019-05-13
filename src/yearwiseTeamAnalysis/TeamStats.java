package yearwiseTeamAnalysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TeamStats {
	// Method for Answer 2
	public static void yearwiseTeamData() throws IOException {
		ArrayList obj = new ArrayList();

		YearlyTeamData.reader();

		YearlyTeamData.read();

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the number of ipl seasons for which analysis is required");

		int seasons = scan.nextInt();

		String year;

		YearlyTeamAnalysisThread[] threads = new YearlyTeamAnalysisThread[seasons];

		for (int i = 0; i < threads.length; i++) {

			YearlyTeamData ytd = new YearlyTeamData();

			System.out.println("Enter the year");

			year = scan.next();

			YearlyTeamAnalysisThread t = new YearlyTeamAnalysisThread(year);

			t.td = ytd;

			t.setName("Thread-" + year);

			threads[i] = t;

		}

		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}

	}
}
