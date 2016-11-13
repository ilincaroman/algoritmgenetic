package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Candidate;

public class GeneticAlgorithm {

	private static final int GENERATION_NUMBEr = 5000;
	private static final int CANDIDATES_TO_CROSSOVER = 70;
	private static final int ELITISM_NUMBER = 3;

	private static final EncryptionService encryptionService = new EncryptionService();
	private static final SelectionService selectionService = new SelectionService();
	private static final PopulationService populationService = new PopulationService();
	private static final FitnessService fitnessService = new FitnessService();
	private static final GeneticOperatorService geneticOperatorService = new GeneticOperatorService();

	public void startAlgorithm() {

		int generationNumber = 0;

		encryptionService.generateDictionary();
		encryptionService.extractSentence();
		encryptionService.generateKey();
		String toEncrypt = encryptionService.extractSentence();
		System.out.println(toEncrypt);
		String Encrypted = encryptionService.encryptSentence(toEncrypt);
		System.out.println(Encrypted);

		populationService.generateFirstPopulation();

		List<Candidate> currentPopulation = populationService.population;

		for (Candidate c : currentPopulation) {
			fitnessService.evaluateFitness(encryptionService.dictionary, Encrypted, c);
		}

		while (generationNumber < GENERATION_NUMBEr) {
			System.out.println("Generation number: " + generationNumber);

			fitnessService.normalizeFit(currentPopulation);

			currentPopulation = selectionService.makeSelectionForPopulation(currentPopulation);
			fitnessService.sortPopulationByFitness(currentPopulation);

			// printPopulation(currentPopulation);

			// selectam primii dupa fitness pentru elitism
			List<Candidate> newPopulation = new ArrayList<>();
			for (int i = 0; i < ELITISM_NUMBER; i++) {
				Candidate elite = currentPopulation.get(0);
				newPopulation.add(elite);
				currentPopulation.remove(0);
			}

			Collections.shuffle(currentPopulation);

			// facem crossover pentru urmatorii 70, sau cat or fi
			for (int i = 0; i < CANDIDATES_TO_CROSSOVER / 2; i++) {
				Candidate first = currentPopulation.get(randInt(0, currentPopulation.size() - 1));
				Candidate second = currentPopulation.get(randInt(0, currentPopulation.size() - 1));

				List<Candidate> parents = new ArrayList<>();
				parents.add(first);
				parents.add(second);

				List<Candidate> children = geneticOperatorService.crossOver(parents);
				fitnessService.evaluateFitness(encryptionService.dictionary, Encrypted, children.get(0));
				fitnessService.evaluateFitness(encryptionService.dictionary, Encrypted, children.get(1));
				newPopulation.addAll(children);

				currentPopulation.remove(first);
				currentPopulation.remove(second);
			}

			// facem mutatie cu restul
			for (Candidate c : currentPopulation) {
				newPopulation.add(geneticOperatorService.mutate(c));
			}

			currentPopulation.clear();

			fitnessService.sortPopulationByFitness(newPopulation);
			// printPopulation(newPopulation);

			printCandidate(newPopulation.get(0));

			currentPopulation = newPopulation;

			generationNumber++;
		}
	}

	@SuppressWarnings("unused")
	private void printPopulation(List<Candidate> population) {
		System.out.println();
		for (Candidate c : population) {
			System.out.print(
					"Fitness: " + c.getFitness() + " normalized to: " + c.getNormalizedFitness() + " for subject ");
			for (Integer i : c.getKey()) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	private void printCandidate(Candidate c) {
		System.out.println(
				"Fitness: " + c.getFitness() + " normalized to: " + c.getNormalizedFitness() + " for subject ");
		for (Integer i : c.getKey()) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	private int randInt(int min, int max) {
		return min + (int) (Math.random() * (max - min + 1));
	}
}
