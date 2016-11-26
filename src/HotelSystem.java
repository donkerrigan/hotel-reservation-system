/**
 * Created by Don Kerrigan on 11/17/2016.
 */
public class HotelSystem {
    public HotelBrand[] brands;

    public HotelSystem()
    {
        brands = new HotelBrand[10];
        for(int i=0; i<10; i++)
            brands[i] = new HotelBrand("Brand " + i);
    }

    public void main(String[] args)
    {
        HotelSystem system = new HotelSystem();


    }
}
