package com.Ecommerce.model;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public final class Client {

    public static Client create(final @Nullable UUID id,final @NotNull String name,final @NotNull String email,final @NotNull String phone,final @NotNull LocalDate birthDate,final @Nullable Address address) {
        UUID newId = (id == null)?UUID.randomUUID():id;
        validateFields(name, email, phone, birthDate);
        return new Client(newId, name, email, phone, birthDate, address);
    }
    private final @NotNull UUID id;
    private final @NotNull String name;
    private final @NotNull String email;
    private final @NotNull String phone;
    private final @NotNull LocalDate birthDate;
    private @Nullable Address address;

    private Client(final @NotNull UUID id,final @NotNull String name,final @NotNull String email,final @NotNull String phone,final @NotNull LocalDate birthDate,final @Nullable Address address) {
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
    public void setAddress(@NotNull Address address){
        this.address = address;
    }

    private static void validateFields(final @NotNull String name,final @NotNull String email,final @NotNull String phone,final @NotNull LocalDate birthDate) {
        Validator.requireNonBlank(name, "Name");
        Validator.requireNonBlank(email, "Email");
        Validator.requireNonBlank(phone, "Phone");
        Validador.requireNotFuture(birthDate, "Birth date");
        Validator.requireMinimumAge(birthDate, 10, "Birth date")
        
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
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return this.id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }
}
