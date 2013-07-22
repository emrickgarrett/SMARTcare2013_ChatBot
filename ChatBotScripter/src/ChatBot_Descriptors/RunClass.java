package ChatBot_Descriptors;

import ChatBot_ReponseCore.ChatBot_InputOutput;
import ChatBot_Core.ChatBot_CoreCharacteristics;

/**
 * This is the run class for the Chat Bot. It creates a Core Characteristics class and passes that to a new {@link ChatBot_InputOutput} class
 * @author William
 */
public class RunClass
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
                ChatBot_CoreCharacteristics chatbotCore = new ChatBot_CoreCharacteristics("Charles", 25, "01/16/1966", Gender.MALE, MentalStatus.ORIENTED);
                ChatBot_InputOutput newChatbotInputOutput = new ChatBot_InputOutput(chatbotCore);
    }//end of main method
}//end of class
