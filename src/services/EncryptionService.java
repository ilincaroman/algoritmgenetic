package services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class EncryptionService {
	private String text;
	public Vector<String> dictionary = new Vector<>();
	protected Vector<Integer> encryptionKey = new Vector<>();
	public static int textSize;

	public void generateKey() {
		int quota = 0;
		while (quota != 26) {
			int positionShift = randInt(0, 25);
			if (encryptionKey.contains(positionShift) == false) {
				encryptionKey.add(positionShift);
				quota++;
			}
		}
	}

	// citeste dintr-un txt si iti genereaza o lista de cuvinte care reprezinta
	// dictionaru, also, salveaza in "text" textul citit
	public void generateDictionary() {

		// citeste din fisier si salveaza in everything
		String everything = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("text.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			everything = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		setText(cleanString(everything));

		String[] parts = everything.split(" ");
		for (int i = 0; i < parts.length; i++) {
			if (dictionary.contains(parts[i].toString()) == false) {
				dictionary.add(parts[i].toString());
			}
		}
	}

	// din toate propozitiile textului alege una
	public String extractSentence() {
		String[] sentences = text.split("\\.");
		return sentences[randInt(0, sentences.length - 1)];
	}

	// criptare, returneaza o lista de int-uri (corespunzatoare pt fiecare
	// litera
	// din alfabet)

	// pare rau, la criptare se returneaza un string criptat

	public String encryptSentence(String sentence) {
		String encryptedSentence = sentence.replace(" ", "");
		for (int i = 0; i < encryptionKey.size(); i++) {
			encryptedSentence = encryptedSentence.replace((char) (i + 'a'), (char) (encryptionKey.get(i) + 'a'));
		}
		textSize = encryptedSentence.length();
		return encryptedSentence;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	private int randInt(int min, int max) {
		return min + (int) (Math.random() * (max - min + 1));
	}

	// curata textul de semne de punctuatie inafara de puncte
	public String cleanString(String toClean) {
		toClean = toClean.replace(",", "");
		toClean = toClean.replace("-", "");
		toClean = toClean.replace("’", "");
		toClean = toClean.replace("(", "");
		toClean = toClean.replace(")", "");
		toClean = toClean.replace("\"", "");
		toClean = toClean.toLowerCase();
		return toClean;
	}
}
