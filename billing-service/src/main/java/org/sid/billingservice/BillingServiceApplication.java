package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repositories.BillRepository;
import org.sid.billingservice.repositories.ProductItemRepository;
import org.sid.billingservice.service.CustomerRestClient;
import org.sid.billingservice.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient, ProductRestClient productRestClient, RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Bill.class);

            //Collection<Product> products = productRestClient.allProducts().getContent();

            Collection<Product> products = List.of(
                    Product.builder().id(1L).name("Computer").price(12).quantity(1200).build(),
                    Product.builder().id(2L).name("Printer").price(32).quantity(120).build(),
                    Product.builder().id(3L).name("Smartphone").price(31).quantity(900).build()
            );

            Long customerId = 1L;

            //Customer customer = customerRestClient.findCustomerById(customerId);
            Customer customer = new Customer();
            customer.setId(customerId);
            customer.setNom("Hassane");
            customer.setEmail("hass@gmail.com");

            if(customer==null) throw new RuntimeException("Customer Not Found !");

            Bill bill = new Bill();
            bill.setBillDate(new Date());
            bill.setCustomerId(customerId); //Mais je ne suis pas sûr que ce customer existe c'est pour cela qu'il faut verifier avec le if
            Bill savedBill = billRepository.save(bill);

            products.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setProductId(product.getId());
                productItem.setBill(savedBill);
                productItem.setQuantity(1+new Random().nextInt(10));
                productItem.setPrice(product.getPrice());
                productItem.setDiscount(Math.random());
                productItemRepository.save(productItem);
            });
        };
    }

}
