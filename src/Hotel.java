import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Don Kerrigan on 11/17/2016.
 *
 */
public class Hotel {
    private String name;
    private Customer user;
    private String number;
    private String email;
    private String location;
    private Room[] roomsArray;
    private Amenities amenities;
    private Scanner input;

    public Hotel(String name, String location)
    {
        number = "(555) 555-5555";
        this.name = name;
        this.location = location;
        roomsArray = new Room[300];
        email = location+"@"+name+".com";
        for(int i=0; i<300; i++)
            roomsArray[i] = new Room(i);

        initializeAmenities();
    }

    public String getName()
    {
        return name;
    }

    public String getLocation()
    {
        return location;
    }

    public void displayHotelMenu(Customer user)
    {
        this.user = user;
        input = new Scanner(System.in);
        String select = "";
        while(!select.equals("5")) {
            System.out.println("\n\nWelcome to the " + name +" Hotel at " + location + " Main Menu: ");
            System.out.println("1: Make a Reservation");
            System.out.println("2: List Amenities");
            System.out.println("3: List Number of Available Rooms");
            System.out.println("4: Show Contact Information");
            System.out.println("5: Return to the Main Menu");
            System.out.println("\nEnter Your Selection: ");
            select = input.next();

            switch(select)
            {
                case "1" :
                    if(user==null)
                        System.out.println("Sorry you must be logged in to use this feature.");
                    else
                        makeReservation();
                    break;
                case "2": listAmenities();
                    break;
                case "3": displayAvailable();
                    break;
                case "4": displayContact();
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid Selection, try again.");
                    break;
            }
        }
    }

    private void makeReservation()
    {
        File reservationFile = new File(name + location + "Reservations" + ".txt");

        try {
            Scanner inputFile = new Scanner(reservationFile);
            writeReservation(reservationFile, inputFile);
        } catch (FileNotFoundException e) {
            try {
                System.out.println("HERE");
                reservationFile.createNewFile();
                PrintWriter writer = new PrintWriter(reservationFile);
                writer.println(name + " " + location + " Reservations File");
                writer.println("Contact: \n" + "Phone: " + number + "\nEmail: " + email + "\n");
                writer.close();
                Scanner inputFile = new Scanner(reservationFile);
                writeReservation(reservationFile, inputFile);
            } catch (IOException f) {
                System.out.println("Sorry something went wrong. Going back to the main menu...");
                return;
            }
        }
    }

