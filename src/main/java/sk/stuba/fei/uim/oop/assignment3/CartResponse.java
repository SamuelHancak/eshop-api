package sk.stuba.fei.uim.oop.assignment3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CartResponse {
    private long id;
    private List<CartProduct> shoppingList;
    private boolean payed;

    public CartResponse(Cart cart){
        this.id = cart.getId();
        this.shoppingList = cart.getShoppingList();
        this.payed = cart.isPayed();
    }
}
