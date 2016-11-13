package services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Candidate;

public class SelectionService {

	public static final int COMPETITION_RANDOM_CHANCE = 80;

	private static final GeneticOperatorService geneticOperatorService = new GeneticOperatorService();
	private static final FitnessService fitnessService = new FitnessService();
	private static final EncryptionService encryptionService = new EncryptionService();

	public List<Candidate> makeSelectionForPopulation(List<Candidate> population) {
		List<Candidate> newPop = new ArrayList<>();

		for (int i = 0; i < population.size(); i++) {
			int popSelector = randInt(1, 2);

			if (popSelector == 1) {
				Candidate candidate = competition(population);
				if (Collections.frequency(population, candidate) > 10) {
					List<Candidate> parents = new ArrayList<>();
					parents.add(population.get(randInt(0, population.size() - 1)));
					parents.add(population.get(randInt(0, population.size() - 1)));
					candidate = geneticOperatorService.crossOver(parents).get(0);
					fitnessService.evaluateFitness(encryptionService.dictionary, GeneticAlgorithm.toDecrypt, candidate);
				}
				newPop.add(candidate);
			} else {
				Candidate candidate = fortuneWheel(population);
				if (Collections.frequency(population, candidate) > 10) {
					candidate = competition(population);
				}
				newPop.add(candidate);
			}
		}
		return newPop;
	}

	public Candidate competition(List<Candidate> subjects) {
		int surprise = randInt(0, 100);
		Candidate first = subjects.get(randInt(0, subjects.size() - 1));
		Candidate second = subjects.get(randInt(0, subjects.size() - 1));

		if (first.compareTo(second) == -1) {
			if (surprise < COMPETITION_RANDOM_CHANCE) {
				return first;
			} else {
				return second;
			}
		} else {
			if (surprise < COMPETITION_RANDOM_CHANCE) {
				return second;
			} else {
				return first;
			}
		}
	}

	public Candidate fortuneWheel(List<Candidate> population) {
		List<Double> wheel = new ArrayList<>();
		List<Integer> intWheel = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("#.####");
		wheel.add(0, 0.0);
		intWheel.add(0, 0);
		for (int i = 1; i < population.size(); i++) {
			double value = wheel.get(i - 1) + population.get(i).getNormalizedFitness();
			if (value == wheel.get(i - 1)) {
				value += 0.0001;
			}
			value = Double.valueOf(df.format(value));
			int val = (int) (value * 10000);
			wheel.add(i, value);
			intWheel.add(i, val);
		}

		int randVal = randInt(0, intWheel.get(intWheel.size() - 1));
		for (int j = 0; j < intWheel.size(); j++) {
			if (intWheel.get(j) < randVal && randVal <= intWheel.get(j + 1)) {
				return population.get(j);
			}
		}
		// nu o sa ajunga niciodata aici, l-am pus doar pentru ca ma enerva
		// eroarea
		return population.get(population.size() - 1);
	}

	// public List<Candidate> fortuneWheel(List<Candidate> population) {
	// List<Double> wheel = new ArrayList<>();
	// List<Integer> intWheel = new ArrayList<>();
	// DecimalFormat df = new DecimalFormat("#.####");
	// wheel.add(0, 0.0);
	// intWheel.add(0, 0);
	// for (int i = 1; i < population.size(); i++) {
	// double value = wheel.get(i - 1) +
	// population.get(i).getNormalizedFitness();
	// if (value == wheel.get(i - 1)) {
	// value += 0.0001;
	// }
	// value = Double.valueOf(df.format(value));
	// int val = (int) (value * 10000);
	// wheel.add(i, value);
	// intWheel.add(i, val);
	// }
	//
	// List<Candidate> newPop = new ArrayList<>();
	// for (int i = 0; i < population.size(); i++) {
	// int randVal = randInt(0, intWheel.get(intWheel.size() - 1));
	// for (int j = 0; j < intWheel.size(); j++) {
	// if (intWheel.get(j) < randVal && randVal <= intWheel.get(j + 1)) {
	// newPop.add(population.get(j));
	// }
	// }
	// }
	// return newPop;
	// }

	private int randInt(int min, int max) {
		return min + (int) (Math.random() * (max - min + 1));
	}
}
