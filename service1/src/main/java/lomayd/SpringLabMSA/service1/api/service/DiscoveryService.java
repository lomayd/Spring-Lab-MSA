package lomayd.SpringLabMSA.service1.api.service;

import lomayd.SpringLabMSA.service1.api.component.RestTemplateClientCommunicator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscoveryService {

    private final DiscoveryClient discoveryClient;
    private final RestTemplateClientCommunicator restTemplateClientCommunicator;

    public List<String> getServices() {

        List<String> services = new ArrayList<>();

        for(String serviceName : discoveryClient.getServices()) {
            for(ServiceInstance serviceInstance : discoveryClient.getInstances(serviceName)) {
                services.add(serviceName + ": " + serviceInstance.getUri());
            }
        }

        return services;
    }

    public String getNameByRestTemplateClientCommunicator(String id) {
        log.info("Communication By RestTemplateClientCommunicator.");
        return restTemplateClientCommunicator.getName(id);
    }
}
