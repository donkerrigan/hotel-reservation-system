/**
 * Created by Don Kerrigan on 11/17/2016.
 */
public class Hotel {
    private String name;
    private String location;
    public Room[] roomsArray;


    public Hotel(String name, String location)
    {
        this.name = name;
        this.location = location;
        roomsArray = new Room[300];

        for(int i=0; i<300; i++)
            roomsArray[i] = new Room(i);

    }

    public String getName()
    {
        return name;
    }

    public String getLocation()
    {
        return location;
    }
}
