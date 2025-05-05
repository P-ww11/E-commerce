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
    public static Address create(final String country,final String region,final String acronym,final String city,final String streetName,final String streetNumber){
        validateFields(country, region, acronym, city, streetName, streetNumber);
        return new Address(country, region, acronym, city, streetName, streetNumber);
    }

    public String getCountry() {
        return country;
    }
    public String getRegion() {
        return region;
    }
    public String getAcronym() {
        return acronym;
    }

    public String getCity() {
        return city;
    }
    public String getStreetName() {
        return streetName;
    }
    public String getStreetNumber() {
        return streetNumber;
    }

    private static void validateFields(final @NotNull String country,final @NotNull String region,final @NotNull String acronym,final @NotNull String city,final @NotNull String streetName,final @NotNull String streetNumber) {
        if (country.isBlank()) throw new InvalidArgumentException("Country is required");
        if (region.isBlank()) throw new InvalidArgumentException("Region is required");
        if (acronym.isBlank()) throw new InvalidArgumentException("Acronym is required");
        if (city.isBlank()) throw new InvalidArgumentException("City is required");
        if (streetName.isBlank())  throw new InvalidArgumentException("Street Name is required");
        if (streetNumber.isBlank()) throw new InvalidArgumentException("Street Number is required");
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return country.equals(address.country) &&
               region.equals(address.region) &&
               acronym.equals(address.acronym) &&
               city.equals(address.city) &&
               streetName.equals(address.streetName) &&
               streetNumber.equals(address.streetNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, region, acronym, city, streetName, streetNumber);
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", acronym='" + acronym + '\'' +
                ", city='" + city + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                '}';
    }

}