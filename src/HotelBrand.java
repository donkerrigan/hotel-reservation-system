import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Created by Don Kerrigan on 11/17/2016.
 */
public class HotelBrand {
    private Scanner input;
    public String brandName;
    List<Hotel> hotelLocations;

    public HotelBrand(String brandName)
    {
        File locationFile = new File("locationsFile.txt");
        Scanner fileIn = null;
        try{
            fileIn = new Scanner(locationFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        hotelLocations = new ArrayList<Hotel>();
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
        List<Hotel> results = new ArrayList<Hotel>();
        System.out.println("Please enter the name of the city you are looking to book in(enter 'ALL' to view all locations): ");
        input = new Scanner(System.in);
        CharSequence search;
        search = input.nextLine();

        if(search.equals("ALL"))
            for(int i=0; i<hotelLocations.size(); i++)
                results.add(hotelLocations.get(i));

        else {
            for(int i=0; i<hotelLocations.size(); i++)
                if(hotelLocations.get(i).getLocation().contains(search))
                    results.add(hotelLocations.get(i));
        }

        if(results.size() == 0) {
            System.out.println("\nNo results found.");
            return;
        }

        else {
            System.out.println("\nShowing Search Results Below: ");
            for(int i=0; i<results.size(); i++) {
                System.out.println(i + ": \t" + results.get(i).getName());
                System.out.println("\t" + results.get(i).getLocation());
            }

            System.out.println("Select a location to look for availability(enter '" + results.size() + "' to exit): ");
            int select = input.nextInt();
            while(select>results.size() || select<0)
            {
                System.out.println("Invalid Location, try again(enter '" + results.size() + "' to exit): ");
                select = input.nextInt();
            }

            if(select==results.size())
                return;

            else{
                results.get(select).displayHotelMenu();
            }
        }
    }
}
