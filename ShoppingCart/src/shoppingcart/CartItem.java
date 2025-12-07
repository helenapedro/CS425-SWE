package shoppingcart;

import products.Product;

import java.math.BigDecimal;
import java.util.Objects;

public class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int initialQuantity) {
        if (product == null) throw new IllegalArgumentException("product is required");
        if (initialQuantity <= 0) throw new IllegalArgumentException("initialQuantity must be > 0");
        this.product = product;
        this.quantity = initialQuantity;
    }

    public void incrementQuantity(int delta) {
        if (delta <= 0) throw new IllegalArgumentException("delta must be positive");
        quantity += delta;
    }

    public void decrementQuantity(int delta) {
        if (delta <= 0) throw new IllegalArgumentException("delta must be positive");
        if (delta > quantity) throw new IllegalArgumentException("delta is greater than current quantity");
        quantity -= delta;
    }

    public BigDecimal lineTotal() {
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        CartItem cartItem = (CartItem) object;
        return product.equals(cartItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }

    @Override
    public String toString() {
        return quantity + " x " + product.getProductNumber() + " (" + product.getDescription() + ") - " + product.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
