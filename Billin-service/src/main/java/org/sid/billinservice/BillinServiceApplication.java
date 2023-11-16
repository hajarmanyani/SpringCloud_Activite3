package org.sid.billinservice;
import org.sid.billinservice.entities.Bill;
import org.sid.billinservice.entities.productItem;
import org.sid.billinservice.model.Customer;
import org.sid.billinservice.model.Product;
import org.sid.billinservice.repository.BillRepository;
import org.sid.billinservice.repository.ProductItemRepository;
import org.sid.billinservice.service.CustomerRestClient;
import org.sid.billinservice.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication @EnableFeignClients
public class BillinServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(BillinServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient){
        return args -> {
            Collection<Product> products=productRestClient.allProducts().getContent();
            Long customerId= 1L;
            Customer customer=customerRestClient.findCustomerById(customerId);
            if(customer==null) throw new RuntimeException("Customer not Found");
            Bill bill=new Bill();
            bill.setBillDate(new Date());
            bill.setCustomerId(customerId);
            Bill savedBill=billRepository.save(bill);
            products.forEach(p ->{
                productItem productItm=new productItem();
                productItm.setBill(savedBill);
                productItm.setProductId(p.getId());
                productItm.setQuantity(1+new Random().nextInt(10));
                productItm.setPrice(p.getPrice());
                productItm.setDiscount(Math.random());
                productItemRepository.save(productItm);
            });
        };
    }
}

