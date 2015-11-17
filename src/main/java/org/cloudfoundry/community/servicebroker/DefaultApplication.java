package org.cloudfoundry.community.servicebroker;

import org.cloudfoundry.community.servicebroker.catalog.Plan;
import org.cloudfoundry.community.servicebroker.catalog.ServiceDefinition;
import org.cloudfoundry.community.servicebroker.repositories.PlanRepository;
import org.cloudfoundry.community.servicebroker.repositories.ServiceDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Collections;
import java.util.UUID;


/** 
 * Default main for spring boot
 * @author sgreenberg
 *
 */
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan
public class DefaultApplication {
    public static void main(String[] args) {
        SpringApplication.run(DefaultApplication.class, args);
    }

    @Autowired
    ServiceDefinitionRepository serviceDefinitionRepository;

    @Autowired
    PlanRepository planRepository;

//    public Catalog catalog() {
//        ServiceDefinition serviceDefinition = new ServiceDefinition("scheduler-service", "scheduler", "A scheduler", true, Collections.singleton(new Plan(UUID.randomUUID().toString(), "Standard", "The standard scheduler")));
//        return new Catalog(Collections.singletonList(serviceDefinition));
//    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                Plan plan = new Plan(UUID.randomUUID().toString(), "Standard", "The standard scheduler");
                planRepository.save(plan);
                ServiceDefinition serviceDefinition = new ServiceDefinition("scheduler-service", "scheduler", "A scheduler", true, Collections.singleton(plan));
                serviceDefinitionRepository.save(serviceDefinition);
            }
        };
    }
}