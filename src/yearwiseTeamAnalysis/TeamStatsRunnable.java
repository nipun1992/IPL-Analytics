package yearwiseTeamAnalysis;

import java.io.IOException;

public class TeamStatsRunnable implements Runnable {

	String year;

	YearlyTeamData td;

	public TeamStatsRunnable(String year) {
		super();
		this.year = year;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {

			// System.out.println("Runnable " + this.year + " being executed by " +
			// Thread.currentThread().getName());

			td.files(year);

			td.result(year);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}