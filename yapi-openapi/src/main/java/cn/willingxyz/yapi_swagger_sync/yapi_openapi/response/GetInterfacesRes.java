package cn.willingxyz.yapi_swagger_sync.yapi_openapi.response;

import cn.willingxyz.yapi_swagger_sync.yapi_openapi.datatype.InterfaceItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class GetInterfacesRes {

    /**
     * count : 1
     * total : 1
     * list : [{"edit_uid":0,"status":"undone","api_opened":false,"tag":["tag1","tag2"],"_id":8719,"method":"GET","catid":2388,"title":"test","path":"/testtt/{id}","project_id":174,"uid":170,"add_time":1566271178}]
     */

    private int _count;
    private int _total;
    private List<InterfaceItem> _list;


}
