package yearwiseTeamAnalysis;

import java.io.IOException;
import java.util.ArrayList;

public class TeamStats {
	// Method for Answer 2
	public static void yearwiseTeamData() throws IOException {
		ArrayList obj = new ArrayList();

		YearlyTeamData.reader();

		YearlyTeamData.read();

		YearlyTeamData.yearAndTeams();

		YearlyTeamData.files();

		YearlyTeamData.result();

	}
}
