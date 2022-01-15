package sk.stuba.fei.uim.oop.assignment3;

import jdk.jfr.Registered;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Registered
public interface CartRepository extends CrudRepository<Cart, Long> {
    List<Cart> findAll();

    Cart findById(long id);
}
