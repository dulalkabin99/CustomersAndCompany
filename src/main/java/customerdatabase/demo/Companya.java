package customerdatabase.demo;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Companya {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String company;

    @OneToMany (mappedBy = "companya",
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
    public Set<Customer> customerSet;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Set<Customer> getCustomerSet() {
        return customerSet;
    }

    public void setCustomerSet(Set<Customer> customerSet) {
        this.customerSet = customerSet;
    }
}
