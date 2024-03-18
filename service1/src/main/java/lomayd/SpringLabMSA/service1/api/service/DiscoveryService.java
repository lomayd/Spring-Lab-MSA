package lomayd.SpringLabMSA.service1.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscoveryService {

    private final DiscoveryClient discoveryClient;

    public List<String> getServices() {

        List<String> services = new ArrayList<>();

        for(String serviceName : discoveryClient.getServices()) {
            for(ServiceInstance serviceInstance : discoveryClient.getInstances(serviceName)) {
                services.add(serviceName + ": " + serviceInstance.getUri());
            }
        }

        return services;
    }
}
