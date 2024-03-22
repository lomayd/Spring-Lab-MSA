package lomayd.SpringLabMSA.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class PostFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "post";
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
        log.info("===== Start Post Filter. =====");

        int httpStatus = requestContext.getResponse().getStatus();
        String responseHttpBody = getResponseHttpBody(requestContext);
        log.info("[Post Filter]: Response HttpStatus: {}, Response HttpBody: {}", httpStatus, responseHttpBody);

        return null;
    }

    private String getResponseHttpBody(RequestContext requestContext) {
        String responseHttpBody = requestContext.getResponseBody();
        if(responseHttpBody == null) {
            InputStream inputStream = requestContext.getResponseDataStream();

            try {
                byte[] inputStreamByte = StreamUtils.copyToByteArray(inputStream);
                responseHttpBody = new String(inputStreamByte, StandardCharsets.UTF_8);
                requestContext.setResponseDataStream(new ByteArrayInputStream(inputStreamByte));
            } catch(IOException e) {
                log.error("It is failed to obtaining Response Http Body.", e);
            }
        }

        return responseHttpBody;
    }
}