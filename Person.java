public class Person {
    //Declaring variables
    private String name;
    private String surname;
    private String email;

    //Constructor to initialize Person object
    public Person(String name,String surname, String email){
        this.name =name;
        this.surname = surname;
        this.email = email;
    }

    //Retrieve person's name
    public String getName() {
        return name;
    }
    //Retrieve person's surname
    public String getSurname() {
        return surname;
    }
    //Retrieve person's email
    public String getEmail(){
        return  email;

    }
    //Set person's name
    public void setName() {
        this.name = name;
    }
    //Set person's surname
    public void setSurname() {
        this.surname = surname;
    }
    //Set person's email
    public void setEmail(){
        this.email= email;
    }

    //Print person's information
    public void sayPerson(){
        System.out.println("Your First Name: " + name);
        System.out.println("Your Last Name: " + surname);
        System.out.println("Your Email: " + email);
    }
}
