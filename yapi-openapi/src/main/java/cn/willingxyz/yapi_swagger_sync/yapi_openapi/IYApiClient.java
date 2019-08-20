package cn.willingxyz.yapi_swagger_sync.yapi_openapi;

import cn.willingxyz.yapi_swagger_sync.yapi_openapi.datatype.InterfaceCat;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.request.AddCatReq;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.request.SaveInterfaceReq;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.response.GetInterfaceRes;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.response.GetInterfacesRes;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.response.GetProjectRes;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.response.Res;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface IYApiClient {
    @RequestLine("POST /api/interface/save")
    Res saveInterface(SaveInterfaceReq req); // todo 响应类型
    @RequestLine("POST /api/interface/add")
    Res addInterface(SaveInterfaceReq req); // todo 响应类型
    @RequestLine("POST /api/interface/up")
    Res updateInterface(SaveInterfaceReq req); // todo 响应类型

    @RequestLine("GET /api/project/get")
    Res<GetProjectRes> getProject();

    @RequestLine("POST /api/interface/add_cat")
    Res addCat(AddCatReq req);

    @RequestLine("GET /api/interface/getCatMenu?project_id={projectId}")
    Res<List<InterfaceCat>> getCats(@Param("projectId") int projectId);

    @RequestLine("GET /api/interface/get?id={id}")
    Res<GetInterfaceRes> getInterface(@Param("id") int id);

    @RequestLine("GET /api/interface/list_cat?catid={catId}&page={page}&limit={limit}")
    Res<GetInterfacesRes> getCatInterfaces(@Param("catId") int catId, @Param("page") int page, @Param("limit") int limit);

    @RequestLine("GET /api/interface/list?project_id={projectId}&page={page}&limit={limit}")
    Res<GetInterfacesRes> getInterfaces(@Param("projectId") int projectId, @Param("page") int page, @Param("limit") int limit);

    @RequestLine("GET /api/interface/list_menu?project_id={projectId}")
    Res getInterfaceListMenu(@Param("projectId") long projectId);
}
