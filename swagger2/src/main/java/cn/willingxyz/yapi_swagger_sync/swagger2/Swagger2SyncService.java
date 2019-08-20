package cn.willingxyz.yapi_swagger_sync.swagger2;

import cn.willingxyz.yapi_swagger_sync.sync_core.AbstractSyncService;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.IYApiClient;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.datatype.InterfaceStatus;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.request.AddCatReq;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.request.SaveInterfaceReq;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import lombok.var;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Swagger2SyncService extends AbstractSyncService {

    private final String _swaggerUrl;

    public Swagger2SyncService(IYApiClient client, int projectId, String swaggerUrl) {
        super(client, projectId);
        _swaggerUrl = swaggerUrl;
    }

    @Override
    protected List<SaveInterfaceReq> getAll() {
        Swagger swagger = getSwagger();
        for (var path : swagger.getPaths().entrySet())
        {
            Operation operation = getOperation(path.getValue());

            SaveInterfaceReq req = new SaveInterfaceReq();
            req.setPath(path.getKey());
            req.setTitle(operation.getSummary());
            req.setDesc(operation.getDescription());
            req.setMethod(convertMethod(path.getValue()));

            String catId = getCatIdByCatName(operation.getTags().get(0));
            if (catId == null)
            {
                createCat(operation.getTags().get(0));
                catId = getCatIdByCatName(operation.getTags().get(0));
            }
            req.setCatid(catId);

            req.setStatus(InterfaceStatus.undone);
            req.setSwitch_notice(true);

            req.setRes_body_type("json");
//            req.setMessage();


        }
        return null;
    }

    private String createCat(String catName) {
        var req = new AddCatReq();
        req.setProject_id(_projectId);
        req.setName(catName);
        _client.addCat(req);
    }

    private String convertMethod(Path value) {
        if (value.getGet() != null)
            return "GET";
        if (value.getPost() != null)
            return "POST";
        if (value.getPut() != null)
            return "PUT";
        if (value.getDelete() != null)
            return "DELETE";
        if (value.getHead() != null)
            return "HEAD";
        if (value.getPatch() != null)
            return "PATCH";
        if (value.getOptions() != null)
            return "OPTIONS";
        return null;
    }

    private Operation getOperation(Path value) {
        if (value.getGet() != null)
            return value.getGet();
        if (value.getPost() != null)
            return value.getPost();
        if (value.getPut() != null)
            return value.getPut();
        if (value.getDelete() != null)
            return value.getDelete();
        if (value.getHead() != null)
            return value.getHead();
        if (value.getPatch() != null)
            return value.getPatch();
        if (value.getOptions() != null)
            return value.getOptions();
        return null;
    }

    @Override
    protected SaveInterfaceReq getItem(String method, String path) {
        return null;
    }

    @Override
    protected List<SaveInterfaceReq> getCat(String catName) {
        return null;
    }


    private Swagger getSwagger()
    {
        return new SwaggerParser().read(_swaggerUrl);
    }

    public static void main(String[] args) {
        var service = new Swagger2SyncService(null, 174,"http://localhost:8765/swagger2.json");
        Swagger swagger = service.getSwagger();
        System.out.println(swagger);
    }
}
