public final class Cart {
    
    private final UUID id;
    private final User user;
    private final Set<ProductItem> items = new HashSet<>();

    private Cart(final UUID id,final User user){
        this.id = id;
        this.user = user;
    }

    public static Cart create(UUID id,User user) {
        id = (id == null)? UUID.randomUUID(): id;
        Objects.requireNonNull(user, "user cannot be null");
        return new Cart(id, user);
    }
    
    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public boolean add(ProductItem item){
        return items.add(Objects.requireNonNull(item, "product item cannot be null"));
    }

    public Set<ProductItem> getItems(){
        return Collections.unmodifiableSet(items);
    }

    public boolean remove(ProductItem item){
        if(!items.remove(Objects.requireNonNull(item, "product item cannot be null"))){
            throw new IllegalArgumentException("product not found in cart " + item);
        }
        return true; 
    }

}