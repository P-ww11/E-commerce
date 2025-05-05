public final class Product{
    private final UUID id;
    private final String name;
    private final String description;
    private final double price;
    private final Category category;

    private Product(final UUID id,final String name,final String description,final double price,final Category category){
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public static Product of(final UUID id,final String name,final String description,final double price, final Category category){
        UUID newId = (id == null)? UUID.randomUUID():id;
        validateFields(newId,name,description,price, category);
        return new Product(newId,name,description,price, category);
    }

    public UUID getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }
    public double getPrice(){
        return price;
    }

    private static void validateFields(final UUID id,final String name,final String description,final double price, final Category category){
        if(id == null) throw new IllegalArgumentException("id cannot be null");
        if(name == null || name.isBlank()) throw new IllegalArgumentException("name cannot be null or empty");
        if(description == null || description.isBlank()) throw new IllegalArgumentException("description cannot be null or empty");
        if(price <= 0 || price > 99999) throw new IllegalArgumentException("price must be greater than 0 and less than or equal to 99999");
        if(category == null) throw new IllegalArgumentException("category cannot be null");
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", price=" + price +
               ", category=" + category +
               '}';
    }
}