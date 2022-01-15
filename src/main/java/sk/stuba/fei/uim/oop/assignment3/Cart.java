package sk.stuba.fei.uim.oop.assignment3;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<CartProduct> shoppingList = new ArrayList<>();
    private boolean payed;

    public void addProduct(CartProduct product){
        this.shoppingList.add(product);
    }
}
