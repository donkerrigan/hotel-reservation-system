/**
 * Created by Don Kerrigan on 11/17/2016.
 *
 */
public class Room {
    public int roomNumber, bedNum;
    public String bedSize;
    public boolean smoking, available;
    public double price;

    public Room(int roomNumber)
    {
        this.roomNumber = roomNumber;
        available = true;
        initialSetup();
    }

    private void initialSetup()
    {
        int rand = (int)(Math.random()*2);

        if(rand==1)
            smoking=true;
        else
            smoking=false;

        rand = (int)(Math.random()*2);

        if(rand==1) {
            bedNum = 1;
            bedSize = "King";
            price = 120.00;
        }
        else{
            bedNum = 2;
            bedSize = "Queen";
            price = 100.00;
        }
    }
}
