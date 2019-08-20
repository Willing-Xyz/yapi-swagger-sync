package cn.willingxyz.yapi_swagger_sync.yapi_openapi.response;

import cn.willingxyz.yapi_swagger_sync.yapi_openapi.datatype.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class GetInterfaceRes {

    /**
     * query_path : {"path":"/invitation_code/inviteBy","params":[]}
     * edit_uid : 0
     * status : undone
     * type : static
     * req_body_is_json_schema : true
     * res_body_is_json_schema : true
     * api_opened : false
     * index : 0
     * tag : []
     * _id : 8632
     * method : POST
     * catid : 2208
     * title : 被哪个邀请码邀请注册的
     * path : /invitation_code/inviteBy
     * project_id : 174
     * req_params : []
     * res_body_type : json
     * uid : 170
     * add_time : 1566184014
     * up_time : 1566262816
     * req_query : []
     * req_headers : [{"required":"1","_id":"5d5b462007c62ab687847445","name":"Content-Type","value":"application/json"}]
     * req_body_form : []
     * __v : 0
     * desc : 被哪个邀请码邀请注册的
     * markdown :
     * req_body_other : {
     "properties": {
     "code": {
     "type": "string",
     "description": "邀请码"
     }
     }
     }
     * req_body_type : json
     * res_body : {
     "$schema": "http://json-schema.org/draft-04/schema#",
     "type": "object",
     "properties": {
     "msg": {
     "type": "string"
     },
     "code": {
     "type": "string"
     },
     "data": {
     "type": "object"
     },
     "time": {
     "type": "integer",
     "format": "int64"
     }
     },
     "description": "Created by wangqiyun on 2018/7/11."
     }
     * username : 孙学盛
     */

    private ReqPath _query_path;
    private int _edit_uid;
    private String _status;
    private String _type;
    private boolean _req_body_is_json_schema;
    private boolean _res_body_is_json_schema;
    private boolean _api_opened;
    private int _index;
    private int __id;
    private String _method;
    private int _catid;
    private String _title;
    private String _path;
    private int _project_id;
    private String _res_body_type;
    private int _uid;
    private int _add_time;
    private int _up_time;
    private int ___v;
    private String _desc;
    private String _markdown;
    private String _req_body_other;
    private String _req_body_type;
    private String _res_body;
    private String _username;
    private List<Tag> _tag;
    private List<ReqParam> _req_params;
    private List<ReqQuery> _req_query;
    private List<ReqHeader> _req_headers;
    private List<ReqBodyForm> _req_body_form;


}
