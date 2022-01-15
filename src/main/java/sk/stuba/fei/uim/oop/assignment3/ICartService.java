package sk.stuba.fei.uim.oop.assignment3;

public interface ICartService {
    Cart create();
    Cart findById(long id);
    void deleteById(long id);
    Cart addProductToCart(Cart cart, CartProduct amount);
    double payCart(Cart cart);
}
