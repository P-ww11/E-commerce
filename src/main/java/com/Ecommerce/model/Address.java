package com.Ecommerce.model;

import static java.util.Objects.hash;

import jakarta.validation.constraints.NotNull;

public final class Address{

    private final String country;
    private final String region;
    private final String acronym;
    private final String city;
    private final String streetName;
    private final String streetNumber;

    private Address(final  String country,final String region,final String acronym,final String city,final String streetName,final String streetNumber){
        this.country = country;
        this.region = region;
        this.acronym = acronym;
        this.city = city;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
    }
    public static Address create(final @NotNull String country,final @NotNull String region,final @NotNull String acronym,final @NotNull String city,final @NotNull String streetName,final @NotNull String streetNumber){
        validateFields(country, region, acronym, city, streetName, streetNumber);
        return new Address(country, region, acronym, city, streetName, streetNumber);
    }

    public String getCountry() {
        return this.country;
    }
    public String getRegion() {
        return this.region;
    }
    public String getAcronym() {
        return this.acronym;
    }

    public String getCity() {
        return this.city;
    }
    public String getStreetName() {
        return this.streetName;
    }
    public String getStreetNumber() {
        return this.streetNumber;
    }

    private static void validateFields(final String country,final String region,final String acronym,final String city,final String streetName,final String streetNumber) {
        if (country.isBlank()) throw new IllegalArgumentException("Country is required");
        else if (region.isBlank()) throw new IllegalArgumentException("Region is required");
        else if (acronym.isBlank()) throw new IllegalArgumentException("Acronym is required");
        else if (city.isBlank()) throw new IllegalArgumentException("City is required");
        else if (streetName.isBlank())  throw new IllegalArgumentException("Street Name is required");
        else if (streetNumber.isBlank()) throw new IllegalArgumentException("Street Number is required");
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return this.country.equals(address.country) &&
               this.region.equals(address.region) &&
               this.acronym.equals(address.acronym) &&
               this.city.equals(address.city) &&
               this.streetName.equals(address.streetName) &&
               this.streetNumber.equals(address.streetNumber);
    }

    @Override
    public int hashCode() {
        return hash(this.country, this.region, this.acronym, this.city, this.streetName, this.streetNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + this.country + '\'' +
                ", region='" + this.region + '\'' +
                ", acronym='" + this.acronym + '\'' +
                ", city='" + this.city + '\'' +
                ", streetName='" + this.streetName + '\'' +
                ", streetNumber='" + this.streetNumber + '\'' +
                '}';
    }

}