/**
 * Created by Don Kerrigan on 11/17/2016.
 */
public class Room {
    public int roomNumber, bedNum;
    public String bedSize;
    public boolean smoking;
    public double price;

    public Room(int roomNumber, int bedNum, String bedsize, boolean smoking, double price)
    {
        this.roomNumber = roomNumber;
        this.bedNum = bedNum;
        this.bedSize = bedsize;
        this.smoking = smoking;
        this.price = price;

    }
}
