import java.util.InputMismatchException;
import java.util.Scanner;

public class PlaneManagement {
    //Initializing the total_seat array
    private static final int[][] total_seats = new int[4][];
    //Creating Scanner object for use inputs
    private static final Scanner input = new Scanner(System.in);
    //Creating array to store Tickets object
    private static final Tickets[] ticket = new Tickets[52];
    //Creating array to store Person object
    private static final Person[] person = new Person[52];

    public static void main(String[] args) {
        int menuOption;
        //Setting seats each row should have
        total_seats[0] = new int[14];
        total_seats[1] = new int[12];
        total_seats[2] = new int[12];
        total_seats[3] = new int[14];

        //Variable decide program end or continue
        boolean endProgramme = false;

        System.out.println("Welcome to the Plane Management application ");

        while (!endProgramme) {
            //Display menu option
            System.out.println("*".repeat(50));
            System.out.println("*"+" ".repeat(18) + "MENU OPTION" + " ".repeat(19)+"*");
            System.out.println("*".repeat(50) + "\n");
            System.out.println("      1) Buy a seat ");
            System.out.println("      2) Cancel a seat ");
            System.out.println("      3) Find first seat available ");
            System.out.println("      4) Show seating plan ");
            System.out.println("      5) Print information and total sales ");
            System.out.println("      6) Search tickets ");
            System.out.println("      0) Quit ");
            System.out.println("*".repeat(50));

            try {
                //Selecting option
                System.out.print("Please select an option: ");
                menuOption = input.nextInt();

                switch (menuOption) {
                    case 1:
                        buy_seat();
                        break;
                    case 2:
                        cancel_seat();
                        break;
                    case 3:
                        find_first_available();
                        break;
                    case 4:
                        show_seating_plan();
                        break;
                    case 5:
                        print_tickets_info();
                        break;
                    case 6:
                        search_ticket();
                        break;
                    case 0:
                        System.out.println("Thank you. GoodBye!");
                        endProgramme = true;
                        break;
                    default:
                        System.out.println("Enter a valid menu option");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again");
                input.next();
            }

        }
    }

    //Booking Seats
    private static void buy_seat() {
        //Entering row letter of buying seat
        System.out.print("Enter the row letter (A-D): ");
        char rowLetterBuy = input.next().toUpperCase().charAt(0);
        //Getting the row index
        int rowNumBuy = rowLetterBuy - 'A';
        //Checking row number in valid range
        if (rowNumBuy < 0 || rowNumBuy >= total_seats.length) {
            System.out.println("Invalid row letter. Please try again");
            return;
        }
        //Entering the seat number
        System.out.print("Enter the seat number: ");
        int seatNumBuy = input.nextInt();
        //Checking seat number in valid range
        if (seatNumBuy < 1 || seatNumBuy > total_seats[rowNumBuy].length) {
            System.out.println("Invalid seat number. Please try again");
            return;
        }
        //Checking seats are sold or not
        if (total_seats[rowNumBuy][seatNumBuy - 1] == 1) {
            System.out.println("Seat already sold. Please select another seat.");
        } else {
            total_seats[rowNumBuy][seatNumBuy - 1] = 1;
            System.out.println("Seat " + rowLetterBuy + " " + seatNumBuy + " purchased successfully.");

            System.out.print("Enter the Name: ");
            String firstName = input.next();

            System.out.print("Enter the Surname: ");
            String lastName = input.next();

            System.out.print("Enter the Email: ");
            String email = input.next();

            //Ticket prices
            double ticketPrice;
            if (seatNumBuy < 6) {
                ticketPrice = 200;
            } else if (seatNumBuy < 10) {
                ticketPrice = 150;
            } else {
                ticketPrice = 180;
            }
            //Check all elements in ticket array and create ticket if it's null.
            for (int i = 0; i < ticket.length; i++) {
                if (ticket[i] == null) {
                    person[i] = new Person(firstName, lastName, email);
                    ticket[i] = new Tickets(rowLetterBuy, seatNumBuy, ticketPrice, person[i]);
                    //Saving ticket information
                    ticket[i].save();
                    System.out.println("Seats booked successfully.");
                    break;
                }
            }

        }

    }

    //Seat cancellation
    private static void cancel_seat() {

        //Entering row letter to cancel
        System.out.print("Enter the row letter (A-D): ");
        char rowLetterCancel = input.next().toUpperCase().charAt(0);
        //Converting it to index
        int rowNumCancel = rowLetterCancel - 'A';
        //Checking the row valid or not
        if (rowNumCancel < 0 || rowNumCancel >= total_seats.length) {
            System.out.println("Invalid row letter. Please try again.");
            return;
        }
        //Entering the seat number
        System.out.print("Enter the seat number: ");
        int seatNumCancel = input.nextInt();
        //Checking the row valid or not
        if (seatNumCancel < 1 || seatNumCancel > total_seats[rowNumCancel].length) {
            System.out.println("Invalid seat number. Please try again");
            return;
        }
        //Checking the seat booked or available
        if (total_seats[rowNumCancel][seatNumCancel - 1] == 0) {
            System.out.println("Seat already available, Please select another seat");
        } else {
            total_seats[rowNumCancel][seatNumCancel - 1] = 0;

            //Check all elements in ticket array and cancel ticket if it's null.
            for (int i = 0; i < ticket.length; i++) {
                if (!(ticket[i] == null)) {
                    ticket[i] = null;
                    break;
                }
            }
            System.out.println("Seat " + rowLetterCancel + " " + seatNumCancel + " canceled successfully");

        }


    }

    //Find first available seat
    private static void find_first_available() {
        boolean findFirst = false;
        //Looping through row
        for (int rowFirst = 0; rowFirst < total_seats.length; rowFirst++) {
            //Looping through seat
            for (int seatFirst = 0; seatFirst < total_seats[rowFirst].length; seatFirst++) {
                //Checking the availability
                if (total_seats[rowFirst][seatFirst] == 0) {
                    //Calculating row letter based on index
                    char rawLetter = (char) ('A' + rowFirst);

                    System.out.println("First available seat " + rawLetter + " " + (seatFirst + 1));

                    findFirst = true;
                    break;
                }
            }
            if (findFirst) {
                break;
            }
        }
        //If no seat is available seat
        if (!findFirst) {
            System.out.println("No seat is available");
        }
    }

    //Show seating plan
    private static void show_seating_plan() {
        //Looping through each row
        for (int rowPlan = 0; rowPlan < total_seats.length; rowPlan++) {
            //Looping though each seat
            for (int seatPlan = 0; seatPlan < total_seats[rowPlan].length; seatPlan++) {
                //Checking current seat availability
                if (total_seats[rowPlan][seatPlan] == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }

    //Print ticket information
    private static void print_tickets_info() {
        // Initializing totalPrice
        double totalPrice = 0;

        //Check all elements in ticket array and print ticket price if it's not null.
        for (int k = 0; k < ticket.length; k++) {
            if (!(ticket[k] == null)) {
                System.out.println("Ticket Information:");
                System.out.println("Row: " + ticket[k].getRow());
                System.out.println("Seats: " + ticket[k].getSeats());
                System.out.println("Price: £" + ticket[k].getPrice());
                System.out.println("Personal Information:");
                System.out.println("Name: " + person[k].getName());
                System.out.println("Surname: " + person[k].getSurname());
                System.out.println("Email: " + person[k].getEmail());
                totalPrice += ticket[k].getPrice();
            }
        }
        System.out.println("Total price is  £ "+totalPrice);
    }

    //Search for tickets
    private static void search_ticket() {
        //Entering the row letter
        System.out.print("Enter the row letter (A-D): ");
        char rowLetterSearch = input.next().toUpperCase().charAt(0);
        //Converting row leetr to index
        int rowNumSearch = rowLetterSearch - 'A';
        //Checking row letter is valid or not
        if (rowNumSearch < 0 || rowNumSearch >= total_seats.length) {
            System.out.println("Invalid row letter. Please try again");
            return;
        }
        //Entering the seat number
        System.out.print("Enter the seat number: ");
        int seatNumSearch = input.nextInt();
        //Checking the seat number is valid or not
        if (seatNumSearch < 1 || seatNumSearch > total_seats[rowNumSearch].length) {
            System.out.println("Invalid seat number. Please try again");
            return;
        }
        //Get false if ticket not found
        boolean seatFound = false;
        //Loop to find the each booked matching seat
        for (int j = 0;  j < 52;  j++) {
            //Checking ticket is not null and matches row & seat number
            if (ticket[j] != null && ticket[j].getRow() == rowLetterSearch && ticket[j].getSeats() == seatNumSearch) {
                //Printing ticket information
                System.out.println("Ticket Information:");
                System.out.println("Seat: " + rowLetterSearch + " " +seatNumSearch);
                System.out.println("Price: £" + ticket[j].getPrice());
                //Print personal information
                System.out.println("Person Information:");
                System.out.println("First Name: " + person[j].getName());
                System.out.println("Last Name: " + person[j].getSurname());
                System.out.println("Email: " + person[j].getEmail());
                seatFound = true;
                break;
            }
        }
        if (!seatFound) {
            System.out.println("This seat is available");
        }
    }
}