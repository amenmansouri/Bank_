package maa.softeam.entities;

public class Address {
    private String street;
    private String city;
    private int zipCode;
    private String extraInfo;

    public Address(String street, String city, int zipCode, String extraInfo) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.extraInfo = extraInfo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
