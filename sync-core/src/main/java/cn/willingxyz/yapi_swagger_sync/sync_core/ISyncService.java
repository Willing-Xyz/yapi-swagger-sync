package cn.willingxyz.yapi_swagger_sync.sync_core;

public interface ISyncService {
    void syncAll();
    void syncItem(String method, String path);
    void syncCat(String catName);
}
