package services;

import java.util.List;

import models.Candidate;

public class GeneticAlgorithm {
	public void startAlgorithm() {
		EncryptionService ES = new EncryptionService();
		ES.generateDictionary();
		ES.extractSentence();
		ES.generateKey();
		String toEncrypt = ES.extractSentence();
		System.out.println(toEncrypt);
		String Encrypted = ES.encryptSentence(toEncrypt);
		System.out.println(Encrypted);

		PopulationService PS = new PopulationService();
		PS.generateFirstPopulation();

		FitnessService FS = new FitnessService();
		for (int i = 0; i < PS.getPopulation().size(); i++) {
			FS.evaluateFitness(ES.dictionary, Encrypted, PS.getPopulation().get(i));
		}

		FS.sortPopulationByFitness(PS.population);
		FS.normalizeFit(PS.population);

		for (Candidate c : PS.population) {
			System.out.print(
					"Fitness: " + c.getFitness() + " normalized to: " + c.getNormalizedFitness() + " for subject ");
			for (Integer i : c.getKey()) {
				System.out.print(i + " ");
			}
			System.out.println();
		}

		SelectionService SS = new SelectionService();
		List<Candidate> newPop = SS.fortuneWheel(PS.population);
		FS.sortPopulationByFitness(newPop);
		System.out.println();

		for (Candidate c : newPop) {
			System.out.print(
					"Fitness: " + c.getFitness() + " normalized to: " + c.getNormalizedFitness() + " for subject ");
			for (Integer i : c.getKey()) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
