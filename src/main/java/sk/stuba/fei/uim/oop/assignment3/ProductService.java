package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    public ProductRepository repository;

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        Product newProduct = new Product();
        newProduct.setAmount(request.getAmount());
        newProduct.setPrice(request.getPrice());
        newProduct.setUnit(request.getUnit());
        newProduct.setDescription(request.getDescription());
        newProduct.setName(request.getName());

        return this.repository.save(newProduct);
    }

    @Override
    public Product update(long id, ProductRequest request) {
        Product updateProduct = this.findById(id);
        String name = request.getName();
        String description = request.getDescription();

        if(name != null){
            updateProduct.setName(name);
        }

        if(description != null){
            updateProduct.setDescription(description);
        }
        return this.repository.save(updateProduct);
    }

    @Override
    public Product findById(long id) {
        return this.repository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        this.repository.deleteById(id);
    }

    @Override
    public int getAmountById(Product request) {
        return request.getAmount();
    }

    @Override
    public ProductAmount incrementAmount(long id, ProductAmount amount) {
        this.findById(id).incrementAmount(amount.getAmount());
        this.repository.save(this.findById(id));

        return new ProductAmount(this.findById(id).getAmount());
    }
}
