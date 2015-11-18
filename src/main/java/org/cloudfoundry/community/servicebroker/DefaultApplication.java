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
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.Collections;
import java.util.UUID;


/**
 * Default main for spring boot
 *
 * @author sgreenberg
 * @author kbastani
 */
@SpringBootApplication
public class DefaultApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DefaultApplication.class).web(true).run(args);
    }

    @Autowired
    ServiceDefinitionRepository serviceDefinitionRepository;

    @Bean
    @Primary
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        return builder.json().defaultViewInclusion(false)
                .autoDetectFields(true)
                .indentOutput(true)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .build();
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                Plan plan = new Plan(UUID.randomUUID().toString(), "Standard", "The standard scheduler");
                ServiceDefinition serviceDefinition = new ServiceDefinition(UUID.randomUUID().toString(), "scheduler", "A scheduler", true, Collections.singleton(plan));
                serviceDefinition.setTags(null);
                serviceDefinitionRepository.save(serviceDefinition);
            }
        };
    }
}