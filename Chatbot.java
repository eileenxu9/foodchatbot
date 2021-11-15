import java.util.Scanner;
import java.io.File;
import java.util.HashMap;

public class Chatbot
  {
  private static HashMap<String, Double> sentiment = new HashMap<String, Double>();

  static{
    try {
      Scanner input = new Scanner(new File("cleanSentiment.csv"));
      while(input.hasNextLine()){
        String[] temp = input.nextLine().split(",");
        sentiment.put(temp[0],Double.parseDouble(temp[1]));
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing cleanSentiment.csv");
    }
  }
	/**
     * Get a default greeting
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "ʕ•́ᴥ•̀ʔっ Hello, how are you feeling today?";
	}

	/**
	 * Gives a response to a user statement
	 *
	 * @param statement
	 *            the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
    statement = statement.toLowerCase();
		String response = "";
    if (statement.length() == 0)
    {
      response = "Too hungry to speak?";
    }

		else if (sentimentVal(statement) > 10)
    {
      response = "(*´∀｀) Wow you seem happy, you must be pretty full";
    }

    else if (sentimentVal(statement) < -10)
    {
      response = "ಠ_ಠ Wow you seem angry, you must be pretty hungry";
    }
    
    else if (statement.indexOf("good") >= 0)
		{
			response = "So you ate already?";
		}

    else if (statement.indexOf("bad") >= 0)
    {
      response = "Have you not eaten yet?";
    }

    else if (findKeyword(statement, "hungry") >= 0)
    {
      response = "(¬‿¬) What do want to eat?";
    }

    else if (findKeyword(statement, "no") >= 0)
    {
			response = "Why so negative? You must be hungry";
    }

    else if (findKeyword(statement, "food") >= 0)
    {
			response = "I love food!";
    }

    else if (findKeyword(statement, "hate") >= 0)
    {
			response = "(ง'̀-'́)ง";
    }

    else if (findKeyword(statement, "taco") >= 0
               || findKeyword(statement, "tacos") >= 0
               || findKeyword(statement, "burrito") >= 0
               || findKeyword(statement, "burritos") >= 0
               || findKeyword(statement, "menudo") >= 0
               || findKeyword(statement, "chilaquiles") >= 0
               || findKeyword(statement, "chilaquile") >= 0
               || findKeyword(statement, "enchiladas") >= 0
               || findKeyword(statement, "enchilada") >= 0
               ){
                 response = "I love Mexican food!";
               }
    
    else if (findKeyword(statement, "sushi") >= 0
               || findKeyword(statement, "ramen") >= 0
               || findKeyword(statement, "sashimi") >= 0
               || findKeyword(statement, "tempura") >= 0
               || findKeyword(statement, "unagi") >= 0
               || findKeyword(statement, "onigiri") >= 0
               || findKeyword(statement, "soba") >= 0
               || findKeyword(statement, "miso soup") >= 0
               || findKeyword(statement, "udon") >= 0
               ){
                 response = "I love Japanese food!";
               }

    else if (findKeyword(statement, "curry") >= 0
               || findKeyword(statement, "tandoori") >= 0
               || findKeyword(statement, "paneer") >= 0
               || findKeyword(statement, "masala") >= 0
               || findKeyword(statement, "naan") >= 0
               || findKeyword(statement, "dosa") >= 0
               || findKeyword(statement, "butter chicken") >= 0
               || findKeyword(statement, "lassi") >= 0
               || findKeyword(statement, "samosas") >= 0
               || findKeyword(statement, "samosa") >= 0
               ){
                 response = "I love Indian food!";
               }
    
    else if (findKeyword(statement, "pasta") >= 0
               || findKeyword(statement, "gelato") >= 0
               || findKeyword(statement, "spaghetti") >= 0
               || findKeyword(statement, "tiramisu") >= 0
               || findKeyword(statement, "fettuccine") >= 0
               || findKeyword(statement, "alfredo") >= 0
               || findKeyword(statement, "lasagna") >= 0
               || findKeyword(statement, "ravioli") >= 0
               ){
                 response = "I love Italian food!";
               }

    else if (statement.indexOf("what") >= 0)
		{
			response = "Dinner's on me (☞ﾟ∀ﾟ)☞";
		}

    else if (statement.length() < 10)
		{
			response = getClarification(statement);
		}

    else
		{
			response = getRandomResponse();
		}
		return response;
	}

	/**
	 * Pick a default response to use if nothing else fits.
	 * @return a non-committal string
	 */
	private String getRandomResponse()
	{
		final int NUMBER_OF_RESPONSES = 9;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";

		if (whichResponse == 0)
		{
			response = "What's your favorite food?";
		}
		else if (whichResponse == 1)
		{
			response = "Mmmmm";
		}
		else if (whichResponse == 2)
		{
			response = "*burp* excuse me ( ⚆ _ ⚆ )";
		}
		else if (whichResponse == 3)
		{
			response = "Did you know that broccoli contains more protein than steak?";
		}

    else if (whichResponse == 4)
		{
			response = "SPAM is short for Spiced Ham";
		}

    else if (whichResponse == 5)
		{
			response = "Humans share about 50% of their DNA with bananas (ㆆ_ㆆ)";
		}

    else if (whichResponse == 6)
		{
			response = "Bananas can float in water";
		}

    else if (whichResponse == 7)
		{
			response = "Did you know that peanut butter glows in the dark after it's exposed to intense light (◑_◑)";
		}

    else if (whichResponse == 8)
		{
			response = "What did the plate say to the fork?";
		}

    return response;
  }

  /**
	 * Conversation that rewrites the sentence and asks for more clarification.
	 * @return a non-committal string
	 */
	private String getClarification(String statement)
	{
		final int NUMBER_OF_RESPONSES = 3;
		double r = Math.random();
		int whichResponse = (int)(r * NUMBER_OF_RESPONSES);
		String response = "";

		if (whichResponse == 0)
		{
			response = statement + "? What's that?";
		}
		else if (whichResponse == 1)
		{
			response = statement + "? What are you talking about?";
		}
		else if (whichResponse == 2)
		{
			response = "¯|_(ツ)_|¯ ???";
		}

    return response;
  }
  
  /**
   * @returns the sentiment value of word as a number between -1 (very negative) to 1 (very positive sentiment) 
   */
  public static double sentimentVal(String statement)
  {
    try
    {
      return sentiment.get(statement.toLowerCase());
    }
    catch(Exception e)
    {
      return 0;
    }
  }

  /**
    * Search for one word in phrase.  The search is not case sensitive.
    * This method will check that the given goal is not a substring of a longer string
    * (so, for example, "I know" does not contain "no").
    * @param statement the string to search
    * @param goal the string to search for
    * @param startPos the character of the string to begin the search at
    * @return the index of the first occurrence of goal in statement or -1 if it's not found
   */
  private int findKeyword(String statement, String goal, int startPos)
  {
    String phrase = statement.trim();
    //  The only change to incorporate the startPos is in the line below
    int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);

    //  Refinement--make sure the goal isn't part of a word
    while (psn >= 0)
    {
      //  Find the string of length 1 before and after the word
      String before = " ", after = " ";
      if (psn > 0)
      {
        before = phrase.substring (psn - 1, psn).toLowerCase();
      }
      if (psn + goal.length() < phrase.length())
      {
        after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
      }

      //  If before and after aren't letters, we've found the word
      if (((before.compareTo ("a") < 0 ) || (before.compareTo("z") > 0))  //  before is not a letter
      && ((after.compareTo ("a") < 0 ) || (after.compareTo("z") > 0)))
      {
        return psn;
      }

      //  The last position didn't work, so let's find the next, if there is one.
      psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
    }
    return -1;
  }

   /**
    * Search for one word in phrase.  The search is not case sensitive.
    * This method will check that the given goal is not a substring of a longer string
    * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.
    * @param statement the string to search
    * @param goal the string to search for
    * @return the index of the first occurrence of goal in statement or -1 if it's not found
    */
  private int findKeyword(String statement, String goal)
  {
    return findKeyword (statement, goal, 0);
  }
}
  