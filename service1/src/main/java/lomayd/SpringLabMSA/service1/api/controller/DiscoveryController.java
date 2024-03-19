package lomayd.SpringLabMSA.service1.api.controller;

import lomayd.SpringLabMSA.service1.api.service.DiscoveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiscoveryController {

    private final DiscoveryService discoveryService;

    @GetMapping("/services")
    public List<String> getServices() {
        return discoveryService.getServices();
    }

    @GetMapping("/restTemplate/name/{id}")
    public String getNameByRestTemplateClientCommunicator(@PathVariable("id") String id) {
        return discoveryService.getNameByRestTemplateClientCommunicator(id);
    }
}
