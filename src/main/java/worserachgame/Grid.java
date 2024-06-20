package worserachgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {
	
	private int GRID_SIZE;
	private char[][] contents;
	private List<Coordinate> coordinates = new ArrayList<>();
	
	private class Coordinate {
		int x;
		int y;
		public Coordinate(int aX, int aY) {
			this.x = aX;
			this.y = aY;
		}
	}
	
	private enum Direction {
		HORIZONTAL,
		VERTICAL,
		DIAGONAL
	}
	
	public Grid(int gridSize) {
		GRID_SIZE = gridSize;
		contents = new char[GRID_SIZE][GRID_SIZE];
		
		for (int i = 0; i < contents.length; i++) {
			for (int j = 0; j < contents.length; j++) {
				contents[i][j] = '_';
				coordinates.add(new Coordinate(i, j));
			}
			
		}
	}
	
	public void fillGrid(List<String> words) {
		Collections.shuffle(coordinates);
		for (String word : words) {
			
			for(Coordinate coordinate :  coordinates) {
				int x =  coordinate.x;
				int y =  coordinate.y;
				
				Direction selectedDirection =  getDirectionForFit(word, coordinate);
				
				if(selectedDirection != null) {
					switch (selectedDirection) {
					case HORIZONTAL:
						for (char aChar : word.toCharArray()) {
							contents[x][y++] =  aChar;
						}
						break;
					case VERTICAL:
						for (char aChar : word.toCharArray()) {
							contents[x++][y] =  aChar;
						}
						break;
					case DIAGONAL :
						for (char aChar : word.toCharArray()) {
							contents[x++][y++] =  aChar;
						}
						break;
					}
					break;
				}
			}
		}
	}

	private Direction getDirectionForFit(String word, Coordinate coordinate) {
		List<Direction> dircetions =  Arrays.asList(Direction.values());
		Collections.shuffle(dircetions);
		
		for (Direction direction : dircetions) {
			if(doesFit(word, coordinate, direction)) {
				return direction;
			}
		}
		return null;
	}

	private boolean doesFit(String word, Coordinate coordinate, Direction direction) {
		
		int wordLength = word.length();
		
		switch (direction) {
		case HORIZONTAL:
			if (coordinate.y + wordLength > GRID_SIZE) return false;
			for (int i = 0; i < word.length(); i++) {
				if(contents[coordinate.x][coordinate.y + i] != '_') return false;
			}
			break;
		case VERTICAL:
			if (coordinate.x + wordLength > GRID_SIZE) return false;
			for (int i = 0; i < word.length(); i++) {
				if(contents[coordinate.x + i][coordinate.y] != '_') return false;
			}
			break;
		case DIAGONAL:
			if (coordinate.x + wordLength > GRID_SIZE 
					|| coordinate.y + wordLength > GRID_SIZE) return false;
			for (int i = 0; i < word.length(); i++) {
				if(contents[coordinate.x + i][coordinate.y + i] != '_') return false;
			}
			break;
		}
		return true;
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
