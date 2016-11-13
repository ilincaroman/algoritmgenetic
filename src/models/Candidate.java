package models;

import java.util.List;

public class Candidate implements Comparable<Candidate> {
	private Double fitnessScore;
	private Double normalizedFitness;
	private List<Integer> decryptionKey;

	public Candidate(List<Integer> key) {
		decryptionKey = key;
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
		return fitnessScore > o.getFitness() ? -1 : fitnessScore < o.getFitness() ? 1 : 0;
	}

	public Double getNormalizedFitness() {
		return normalizedFitness;
	}

	public void setNormalizedFitness(Double normalizedFitness) {
		this.normalizedFitness = normalizedFitness;
	}
}
