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
            System.out.println("3: ");
            System.out.println("4: ");
            System.out.println("5: Exit System");
            response = input.next();

            switch(response) {
                case "1":
                    System.out.println("\nShowing all brand names: ");
                    for(int i=0; i<brands.size(); i++)
                        System.out.println(brands.get(i).getHotelBrand());
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
        if(input.hasNextLine())
            search = input.nextLine();
        search = input.nextLine();
        for(int i=0; i<brands.size(); i++)
            if(brands.get(i).getHotelBrand().contains(search))
                results.add(brands.get(i));

        if(results.size()==0) {
            System.out.println("Sorry no results were found.");
            return;
        }

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
        }
    }
}
