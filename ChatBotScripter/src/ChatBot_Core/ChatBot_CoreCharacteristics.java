package ChatBot_Core;

import ChatBot_Descriptors.Allergies;
import ChatBot_Descriptors.Gender;
import ChatBot_Descriptors.MentalStatus;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class contains all of the core characteristics of the ChatBot. This includes medical status
 * and personal response preferences.
 *
 * @author William
 */
public class ChatBot_CoreCharacteristics
{

    private String name;
    private String nickName;
    private int age;
    private int medicalRecordNumber;
    private String address;
    private String providerName;
    private String dateOfBirth;
    private Gender gender;
    private ArrayList<ChatBot_Medication> medicationList;
    private ArrayList<ChatBot_Disfunction> disfunctionList;
    private Allergies allergyLevel;
    private ArrayList<String> functionalLimits;
    private ArrayList<String> ActivitiesPermitted;
    private MentalStatus mentalStatus;

    public ChatBot_CoreCharacteristics(String name, int age, String dateOfBirth, Gender gender, MentalStatus mentalStatus)
    {
        this.name = name;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.mentalStatus = mentalStatus;
    }//end of constructor
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getMedicalRecordNumber()
    {
        return medicalRecordNumber;
    }

    public void setMedicalRecordNumber(int medicalRecordNumber)
    {
        this.medicalRecordNumber = medicalRecordNumber;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getProviderName()
    {
        return providerName;
    }

    public void setProviderName(String providerName)
    {
        this.providerName = providerName;
    }

    public String getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public ArrayList<ChatBot_Medication> getMedicationList()
    {
        return medicationList;
    }

    public void setMedicationList(ArrayList<ChatBot_Medication> medicationList)
    {
        this.medicationList = medicationList;
    }

    public ArrayList<ChatBot_Disfunction> getDisfunctionList()
    {
        return disfunctionList;
    }

    public void setDisfunctionList(ArrayList<ChatBot_Disfunction> disfunctionList)
    {
        this.disfunctionList = disfunctionList;
    }

    public Allergies getAllergyLevel()
    {
        return allergyLevel;
    }

    public void setAllergyLevel(Allergies allergyLevel)
    {
        this.allergyLevel = allergyLevel;
    }

    public ArrayList<String> getFunctionalLimits()
    {
        return functionalLimits;
    }

    public void setFunctionalLimits(ArrayList<String> functionalLimits)
    {
        this.functionalLimits = functionalLimits;
    }

    public ArrayList<String> getActivitiesPermitted()
    {
        return ActivitiesPermitted;
    }

    public void setActivitiesPermitted(ArrayList<String> ActivitiesPermitted)
    {
        this.ActivitiesPermitted = ActivitiesPermitted;
    }

    public MentalStatus getMentalStatus()
    {
        return mentalStatus;
    }

    public void setMentalStatus(MentalStatus mentalStatus)
    {
        this.mentalStatus = mentalStatus;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + this.age;
        hash = 89 * hash + Objects.hashCode(this.address);
        hash = 89 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 89 * hash + Objects.hashCode(this.gender);
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
        final ChatBot_CoreCharacteristics other = (ChatBot_CoreCharacteristics) obj;
        if (!Objects.equals(this.name, other.name))
        {
            return false;
        }
        if (this.age != other.age)
        {
            return false;
        }
        if (!Objects.equals(this.address, other.address))
        {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth))
        {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender))
        {
            return false;
        }
        return true;
    }
    
}//end of class
