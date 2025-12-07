package shoppingcart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import products.Product;

public class ShoppingCart {
    private final List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("product must not be null");
        Optional<CartItem> existing = findByProductNumber(product.getProductNumber());
        if (existing.isPresent()) {
            existing.get().incrementQuantity(1);
        } else {
            items.add(new CartItem(product, 1));
        }
    }

    public void removeProduct(Product product) {
        if (product == null) throw new IllegalArgumentException("product must not be null");
        Optional<CartItem> existing = findByProductNumber(product.getProductNumber());
        if (existing.isPresent()) {
            CartItem item = existing.get();
            if (item.getQuantity() > 1) {
                item.decrementQuantity(1);
            } else {
                items.remove(item);
            }
        }
    }

    public BigDecimal totalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem item : items) {
            total = total.add(item.lineTotal());
        }
        return total;
    }

    public void printContents() {
        System.out.println("Shopping cart contents:");
        if (items.isEmpty()) {
            System.out.println("[empty]");
            return;
        }
        for (CartItem item : items) {
            System.out.println(item);
        }
        System.out.println("Total price = " + totalPrice());
    }

    private Optional<CartItem> findByProductNumber(String productNumber) {
        return items.stream()
                .filter(i -> i.getProduct().getProductNumber().equals(productNumber))
                .findFirst();
    }

    List<CartItem> getItemsSnapshot() {
        return new ArrayList<>(items);
    }
}