    private void writeReservation(File reservationFile, Scanner readReservation)
    {
        boolean correctFormat = false;
        while(readReservation.hasNextLine())
            readReservation.nextLine();
        try {
            System.out.println("Thank you for choosing to stay with us!\n\nWe just need to ask a few questions(Enter 'cancel' to cancel your reservation): ");
            System.out.println("Enter the Date you wish to make a reservation(dd-MMM-yyyy): ");
            if(input.hasNext())
                input.nextLine();
            String date = input.nextLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            Date date2=null;
            while(!correctFormat) {
                try {
                    if(date.equals("cancel")) {
                        System.out.print("Are you sure you want to cancel?(Y or N): ");
                        date = input.nextLine();
                        if (date.equals("Y"))
                            return;
                    }
                    else {
                        date2 = dateFormat.parse(date);
                        correctFormat = true;
                    }
                } catch (ParseException e) {
                    System.out.println("Sorry that was the wrong format of date...try again.");
                    System.out.println("Enter the Date you wish to make a reservation(dd-MMM-yyyy): ");
                    date = input.nextLine();
                }
            }


            System.out.print("What bed size do you need?(Queen or King): ");
            String bedSize = input.nextLine();
            while(!bedSize.equals("Queen") && !bedSize.equals("King")) {
                if(bedSize.equals("cancel")){
                    System.out.print("Are you sure you want to cancel?(Y or N): ");
                    bedSize = input.nextLine();
                    if(bedSize.equals("Y"))
                        return;
                }
                else
                    System.out.println("Sorry that was an incorrect selection. Try again.");
                System.out.print("What bed size do you need?(Queen or King): ");
                bedSize = input.nextLine();
            }

            System.out.print("Great!\nOne more question, would you like your room to be smoking friendly?(Y or N or 'cancel'): ");
            String smoke = input.nextLine();
            while(!smoke.equals("Y") && !smoke.equals("N"))
            {
                if(smoke.equals("cancel")){
                    System.out.print("Are you sure you want to cancel?(Y or N): ");
                    smoke = input.nextLine();
                    if(smoke.equals("Y"))
                        return;
                    else
                        smoke = null;
                }
                else
                    System.out.println("Sorry that was an incorrect selection. Try again.");
                System.out.print("Would you like your room to be smoking friendly?(Y or N): ");
                smoke = input.nextLine();
            }

            System.out.println("Great! Please confirm your reservation information below...");
            System.out.println("Name: " + user.getUsername());
            System.out.println("Date: " + date2);
            System.out.print("Bed Size: ");
            if(bedSize.equals("K"))
                System.out.println("King");
            else
                System.out.println("Queen");
            System.out.print("Smoking: ");
            if(smoke.equals("Y"))
                System.out.println("Yes");
            else
                System.out.println("No");

            System.out.println("Is this information correct?(Y or N or 'cancel'): ");
            String confirm = input.nextLine();
            while(!confirm.equals("Y") && !confirm.equals("N"))
            {
                if(confirm.equals("cancel")){
                    System.out.print("Are you sure you want to cancel?(Y or N): ");
                    confirm = input.nextLine();
                    if(confirm.equals("Y"))
                        return;
                    else
                        confirm = null;
                }
                else {
                    System.out.println("Sorry that was not an option...try again.");
                    System.out.println("Is this information correct?(Y or N or 'cancel'): ");
                    confirm = input.nextLine();
                }
            }
            if(confirm.equals("Y"))
            {
                try(FileWriter fw = new FileWriter(reservationFile, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw))
                {
                    out.println("Name: " + user.getUsername());
                    out.println("Date: " + date2);
                    out.println("BedSize: " + bedSize);
                    out.println("Smoking: " + smoke);
                    out.println("\n");
                    out.close();
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }
                try(FileWriter userWriter = new FileWriter(user.getUsername()+".txt", true);
                    BufferedWriter userBuff = new BufferedWriter(userWriter);
                    PrintWriter userOut = new PrintWriter(userBuff))
                {
                    userOut.println("Hotel: " + name + " at " + location);
                    userOut.println("Name: " + user.getUsername());
                    userOut.println("Date: " + date2);
                    userOut.println("BedSize: " + bedSize);
                    userOut.println("Smoking: " + smoke);
                    userOut.println("\n");
                    userOut.close();
                } catch (IOException e) {
                    //exception handling left as an exercise for the reader
                }
            }
            else if(confirm.equals("N"))
            {
                System.out.println("Okay let's start over...");
                writeReservation(reservationFile, readReservation);
                return;
            }

        }finally {

        }
    }

    private void displayAvailable()
    {
        int queen=0, king=0;
        for (Room aRoomsArray : roomsArray) {
            if (aRoomsArray.available) {
                if (aRoomsArray.bedSize.equals("Queen"))
                    queen++;
                else
                    king++;
            }
        }
        System.out.println("Displaying Number of Available Rooms:");
        System.out.println("\tTotal Rooms available: " + (queen+king));
        System.out.println("\tQueen Size Bedrooms Available:\t"+queen);
        System.out.println("\tKing Size Bedrooms Available:\t" + king);
    }

    public void displayContact()
    {
        System.out.println("\t" + name + "\n\tContact Us:");
        System.out.println("\tPhone number: " + number);
        System.out.println("\tEmail Address: " + email);
        System.out.println("\tLocation: " + location);
    }

    private void listAmenities()
    {
        System.out.println("Here is a list of the amenities at the " + name + " Hotel at " + location +": ");
        System.out.println("\n\tWater Around the Hotel:\n\t\t"+amenities.waterType);
        System.out.println("\tExercise Room:\n\t\t" + amenities.exerciseType);
        System.out.println("\tViews from Hotel: ");
        if(amenities.view)
            System.out.println("\t\tYes");
        else
            System.out.println("\t\tNo");
        System.out.println("\tEntertainment Available: \n\t\t" + amenities.entertainment);
    }

    private void initializeAmenities(){
        String waterType, exerciseType,entertainment;
        boolean view = false;
        int rand = (int)(Math.random()*3);
        if(rand == 0)
            waterType = "Pool";
        else if(rand == 1)
            waterType = "Ocean";
        else
            waterType = "Lake";
        rand = (int)(Math.random()*2);
        if(rand == 0)
            exerciseType = "Gym";
        else
            exerciseType = "None";
        rand = (int)(Math.random()*4);
        if(rand == 0)
            entertainment = "Business Computers";
        else if(rand == 1)
            entertainment = "Bar";
        else if(rand == 2)
            entertainment = "Bar and Restaurant";
        else
            entertainment = "None";
        rand = (int)(Math.random()*2);
        if(rand == 1)
            view = true;

        amenities = new Amenities(waterType, exerciseType, view, entertainment);
    }
}
