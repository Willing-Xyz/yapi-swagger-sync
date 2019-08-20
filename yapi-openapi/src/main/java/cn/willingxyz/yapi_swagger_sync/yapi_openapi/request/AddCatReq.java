package cn.willingxyz.yapi_swagger_sync.yapi_openapi.request;

import lombok.Data;

@Data
public class AddCatReq {
    private String _desc;
    private String _name;
    private int _project_id;
}
