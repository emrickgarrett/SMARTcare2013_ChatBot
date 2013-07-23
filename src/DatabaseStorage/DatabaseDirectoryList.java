package DatabaseStorage;

import java.util.ArrayList;

/**
 * The purpose of this class is to provide a means by which the program is capable of holding multiple pieces of information
 * @author William
 */
public class DatabaseDirectoryList
{
    ArrayList<DatabaseContainer> databaseContainerList;
    ArrayList<OwnershipContainer> ownershipContainerList;
    
    public DatabaseDirectoryList()
    {
        
    }//end of constructor

    public DatabaseDirectoryList(ArrayList<DatabaseContainer> databaseContainerList, ArrayList<OwnershipContainer> ownershipContainerList)
    {
        this.databaseContainerList = databaseContainerList;
        this.ownershipContainerList = ownershipContainerList;
    }//end of constructor
    
    public ArrayList<DatabaseContainer> getDatabaseContainerList()
    {
        return databaseContainerList;
    }

    public void setDatabaseContainerList(ArrayList<DatabaseContainer> databaseContainerList)
    {
        this.databaseContainerList = databaseContainerList;
    }

    public ArrayList<OwnershipContainer> getOwnershipContainerList()
    {
        return ownershipContainerList;
    }

    public void setOwnershipContainerList(ArrayList<OwnershipContainer> ownershipContainerList)
    {
        this.ownershipContainerList = ownershipContainerList;
    }
    
    
}//end of class
