package DatabaseStorage;

import java.util.ArrayList;

/**
 * This is a specific implementation of the {@link DatabaseContainer} class which handles the ownership aspect of the bot
 * @author William
 */
public class OwnershipContainer extends DatabaseContainer
{
    private ArrayList<ArrayList<Integer>> referenceList;
    public OwnershipContainer(String name, ArrayList<String> contents)
    {
        super(name, contents);
    }//end of constructor

    public ArrayList<ArrayList<Integer>> getReferenceList()
    {
        return referenceList;
    }

    public void setReferenceList(ArrayList<ArrayList<Integer>> referenceList)
    {
        this.referenceList = referenceList;
    }
    
}//end of class
