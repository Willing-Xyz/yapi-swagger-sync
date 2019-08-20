package cn.willingxyz.yapi_swagger_sync.yapi_openapi;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;

public class YApiClients {
    public static IYApiClient create(String url, String token)
    {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(new YApiTarget<>(IYApiClient.class, url, token));
    }
}
