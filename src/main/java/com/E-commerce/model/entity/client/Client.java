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
    private final Cart cart;

    private Client(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
        this.birthDate = builder.birthDate;
        this.address = builder.address;
        this.cart = builder.cart;
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

    public Cart getCart() {
        return cart;
    }

    public static final class Builder {

        private UUID id;
        private String name;
        private String email;
        private String phone;
        private LocalDate birthDate;
        private Address address;
        private Cart cart;

        public Builder(UUID id, String name, String email, String phone, LocalDate birthDate, Address address) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.birthDate = birthDate;
            this.address = address;
            this.cart = new Cart(); 
        }

        public Client build() {
            validateFields();
            return new Client(this);
        }

        private void validateFields() {
            if (id == null) throw new IllegalArgumentException("ID não pode ser nulo");
            if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
            if (email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
            if (phone == null || phone.trim().isEmpty()) throw new IllegalArgumentException("Telefone não pode ser nulo ou vazio");
            if (birthDate == null) throw new IllegalArgumentException("Data de nascimento não pode ser nula");
            if (address == null) throw new IllegalArgumentException("Endereço não pode ser nulo");
        }

        public static Client fromStringId(String id, String name, String email, String phone, LocalDate birthDate, Address address) {
            return new Builder(UUID.fromString(id), name, email, phone, birthDate, address).build();
        }
    }
}
