package AISearch;

public class GoalNodeNotFoundException extends Exception
{
	public GoalNodeNotFoundException()
	{
		super("There was no goal node found.");
	}

	public GoalNodeNotFoundException(String message)
	{
		super(message);
	}
}
