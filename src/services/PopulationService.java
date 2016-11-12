package services;

import java.util.ArrayList;
import java.util.List;

public class PopulationService {
	private List<List<Integer>> population = new ArrayList<>();

	public List<Integer> generateKey() {
		List<Integer> candidateKey = new ArrayList<>();

		int quota = 0;
		while (quota != 26) {
			int positionShift = randInt(0, 25);
			if (candidateKey.contains(positionShift) == false) {
				candidateKey.add(positionShift);
				quota++;
			}
		}
		return candidateKey;
	}

	public void generateFirstPopulation() {
		int quota = 0;
		while (quota != 100) {
			List<Integer> someKey = generateKey();
			population.add(someKey);
			quota++;
		}
		// checkin if generation is kewl
		for (int i = 0; i < population.size(); i++) {
			for (int j = 0; j < population.get(i).size(); j++) {
				System.out.print(population.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}

	public void passToNextGeneration(List<List<Integer>> population) {
		this.population = population;
	}

	public List<List<Integer>> getPopulation() {
		return population;
	}

	private int randInt(int min, int max) {
		return min + (int) (Math.random() * (max - min + 1));
	}
}
