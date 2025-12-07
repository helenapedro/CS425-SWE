package products;

import java.math.BigDecimal;
import java.util.Objects;

public final class Product {
	private final String productNumber;
	private final BigDecimal price;
	private final String description;

	public Product(String productNumber, double price, String description) {
		if (productNumber == null || productNumber.isBlank()) {
            throw new IllegalArgumentException("productNumber must not be null or empty");
        }
        if (description == null) { description = ""; }

		this.productNumber = productNumber;
		this.price = BigDecimal.valueOf(price);
		this.description = description;
	}

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if(object == null || getClass() != object.getClass())
            return false;
        Product product = (Product) object;
        return productNumber.equals(product.productNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productNumber);
    }

    @Override
    public String toString() {
        return productNumber + " - " + description + " @ " + price;
    }

	public String getProductNumber() {
		return productNumber;
	}

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
