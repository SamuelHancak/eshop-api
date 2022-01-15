package sk.stuba.fei.uim.oop.assignment3;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Product update(long id, ProductRequest request);
    Product findById(long id);
    void deleteById(long id);
    int getAmountById(Product request);
    ProductAmount incrementAmount(long id, ProductAmount amount);
}
