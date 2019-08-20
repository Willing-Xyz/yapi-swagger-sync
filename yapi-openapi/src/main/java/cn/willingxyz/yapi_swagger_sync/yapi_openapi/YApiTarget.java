package cn.willingxyz.yapi_swagger_sync.yapi_openapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Request;
import feign.RequestTemplate;
import feign.Target;

import java.nio.charset.Charset;
import java.util.Map;

import static feign.Util.checkNotNull;
import static feign.Util.emptyToNull;

public class YApiTarget<T> implements Target<T> {
    private final Class<T> _type;
    private final String _name;
    private final String _url;
    private final String _token;

    private final ObjectMapper _objectMapper = new ObjectMapper();

    public YApiTarget(Class<T> type, String url, String token) {
        this(type, url, url, token);
    }

    public YApiTarget(Class<T> type, String name, String url, String token) {
        this._type = checkNotNull(type, "type");
        this._name = checkNotNull(emptyToNull(name), "name");
        this._url = checkNotNull(emptyToNull(url), "url");
        this._token = checkNotNull(emptyToNull(token), "token");
    }

    @Override
    public Class<T> type() {
        return _type;
    }

    @Override
    public String name() {
        return _name;
    }

    @Override
    public String url() {
        return _url;
    }

    public String token()
    {
        return _token;
    }

    @Override
    public Request apply(RequestTemplate input) {

        if (input.method().equals("POST")) {
            input.header("Content-Type", "application/json");

            try {
                Map map = _objectMapper.readValue(input.requestBody().asString(), Map.class);
                map.put("token", _token);

                input.body(Request.Body.encoded(_objectMapper.writeValueAsBytes(map), Charset.forName("UTF-8")));
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }
        }

        if (input.method().equals("GET"))
            input.query("token", _token);


        if (input.url().indexOf("http") != 0) {
            input.target(url());
        }
        return input.request();
    }
}
