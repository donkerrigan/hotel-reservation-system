import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Created by Don Kerrigan on 11/17/2016.
 */
public class HotelBrand {
    private Scanner input;
    public String brandName;
    public Hotel[] hotelLocations;

    public HotelBrand(String brandName)
    {
        hotelLocations = new Hotel[10];
        this.brandName = brandName;
        for(int i=0; i<10; i++)
            hotelLocations[i] = new Hotel(brandName, ""+i);
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
        CharSequence search = input.next();
        if(search.equals("ALL"))
            for(int i=0; i<hotelLocations.length; i++)
                results.add(hotelLocations[i]);

        else {
            for(int i=0; i<hotelLocations.length; i++)
                if(hotelLocations[i].getName().contains(search))
                    results.add(hotelLocations[i]);
        }

        if(results.size() == 0) {
            System.out.println("No results found.");
            return;
        }

        else {
            System.out.println("Showing Search Results Below: ");
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
