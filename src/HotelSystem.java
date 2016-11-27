import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Don Kerrigan on 11/17/2016.
 */
public class HotelSystem {
    public List<HotelBrand> brands;
    Scanner input;

    public HotelSystem()
    {
        File brandFile = new File("brandFile.txt");
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(brandFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        brands = new ArrayList<HotelBrand>();
        input = new Scanner(System.in);
        while(fileIn.hasNext())
            brands.add(new HotelBrand(fileIn.nextLine()));
        fileIn.close();
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
            System.out.println("3: Login");
            System.out.println("4: Create Account");
            System.out.println("5: Exit System");
            System.out.print("\nEnter Your Selection: ");
            response = input.next();

            switch(response) {
                case "1":
                    System.out.println("\nShowing all brand names: ");
                    for (HotelBrand brand : brands)
                        System.out.println(brand.getHotelBrand());
                    break;
                case "2": searchHotelBrand();
                    break;
                case "3": System.out.println("Feature currently unavailable. Sorry for the inconvenience.");
                    break;
                case "4": System.out.println("Feature currently unavailable. Sorry for the inconvenience.");
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
        do{


        System.out.println("\nEnter your search for Hotel Brand Names(enter '0' to go back to the menu): ");
        if(input.hasNextLine())
            search = input.nextLine();
        search = input.nextLine();
        for (HotelBrand brand1 : brands)
            if (brand1.getHotelBrand().contains(search))
                results.add(brand1);

        if(results.size()==0)
            System.out.println("Sorry no results were found.");

        else {
            System.out.println("\nShowing search results: ");
            for (int i = 0; i < results.size(); i++)
                System.out.println((i+1) + ": " + results.get(i).getHotelBrand());
            System.out.println("\nSelect a Brand you wish to view(enter '0' to go to main menu): ");
            boolean e = true;
            int brand = -1;
            do
            {
                try {
                    brand = input.nextInt();
                    if(brand>0 && brand<=results.size())
                        e = false;
                    else if(brand == 0)
                        return;
                    else
                        throw new InputMismatchException();
                }
                catch(InputMismatchException f)
                {
                    System.out.println("Invalid Selection, try again(enter '0' to go to main menu): ");
                    input.next();
                }
            }while(e);

            results.get(brand-1).searchLocations();
            return;
        }
        }while(!search.equals("0"));
    }
}
