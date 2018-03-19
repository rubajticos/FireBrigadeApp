package com.michalrubajczyk.myfirebrigade.model.apiRequests;

/**
 * Created by Michal on 18/03/2018.
 */

public interface DataListener {
    public void onSuccess(String data);

    public void onError(int code);
}
