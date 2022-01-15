package sk.stuba.fei.uim.oop.assignment3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService service;

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCartById(@PathVariable("id") long id){
        if(this.isNotFound(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CartResponse(this.service.findById(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCart(@PathVariable("id") long id){
        if(this.isNotFound(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.service.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CartResponse> addCart(){
        Cart response = this.service.create();
        return new ResponseEntity<>(new CartResponse(response), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<CartResponse> addProductToCart(@PathVariable("id") long id, @RequestBody CartProduct product){
        if(this.isNotFound(id) || this.productService.findById(product.getProductId()) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cart cart = this.service.findById(id);
        if(cart.isPayed() || this.productService.findById(product.getProductId()).getAmount() < product.getAmount()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new CartResponse(this.service.addProductToCart(cart, product)), HttpStatus.OK);
    }

    @GetMapping("/{id}/pay")
    public ResponseEntity<Double> payCart(@PathVariable("id") long id){
        if(this.isNotFound(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cart cart = this.service.findById(id);
        if(cart.isPayed()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(this.service.payCart(cart), HttpStatus.OK);
    }

    private boolean isNotFound(long id){
        return this.service.findById(id) == null;
    }
}
