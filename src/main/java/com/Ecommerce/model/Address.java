package com.Ecommerce.model;

import static java.util.Objects.hash;
import org.jetbrains.annotations.NotNull;

public final class Address {

    private final String country;
    private final String region;
    private final String acronym;
    private final String city;
    private final String streetName;
    private final String streetNumber;

    private Address(Builder builder) {
        this.country = builder.country;
        this.region = builder.region;
        this.acronym = builder.acronym;
        this.city = builder.city;
        this.streetName = builder.streetName;
        this.streetNumber = builder.streetNumber;
    }

    public static Builder builder() {
        return new Builder();
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

    private static void validateFields(final String country, final String region, final String acronym,
                                       final String city, final String streetName, final String streetNumber) {
        Validator.requireNonBlank(country, "Country");
        Validator.requireNonBlank(region, "Region");
        Validator.requireNonBlank(acronym, "Acronym");
        Validator.requireNonBlank(city, "City");
        Validator.requireNonBlank(streetName, "StreetName");
        Validator.requireNonBlank(streetNumber, "StreetNumber");
    }

    public static class Builder {
        private String country;
        private String region;
        private String acronym;
        private String city;
        private String streetName;
        private String streetNumber;

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder region(String region) {
            this.region = region;
            return this;
        }

        public Builder acronym(String acronym) {
            this.acronym = acronym;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder streetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder streetNumber(String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public Address build() {
            validateFields(country, region, acronym, city, streetName, streetNumber);
            return new Address(this);
        }
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
