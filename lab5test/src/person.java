

public class person implements java.io.Serializable
{
    String name;
    String surname;
    String patronymic;
    String age;
    String someBiography;
    String gender;
    public person(String new_name, String new_surname, String new_patronymic, String new_age, String new_biography, boolean gender)
    {
        name = new_name;
        surname = new_surname;
        patronymic = new_patronymic;
        age = new_age;
        someBiography = new_biography;
        if(gender)
            this.gender = "Male";
        else
            this.gender = "Female";
    }
    public String getInitials()
    {
        return name + " " + surname;
    }
    public String getInformation()
    {
        return getInitials() + "\n" + patronymic + "\nBorn: " + age + "\nGender: " + gender + "\nBiography: " + someBiography;
    }
}