package SlidePuzzle;

import AISearch.AbstractSearchNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Puzzle extends AbstractSearchNode
{
	public enum Tile
	{
		FIRST,
		SECON,
		THIRD,
		FOURT,
		FIFTH,
		SIXTH,
		SEVEN,
		EIGHT,
		BLANK
	}

	private Tile[][] grid;

	public Puzzle()
	{
		super();
		this.grid = getNewGrid();

		shuffleGrid();
	}

	private Puzzle(AbstractSearchNode parentNode, Tile[][] grid)
	{
		super(parentNode);
		this.grid = grid;
	}

	public Tile[][] getGrid()
	{
		return grid;
	}

	private Tile[][] getNewGrid()
	{
		Tile[][] newGrid = new Tile[3][3];
		newGrid[0][0] = Tile.FIRST;
		newGrid[0][1] = Tile.SECON;
		newGrid[0][2] = Tile.THIRD;
		newGrid[1][0] = Tile.FOURT;
		newGrid[1][1] = Tile.FIFTH;
		newGrid[1][2] = Tile.SIXTH;
		newGrid[2][0] = Tile.SEVEN;
		newGrid[2][1] = Tile.EIGHT;
		newGrid[2][2] = Tile.BLANK;
		return newGrid;
	}

	private void shuffleGrid()
	{
		Random randomInt = new Random();
		int numMoves = 20 + randomInt.nextInt(50);
		for (int i = 0; i < numMoves; i++)
		{
			switch (randomInt.nextInt(4))
			{
				case 0 -> this.grid = moveBlankUp();
				case 1 -> this.grid = moveBlankDown();
				case 2 -> this.grid = moveBlankLeft();
				case 3 -> this.grid = moveBlankRight();
			}
		}

	}

	private Tile[][] copyGrid()
	{
		Tile[][] copy = new Tile[3][3];
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				copy[i][j] = this.getGrid()[i][j];
			}

		}
		return copy;
	}

	private Tile[][] moveBlankUp()
	{
		Tile[][] newGrid = copyGrid();
		int[] blankPos = new int[2];
		for (int i = 0; i < newGrid.length; i++)
		{
			for (int j = 0; j < newGrid.length; j++)
			{
				if (newGrid[i][j].equals(Tile.BLANK))
				{
					blankPos[0] = i;
					blankPos[1] = j;
				}
			}
		}
		if (!(blankPos[0] == 0))
		{
			newGrid[blankPos[0]][blankPos[1]] = newGrid[blankPos[0] - 1][blankPos[1]];
			newGrid[blankPos[0] - 1][blankPos[1]] = Tile.BLANK;
		}
		return newGrid;
	}

	private Tile[][] moveBlankDown()
	{
		Tile[][] newGrid = copyGrid();
		int[] blankPos = new int[2];
		for (int i = 0; i < newGrid.length; i++)
		{
			for (int j = 0; j < newGrid.length; j++)
			{
				if (newGrid[i][j].equals(Tile.BLANK))
				{
					blankPos[0] = i;
					blankPos[1] = j;
				}
			}
		}
		if (!(blankPos[0] == 2))
		{
			newGrid[blankPos[0]][blankPos[1]] = newGrid[blankPos[0] + 1][blankPos[1]];
			newGrid[blankPos[0] + 1][blankPos[1]] = Tile.BLANK;
		}
		return newGrid;
	}

	private Tile[][] moveBlankLeft()
	{
		Tile[][] newGrid = copyGrid();
		int[] blankPos = new int[2];
		for (int i = 0; i < newGrid.length; i++)
		{
			for (int j = 0; j < newGrid.length; j++)
			{
				if (newGrid[i][j].equals(Tile.BLANK))
				{
					blankPos[0] = i;
					blankPos[1] = j;
				}
			}
		}
		if (!(blankPos[1] == 0))
		{
			newGrid[blankPos[0]][blankPos[1]] = newGrid[blankPos[0]][blankPos[1] - 1];
			newGrid[blankPos[0]][blankPos[1] - 1] = Tile.BLANK;
		}
		return newGrid;
	}

	private Tile[][] moveBlankRight()
	{
		Tile[][] newGrid = copyGrid();
		int[] blankPos = new int[2];
		for (int i = 0; i < newGrid.length; i++)
		{
			for (int j = 0; j < newGrid.length; j++)
			{
				if (newGrid[i][j].equals(Tile.BLANK))
				{
					blankPos[0] = i;
					blankPos[1] = j;
				}
			}
		}
		if (!(blankPos[1] == 2))
		{
			newGrid[blankPos[0]][blankPos[1]] = newGrid[blankPos[0]][blankPos[1] + 1];
			newGrid[blankPos[0]][blankPos[1] + 1] = Tile.BLANK;
		}
		return newGrid;
	}

	@Override
	public boolean inGoalState()
	{
		return Arrays.deepEquals(this.grid, getNewGrid());
	}

	@Override
	public ArrayList<AbstractSearchNode> generateChildNodes()
	{
		ArrayList<AbstractSearchNode> newPuzzles = new ArrayList<>();
		newPuzzles.add(new Puzzle(this, this.moveBlankUp()));
		newPuzzles.add(new Puzzle(this, this.moveBlankDown()));
		newPuzzles.add(new Puzzle(this, this.moveBlankLeft()));
		newPuzzles.add(new Puzzle(this, this.moveBlankRight()));
		return newPuzzles;
	}

	@Override
	public boolean equalsNode(AbstractSearchNode node)
	{
		return Arrays.deepEquals(((Puzzle) node).grid, this.grid);
	}
}
