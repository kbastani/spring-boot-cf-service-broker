package org.cloudfoundry.community.servicebroker.service;

import org.cloudfoundry.community.servicebroker.exception.ServiceBrokerException;
import org.cloudfoundry.community.servicebroker.exception.ServiceInstanceBindingExistsException;
import org.cloudfoundry.community.servicebroker.model.CreateServiceInstanceBindingRequest;
import org.cloudfoundry.community.servicebroker.model.DeleteServiceInstanceBindingRequest;
import org.cloudfoundry.community.servicebroker.catalog.ServiceInstanceBinding;
import org.springframework.stereotype.Service;

@Service
public class ServiceInstanceBindingServiceImpl implements ServiceInstanceBindingService {
    /**
     * Create a new binding to a service instance.
     *
     * @param createServiceInstanceBindingRequest containing parameters sent from Cloud Controller
     * @return The newly created ServiceInstanceBinding
     * @throws ServiceInstanceBindingExistsException if the same binding already exists
     * @throws ServiceBrokerException                on internal failure
     */
    @Override
    public ServiceInstanceBinding createServiceInstanceBinding(CreateServiceInstanceBindingRequest createServiceInstanceBindingRequest) throws ServiceInstanceBindingExistsException, ServiceBrokerException {
        return new ServiceInstanceBinding(createServiceInstanceBindingRequest.getBindingId(), createServiceInstanceBindingRequest.getServiceInstanceId(), null, null, createServiceInstanceBindingRequest.getAppGuid());
    }

    /**
     * Delete the service instance binding. If a binding doesn't exist,
     * return null.
     *
     * @param deleteServiceInstanceBindingRequest containing parameters sent from Cloud Controller
     * @return The deleted ServiceInstanceBinding or null if one does not exist
     * @throws ServiceBrokerException on internal failure
     */
    @Override
    public ServiceInstanceBinding deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest deleteServiceInstanceBindingRequest) throws ServiceBrokerException {
        return null;
    }
}
