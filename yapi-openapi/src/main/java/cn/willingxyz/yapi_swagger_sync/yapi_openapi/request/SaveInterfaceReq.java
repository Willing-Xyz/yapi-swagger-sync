package cn.willingxyz.yapi_swagger_sync.yapi_openapi.request;

import cn.willingxyz.yapi_swagger_sync.yapi_openapi.datatype.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SaveInterfaceReq {
    private List<ReqParam> _req_query = new ArrayList<>();
    private List<ReqParam> _req_headers = new ArrayList<>();
    private List<ReqParam> _req_body_from = new ArrayList<>();
    private String _title;
    private int _catid;
    private String _path;
    private InterfaceStatus _status;
    private boolean _res_body_is_json_schema = true;
    private String _res_body_type;
    private String _res_body;
    private boolean _switch_notice;
    private String _message;
    private String _desc;
    private String _method;
    private List<ReqParam> _req_params = new ArrayList<>();
    private int _id;

    private String _req_body_type = "json";
    private String _req_body_other;
    private boolean _req_body_is_json_schema = true;
}
