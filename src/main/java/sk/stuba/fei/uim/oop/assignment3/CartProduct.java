package sk.stuba.fei.uim.oop.assignment3;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class CartProduct {
    @Id
    private long productId;
    private int amount;
}
