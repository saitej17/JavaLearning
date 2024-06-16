package worserachgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {
	
	private int GRID_SIZE;
	private char[][] contents;
	private List<Corordinate> coordinates = new ArrayList<>();
	
	private class Corordinate {
		int x;
		int y;
		public Corordinate(int aX, int aY) {
			this.x = aX;
			this.y = aY;
		}
	}
	
	public Grid(int gridSize) {
		GRID_SIZE = gridSize;
		contents = new char[GRID_SIZE][GRID_SIZE];
		
		for (int i = 0; i < contents.length; i++) {
			for (int j = 0; j < contents.length; j++) {
				contents[i][j] = '_';
				coordinates.add(new Corordinate(i, j));
			}
			
		}
	}
	
	public void fillGrid(List<String> words) {
		for (String word : words) {
			Collections.shuffle(coordinates);
			
			for(Corordinate corordinate :  coordinates) {
				int x =  corordinate.x;
				int y =  corordinate.y;
				
				if(y + word.length() < GRID_SIZE) {
					for (char aChar : word.toCharArray()) {
						contents[x][y++] =  aChar;
					}
				}
				break;
			}
		}
	}
	
	public void displayGrid() {
		for (int i = 0; i < contents.length; i++) {
			for (int j = 0; j < contents.length; j++) {
				System.out.print(contents[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
