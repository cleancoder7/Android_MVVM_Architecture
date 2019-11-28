package com.example.android_rd_code.ui.auth.login;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.android_rd_code.R;
import com.example.android_rd_code.base.BaseActivity;
import com.example.android_rd_code.databinding.ActivityLoginBinding;
import com.example.android_rd_code.util.AppConstant;
import com.example.android_rd_code.util.AppUtil;
import com.example.android_rd_code.util.PreferenceKeeper;
import com.example.android_rd_code.util.ViewModelFactory;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding ui;
    @Inject
    ViewModelFactory viewModelFactory;
    private LoginViewModel viewModel;
    private String isoCode;

    @Override
    protected int layoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = (ActivityLoginBinding) binding;
        viewModel = viewModelFactory.create(LoginViewModel.class);
        ui.etPhoneNumber.addTextChangedListener(new BaseActivity.MyTextWatcher(ui.etPhoneNumber, ui.textInputLayoutPhoneNumber));
        ui.etPasswordLogin.addTextChangedListener(new MyTextWatcher(ui.etPasswordLogin, ui.textInputLayoutPassword));

        ui.etPasswordLogin.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login();
                return true;
            }
            return false;
        });


        setObservables();

    }

    private void setObservables() {
        viewModel.success.observe(this, data -> {
            AppUtil.showToast(data.message);
            hideProgressBar();
            Bundle bundle = new Bundle();
            switch (data.status) {

                case AppConstant.API_CODE.COMPLETE_PROFILE:
                    if (data.accessToken != null) {
                        PreferenceKeeper keeper = PreferenceKeeper.getInstance();
                        keeper.setAccessToken(data.accessToken);
                        keeper.setUserLoggedIn(true);
                    }
                    Toast.makeText(this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                    launchActivity(LoginActivity.class, bundle);
                    finishAffinity();
                    break;
            }
        });

        viewModel.error.observe(this, errors -> {
            hideProgressBar();

        });
    }


    private boolean loginValidation(String email, String password) {
        if (!validatePhoneNumber(email, ui.textInputLayoutPhoneNumber)) {
            return false;
        }

        if (!validateLoginPassword(password, ui.textInputLayoutPassword)) {
            return false;
        }

        return true;
    }


    public void loginClick(View view) {
        login();
    }

    private void login() {
        if (AppUtil.isConnection()) {
            String phone = ui.etPhoneNumber.getText().toString();
            String password = ui.etPasswordLogin.getText().toString();
            if (loginValidation(phone, password)) {
                hideSoftKeyBoard();
                showProgressBar();
                viewModel.onUserLogin(phone, password, isoCode);
            }
        }
    }

}
