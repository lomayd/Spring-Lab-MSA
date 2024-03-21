package lomayd.SpringLabMSA.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RoutingFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "routing";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        log.info("===== Start Routing Filter. =====");
        return null;
    }
}
