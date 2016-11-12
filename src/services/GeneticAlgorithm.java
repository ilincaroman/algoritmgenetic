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
	}
}
