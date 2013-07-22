package ChatBot_ReponseCore;

/**
 * This class represents a context holder for the chatbot
 *
 * @author William
 */
public class Context
{

    private final String contextName;
    private String contextDescription;
    //put references to good responses in here.....

    public Context(String name)
    {
        this.contextName = name;
    }//end of constructor

    public String getContextName()
    {
        return contextName;
    }
    
    public String getContextDescription()
    {
        return contextDescription;
    }

    public void setContextDescription(String contextDescription)
    {
        this.contextDescription = contextDescription;
    }
    
}//end of class