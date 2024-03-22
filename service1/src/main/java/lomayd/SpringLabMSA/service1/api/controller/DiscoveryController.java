package lomayd.SpringLabMSA.service1.api.controller;

import lomayd.SpringLabMSA.service1.api.service.DiscoveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
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

    @GetMapping("/ribbon/name/{id}")
    public String getNameByRibbonClientCommunicator(@PathVariable("id") String id, @RequestHeader("foo") String foo) {
        log.info("Http Header foo: {}", foo);
        return discoveryService.getNameByRibbonClientCommunicator(id);
    }

    @GetMapping("/feign/name/{id}")
    public String getNameByFeignClientCommunicator(@PathVariable("id") String id) {
        return discoveryService.getNameByFeignClientCommunicator(id);
    }
}
