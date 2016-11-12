package services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class EncryptionService {
	private String text;
	private List<String> dictionary;

	// citeste dintr-un txt si iti genereaza o lista de cuvinte care reprezinta
	// dictionaru, also, salveaza in "text" textul citit
	public void generateDictionary() {

		// citeste din fisier si salveaza in everything
		String everything = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("text.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(everything);

		// curata textul de semne de punctuatie
		everything = everything.replace(",", "");
		everything = everything.replace(".", "");
		everything = everything.replace("-", "");
		everything = everything.replace("’", "");
		everything = everything.replace("(", "");
		everything = everything.replace(")", "");
		everything = everything.replace("\"", "");

		String[] parts = everything.split(" ");
		for (int i = 0; i < parts.length; i++) {
			System.out.println(parts[i]);
			String element = parts[i];
			dictionary.add(element);

		}
	}

	// din toate propozitiile textului alege una
	public String extractSentence(String text) {
		return null;
	}

	// criptare, returneaza o ista de int-uri (corespunzatoare pt fiecare litera
	// din alfabet)
	public List<Integer> encryptSentence(String sentence) {
		return null;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
