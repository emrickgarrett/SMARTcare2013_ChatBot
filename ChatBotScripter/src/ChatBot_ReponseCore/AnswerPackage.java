package ChatBot_ReponseCore;

import DatabaseStorage.DatabaseContainer;
import DatabaseStorage.OwnershipContainer;
import java.util.Objects;

/**
 * This class contains all of the answer information for a ChatBot
 *
 * @author William
 */
public class AnswerPackage
{

    private String questionString;
    private String responseString;
    private boolean isInDataBase;
    private DatabaseContainer DatabaseContainer;
    private OwnershipContainer ownershipContainer;

    public AnswerPackage()
    {
        
    }//end of constructore
    
    public AnswerPackage(boolean isInDatabase)
    {
        this.isInDataBase = isInDatabase;
    }//end of constructor

    public AnswerPackage(String responseString, boolean isInDatabase)
    {
        this.responseString = responseString;
        this.isInDataBase = isInDatabase;
    }//end of constructor

    public String getQuestionString()
    {
        return questionString;
    }

    public void setQuestionString(String questionString)
    {
        this.questionString = questionString;
    }
    
    public String getResponseString()
    {
        return responseString;
    }

    public void setResponseString(String responseString)
    {
        this.responseString = responseString;
    }

    public boolean isIsInDataBase()
    {
        return isInDataBase;
    }

    public void setIsInDataBase(boolean isInDataBase)
    {
        this.isInDataBase = isInDataBase;
    }

    public DatabaseContainer getDatabaseContainer()
    {
        return DatabaseContainer;
    }

    public void setDatabaseContainer(DatabaseContainer DatabaseContainer)
    {
        this.DatabaseContainer = DatabaseContainer;
    }

    public OwnershipContainer getOwnershipContainer()
    {
        return ownershipContainer;
    }

    public void setOwnershipContainer(OwnershipContainer ownershipContainer)
    {
        this.ownershipContainer = ownershipContainer;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.questionString);
        hash = 17 * hash + Objects.hashCode(this.responseString);
        hash = 17 * hash + (this.isInDataBase ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final AnswerPackage other = (AnswerPackage) obj;
        if (!Objects.equals(this.questionString, other.questionString))
        {
            return false;
        }
        if (!Objects.equals(this.responseString, other.responseString))
        {
            return false;
        }
        if (this.isInDataBase != other.isInDataBase)
        {
            return false;
        }
        return true;
    }
    
    
}//end of class
