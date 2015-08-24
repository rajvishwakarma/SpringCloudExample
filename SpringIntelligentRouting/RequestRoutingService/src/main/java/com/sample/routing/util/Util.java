package com.sample.routing.util;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Util {

    @Autowired
    private LoadBalancerClient loadBalancer;

    /**
     * TODO: Complement this with a simpler version without fallback-url!
     *
     * @param serviceId
     * @param fallbackUri
     * @return
     */
    public URI getServiceUrl(String serviceId, String fallbackUri) {
        URI uri = null;
        try {
            ServiceInstance instance = loadBalancer.choose(serviceId);
            uri = instance.getUri();
        } catch (RuntimeException e) {
            // Eureka not available, use fallback
            uri = URI.create(fallbackUri);
        }

        return uri;
    }

    public <T> ResponseEntity<T> createOkResponse(T body) {
        return createResponse(body, HttpStatus.OK);
    }

    public <T> ResponseEntity<T> createResponse(T body, HttpStatus httpStatus) {
        return new ResponseEntity<T>(body, httpStatus);
    }
}
