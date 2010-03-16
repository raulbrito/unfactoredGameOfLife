package com.kbaribeau.conway;

public class Game {
	private boolean[][] grid;

	public boolean isAlive(int y, int x) {
		return grid[y][x];
	}
	
	private void setAlive(int y, int x, boolean alive) {
		grid[y][x] = alive;
	}

	public Game(String start) {
		int sizeX = start.indexOf("\n");
		int sizeY = start.split("\n").length;
		grid = new boolean[sizeY][sizeX];

		buildTable(start);
	}

	private void buildTable(String start) {
		int x = 0, y = 0;
		for (char c : start.toCharArray()) {
			switch (c) {
				case '\n':
					y++;
					x = 0;
					continue;
				case '.':
					this.setAlive(y, x, false);
					break;
				case 'X':
					this.setAlive(y, x, true);
					break;
			}
			x++;
		}
	}


	public void tick() {
		Game newGame = new Game(toString());
		for (int y = 0; y < gridRowLength(newGame); y++) {
			for (int x = 0; x < gridColumnLength(newGame); x++) {
				
				int neighborCount = countNumberOfNeighbours(y, x);
				
				if (neighborCount == 1 || neighborCount == 0) {
					newGame.setAlive(y, x, false);
				}
				if (neighborCount > 3) {
					newGame.setAlive(y, x, false);
				}
				if (neighborCount == 3) {
					newGame.setAlive(y, x, true);
				}
			}
		}

		this.grid = newGame.grid;
	}

	private int countNumberOfNeighbours(int y, int x) {
		int neighborCount = 0;

		if (hasLife(y - 1, x - 1)) neighborCount++;
		if (hasLife(y, x - 1)) neighborCount++;
		if (hasLife(y + 1, x - 1)) neighborCount++;
		if (hasLife(y - 1, x)) neighborCount++;
		if (hasLife(y + 1, x)) neighborCount++;
		if (hasLife(y - 1, x + 1)) neighborCount++;
		if (hasLife(y, x + 1)) neighborCount++;
		if (hasLife(y + 1, x + 1)) neighborCount++;

		return neighborCount;
	}

	private int gridColumnLength(Game game) {
		return game.grid[0].length;
	}

	private int gridRowLength(Game game) {
		return game.grid.length;
	}

	private boolean hasLife(int y, int x) {
		boolean rowExists = (y >= 0 && y < gridRowLength(this));
		boolean columnExists = (x >= 0 && x < gridColumnLength(this));
		return rowExists && columnExists && isAlive(y, x);
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (boolean[] column : grid) {
			for (int x = 0; x < grid[0].length; x++) {
				result.append(column[x] ? "X" : ".");
			}
			result.append("\n");
		}

		return result.deleteCharAt(result.lastIndexOf("\n")).toString();
	}
	
	
}
