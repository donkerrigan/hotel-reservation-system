import java.io.*;
import java.util.Scanner;

/**
 * Created by Don Kerrigan on 11/17/2016.
 *
 */
public class Customer {
    private String username;
    private String password;
    private File userFile;
    private PrintWriter writer;

    public Customer(String username)
    {
        this.username = username;
        this.userFile = new File(username+".txt");
    }

    public Customer()
    {
        Scanner input = new Scanner(System.in);
        boolean loop = true;
        do {
            System.out.print("\nPlease enter your desired username/email(enter '0' to exit to menu): ");
            username = input.nextLine();
            if(username.equals("0")) {
                username = null;
                return;
            }
            try {
                userFile = new File(username + ".txt");
                Scanner inputFile = new Scanner(userFile);
                System.out.println("\nSorry, that username is already taken. Try a different username.");
            } catch (FileNotFoundException e) {
                try {
                    userFile.createNewFile();
                    writer = new PrintWriter(userFile);
                    writer.println(username);
                    System.out.print("Great! We've created your new username.\nNow please enter your desired password(do not use any spaces):  ");
                    password = input.nextLine();
                    if(password.contains(" "))
                        while(password.contains(" "))
                        {
                            System.out.println("\nI'm sorry it looks like you used an invalid character in your password.\n\nTry a different password(do not use any spaces): ");
                            password = input.nextLine();
                        }
                    else
                    {
                        writer.println(password);
                        System.out.println("\nGreat! We have linked your password to your username!\nYou are now logged in!");
                        loop = false;
                        writer.println("");
                        writer.println("Reservations: ");
                        writer.println("");
                        writer.close();
                    }
                }
                catch(IOException f)
                {
                    System.out.println("Sorry something went wrong. Going back to the main menu...");
                    return;
                }
            }
        }while(loop);
    }
    public String getUsername()
    {
        return username;
    }
}
