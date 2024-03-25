package lomayd.SpringLabMSA.service1.api.dao;

import lomayd.SpringLabMSA.service1.api.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class HystrixTestDao {

    public String getIdInfo(String id) {
        log.info("Searching from Database...");

        CommonUtil.randomlyRunLong();
        return id + "s info";
    }
}
