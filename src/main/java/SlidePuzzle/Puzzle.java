package SlidePuzzle;

import AISearch.AbstractSearchNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Puzzle extends AbstractSearchNode
{

	private int[][] grid;
	private int sideLength;

	public Puzzle(int sideLength)
	{
		super();
		this.sideLength = sideLength;
		this.grid = getNewGrid();

		shuffleGrid();
	}

	private Puzzle(Puzzle parentNode, int[][] grid)
	{
		super(parentNode);
		this.sideLength = parentNode.getSideLength();
		this.grid = grid;
	}

	public int[][] getGrid()
	{
		return grid;
	}

	public int getSideLength()
	{
		return sideLength;
	}

	private int[][] getNewGrid()
	{
		int[][] newGrid = new int[sideLength][sideLength];
		int currentNum = 1;
		for (int i = 0; i < sideLength; i++)
		{
			for (int j = 0; j < sideLength; j++)
			{
				newGrid[i][j] = currentNum;
				currentNum++;
			}
		}
		newGrid[sideLength - 1][sideLength - 1] = 0;
		return newGrid;
	}

	private void shuffleGrid()
	{
		Random randomInt = new Random();
		int numMoves = (int) Math.pow(this.sideLength, 3);
		System.out.printf("Shuffling %s moves.%n", numMoves);
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

	private int[][] copyGrid()
	{
		int[][] copy = new int[this.sideLength][this.sideLength];
		for (int i = 0; i < this.sideLength; i++)
		{
			for (int j = 0; j < this.sideLength; j++)
			{
				copy[i][j] = this.getGrid()[i][j];
			}

		}
		return copy;
	}

	private int[][] moveBlankUp()
	{
		int[][] newGrid = copyGrid();
		int[] blankPos = new int[2];
		for (int i = 0; i < newGrid.length; i++)
		{
			for (int j = 0; j < newGrid[i].length; j++)
			{
				if (newGrid[i][j] == 0)
				{
					blankPos[0] = i;
					blankPos[1] = j;
				}
			}
		}
		if (!(blankPos[0] == 0))
		{
			newGrid[blankPos[0]][blankPos[1]] = newGrid[blankPos[0] - 1][blankPos[1]];
			newGrid[blankPos[0] - 1][blankPos[1]] = 0;
		}
		return newGrid;
	}

	private int[][] moveBlankDown()
	{
		int[][] newGrid = copyGrid();
		int[] blankPos = new int[2];
		for (int i = 0; i < newGrid.length; i++)
		{
			for (int j = 0; j < newGrid[i].length; j++)
			{
				if (newGrid[i][j] == 0)
				{
					blankPos[0] = i;
					blankPos[1] = j;
				}
			}
		}
		if (!(blankPos[0] == this.sideLength - 1))
		{
			newGrid[blankPos[0]][blankPos[1]] = newGrid[blankPos[0] + 1][blankPos[1]];
			newGrid[blankPos[0] + 1][blankPos[1]] = 0;
		}
		return newGrid;
	}

	private int[][] moveBlankLeft()
	{
		int[][] newGrid = copyGrid();
		int[] blankPos = new int[2];
		for (int i = 0; i < newGrid.length; i++)
		{
			for (int j = 0; j < newGrid[i].length; j++)
			{
				if (newGrid[i][j] == 0)
				{
					blankPos[0] = i;
					blankPos[1] = j;
				}
			}
		}
		if (!(blankPos[1] == 0))
		{
			newGrid[blankPos[0]][blankPos[1]] = newGrid[blankPos[0]][blankPos[1] - 1];
			newGrid[blankPos[0]][blankPos[1] - 1] = 0;
		}
		return newGrid;
	}

	private int[][] moveBlankRight()
	{
		int[][] newGrid = copyGrid();
		int[] blankPos = new int[2];
		for (int i = 0; i < newGrid.length; i++)
		{
			for (int j = 0; j < newGrid[i].length; j++)
			{
				if (newGrid[i][j] == 0)
				{
					blankPos[0] = i;
					blankPos[1] = j;
				}
			}
		}
		if (!(blankPos[1] == this.sideLength - 1))
		{
			newGrid[blankPos[0]][blankPos[1]] = newGrid[blankPos[0]][blankPos[1] + 1];
			newGrid[blankPos[0]][blankPos[1] + 1] = 0;
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
