package com.michalrubajczyk.myfirebrigade.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.michalrubajczyk.myfirebrigade.model.DataListener;
import com.michalrubajczyk.myfirebrigade.model.RegisterModelImpl;
import com.michalrubajczyk.myfirebrigade.model.dto.UserDTO;
import com.michalrubajczyk.myfirebrigade.view.RegisterView;

import org.apache.commons.httpclient.HttpStatus;

/**
 * Created by Michal on 18/03/2018.
 */

public class RegisterPresenterImpl implements RegisterPresenter {

    RegisterView mRegisterView;
    RegisterModelImpl mRegisterModel;

    public RegisterPresenterImpl(RegisterView registerView, Context context) {
        this.mRegisterView = registerView;
        mRegisterModel = new RegisterModelImpl(context);
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
                UserDTO user = new UserDTO(username, password);
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

    private void errorResponseCodeSupport(Integer code) throws NullPointerException {
        switch (code) {
            case HttpStatus.SC_CONFLICT:
                mRegisterView.userExistError();
                break;
            case HttpStatus.SC_BAD_REQUEST:
                mRegisterView.badRequestError();
            case -999:
                mRegisterView.timeoutError();
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
