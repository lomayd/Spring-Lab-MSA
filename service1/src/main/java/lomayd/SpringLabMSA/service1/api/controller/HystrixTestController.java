package lomayd.SpringLabMSA.service1.api.controller;

import lomayd.SpringLabMSA.service1.api.service.HystrixTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HystrixTestController {

    private final HystrixTestService hystrixTestService;

    @GetMapping("/hystrix1")
    public String hystrix1() {
        String id = "1";
        return hystrixTestService.getName(id);
    }

    @GetMapping("/hystrix2")
    public String hystrix2() {
        String id = "1";
        return hystrixTestService.getIdInfo(id);
    }

}
