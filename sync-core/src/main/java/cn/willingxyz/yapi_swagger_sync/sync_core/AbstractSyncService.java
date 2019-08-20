package cn.willingxyz.yapi_swagger_sync.sync_core;

import cn.willingxyz.yapi_swagger_sync.yapi_openapi.IYApiClient;
import cn.willingxyz.yapi_swagger_sync.yapi_openapi.request.SaveInterfaceReq;
import lombok.var;

import java.util.Collections;
import java.util.List;

public abstract class AbstractSyncService implements ISyncService{

    protected final int _projectId;
    protected IYApiClient _client;

    public AbstractSyncService(IYApiClient client, int projectId)
    {
        _client = client;
        _projectId = projectId;
    }

    @Override
    public void syncAll() {
        List<SaveInterfaceReq> reqs = getAll();
        handleList(reqs);
    }


    @Override
    public void syncItem(String method, String path) {
        handleList(Collections.singletonList(getItem(method, path)));
    }

    @Override
    public void syncCat(String catName) {
        handleList(getCat(catName));
    }
    private void handleList(List<SaveInterfaceReq> reqs) {
        for (SaveInterfaceReq req : reqs)
        {
            _client.saveInterface(req);
        }
    }

    protected String getCatIdByCatName(String catName)
    {
        var res = _client.getCats(_projectId);
        return res.getData().stream()
                .filter(o -> o.getName().equals(catName))
                .map(o -> o.getName())
                .findFirst().orElse(null);
    }

    protected abstract List<SaveInterfaceReq> getAll();
    protected abstract SaveInterfaceReq getItem(String method, String path);
    protected abstract List<SaveInterfaceReq> getCat(String catName);

}
