package ChatBot_Core;

/**
 * This class contains all of the information for the "medications" that the ChatBot is "taking".
 * This allows for accurate responses from the ChatBot when asked questions regarding the medication
 * status.
 *
 * @author William
 */
public class ChatBot_Medication
{
    String medicationName;
    double dosageAmount;
    String medicationStartDate;

    public String getMedicationName()
    {
        return medicationName;
    }

    public void setMedicationName(String medicationName)
    {
        this.medicationName = medicationName;
    }

    public double getDosageAmount()
    {
        return dosageAmount;
    }

    public void setDosageAmount(double dosageAmount)
    {
        this.dosageAmount = dosageAmount;
    }

    public String getMedicationStartDate()
    {
        return medicationStartDate;
    }

    public void setMedicationStartDate(String medicationStartDate)
    {
        this.medicationStartDate = medicationStartDate;
    }
    
}//end of class
