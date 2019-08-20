package cn.willingxyz.yapi_swagger_sync.yapi_openapi;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.var;

public class Test {
    public static void main(String[] args) {
        IYApiClient client = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(new YApiTarget<>(IYApiClient.class, "https://yapi.tianli.shop", "283c77479f66609e2ee9f2f3da4833e0cc6f87cc9e3fb20f640d71386d0b6548"));

//        var res = client.getCats("174");
//        var req = new AddCatReq();
//        req.setName("hehe");
//        req.setProject_id("174");
//        var res = client.addCat(req);
//        var res = client.getInterfaceListMenu(174);
//        System.out.println(res);
//        var res = client.getProject();
//        System.out.println(res);
//        SaveInterfaceReq req = new SaveInterfaceReq();
//        req.setCatid("2208");
//        req.setDesc("testsdfsdf");
//        req.setPath("/api/nnxy/v1.0/test-sync");
//        req.setMethod("POST");
//        req.setTitle("title.sdf..1");
//        Res res = client.saveInterface(req);


    }
}
