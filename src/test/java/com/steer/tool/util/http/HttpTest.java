package com.steer.tool.util.http;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class HttpTest {
    private Logger log = LoggerFactory.getLogger(HttpTest.class);
    @Test
    public void testGet() throws Exception {
        HttpResult result = HttpUtil.doGet("https://www.baidu.com");
        log.info(result.getBody());
    }

    @Test
    public void testPost() throws Exception {
        Map reqParam = new HashMap();
        HttpResult result = HttpUtil.doPost("", reqParam, null);
        log.info(result.getBody());
    }
}
