package com.app.carsaathi.Pojo;

public class StoreItem {
    private int id;
    private String Image;
    private String CarName;
    private int Price;
    private String Type;
    private String Km ;
    private String Location;

    public StoreItem(int id,String Image, String CarName, int Price, String Type, String Km, String Location){
        this.id = id;
        this.CarName = CarName;
        this.Image = Image;
        this.Price = Price;
        this.Type = Type;
        this.Km = Km;
        this.Location = Location;
    }

    public int getId() {
        return id;
    }

    public String getCarName() {
        return CarName;
    }

    public String getImage() {
        return Image;
    }

    public int getPrice() {
        return Price;
    }

    public String getType() {
        return Type;
    }

    public String getKm() {
        return Km;
    }

    public String getLocation() {
        return Location;
    }
}
