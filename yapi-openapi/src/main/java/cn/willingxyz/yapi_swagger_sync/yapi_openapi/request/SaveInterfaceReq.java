package cn.willingxyz.yapi_swagger_sync.yapi_openapi.request;

import cn.willingxyz.yapi_swagger_sync.yapi_openapi.datatype.*;
import lombok.Data;

import java.util.List;

@Data
public class SaveInterfaceReq {
    private List<ReqQuery> _req_query;
    private List<ReqHeader> _req_headers;
    private List<ReqBodyForm> _req_body_from;
    private String _title;
    private String _catid;
    private String _path;
    private InterfaceStatus _status;
    private String _res_body_type;
    private String _res_body;
    private boolean _switch_notice;
    private String _message;
    private String _desc;
    private String _method;
    private List<ReqParam> _req_params;
    private int _id;
}
