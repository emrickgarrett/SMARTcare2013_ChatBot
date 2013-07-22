package DatabaseStorage;

/**
 * This class is designed to be a single return type for a method which needs to return multiple variables
 * @author William
 */
public class MethodReturnObject
{
    private int intHolder;
    private double doubleHolder;
    private float floatHolder;
    private String StringHolder;
    private long longHolder;
    private char charHolder;
    private boolean booleanHolder;
    
    public MethodReturnObject()
    {
        
    }//end of constructor

    public int getIntHolder()
    {
        return intHolder;
    }

    public void setIntHolder(int intHolder)
    {
        this.intHolder = intHolder;
    }

    public double getDoubleHolder()
    {
        return doubleHolder;
    }

    public void setDoubleHolder(double doubleHolder)
    {
        this.doubleHolder = doubleHolder;
    }

    public float getFloatHolder()
    {
        return floatHolder;
    }

    public void setFloatHolder(float floatHolder)
    {
        this.floatHolder = floatHolder;
    }

    public String getStringHolder()
    {
        return StringHolder;
    }

    public void setStringHolder(String StringHolder)
    {
        this.StringHolder = StringHolder;
    }

    public long getLongHolder()
    {
        return longHolder;
    }

    public void setLongHolder(long longHolder)
    {
        this.longHolder = longHolder;
    }

    public char getCharHolder()
    {
        return charHolder;
    }

    public void setCharHolder(char charHolder)
    {
        this.charHolder = charHolder;
    }

    public boolean isBooleanHolder()
    {
        return booleanHolder;
    }

    public void setBooleanHolder(boolean booleanHolder)
    {
        this.booleanHolder = booleanHolder;
    }
    
}//end of class
