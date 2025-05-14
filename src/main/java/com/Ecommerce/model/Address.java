package com.Ecommerce.model;

import com.Ecommerce.utils.Validator;
import org.jetbrains.annotations.NotNull;


import static java.util.Objects.hash;

public final class Address {

    public static @NotNull Builder builder() {
        return new Builder();
    }

    private static void validateFields(@NotNull String country,
                                       @NotNull String region,
                                       @NotNull String continent,
                                       @NotNull String acronym,
                                       @NotNull String city,
                                       @NotNull String streetName,
                                       @NotNull String streetNumber ) {

        Validator.requireNonBlank(country, "Country");
        Validator.requireNonBlank(region, "Region");
        Validator.requireNonBlank(acronym, "Acronym");
        Validator.requireNonBlank(city, "City");
        Validator.requireNonBlank(streetName, "Street name");
        Validator.requireNonBlank(streetNumber, "Street number");
        Validator.requireNonBlank(continent, "Continent");
    }

    private final @NotNull String country;
    private final @NotNull String region;
    private final @NotNull String continent;
    private final @NotNull String acronym;
    private final @NotNull String city;
    private final @NotNull String streetName;
    private final @NotNull String streetNumber;

    private Address(@NotNull Builder builder) {
        this.country = builder.country;
        this.region = builder.region;
        this.continent = builder.continent;
        this.acronym = builder.acronym;
        this.city = builder.city;
        this.streetName = builder.streetName;
        this.streetNumber = builder.streetNumber;
    }

    public @NotNull String getCountry() {
        return country;
    }

    public @NotNull String getRegion() {
        return region;
    }
    
    public @NotNull String getContinent(){
        return continent;
    }

    public @NotNull String getAcronym() {
        return acronym;
    }

    public @NotNull String getCity() {
        return city;
    }

    public @NotNull String getStreetName() {
        return streetName;
    }

    public @NotNull String getStreetNumber() {
        return streetNumber;
    }

    public static final class Builder {

        private @NotNull String country;
        private @NotNull String region;
        private @NotNull String continent;
        private @NotNull String acronym;
        private @NotNull String city;
        private @NotNull String streetName;
        private @NotNull String streetNumber;

        public @NotNull Builder country(@NotNull String country) {
            this.country = country;
            return this;
        }

        public @NotNull Builder region(@NotNull String region) {
            this.region = region;
            return this;
        }

        public @NotNull Builder continent(@NotNull String continent){
            this.continent = continent;
            return this;
        }

        public @NotNull Builder acronym(@NotNull String acronym) {
            this.acronym = acronym;
            return this;
        }

        public @NotNull Builder city(@NotNull String city) {
            this.city = city;
            return this;
        }

        public @NotNull Builder streetName(@NotNull String streetName) {
            this.streetName = streetName;
            return this;
        }

        public @NotNull Builder streetNumber(@NotNull String streetNumber) {
            this.streetNumber = streetNumber;
            return this;
        }

        public @NotNull Address build() {
            validateFields(this.country, this.region, this.continent, this.acronym, this.city, this.streetName, this.streetNumber);
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
               this.continent.equals(address.continent) &&
               this.acronym.equals(address.acronym) &&
               this.city.equals(address.city) &&
               this.streetName.equals(address.streetName) &&
               this.streetNumber.equals(address.streetNumber);
    }

    @Override
    public int hashCode() {
        return hash(this.country, this.region, this.continent, this.acronym, this.city, this.streetName, this.streetNumber);
    }

    @Override
    public @NotNull String toString() {
        return "Address{" +
               "country='" + this.country + '\'' +
               ", region='" + this.region + '\'' +
               ", continent='" + this.continent + '\'' +
               ", acronym='" + this.acronym + '\'' +
               ", city='" + this.city + '\'' +
               ", streetName='" + this.streetName + '\'' +
               ", streetNumber='" + this.streetNumber + '\'' +
               '}';
    }
}
