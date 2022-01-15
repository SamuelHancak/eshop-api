package sk.stuba.fei.uim.oop.assignment3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAmount {
    private int amount;

    public ProductAmount(ProductAmount amount){
        this.setAmount(amount.getAmount());
    }
}
