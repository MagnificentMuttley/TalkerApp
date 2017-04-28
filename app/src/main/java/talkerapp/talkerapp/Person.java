package talkerapp.talkerapp;

public class Person
{
    private String name;
    private String surname;
    private String txt;
    
    public Person(String name, String surname, String txt)
    {
        this.name = name;
        this.surname = surname;
        this.txt = txt;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getSurname()
    {
        return surname;
    }
    
    public String getTxt()
    {
        return txt;
    }
}
