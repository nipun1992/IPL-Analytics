package threads;

import java.util.HashMap;

import economy.BestEconomyBowlers;

public class YearWiseBowlerStats extends Thread {
	String year;

	public BestEconomyBowlers bestbowlers;
	public HashMap<String, String> mid_year;
	public HashMap<String, HashMap> mid_bowler;

	public YearWiseBowlerStats(String year) {
		super();
		this.year = year;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();

		System.out.println(Thread.currentThread().getName());
		
		System.out.println((bestbowlers.yearbowlerStats(mid_year, mid_bowler, year)).size());
		System.out.println((bestbowlers.yearbowlerStats(mid_year, mid_bowler, year)));
	}
}
