package cn.willingxyz.yapi_swagger_sync.yapi_openapi.datatype;

import lombok.Data;

import java.util.List;

@Data
public class ReqPath {
    private String _path;
    private List<Object> _params; // todo 未返回数据
}
