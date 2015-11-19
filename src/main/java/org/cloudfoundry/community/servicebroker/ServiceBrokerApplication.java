package org.cloudfoundry.community.servicebroker;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cloudfoundry.community.servicebroker.catalog.Plan;
import org.cloudfoundry.community.servicebroker.catalog.ServiceDefinition;
import org.cloudfoundry.community.servicebroker.repositories.ServiceDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Arrays;
import java.util.Collections;


/**
 * Default main for spring boot
 *
 * @author sgreenberg
 * @author kbastani
 */
@SpringBootApplication
public class ServiceBrokerApplication {

    @Autowired
    private ServiceDefinitionRepository serviceDefinitionRepository;

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceBrokerApplication.class).web(true).run(args);
    }

    @Bean
    @Primary
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.json()
                .defaultViewInclusion(false)
                .autoDetectFields(true)
                .indentOutput(true)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .build();
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return strings -> {

            // Initialize the service broker definition when running in cloud profile
            if (Arrays.asList(environment.getActiveProfiles()).contains("cloud")) {
                // Initialize a default service definition for the purpose of this example
                Plan plan = new Plan("standard", "The standard scheduler");
                ServiceDefinition serviceDefinition = new ServiceDefinition("scheduler", "A scheduler", true, Collections.singleton(plan));
                serviceDefinitionRepository.save(serviceDefinition);
            }
        };
    }

}