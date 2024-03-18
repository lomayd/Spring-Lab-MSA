package lomayd.SpringLabMSA.service2.api.service;

import org.springframework.stereotype.Service;

@Service
public class NameService {

    public String getName(String id) {
        switch(id) {
            case "1":
                return "Jesse";
            case "2":
                return "Jimmy";
            default:
                return "UnKnown";
        }
    }
}
