import java.util.Scanner;

/**
  * A simple class to run the Chatbot class.
  * @author Anya Sengupta and Eileen Xu
  * @version November 1
  */
public class Main
{

    /**
  * Create a Chatbot, give it user input, and print its replies.
    */
  public static void main(String[] args)
  {
    Chatbot chatbot = new Chatbot();
    System.out.println (chatbot.getGreeting());
    Scanner in = new Scanner (System.in);
    String statement = in.nextLine();
    while (!statement.equals("I'm not hungry"))
    {
      System.out.println (chatbot.getResponse(statement));
      statement = in.nextLine();
    }
    System.out.println("(°ロ°)☝ Goodbye.");
  }
}