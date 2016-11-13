package services;

import java.util.ArrayList;
import java.util.List;

import models.Candidate;

public class GeneticOperatorService {

	public Candidate mutate(Candidate subject) {
		int firstPosition = randInt(0, 25);
		int secondPosition = randInt(0, 25);

		if (firstPosition == secondPosition) {
			if (firstPosition == 25) {
				secondPosition = firstPosition - 1;
			} else {
				secondPosition = firstPosition + 1;
			}
		}

		List<Integer> key = subject.getKey();
		int aux = key.get(firstPosition);
		key.set(firstPosition, key.get(secondPosition));
		key.set(secondPosition, aux);

		return subject;
	}

	public List<Candidate> crossOver(List<Candidate> parents) {
		List<Integer> firstParent = parents.get(0).getKey();
		List<Integer> secondParent = parents.get(1).getKey();

		int crossPoint = randInt(1, 24);

		List<Integer> firstChild = basicCrossOver(firstParent, secondParent, crossPoint);
		List<Integer> secondChild = basicCrossOver(secondParent, firstParent, crossPoint);

		return solveConflicts(firstChild, secondChild);
	}

	private List<Candidate> solveConflicts(List<Integer> firstChild, List<Integer> secondChild) {
		List<Integer> firstChildDoubles = getDoubleApparitionElements(firstChild);
		List<Integer> secondChildDoubles = getDoubleApparitionElements(secondChild);

		for (int firstValue : firstChildDoubles) {
			int associateIndex = randInt(0, secondChildDoubles.size() - 1);
			int associateValue = secondChildDoubles.get(associateIndex);

			firstChild.set(firstChild.indexOf(firstValue), associateValue);
			secondChild.set(secondChild.indexOf(associateValue), firstValue);

			secondChildDoubles.remove(associateIndex);
		}

		List<Candidate> results = new ArrayList<>();
		results.add(new Candidate(firstChild));
		results.add(new Candidate(secondChild));

		return results;
	}

	private List<Integer> getDoubleApparitionElements(List<Integer> subject) {
		List<Integer> save = new ArrayList<>(subject);

		List<Integer> doubleApparitionElements = new ArrayList<>();
		for (int e : save) {
			if (subject.indexOf(e) != subject.lastIndexOf(e)) {
				if (doubleApparitionElements.contains(e)) {
					continue;
				}
				doubleApparitionElements.add(e);
			}
		}
		return doubleApparitionElements;
	}

	private List<Integer> basicCrossOver(List<Integer> firstParent, List<Integer> secondParent, int crossPoint) {
		List<Integer> firstChild = new ArrayList<>();

		for (int i = 0; i < 26; i++) {
			if (i < crossPoint) {
				firstChild.add(firstParent.get(i));
			} else {
				firstChild.add(secondParent.get(i));
			}
		}
		return firstChild;
	}

	private int randInt(int min, int max) {
		return min + (int) (Math.random() * (max - min + 1));
	}
}
