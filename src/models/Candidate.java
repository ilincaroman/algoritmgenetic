package models;

import java.util.List;

public class Candidate implements Comparable<Candidate> {
	public Double fitnessScore;
	public List<Integer> decryptionKey;

	public Candidate(Double fitness, List<Integer> key) {
		setFitness(fitness);
		setKey(key);
	}

	public Double getFitness() {
		return fitnessScore;
	}

	public List<Integer> getKey() {
		return decryptionKey;
	}

	public void setFitness(Double fitness) {
		fitnessScore = fitness;
	}

	public void setKey(List<Integer> key) {
		decryptionKey = key;
	}

	@Override
	public int compareTo(Candidate o) {
		// TODO Auto-generated method stub
		return fitnessScore > o.getFitness() ? -1 : fitnessScore < o.getFitness() ? 1 : 0;
	}
}
