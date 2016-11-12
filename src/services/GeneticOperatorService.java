package services;

import java.util.ArrayList;
import java.util.List;

public class GeneticOperatorService {

	public List<Integer> mutate(List<Integer> subject) {
		int firstPosition = randInt(0, 25);
		int secondPosition = randInt(0, 25);

		if (firstPosition == secondPosition) {
			secondPosition++;
		}

		int aux = subject.get(firstPosition);
		subject.set(firstPosition, subject.get(secondPosition));
		subject.set(secondPosition, aux);

		return subject;
	}

	public List<List<Integer>> crossOver(List<List<Integer>> parents) {
		List<Integer> firstParent = parents.get(0);
		List<Integer> secondParent = parents.get(1);

		int crossPoint = randInt(1, 24);

		List<Integer> firstChild = basicCrossOver(firstParent, secondParent, crossPoint);
		List<Integer> secondChild = basicCrossOver(secondParent, firstParent, crossPoint);

		List<List<Integer>> results = solveConflicts(firstChild, secondChild);

		return results;
	}

	private List<List<Integer>> solveConflicts(List<Integer> firstChild, List<Integer> secondChild) {
		List<Integer> firstChildDoubles = getDoubleApparitionElements(firstChild);
		List<Integer> secondChildDoubles = getDoubleApparitionElements(secondChild);

		for (int firstValue : firstChildDoubles) {
			int associateIndex = randInt(0, secondChildDoubles.size() - 1);
			int associateValue = secondChildDoubles.get(associateIndex);

			firstChild.set(firstChild.indexOf(firstValue), associateValue);
			secondChild.set(secondChild.indexOf(associateValue), firstValue);

			secondChildDoubles.remove(associateIndex);
		}

		List<List<Integer>> results = new ArrayList<>();
		results.add(firstChild);
		results.add(secondChild);

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
