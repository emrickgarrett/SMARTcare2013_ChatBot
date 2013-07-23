package DatabaseStorage;

import java.util.ArrayList;
import java.util.Objects;

/**
 * This is the template for the container object that will hold all of information regarding the database file and content information
 * @author William
 */
public class DatabaseContainer
{
    private String databaseName;
    private ArrayList<String> contents;

    public DatabaseContainer(String name, ArrayList<String> contents)
    {
        this.databaseName = name;
        this.contents = contents;
    }//end of constructor
    
    public String getDatabaseName()
    {
        return databaseName;
    }

    public void setDatabaseName(String databaseName)
    {
        this.databaseName = databaseName;
    }

    public ArrayList<String> getContents()
    {
        return contents;
    }

    public void setContents(ArrayList<String> contents)
    {
        this.contents = contents;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.databaseName);
        hash = 97 * hash + Objects.hashCode(this.contents);
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
        final DatabaseContainer other = (DatabaseContainer) obj;
        if (!Objects.equals(this.databaseName, other.databaseName))
        {
            return false;
        }
        if (!Objects.equals(this.contents, other.contents))
        {
            return false;
        }
        return true;
    }
    
    
    
}//end of class
