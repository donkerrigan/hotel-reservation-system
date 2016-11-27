import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/**
 * Created by Don Kerrigan on 11/17/2016.
 *
 */
public class HotelBrand {
    private String brandName;
    private List<Hotel> hotelLocations;

    public HotelBrand(String brandName)
    {
        File locationFile = new File("locationsFile.txt");
        Scanner fileIn;
        try{
            fileIn = new Scanner(locationFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found!");
            return;
        }
        hotelLocations = new ArrayList<>();
        this.brandName = brandName;
        while(fileIn.hasNext())
            hotelLocations.add(new Hotel(brandName, fileIn.nextLine()));
        fileIn.close();
    }

    public String getHotelBrand()
    {
        return brandName;
    }

    public void searchLocations()
    {
        List<Hotel> results = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        CharSequence search;
        do {
            System.out.println("Please enter the name of the city you are looking to book in(enter 'ALL' to view all locations or '0' to go to the Main Menu): ");
            search = input.nextLine();

            if (search.equals("ALL"))
                results.addAll(hotelLocations);

            else
                for (Hotel hotelLocation : hotelLocations)
                    if (hotelLocation.getLocation().contains(search))
                        results.add(hotelLocation);

            if (results.size() == 0) {
                System.out.println("\nNo results found.");
            } else {
                System.out.println("\nShowing Search Results Below: ");
                for (int i = 0; i < results.size(); i++) {
                    System.out.println((i + 1) + ": \t" + results.get(i).getName());
                    System.out.println("\t" + results.get(i).getLocation());
                }

                System.out.println("Select a location to look for availability(enter '0' to exit): ");
                boolean e = true;
                int select = 0;
                do {
                    try {
                        select = input.nextInt();
                        if (select <= results.size() && select > 0)
                            e = false;
                        else if (select == 0)
                            return;
                        else
                            throw new InputMismatchException();
                    } catch (InputMismatchException f) {
                        System.out.println("Invalid Location, try again(enter '0' to exit): ");
                        input.next();
                    }
                } while (e);

                results.get(select - 1).displayHotelMenu();
                return;
            }
        }while (!search.equals("0")) ;
    }
}
