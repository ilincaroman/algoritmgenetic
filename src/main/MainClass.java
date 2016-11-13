package main;

import services.GeneticAlgorithm;

public class MainClass {

	public static void main(String[] args) {
		GeneticAlgorithm ga = new GeneticAlgorithm();
		ga.startAlgorithm();

		// PopulationService ps = new PopulationService();
		//
		// ps.generateFirstPopulation();

		// GeneticOperatorService gos = new GeneticOperatorService();
		// Integer[] vector1 = { 8, 19, 16, 21, 4, 9, 2, 25, 0, 3, 7, 22, 12,
		// 11, 14, 20, 15, 13, 5, 24, 23, 1, 17, 18, 10,
		// 6 };
		// Integer[] vector2 = { 9, 11, 12, 2, 24, 7, 1, 3, 19, 13, 20, 18, 17,
		// 14, 5, 8, 15, 16, 25, 21, 0, 22, 6, 23, 4,
		// 10 };
		//
		// List<Integer> parent1 = new ArrayList<>(Arrays.asList(vector1));
		// List<Integer> parent2 = new ArrayList<>(Arrays.asList(vector2));
		//
		// List<List<Integer>> parents = new ArrayList<>();
		// parents.add(parent1);
		// parents.add(parent2);
		//
		// gos.crossOver(parents);
		// gos.mutate(new ArrayList<>(Arrays.asList(vector)));
	}

}
