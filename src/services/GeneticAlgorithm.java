package services;

public class GeneticAlgorithm {
	public void startAlgorithm() {
		EncryptionService ES = new EncryptionService();
		ES.generateDictionary();
	}
}
