package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping()
    public List<ProductResponse> getAllProducts(){
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") long id){
        if(this.isNotFound(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ProductResponse(this.service.findById(id)), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest newProduct) {
        return new ResponseEntity<>(new ProductResponse(this.service.create(newProduct)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") long id, @RequestBody ProductRequest product){
        if(this.isNotFound(id)){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ProductResponse(this.service.update(id, product)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id){
        if(this.isNotFound(id)){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.service.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/amount")
    public ResponseEntity<ProductAmount> getAmount(@PathVariable("id") long id){
        if(this.isNotFound(id)){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductAmount productAmount = new ProductAmount(this.service.getAmountById(this.service.findById(id)));

        return new ResponseEntity<>(productAmount, HttpStatus.OK);
    }

    @PostMapping("/{id}/amount")
    public ResponseEntity<ProductAmount> incrementAmount(@PathVariable("id") long id, @RequestBody ProductAmount amount){
        if(this.isNotFound(id)){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(this.service.incrementAmount(id, amount), HttpStatus.OK);
    }

    private boolean isNotFound(long id){
        return this.service.findById(id) == null;
    }

}
