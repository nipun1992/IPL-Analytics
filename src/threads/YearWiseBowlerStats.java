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
	public YearWiseBowlerStats(String year, BestEconomyBowlers bestbowlers) {
		super();
		this.year = year;
		this.bestbowlers = bestbowlers;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		HashMap<String, ArrayList> bowlers_stats = bestbowlers.yearbowlerStats(mid_year, mid_bowler, year, bowlers);

		System.out.println(Thread.currentThread().getName() + " " + bowlers_stats);
		
	}
}
