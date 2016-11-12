package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import models.Candidate;

public class FitnessService {

	public List<Candidate> candidates = new ArrayList<Candidate>();
	public List<Candidate> newcandidates = new ArrayList<Candidate>();

	// a trebuit sa adaug dictionary aici pentru ca am nevoie pentru
	// functia de fitting + dictionary, in EncryptionService e acum public
	public Double evaluateFitness(Vector<String> dictionary, String toDecrypt, List<Integer> subject) {
		String decryptedSentence = toDecrypt;
		double fitnessScore = 0;
		for (int i = 0; i < subject.size(); i++) {
			decryptedSentence = decryptedSentence.replace((char) (subject.get(i) + 'a'), (char) (i + 'a'));
		}

		// iteram prin dictionar si vedem cate cuvinte se gasesc in decriptare
		for (int i = 0; i < dictionary.size(); i++) {
			if (decryptedSentence.contains(dictionary.get(i))) {
				fitnessScore += dictionary.get(i).length();
			}
		}

		Candidate candidate = new Candidate(fitnessScore, subject);
		candidates.add(candidate);

		return fitnessScore;
	}

	public Double getMaxFit() {
		Double maxFit = 0.0;
		for (int i = 0; i < candidates.size(); i++) {
			if (candidates.get(i).getFitness() > maxFit) {
				maxFit = candidates.get(i).getFitness();
			}
		}
		return maxFit;
	}

	public void normalizeFit() {
		for (int i = 0; i < candidates.size(); i++) {
			Double max = getMaxFit();
			System.out.println(candidates.get(i).getFitness() / max);
			candidates.get(i).setFitness(candidates.get(i).getFitness() / max);
		}
	}

	public void printOrdered() {
		for (int i = 0; i < candidates.size(); i++) {
			System.out.println("Fitness of: " + candidates.get(i).getFitness() + " of this candidate: ");
			for (int j = 0; j < candidates.get(i).getKey().size(); j++) {
				System.out.print(candidates.get(i).getKey().get(j) + " ");
			}
			System.out.println();
			System.out.println();
		}
	}

	public void sortPopulationByFitness() {
		Collections.sort(candidates);
	}

}
