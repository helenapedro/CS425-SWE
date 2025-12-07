package application;

import products.Product;
import shoppingcart.ShoppingCart;


public class Application {
	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();

		Product tv = new Product("A123", 100.0, "TV");
		cart.addProduct(tv);

        Product mp3 = new Product("A665", 75.0, "MP3 Player");
		cart.addProduct(mp3);
        cart.addProduct(mp3); // add second unit
		
		cart.printContents();
		
		cart.removeProduct(mp3);
		cart.removeProduct(mp3); // remove second unit -> should remove line
		
		cart.printContents();

	}

}
