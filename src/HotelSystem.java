import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Don Kerrigan on 11/17/2016.
 */
public class HotelSystem {
    public HotelBrand[] brands;
    Scanner input;

    public HotelSystem()
    {
        brands = new HotelBrand[10];
        input = new Scanner(System.in);
        for(int i=0; i<10; i++)
            brands[i] = new HotelBrand("Brand " + i);
        displayMenu();
    }

    public static void main(String[] args)
    {
        HotelSystem system = new HotelSystem();

    }

    private void displayMenu()
    {
        String response = "0";
        while(!response.equals("5"))
        {
            System.out.println("\n\n\nHotel Reservation Main Menu: \n");
            System.out.println("1: Display Available Hotel Brands.");
            System.out.println("2: Search for Hotel Brand Name");
            System.out.println("3: ");
            System.out.println("4: ");
            System.out.println("5: Exit System");
            response = input.next();

            switch(response) {
                case "1":
                    break;
                case "2": searchHotelBrand();
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Invalid Selection, please try again.");
                    break;
            }
        }
    }

    public void searchHotelBrand()
    {
        CharSequence search;
        List<HotelBrand> results = new ArrayList<HotelBrand>();
        System.out.println("\nEnter your search for Hotel Brand Names: ");
        search = input.next();

        for(int i=0; i<brands.length; i++)
            if(brands[i].getHotelBrand().contains(search))
                results.add(brands[i]);

        if(results.size()==0) {
            System.out.println("Sorry no results were found.");
            return;
        }

        else {
            System.out.println("\nShowing search results: ");
            for (int i = 0; i < results.size(); i++)
                System.out.println(i + ": " + results.get(i).getHotelBrand());
            System.out.println("\nSelect a Brand you wish to view(enter '" + results.size() + "' to go to main menu): ");
            int brand = input.nextInt();

            while(brand<0 || brand>results.size())
            {
                System.out.println("Invalid Selection, try again(enter '" + (results.size()) + "' to go to main menu): ");
                brand = input.nextInt();
            }

            if(brand>0 && brand<results.size())
                results.get(brand).searchLocations();
            else
                return;

        }
    }
}
