package com.michalrubajczyk.myfirebrigade.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.michalrubajczyk.myfirebrigade.R;
import com.michalrubajczyk.myfirebrigade.presenter.RegisterPresenter;
import com.michalrubajczyk.myfirebrigade.presenter.RegisterPresenterImpl;
import com.michalrubajczyk.myfirebrigade.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    private RegisterPresenter mPresenter;

    private EditText login;
    private EditText password;
    private EditText rePassword;
    private Button registerButton;
    private ProgressDialog progressDialog;

    public RegisterActivity() {
        mPresenter = new RegisterPresenterImpl(RegisterActivity.this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mPresenter = new RegisterPresenterImpl(RegisterActivity.this, getApplicationContext());

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.repassword);
        registerButton = findViewById(R.id.registeR_button);
        progressDialog = new ProgressDialog(RegisterActivity.this);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("RegisterActivity", "kliniety przycisk rejestracji");
                mPresenter.performLogin(login.getText().toString(), password.getText().toString(), rePassword.getText().toString());
            }
        });
    }


    @Override
    public void registerValidations() {
        login.setError("Wypelnij pole");
        password.setError("Wypelnij pole");
        rePassword.setError("Wypelnij to pole");
    }

    @Override
    public void registerSuccess() {
        Toast.makeText(this, "Rejestracja udana", Toast.LENGTH_LONG).show();
    }

    @Override
    public void passwordNotTheSame() {
        password.setError("Hasla nie sa takie same");
        rePassword.setError("Hasla nie sa takie same");
    }

    @Override
    public void progressDialogShow() {
        progressDialog.setMessage("Trwa pobieranie danych...");
        progressDialog.show();
    }

    @Override
    public void progressDialogDismiss() {
        progressDialog.dismiss();
    }

    @Override
    public void userExistError() {
        Toast.makeText(this, "Rejestracja nieudana - uzytkownik istnieje", Toast.LENGTH_LONG).show();
        login.setError("Podany uzytkownik juz istnieje");
    }

    @Override
    public void badRequestError() {
        Toast.makeText(this, "Blad serwera - sprobuj pozniej", Toast.LENGTH_LONG).show();
    }
}
