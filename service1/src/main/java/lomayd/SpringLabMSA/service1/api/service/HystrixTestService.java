package lomayd.SpringLabMSA.service1.api.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lomayd.SpringLabMSA.service1.api.component.RibbonClientCommunicator;
import lomayd.SpringLabMSA.service1.api.dao.HystrixTestDao;
import lomayd.SpringLabMSA.service1.api.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HystrixTestService {

    private final RibbonClientCommunicator ribbonClientCommunicator;
    private final HystrixTestDao hystrixTestDao;

    @HystrixCommand
    public String getName(String id) {
        log.info("Communicating...");

        CommonUtil.randomlyRunLong(3, 3000);
        return ribbonClientCommunicator.getName(id);
    }

    @HystrixCommand(
            threadPoolProperties =
                    {@HystrixProperty(name = "coreSize",value="30"),
                            @HystrixProperty(name="maxQueueSize", value="10")},
            commandProperties={
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000"),
                    @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
                    @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
                    @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
                    @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
                    @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")
            },
            fallbackMethod = "buildFallbackIdInfo"
    )
    public String getIdInfo(String id) {
        return hystrixTestDao.getIdInfo(id);
    }

    private String buildFallbackIdInfo(String id){
        return id + "'s Fallback Info";
    }
}
