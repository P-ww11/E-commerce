package com.Ecommerce.model;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public final class Client {

    private final UUID id;
    private final String name;
    private final String email;
    private final String phone;
    private final LocalDate birthDate;
    private final Address address;

    private Client(final UUID id,final String name,final String email,final String phone,final LocalDate birthDate,final Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
    }
    public static Client create(final UUID id,final @NotNull String name,final @NotNull String email,final @NotNull String phone,final @NotNull LocalDate birthDate,final @NotNull Address address) {
        UUID newId = (id == null)?UUID.randomUUID():id;
        validateFields(name, email, phone, birthDate);
        return new Client(newId, name, email, phone, birthDate, address);
    }


    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public Address getAddress() {
        return this.address;
    }

    private static void validateFields(final String name,final String email,final String phone,final LocalDate birthDate) {
        LocalDate dateNow = LocalDate.now();
        if (name.isBlank()) throw new IllegalArgumentException("Name cannot be empty");
        else if (email.isBlank()) throw new IllegalArgumentException("Email cannot be empty");
        else if (phone.isBlank()) throw new IllegalArgumentException("Phone cannot be empty");
        else if (birthDate.isAfter(dateNow)) throw new IllegalArgumentException("Birth date cannot be in the future.");
        else if (Period.between(birthDate, dateNow).getYears() <= 10) throw new IllegalArgumentException("User must be older than 10 years.");
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", email='" + this.email + '\'' +
                ", phone='" + this.phone + '\'' +
                ", birthDate=" + this.birthDate +
                ", address=" + this.address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return this.id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
