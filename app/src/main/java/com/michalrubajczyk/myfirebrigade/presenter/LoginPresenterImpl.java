package com.michalrubajczyk.myfirebrigade.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.LoginRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.errors.HttpErrors;
import com.michalrubajczyk.myfirebrigade.view.LoginView;

import org.apache.commons.httpclient.HttpStatus;

/**
 * Created by Michal on 20/03/2018.
 */

public class LoginPresenterImpl implements LoginPresenter, HttpErrors {

    LoginView mLoginView;
    LoginRequestImpl mLoginModel;

    public LoginPresenterImpl(LoginView loginView, Context context) {
        this.mLoginView = loginView;
        mLoginModel = new LoginRequestImpl(context);
    }

    @Override
    public void performCredentials(String login, String password) {
        if (validateCredentials(login, password)) {
            mLoginView.progressDialogShow();
            mLoginModel.loginUserAndGetAccessToken(login, password, new DataListener() {
                @Override
                public void onSuccess(String data) {
                    mLoginView.signInSuccess();
                    mLoginView.progressDialogDismiss();
                    Log.d("Logowanie", "Zalogowano poprawnie");
                }

                @Override
                public void onError(int code) {
                    errorResponseCodeSupport(code);
                    mLoginView.progressDialogDismiss();
                }
            });
        }
    }

    private boolean validateCredentials(String login, String password) {
        if (TextUtils.isEmpty(login) && TextUtils.isEmpty(password)) {
            mLoginView.emptyLoginError();
            mLoginView.emptyPasswordError();
            return false;
        } else if (TextUtils.isEmpty(login)) {
            mLoginView.emptyLoginError();
            return false;
        } else if (TextUtils.isEmpty(password)) {
            mLoginView.emptyPasswordError();
            return false;
        }

        return true;
    }


    @Override
    public void errorResponseCodeSupport(Integer code) {
        switch (code) {
            case HttpStatus.SC_BAD_REQUEST:
                mLoginView.signInValidationError();
                break;
            case HttpStatus.SC_UNAUTHORIZED:
                mLoginView.signInValidationError();
            case -999:
                mLoginView.signInTimeoutError();
                break;
        }
    }
}
