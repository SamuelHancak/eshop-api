package sk.stuba.fei.uim.oop.assignment3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    private int amount;
    private String unit;
    private double price;

    public void incrementAmount(int amount){
        int newAmount = this.getAmount() + amount;
        this.setAmount(newAmount);
    }
}
