package com.ecommerce.model;

import com.ecommerce.utils.Validator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public final class Client {

    public static @NotNull Client create(@Nullable UUID id,
                                         @NotNull String name,
                                         @NotNull String email,
                                         @NotNull String phone,
                                         @NotNull LocalDate birthDate,
                                         @Nullable Address address) {

        UUID newId = (id == null) ? UUID.randomUUID() : id;
        validateFields(name, email, phone, birthDate);
        return new Client(newId, name, email, phone, birthDate, address);
    }

    private static void validateFields(@NotNull String name,
                                       @NotNull String email,
                                       @NotNull String phone,
                                       @NotNull LocalDate birthDate) {

        Validator.requireNonBlank(name, "Name");
        Validator.requireNonBlank(email, "Email");
        Validator.requireNonBlank(phone, "Phone");
        Validator.requireNotFuture(birthDate, "Birth date");
        Validator.requireMinimumAge(birthDate, 10, "Birth date");
    }

    private final @NotNull UUID id;
    private final @NotNull String name;
    private final @NotNull String email;
    private final @NotNull String phone;
    private final @NotNull LocalDate birthDate;
    private @Nullable Address address;

    private Client(@NotNull UUID id,
                   @NotNull String name,
                   @NotNull String email,
                   @NotNull String phone,
                   @NotNull LocalDate birthDate,
                   @Nullable Address address) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
    }

    public @NotNull UUID getId() {
        return this.id;
    }

    public @NotNull String getName() {
        return this.name;
    }

    public @NotNull String getEmail() {
        return this.email;
    }

    public @NotNull String getPhone() {
        return this.phone;
    }

    public @NotNull LocalDate getBirthDate() {
        return this.birthDate;
    }

    public @Nullable Address getAddress() {
        return this.address;
    }

    public void setAddress(@NotNull Address address) {
        this.address = address;
    }

    @Override
    public @NotNull String toString() {
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
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return this.id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
