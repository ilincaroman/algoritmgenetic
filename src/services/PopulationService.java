package services;

import java.util.ArrayList;
import java.util.List;

import models.Candidate;

public class PopulationService {
	public static final int POPULATION_SIZE = 100;
	public List<Candidate> population = new ArrayList<>();

	public List<Integer> generateKey() {
		List<Integer> candidateKey = new ArrayList<>();
		List<Integer> domain = new ArrayList<>();

		for (int i = 0; i < 26; i++) {
			domain.add(i);
		}

		while (domain.size() > 0) {
			int position = randInt(0, domain.size() - 1);
			candidateKey.add(domain.get(position));
			domain.remove(position);
		}

		return candidateKey;
	}

	public void generateFirstPopulation() {
		int quota = 0;
		while (quota < POPULATION_SIZE) {
			List<Integer> someKey = generateKey();
			Candidate subject = new Candidate(someKey);
			population.add(subject);
			quota++;
		}
	}

	public void passToNextGeneration(List<Candidate> population) {
		this.population = population;
	}

	public List<Candidate> getPopulation() {
		return population;
	}

	private int randInt(int min, int max) {
		return min + (int) (Math.random() * (max - min + 1));
	}
}
