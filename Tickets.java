import java.io.FileWriter;

public class Tickets {
    //Declare variables
    private char row;
    private int seats;
    private double price;
    private Person person;

    //Constructor to initialize Tickets object
    public Tickets(char row,int seats, double price, Person person){
        this.row = row;
        this.seats = seats;
        this.price = price;
        this.person = person;
    }

    //Retrieve row letter
    public char getRow(){return row;}
    //Retrieve seat number
    public int getSeats(){
        return  seats;
    }
    //Retrieve price
    public double getPrice(){
        return price;
    }
    //Retrieve person
    public Person getPerson() {
        return person;
    }

    //Set row letter
    public void setRow(char row){this.row = row;}
    //Set seat number
    public void setSeats(int seats){this.seats = seats;}
    //Set price
    public void setPrice(double price){this.price = price;}
    //Set person
    public void setPerson(Person person){this.person = person;}

    //Print ticket information
    public void sayTickets(){
        System.out.println("Row letter: " + getRow());
        System.out.println("Seat number: " + getSeats());
        System.out.println("Price of ticket: " + getPrice());
        System.out.println("Personal information: ");
        person.sayPerson();
    }

    //Save information to a file
    public void save(){
        char rowLetterSave = getRow();
        int seatNumSave = getSeats();
        String fileName = row + " " + seats +".txt";
        try{
            //Creating a file
            FileWriter writer = new FileWriter(fileName);
            //Writing information to a file
            writer.write("Ticket Information\n");
            writer.write("Row: " + rowLetterSave +"\n");
            writer.write("Seats: "+ seatNumSave+"\n");
            writer.write("Price: "+ price+"\n");

            writer.write("Personal Information\n");

            writer.write("First Name: " + person.getName() + "\n");
            writer.write("Last Name: " + person.getSurname() + "\n");
            writer.write("Email: " + person.getEmail() + "\n");
            writer.close();

            System.out.println("Ticket information saved to "+ fileName);
        }catch (Exception e){
            System.out.println("Error in ticket information");
        }
    }
}