package services;

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
		for (int i = 0; i < PS.population.size(); i++) {
			FS.evaluateFitness(ES.dictionary, Encrypted, PS.population.get(i));
		}
		FS.sortPopulationByFitness();
		// FS.printOrdered();
		// System.out.println("MAXIM AM ZIS" + FS.getMaxFit());
		// FS.normalizeFit();
		FS.printOrdered();
	}
}
