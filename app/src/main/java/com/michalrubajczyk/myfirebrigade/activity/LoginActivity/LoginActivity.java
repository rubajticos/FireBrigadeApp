package com.michalrubajczyk.myfirebrigade.activity.LoginActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.activity.FireBrigadeActivity.FireBrigadeActivity;
import com.michalrubajczyk.myfirebrigade.activity.RegisterActivity.RegisterActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter mPresenter;

    // UI references.
    private EditText mLoginEditText;
    private EditText mPasswordEditText;
    private View mLoginFormView;
    private Button goToRegisterButton;
    private Button mSignInButton;
    private ProgressDialog mProgressDialog;

    public LoginActivity() {
    }

    public LoginActivity(LoginPresenter mPresenter) {
        this.mPresenter = new LoginPresenterImpl(LoginActivity.this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        setTitle(R.string.activity_login);


        mPresenter = new LoginPresenterImpl(LoginActivity.this, this);

        mProgressDialog = new ProgressDialog(LoginActivity.this);
        mLoginEditText = (EditText) findViewById(R.id.login);
        mPasswordEditText = (EditText) findViewById(R.id.password);
        mSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LoginActivity", "Wcisnieto przycisk logowania");
                mPresenter.performCredentials(mLoginEditText.getText().toString(), mPasswordEditText.getText().toString());
            }
        });
        mLoginFormView = findViewById(R.id.login_form);

        goToRegisterButton = (Button) findViewById(R.id.go_to_register_button);
        goToRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void emptyLoginError() {
        mLoginEditText.setError(getString(R.string.fill_the_field));
    }

    @Override
    public void emptyPasswordError() {
        mPasswordEditText.setError(getString(R.string.fill_the_field));
    }

    @Override
    public void signInValidationError() {
        mLoginEditText.setError(getString(R.string.invalid_login_or_password));
        mPasswordEditText.setError(getString(R.string.invalid_login_or_password));
    }

    @Override
    public void signInSuccess() {
        Toast.makeText(this, getString(R.string.sign_in_success), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), FireBrigadeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void signInError() {
        Toast.makeText(this, getString(R.string.sing_in_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void signInTimeoutError() {
        Toast.makeText(this, getString(R.string.timeout_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void progressDialogShow() {
        mProgressDialog.setMessage(getString(R.string.sign_in_dialog));
        mProgressDialog.show();
    }

    @Override
    public void progressDialogDismiss() {
        mProgressDialog.dismiss();
    }

    @Override
    public void badRequestError() {
        Toast.makeText(this, getString(R.string.server_error_try_again_later), Toast.LENGTH_LONG).show();
    }
}

