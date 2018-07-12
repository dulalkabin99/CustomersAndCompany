package customerdatabase.demo;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
    Iterable <Customer> findAllByLastnameContaining(String lastName);
    Iterable <Customer> findAllByStateContaining(String state);

}
