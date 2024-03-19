package lomayd.SpringLabMSA.service2.api.controller;

import lomayd.SpringLabMSA.service2.api.service.NameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NameController {

    private final NameService nameService;

    @GetMapping("/name/{id}")
    public String getName(@PathVariable("id") String id) {
        return nameService.getName(id);
    }
}
