package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRepository customerRepository,
                                               RepositoryRestConfiguration restConfiguration){
        return args -> {
            //Par defaut RestRessource n'expose pas l'id donc ajouté RestConfiguration pour exposer l'id
            restConfiguration.exposeIdsFor(Customer.class);
            customerRepository.saveAll(
                    List.of(
                            Customer.builder().nom("Hassane").email("hass@gmail.com").build(),
                            Customer.builder().nom("Julie").email("julgmail.com").build(),
                            Customer.builder().nom("Moussa").email("mousgmail.com").build()
                    )
            );
            customerRepository.findAll().forEach(customer -> {
                System.out.println(customer);
            });
        };
    }

}
