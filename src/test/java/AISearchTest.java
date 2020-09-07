import AISearch.AISearch;
import AISearch.GoalNodeNotFoundException;
import SlidingTilePuzzle.Puzzle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AISearchTest
{
	static Puzzle solvedPuzzle;
	static Puzzle shuffledPuzzle;
	static int shuffleDepth;

	@BeforeAll
	static void beforeAll()
	{
		solvedPuzzle = new Puzzle(4, 0);
		shuffledPuzzle = new Puzzle(4, 0);
		shuffledPuzzle.setGrid(shuffledPuzzle.moveBlankUp());
		shuffledPuzzle.setGrid(shuffledPuzzle.moveBlankLeft());
		shuffledPuzzle.setGrid(shuffledPuzzle.moveBlankUp());
		shuffledPuzzle.setGrid(shuffledPuzzle.moveBlankLeft());
		shuffledPuzzle.setGrid(shuffledPuzzle.moveBlankUp());
		shuffledPuzzle.setGrid(shuffledPuzzle.moveBlankRight());
		shuffledPuzzle.setGrid(shuffledPuzzle.moveBlankDown());
		shuffledPuzzle.setGrid(shuffledPuzzle.moveBlankRight());
		shuffleDepth = 8;
	}

	@DisplayName("Nodes do not expand further than their depth limit")
	@Test
	void depthLimitNotExceeded()
	{
		// the depth limit is shallower than the solution therefore if a solution is found
		// then the depth limit is not working correctly
		assertAll(
				() -> assertThrows(GoalNodeNotFoundException.class, () -> AISearch.applyBreadthFirstSearch(shuffledPuzzle, shuffleDepth - 1)),
				() -> assertThrows(GoalNodeNotFoundException.class, () -> AISearch.applyBreadthFirstSearch(shuffledPuzzle, true, shuffleDepth - 1)),
				() -> assertThrows(GoalNodeNotFoundException.class, () -> AISearch.applyBreadthFirstSearch(shuffledPuzzle, false, shuffleDepth - 1)),
				() -> assertThrows(GoalNodeNotFoundException.class, () -> AISearch.applyDepthFirstSearch(shuffledPuzzle, shuffleDepth - 1))
		);
	}

	@DisplayName("Exception is thrown if the depth limit value supplied is too low")
	@Test
	void depthLimitEnforcesMinimumValue()
	{
		assertAll(
				() -> assertThrows(IllegalArgumentException.class, () -> AISearch.applyBreadthFirstSearch(solvedPuzzle, 0)),
				() -> assertThrows(IllegalArgumentException.class, () -> AISearch.applyBreadthFirstSearch(solvedPuzzle, -1)),
				() -> assertThrows(IllegalArgumentException.class, () -> AISearch.applyBreadthFirstSearch(solvedPuzzle, Integer.MIN_VALUE)),
				() -> assertThrows(IllegalArgumentException.class, () -> AISearch.applyBreadthFirstSearch(solvedPuzzle, true, 0)),
				() -> assertThrows(IllegalArgumentException.class, () -> AISearch.applyBreadthFirstSearch(solvedPuzzle, true, -1)),
				() -> assertThrows(IllegalArgumentException.class, () -> AISearch.applyBreadthFirstSearch(solvedPuzzle, true, Integer.MIN_VALUE)),
				() -> assertThrows(IllegalArgumentException.class, () -> AISearch.applyBreadthFirstSearch(solvedPuzzle, false, 0)),
				() -> assertThrows(IllegalArgumentException.class, () -> AISearch.applyBreadthFirstSearch(solvedPuzzle, false, -1)),
				() -> assertThrows(IllegalArgumentException.class, () -> AISearch.applyBreadthFirstSearch(solvedPuzzle, false, Integer.MIN_VALUE)),
				() -> assertThrows(IllegalArgumentException.class, () -> AISearch.applyDepthFirstSearch(solvedPuzzle, 0)),
				() -> assertThrows(IllegalArgumentException.class, () -> AISearch.applyDepthFirstSearch(solvedPuzzle, -1)),
				() -> assertThrows(IllegalArgumentException.class, () -> AISearch.applyDepthFirstSearch(solvedPuzzle, Integer.MIN_VALUE))
		);
	}


	@DisplayName("Solution returned is shallowest at solution depth limit")
	@Test
	void alwaysFindShallowSolution()
	{
		assertAll(
				() -> assertEquals(8, AISearch.applyBreadthFirstSearch(shuffledPuzzle, shuffleDepth).getNodeDepth()),
				() -> assertEquals(8, AISearch.applyBreadthFirstSearch(shuffledPuzzle, true, shuffleDepth).getNodeDepth()),
				() -> assertEquals(8, AISearch.applyBreadthFirstSearch(shuffledPuzzle, false, shuffleDepth).getNodeDepth()),
				() -> assertEquals(8, AISearch.applyDepthFirstSearch(shuffledPuzzle, shuffleDepth).getNodeDepth())
		);
	}

	@DisplayName("Solution returned by breadth first is always shallowest")
	@Test
	void breadthFirstShallowSolution()
	{
		assertAll(
				() -> assertEquals(8, AISearch.applyBreadthFirstSearch(shuffledPuzzle).getNodeDepth()),
				() -> assertEquals(8, AISearch.applyBreadthFirstSearch(shuffledPuzzle, true).getNodeDepth()),
				() -> assertEquals(8, AISearch.applyBreadthFirstSearch(shuffledPuzzle, false).getNodeDepth()),
				() -> assertEquals(8, AISearch.applyBreadthFirstSearch(shuffledPuzzle, Integer.MAX_VALUE).getNodeDepth()),
				() -> assertEquals(8, AISearch.applyBreadthFirstSearch(shuffledPuzzle, Integer.MAX_VALUE).getNodeDepth()),
				() -> assertEquals(8, AISearch.applyBreadthFirstSearch(shuffledPuzzle, true, Integer.MAX_VALUE).getNodeDepth()),
				() -> assertEquals(8, AISearch.applyBreadthFirstSearch(shuffledPuzzle, false, Integer.MAX_VALUE).getNodeDepth())
		);
	}

	@DisplayName("Solution returned by breadth first is correct")
	@Test
	void breadthFirstFindsSolution()
	{
		assertAll(
				() -> assertTrue(AISearch.applyBreadthFirstSearch(shuffledPuzzle).inGoalState()),
				() -> assertTrue(AISearch.applyBreadthFirstSearch(shuffledPuzzle, true).inGoalState()),
				() -> assertTrue(AISearch.applyBreadthFirstSearch(shuffledPuzzle, false).inGoalState()),
				() -> assertTrue(AISearch.applyBreadthFirstSearch(shuffledPuzzle, Integer.MAX_VALUE).inGoalState()),
				() -> assertTrue(AISearch.applyBreadthFirstSearch(shuffledPuzzle, Integer.MAX_VALUE).inGoalState()),
				() -> assertTrue(AISearch.applyBreadthFirstSearch(shuffledPuzzle, true, Integer.MAX_VALUE).inGoalState()),
				() -> assertTrue(AISearch.applyBreadthFirstSearch(shuffledPuzzle, false, Integer.MAX_VALUE).inGoalState())
		);
	}

	@DisplayName("Solution returned by depth first is correct")
	@Test
	void depthFirstFindsSolution()
	{
		assertAll(
				() -> assertTrue(AISearch.applyDepthFirstSearch(shuffledPuzzle, shuffleDepth + 4).inGoalState())
		);
	}
}