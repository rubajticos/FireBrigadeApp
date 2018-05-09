package com.michalrubajczyk.myfirebrigade.activity.RegisterActivity;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.michalrubajczyk.myfirebrigade.model.apiRequests.DataListener;
import com.michalrubajczyk.myfirebrigade.model.apiRequests.RegisterRequestImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.User;
import com.michalrubajczyk.myfirebrigade.model.errors.HttpErrors;

import java.net.HttpURLConnection;

/**
 * Created by Michal on 18/03/2018.
 */

public class RegisterPresenterImpl implements RegisterPresenter, HttpErrors {

    RegisterView mRegisterView;
    RegisterRequestImpl mRegisterModel;

    public RegisterPresenterImpl(RegisterView registerView, Context context) {
        this.mRegisterView = registerView;
        mRegisterModel = new RegisterRequestImpl(context);
    }

    @Override
    public void performLogin(String username, String password, String repassword) {
        if (emptyStringValidation(username, password, repassword)) {
            Log.d("RP: walidacja", "puste pola");
            mRegisterView.registerValidations();
        } else {
            if (isPasswordTheSame(password, repassword)) {
                Log.d("RP: password validation", "hasla takie same");
                mRegisterView.progressDialogShow();
                User user = new User(username, password);
                mRegisterModel.registerUser(user, new DataListener() {
                    @Override
                    public void onSuccess(String data) {
                        Log.d("RP: success", "rejestracja udana");
                        mRegisterView.registerSuccess();
                        mRegisterView.progressDialogDismiss();
                    }

                    @Override
                    public void onError(int code) {
                        errorResponseCodeSupport(code);
                        Log.d("RP: error", "rejestracja nieudana");
                        mRegisterView.progressDialogDismiss();
                    }
                });
            } else {
                Log.d("RP: password validation", "rozne hasla");
                mRegisterView.passwordNotTheSame();
            }
        }

    }

    @Override
    public void errorResponseCodeSupport(Integer code) throws NullPointerException {
        switch (code) {
            case HttpURLConnection.HTTP_CONFLICT:
                mRegisterView.userExistError();
                break;
            case HttpURLConnection.HTTP_BAD_REQUEST:
                mRegisterView.badRequestError();
                break;
            case -999:
                mRegisterView.timeoutError();
                break;
        }
    }

    private boolean emptyStringValidation(String s1, String s2, String s3) {
        if (TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2) || TextUtils.isEmpty(s3)) {
            return true;
        }
        return false;
    }

    private boolean isPasswordTheSame(String pass1, String pass2) {
        if (TextUtils.equals(pass1, pass2)) {
            return true;
        } else {
            return false;
        }
    }
}
