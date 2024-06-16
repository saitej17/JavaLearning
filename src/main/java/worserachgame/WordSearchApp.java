package worserachgame;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WordSearchApp {

	public static void main(String[] args) {
				
		//fill the words
		List<String> words = Arrays.asList("ONE", "TWO", "THREE");
		Grid wordsGrid = new Grid(10);
		wordsGrid.fillGrid(words);
		wordsGrid.displayGrid();
	}

}
