package threads;

import java.util.ArrayList;
import java.util.HashMap;

import economy.BestEconomyBowlers;

public class YearWiseBowlerStats extends Thread {

	// Declaring a String variable to store value of year
	String year;

	// Declaring a variable for BestEconomyBowlers type
	public BestEconomyBowlers bestbowlers;

	// Declaring a HashMap reference variable to store match id and year
	public HashMap<String, String> mid_year;

	// Declaring a HashMap reference variable to store match id and bowler
	// statistics
	public HashMap<String, HashMap> mid_bowler;

	public ArrayList<String> bowlers;

	// Constructor
	public YearWiseBowlerStats(String year) {
		super();
		this.year = year;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		bestbowlers.yearbowlerStats(mid_year, mid_bowler, year, bowlers);

		// System.out.println(Thread.currentThread().getName());

		// System.out.println((bestbowlers.yearbowlerStats(mid_year, mid_bowler, year,
		// bowlers)).size());
		// System.out.println(year + "\t" + (bestbowlers.yearbowlerStats(mid_year,
		// mid_bowler, year, bowlers)));
	}
}
