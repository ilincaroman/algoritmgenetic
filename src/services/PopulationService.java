package services;

import java.util.List;

public class PopulationService {
	private List<List<Integer>> population;

	public void generateFirstPopulation() {

	}

	public void passToNextGeneration(List<List<Integer>> population) {
		this.population = population;
	}

	public List<List<Integer>> getPopulation() {
		return population;
	}
}
