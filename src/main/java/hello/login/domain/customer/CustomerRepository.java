package hello.login.domain.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.*;

@Slf4j
@Repository
//@RequiredArgsConstructor
public class CustomerRepository {
    private static Map<Long, Customer> store = new HashMap<>(); //static 사용
    private static long sequence = 0L;//static 사용

    public Customer save(@Valid Customer customer) {
        customer.setId(++sequence);
        log.info("save: customer={}", customer);
        store.put(customer.getId(), customer);
        return customer;
    }

    public Customer findById(Long id) {
        return store.get(id);
    }

    public Optional<Customer> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<Customer> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
    /*private final EntityManager em;

    public List<Customer> findByLoginId(String loginId) {
        return em.createQuery("select m from Customer m where m.loginId=:loginId", Customer.class)
                .setParameter("loginId", loginId).getResultList();
    }

    public void save(Customer customer) {

        em.persist(customer);
    }

    public Customer findById(Long customerId) {

        return em.find(Customer.class, customerId);
    }*/
}
