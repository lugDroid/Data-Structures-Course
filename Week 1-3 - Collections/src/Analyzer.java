import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * SD2x Homework #3
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */
public class Analyzer {
	
	public static List<Sentence> readFile(String filename) {
		String line;
		String lineFields[];
		Sentence newSentence;
		int score;
		String text;
		LinkedList<Sentence> sentenceList = new LinkedList<>();
		
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(fileReader);

			while ((line = reader.readLine()) != null) {
				lineFields = line.split(" ", 2);
				try {
					score = Integer.parseInt(lineFields[0]);
					if (score >= -2 && score <= 2 && lineFields.length > 1) {
						text = lineFields[1].trim().toLowerCase();
						newSentence = new Sentence(score, text);
						sentenceList.add(newSentence);
					}
				}
				
				catch (NumberFormatException ex) {
					System.out.println("Wrong number:" + ex.getMessage());
				}
			}

			reader.close();
		}
		
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file");
			return new LinkedList<Sentence>();
		}
		
		catch (IOException ex) {
			System.out.println("Error reading file");
			return new LinkedList<Sentence>();
		}
		
		catch (NullPointerException ex) {
			System.out.println("Wrong filename");
			return new LinkedList<Sentence>();
		} 
		
		return sentenceList;

	}
	
	public static Set<Word> allWords(List<Sentence> sentences) {
		HashSet<Word> wordSet = new HashSet<>();
		HashSet<String> stringSet = new HashSet<>();
		
		if (sentences == null)
			return wordSet;
		
		for (Sentence sentence : sentences) {
			if (sentence != null) {
				int score = sentence.getScore();
				String text = sentence.getText().toLowerCase();

				StringTokenizer st = new StringTokenizer(text);

				while (st.hasMoreTokens()) {
					String word = st.nextToken();
					// check if word starts with a valid character
					char firstChar = word.charAt(0);
					if (firstChar >= 'a' &&  firstChar <= 'z') {
						if (!stringSet.contains(word)) {
							// add word string to string set to keep a record of words seen
							stringSet.add(word);
	
							// create new word object to add
							Word wordObj = new Word(word);
							wordObj.count++;
							wordObj.total += score;
	
							// add object to list
							wordSet.add(wordObj);
						} else {
							// if word has already been seen
							// get reference to existing word object
							for (Word wordObj : wordSet) {
								if (wordObj.getText().equals(word)) {
									wordObj.count++;
									wordObj.total += score;
								}
							}
						}
					}
				}
			}

		}
		
		
		return wordSet;

	}
	
	/*
	 * Implement this method in Part 3
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {

		/* IMPLEMENT THIS METHOD! */
		
		return null; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * Implement this method in Part 4
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {

		/* IMPLEMENT THIS METHOD! */
		
		return 0; // this line is here only so this code will compile if you don't modify it

	}
	
	/*
	 * This method is here to help you run your program. Y
	 * You may modify it as needed.
	 */
	public static void main(String[] args) {
		/*if (args.length == 0) {
			System.out.println("Please specify the name of the input file");
			System.exit(0);
		}
		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile(filename);
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);*/
		
		List<Sentence> list = Analyzer.readFile("reviews.txt");
		
		for (Sentence sent : list) {
			System.out.println(sent.getText() + ": " + sent.getScore());
		}
	}
}
