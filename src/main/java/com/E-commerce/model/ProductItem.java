public final class ProductItem{

    private final UUID id;
    private final Product product;
    private final int quantity;
    private final Cart cart;
    private final Order order;

    private ProductItem(final UUID id,final Product product,final int quantity,final Cart cart,final Order order){
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.cart = cart;
        this.order = order;
    }
    public static ProductItem of(final UUID id,final Product product,final int quantity,final Cart cart,final Order order){
        UUID newId = (id == null)? UUID.randomUUID():id;
        validateFields(newId,product,quantity,cart,order);
        return new ProductItem(newId,product,quantity,cart,order);
    }

    public UUID getId(){
        return id;
    }
    public Product getProduct(){
        return product;
    }
    public int getQuantity(){
        return quantity;
    }
    public Cart getCart(){
        return cart;
    }
    public Order getOrder(){
        return order;
    }

    private static void validateFields(final UUID id, final Product product, final int quantity, final Cart cart, final Order order) {
        if (id == null) throw new IllegalArgumentException("ID must not be null");
        if (product == null) throw new IllegalArgumentException("Product must not be null");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0");
        if (cart == null && order == null) throw new IllegalArgumentException("Either Cart or Order must be present");
    }

     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductItem)) return false;
        ProductItem that = (ProductItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", cart=" + cart +
                ", order=" + order +
                '}';
    }
}