package lomayd.SpringLabMSA.service1.api.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestTemplateClientCommunicator {

    @Autowired
    private DiscoveryClient discoveryClient;

    public String getName(String id) {

        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("service2");

        if(instances.size() == 0) {
            return null;
        }

        String serviceUri = instances.get(0).getUri().toString() + "/name/" + id;

        ResponseEntity<String> restExchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null, String.class, id);

        return id + " is " + restExchange.getBody();


    }
}
