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

		try {

			td.files(year);

			td.result(year);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
