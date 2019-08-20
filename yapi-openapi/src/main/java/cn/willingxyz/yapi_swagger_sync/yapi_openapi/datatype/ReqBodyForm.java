package cn.willingxyz.yapi_swagger_sync.yapi_openapi.datatype;

import lombok.Data;

@Data
public class ReqBodyForm {
    private String _name;
    private String _type;
    private String _example;
    private String _desc;
    /**
     * 0 或者 1
     */
    private String _required;
}
