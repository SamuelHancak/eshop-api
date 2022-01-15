package sk.stuba.fei.uim.oop.assignment3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private CartProduct shoppingList;
    private boolean payed;
}
