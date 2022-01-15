package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart create() {
        Cart newCart = new Cart();
        newCart.setShoppingList(new ArrayList<>());
        newCart.setPayed(false);

        return this.repository.save(newCart);
    }

    @Override
    public Cart findById(long id) {
        return this.repository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        this.repository.deleteById(id);
    }

    @Override
    public Cart addProductToCart(Cart cart, CartProduct product) {
        Product addedProduct = this.productRepository.findById(product.getProductId());
        cart.addProduct(product);

        addedProduct.incrementAmount(-product.getAmount());
        this.productRepository.save(addedProduct);
        return this.repository.save(cart);
    }

    @Override
    public double payCart(Cart cart) {
        double sum = 0;
        cart.setPayed(true);

        for(var price : cart.getShoppingList()){
            Product product = this.productRepository.findById(price.getProductId());
            sum = sum + product.getPrice();
        }

        return sum;
    }
}
