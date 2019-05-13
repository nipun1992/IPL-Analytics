package yearwiseTeamAnalysis;

import java.io.IOException;

public class YearlyTeamAnalysisThread extends Thread {

	String year;

	YearlyTeamData td;

	public YearlyTeamAnalysisThread(String year) {
		super();
		this.year = year;
	}

	public void run() {
		// TODO Auto-generated method stub

		try {

			// System.out.println(Thread.currentThread().getName());
			td.files(year);

			td.result(year);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * td.result(year); td.write(year);
		 */

	}

}
