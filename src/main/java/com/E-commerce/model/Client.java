package project.store_game.model.entity.client;

import java.time.LocalDate;
import java.util.UUID;

import project.store_game.model.entity.Address;

public final class Client {

    private final UUID id;
    private final String name;
    private final String email;
    private final String phone;
    private final LocalDate birthDate;
    private final Address address;

    private Client(UUID id, String name, String email, String phone, LocalDate birthDate, Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.address = address;
    }
    public static Client create(UUID id, String name, String email, String phone, LocalDate birthDate, Address address) {
        id = (id == null)?UUID.randomUUID():id;
        validateFields(id, name, email, phone, birthDate, address);
        return new Client(id, name, email, phone, birthDate, address);
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Address getAddress() {
        return address;
    }

    private static void validateFields(final @NotNull UUID id,final @NotNull String name,final @NotNull String email,final @NotNull String phone,final @NotNull LocalDate birthDate,final @NotNull Address address) {
        LocalDate dateNow = LocalDate.now();
        if (name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be null or empty");
        if (email.trim().isEmpty()) throw new IllegalArgumentException("Email cannot be null or empty");
        if (phone.trim().isEmpty()) throw new IllegalArgumentException("Phone cannot be null or empty");
        if (birthDate.after(dateNow)) throw new IllegalArgumentException("Birth date cannot be in the future.");
        if (Period.between(birthDate, dateNow).getYears() <= 10) throw new IllegalArgumentException("User must be older than 10 years.");
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
