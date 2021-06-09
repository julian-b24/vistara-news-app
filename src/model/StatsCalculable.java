package model;

import java.util.HashMap;

public interface StatsCalculable {

	public HashMap<String, HashMap<String, Double>> generateStats();

	public String reportToString();
}