package kzasd.kz.myapplication;

/**
 * Created by Алишер on 14.06.2016.
 */
public class Apartment  {

    private String obgectId;
    private String Title;
    private int Price;
    private String Image;

    public String getObjectId() {
        return obgectId;
    }

    public void setObgectId(String obgectId) {
        this.obgectId = obgectId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
