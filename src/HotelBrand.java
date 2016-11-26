/**
 * Created by Don Kerrigan on 11/17/2016.
 */
public class HotelBrand {
    private String brandName;
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
}
