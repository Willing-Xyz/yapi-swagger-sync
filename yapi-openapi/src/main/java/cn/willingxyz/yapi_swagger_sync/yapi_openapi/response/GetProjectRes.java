package cn.willingxyz.yapi_swagger_sync.yapi_openapi.response;

import cn.willingxyz.yapi_swagger_sync.yapi_openapi.datatype.Tag;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class GetProjectRes {

    /**
     * switch_notice : true
     * is_mock_open : false
     * strice : false
     * is_json5 : false
     * _id : 174
     * name : 99信用
     * basepath : /api/nnxy/v1.0
     * project_type : private
     * uid : 170
     * group_id : 68
     * icon : code-o
     * color : blue
     * add_time : 1565866363
     * up_time : 1566269922
     * env : [{"header":[{"name":"Accept","value":"sdf"},{"name":"Accept-Charset","value":"sd"},{"name":"Cookie","value":"sdf=sdf;sdf=sdf"}],"global":[{"_id":"5d5b61e207c62a1ba4847455","name":"sdf","value":"sdf"}],"_id":"5d55397b07c62abdc0847323","name":"local","domain":"http://127.0.0.1"}]
     * tag : [{"_id":"5d5b61bc07c62a383f847453","name":"tag1","desc":"test"},{"_id":"5d5b61bc07c62a305e847452","name":"tag2","desc":"test2"}]
     * cat : []
     * role : false
     */

    private boolean _switch_notice;
    private boolean _is_mock_open;
    private boolean _strice;
    private boolean _is_json5;
    private int __id;
    private String _name;
    private String _basepath;
    private String _project_type;
    private int _uid;
    private int _group_id;
    private String _icon;
    private String _color;
    private int _add_time;
    private int _up_time;
    private boolean _role;
    private List<Env> _env;
    private List<Tag> _tag;
    private List<Object> _cat; // todo 接口返回了空数组

    @NoArgsConstructor
    @Data
    public static class Env {
        /**
         * header : [{"name":"Accept","value":"sdf"},{"name":"Accept-Charset","value":"sd"},{"name":"Cookie","value":"sdf=sdf;sdf=sdf"}]
         * global : [{"_id":"5d5b61e207c62a1ba4847455","name":"sdf","value":"sdf"}]
         * _id : 5d55397b07c62abdc0847323
         * name : local
         * domain : http://127.0.0.1
         */

        private String __id;
        private String _name;
        private String _domain;
        private List<Header> _header;
        private List<Global> _global;

        @NoArgsConstructor
        @Data
        public static class Header {
            /**
             * name : Accept
             * value : sdf
             */

            private String _name;
            private String _value;
        }

        @NoArgsConstructor
        @Data
        public static class Global {
            /**
             * _id : 5d5b61e207c62a1ba4847455
             * name : sdf
             * value : sdf
             */

            private String __id;
            private String _name;
            private String _value;
        }
    }


}
