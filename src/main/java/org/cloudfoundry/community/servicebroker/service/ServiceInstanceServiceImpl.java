package org.cloudfoundry.community.servicebroker.service;

import org.cloudfoundry.community.servicebroker.exception.ServiceBrokerException;
import org.cloudfoundry.community.servicebroker.exception.ServiceInstanceDoesNotExistException;
import org.cloudfoundry.community.servicebroker.exception.ServiceInstanceExistsException;
import org.cloudfoundry.community.servicebroker.exception.ServiceInstanceUpdateNotSupportedException;
import org.cloudfoundry.community.servicebroker.model.CreateServiceInstanceRequest;
import org.cloudfoundry.community.servicebroker.model.DeleteServiceInstanceRequest;
import org.cloudfoundry.community.servicebroker.catalog.ServiceInstance;
import org.cloudfoundry.community.servicebroker.model.UpdateServiceInstanceRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServiceInstanceServiceImpl implements ServiceInstanceService {


    /**
     * Create a new instance of a service
     *
     * @param createServiceInstanceRequest containing the parameters from CloudController
     * @return The newly created ServiceInstance
     * @throws ServiceInstanceExistsException if the service instance already exists.
     * @throws ServiceBrokerException         if something goes wrong internally
     */
    @Override
    public ServiceInstance createServiceInstance(CreateServiceInstanceRequest createServiceInstanceRequest) throws ServiceInstanceExistsException, ServiceBrokerException {
        ServiceInstance serviceInstance = new ServiceInstance(createServiceInstanceRequest);

        return serviceInstance.withDashboardUrl("http://standard-scheduler.bosh-lite.com");
    }

    /**
     * @param serviceInstanceId The id of the serviceInstance
     * @return The ServiceInstance with the given id or null if one does not exist
     */
    @Override
    public ServiceInstance getServiceInstance(String serviceInstanceId) {
        return new ServiceInstance(new CreateServiceInstanceRequest(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString())).withDashboardUrl("http://standard-scheduler.bosh-lite.com");
    }

    /**
     * Delete and return the instance if it exists.
     *
     * @param deleteServiceInstanceRequest containing pertinent information for deleting the service.
     * @return The deleted ServiceInstance or null if one did not exist.
     * @throws ServiceBrokerException is something goes wrong internally
     */
    @Override
    public ServiceInstance deleteServiceInstance(DeleteServiceInstanceRequest deleteServiceInstanceRequest) throws ServiceBrokerException {
        return null;
    }

    /**
     * Update a service instance. Only modification of service plan is supported.
     *
     * @param updateServiceInstanceRequest detailing the request parameters
     * @return The updated serviceInstance
     * @throws ServiceInstanceUpdateNotSupportedException if particular plan change is not supported
     *                                                    or if the request can not currently be fulfilled due to the state of the instance.
     * @throws ServiceInstanceDoesNotExistException       if the service instance does not exist
     * @throws ServiceBrokerException                     if something goes wrong internally
     */
    @Override
    public ServiceInstance updateServiceInstance(UpdateServiceInstanceRequest updateServiceInstanceRequest) throws ServiceInstanceUpdateNotSupportedException, ServiceBrokerException, ServiceInstanceDoesNotExistException {
        return null;
    }
}
