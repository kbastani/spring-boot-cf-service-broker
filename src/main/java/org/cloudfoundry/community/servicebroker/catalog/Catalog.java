package org.cloudfoundry.community.servicebroker.catalog;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.cloudfoundry.community.servicebroker.repositories.ServiceDefinitionRepository;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The catalog of services offered by this broker.
 * 
 * @author sgreenberg@gopivotal.com
 */
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
@Service
public class Catalog implements Serializable {

    @NotEmpty
	@JsonSerialize
	@JsonProperty("services")
	private List<ServiceDefinition> serviceDefinitions = new ArrayList<>();

    @Autowired
    ServiceDefinitionRepository serviceDefinitionRepository;

    public Catalog() {
    }

    public Catalog(List<ServiceDefinition> serviceDefinitions) {
		this.setServiceDefinitions(serviceDefinitions); 
	}

    public List<ServiceDefinition> getServiceDefinitions() {
        if (serviceDefinitions.size() == 0) {
            // ensure serialization as an empty array, not null
            serviceDefinitions = new ArrayList<>(((Collection<ServiceDefinition>) serviceDefinitionRepository.findAll()));
        }

        return serviceDefinitions;
	}

	private void setServiceDefinitions(List<ServiceDefinition> serviceDefinitions) {
		if ( serviceDefinitions == null ) {
			// ensure serialization as an empty array, not null
			this.serviceDefinitions = new ArrayList<>(((Collection<ServiceDefinition>) serviceDefinitionRepository.findAll()));
		} else {
			this.serviceDefinitions = serviceDefinitions;
		} 
	}
	
}
