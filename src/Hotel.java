import java.util.Scanner;

/**
 * Created by Don Kerrigan on 11/17/2016.
 */
public class Hotel {
    private String name;
    private String location;
    public Room[] roomsArray;
    private Amenities amenities;
    Scanner input;

    public Hotel(String name, String location)
    {
        this.name = name;
        this.location = location;
        roomsArray = new Room[300];

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
            System.out.println("4: ");
            System.out.println("5: Return to the Main Menu");
            select = input.next();

            switch(select)
            {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Invalid Selection, try again.");
                    break;
            }
        }
    }

    private void initializeAmenities(){

    }
}
