import java.util.Scanner;

/**
 * Created by Don Kerrigan on 11/17/2016.
 *
 */
public class Hotel {
    private String name;
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

    public void displayHotelMenu()
    {
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
                case "1": System.out.println("Feature currently unavailable. Sorry for the inconvenience.");
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

    private void displayAvailable()
    {
        int queen=0, king=0;
        for(int i=0; i<roomsArray.length; i++)
        {
            if(roomsArray[i].available) {
                if (roomsArray[i].bedSize.equals("Queen"))
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
