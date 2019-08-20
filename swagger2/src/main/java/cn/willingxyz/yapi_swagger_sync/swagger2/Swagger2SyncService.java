package cn.willingxyz.yapi_swagger_sync.swagger2;

import cn.willingxyz.yapi_swagger_sync.sync_core.AbstractSyncService;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.IYApiClient;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.YApiClients;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.datatype.InterfaceStatus;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.datatype.ReqParam;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.request.AddCatReq;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.request.SaveInterfaceReq;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Response;
import io.swagger.models.Swagger;
import io.swagger.models.parameters.*;
import io.swagger.parser.SwaggerParser;
import lombok.var;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Swagger2SyncService extends AbstractSyncService {

    private final String _swaggerUrl;
    private ObjectMapper _objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public Swagger2SyncService(IYApiClient client, int projectId, String swaggerUrl) {
        super(client, projectId);
        _swaggerUrl = swaggerUrl;
    }

    @Override
    protected List<SaveInterfaceReq> getAll() {
        Swagger swagger = getSwagger();
        List<SaveInterfaceReq> reqs = new ArrayList<>();
        for (var path : swagger.getPaths().entrySet())
        {


            SaveInterfaceReq req = convertReq(path);
            if (req == null)
                continue;

            reqs.add(req);
        }
        return reqs;
    }

    private SaveInterfaceReq convertReq(Map.Entry<String, Path> path) {
        Operation operation = getOperation(path.getValue());
        if (operation == null)
            return null;

        SaveInterfaceReq req = new SaveInterfaceReq();

        req.setPath(path.getKey());
        req.setTitle(operation.getSummary());
        req.setDesc(operation.getDescription());
        req.setMethod(convertMethod(path.getValue()));

        req.setStatus(InterfaceStatus.undone);
        req.setSwitch_notice(true);

        handleCatId(operation, req);
        handleResponse(operation, req);
        handleParameter(operation, req);
        return req;
    }

    private void handleCatId(Operation operation, SaveInterfaceReq req) {
        Integer catId = getCatIdByCatName(operation.getTags().get(0));
        if (catId == null)
        {
            createCat(operation.getTags().get(0));
            catId = getCatIdByCatName(operation.getTags().get(0));
        }
        req.setCatid(catId);
    }

    private void handleResponse(Operation operation, SaveInterfaceReq req) {
        Response response = operation.getResponses().entrySet().stream().map(o -> o.getValue()).findFirst().orElse(null);
        if (response != null) {
            req.setRes_body_type("json");
            try {
                if (response.getResponseSchema() != null)
                    req.setRes_body(_objectMapper.writeValueAsString(response.getResponseSchema()));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleParameter(Operation operation, SaveInterfaceReq req) {
        for (var parameter : operation.getParameters())
        {
            if (parameter instanceof HeaderParameter
                || parameter instanceof QueryParameter
                || parameter instanceof PathParameter
                )
            {
                AbstractSerializableParameter param = (AbstractSerializableParameter) parameter;

                ReqParam reqParam = new ReqParam();
                reqParam.setName(parameter.getName());
                reqParam.setDesc(parameter.getDescription());
                reqParam.setType(param.getType());
                reqParam.setRequired(getRequired(parameter.getRequired()));

                if (parameter instanceof HeaderParameter) {
                    req.getReq_headers().add(reqParam);
                }
                else if (parameter instanceof QueryParameter)
                {
                    req.getReq_query().add(reqParam);
                }
                else if (parameter instanceof PathParameter)
                {
                    req.getReq_params().add(reqParam);
                }
            }
            else if (parameter instanceof BodyParameter)
            {
                try {
                    String str = _objectMapper.writeValueAsString(((BodyParameter) parameter).getSchema());

                    req.setReq_body_other(str);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
//                    req.set
            }


        }
    }

    private String getRequired(boolean required) {
        return required ? "1" : "0";
    }

    private void createCat(String catName) {
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
        Map.Entry<String, Path> op = getSwagger().getPaths().entrySet().stream()
                .filter(o -> o.getKey().equals(path) && convertMethod(o.getValue()).equals(method))
                .findFirst().orElse(null);
        if (op == null)
            throw new RuntimeException("未找到");
        return convertReq(op);
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
        var service = new Swagger2SyncService(YApiClients.create("https://yapi.tianli.shop", "283c77479f66609e2ee9f2f3da4833e0cc6f87cc9e3fb20f640d71386d0b6548"),
                174,"http://localhost:8765/swagger2.json");
//        service.syncItem("GET", "/api/nnxy/v1.0/withdrawableAmount");
        service.syncAll();
    }

}
