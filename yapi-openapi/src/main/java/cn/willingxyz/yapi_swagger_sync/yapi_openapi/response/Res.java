package cn.willingxyz.yapi_swagger_sync.yapi_openapi.response;

import lombok.Data;

@Data
public class Res<T> {
    private Integer _errcode;
    private String _errmsg;
    private T _data;
}
