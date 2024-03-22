package lomayd.SpringLabMSA.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
@Slf4j
public class PreFilter extends ZuulFilter {

    private static String AUTHORIZATION_VALUE = "This is authorization value.";

    @Override
    public String filterType() {
        return "pre";
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
        RequestContext requestContext = RequestContext.getCurrentContext();
        log.info("===== Start Pre Filter. =====");


        String requestUri = requestContext.getRequest().getRequestURI();
        String requestHttpBody = getRequestHttpBody(requestContext);
        log.info("[Pre Filter]: Request URI: {}, Request HttpBody: {}", requestUri, requestHttpBody);

        String authorization = requestContext.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isEmpty(authorization) || !AUTHORIZATION_VALUE.equals(authorization)) {
            responseError(requestContext);
        }

        requestContext.addZuulRequestHeader("foo", "bar");
        return null;
    }

    private void responseError(RequestContext requestContext) {
        try {
            requestContext.setSendZuulResponse(false);
            requestContext.getResponse().sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private String getRequestHttpBody(RequestContext requestContext) {
        String requestHttpBody = null;
        try {
            InputStream inputStream = (InputStream) requestContext.get("requestEntity");
            if(inputStream == null) {
                inputStream = requestContext.getRequest().getInputStream();
                requestHttpBody = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
            }
        } catch (IOException e) {
            log.error("It is failed to obtaining Request Http Body.", e);
            return "";
        }

        return requestHttpBody;
    }
}