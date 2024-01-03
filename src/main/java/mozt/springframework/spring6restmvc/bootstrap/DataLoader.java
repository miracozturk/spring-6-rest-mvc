package mozt.springframework.spring6restmvc.bootstrap;

import mozt.springframework.spring6restmvc.entity.Beverage;
import mozt.springframework.spring6restmvc.entity.Customer;
import mozt.springframework.spring6restmvc.model.BeverageStyle;
import mozt.springframework.spring6restmvc.repository.BeverageRepository;
import mozt.springframework.spring6restmvc.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Configuration
public class DataLoader implements CommandLineRunner {
    private final BeverageRepository bs;
    private final CustomerRepository cs;

    public DataLoader(BeverageRepository bs, CustomerRepository cs) {
            this.bs = bs;
            this.cs = cs;
    }


    @Override
    public void run(String... args) throws Exception {
        loadBeverage();
        loadCustomer();
    }

    private void loadCustomer() {


        Customer c1 = Customer.builder().customerName("CustomerName1")
                .id(UUID.randomUUID())
                .version(0)
                .lastModifiedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
        Customer c2 = Customer.builder().customerName("CustomerName2")
                .id(UUID.randomUUID())
                .version(0)
                .lastModifiedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
        Customer c3 = Customer.builder().customerName("CustomerName3")
                .id(UUID.randomUUID())
                .version(0)
                .lastModifiedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();

        this.cs.saveAll(List.of(c1, c2,c3));
        System.out.println("Customer example data habe been loaded");
    }

    private void loadBeverage() {
        Beverage beverage1 =
                Beverage.builder()
                        .id(UUID.randomUUID())
                        .version(1)
                        .beverageName("Cocacola")
                        .beverageStyle(BeverageStyle.ALE)
                        .upc("12356")
                        .price(new BigDecimal("12.99"))
                        .quantityOnHand(122)
                        .createdDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build();
        Beverage beverage2 =
                Beverage.builder()
                        .id(UUID.randomUUID())
                        .version(1)
                        .beverageName("Fanta")
                        .beverageStyle(BeverageStyle.ALE)
                        .upc("12356")
                        .price(new BigDecimal("13.99"))
                        .quantityOnHand(122)
                        .createdDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build();
        Beverage beverage3 =
                Beverage.builder()
                        .id(UUID.randomUUID())
                        .version(1)
                        .beverageName("Ayran")
                        .beverageStyle(BeverageStyle.ALE)
                        .upc("12356")
                        .price(new BigDecimal("14.99"))
                        .quantityOnHand(122)
                        .createdDate(LocalDateTime.now())
                        .updateDate(LocalDateTime.now())
                        .build();
        
        this.bs.saveAll(List.of(beverage1,beverage2,beverage3));
        System.out.println("Customer example data habe been loaded");

    }
}
