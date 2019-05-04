package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import tossWinFiled.Toss;
import yearwiseTeamAnalysis.ReadFiles;
import yearwiseTeamAnalysis.WriteData;

public class Client {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		/*
		 * Answer 1 : Gathered data for year 2017 and 2016
		 */
		 //Client.tossField();

		/*
		 * Answer 2 : Partially Solved. Gathered data completely for the year 2017,
		 * gathered data partially for years 2008, 2011 and 2016
		 */
		Client.yearwiseTeamData();
	}

	// Method for Answer 1
	public static void tossField() throws IOException {

		Toss.print();

	}

	// Method for Answer 2
	public static void yearwiseTeamData() throws IOException {
		ArrayList obj = new ArrayList();

		obj = ReadFiles.files();

		WriteData.write((ArrayList) obj.get(0), (HashMap) obj.get(1), (HashMap) obj.get(2), (HashMap) obj.get(3),
				(ArrayList) obj.get(4), (HashMap) obj.get(5));

	}

}
