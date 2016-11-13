package services;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import models.Candidate;

public class FitnessService {

	// a trebuit sa adaug dictionary aici pentru ca am nevoie pentru
	// functia de fitting + dictionary, in EncryptionService e acum public
	public Double evaluateFitness(Vector<String> dictionary, String toDecrypt, Candidate subject) {
		String decryptedSentence = toDecrypt;
		double fitnessScore = 0;
		for (int i = 0; i < subject.getKey().size(); i++) {
			decryptedSentence = decryptedSentence.replace((char) (subject.getKey().get(i) + 'a'), (char) (i + 'a'));
		}

		// iteram prin dictionar si vedem cate cuvinte se gasesc in decriptare
		for (int i = 0; i < dictionary.size(); i++) {
			if (decryptedSentence.contains(dictionary.get(i))) {
				fitnessScore += dictionary.get(i).length();
			}
		}

		subject.setFitness(fitnessScore);

		return fitnessScore;
	}

	public void normalizeFit(List<Candidate> candidates) {
		Double max = 0.0;

		for (Candidate c : candidates) {
			max += c.getFitness();
		}

		DecimalFormat df = new DecimalFormat("#.####");

		for (Candidate c : candidates) {
			double candidateFitness = c.getFitness() / max;
			c.setNormalizedFitness(Double.valueOf(df.format(candidateFitness)));
		}
	}

	public void sortPopulationByFitness(List<Candidate> candidates) {
		Collections.sort(candidates);
	}

}
