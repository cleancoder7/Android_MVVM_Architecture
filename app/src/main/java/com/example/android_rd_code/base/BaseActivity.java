package com.example.android_rd_code.base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;


import com.example.android_rd_code.R;
import com.example.android_rd_code.util.AppConstant;
import com.example.android_rd_code.util.AppUtil;
import com.example.android_rd_code.util.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    public ViewDataBinding binding;
    private ProgressDialog progressDialog;
    private String password;
    private String passwordOld;

    public final String PASSWORD_REGEX = "^(?=.*[A-Z]{1,})(?=.*[a-z]{1,})(?=.*[0-9]{1,})(?!.*[ ])(?=.*[^A-Za-z0-9]{1,}).{8,}$";
    private static final String IMAGE_DIRECTORY = "/carbuddy";
    private static final String TAG = "base_activity";

    @Inject
    public ViewModelFactory viewModelFactory;

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, layoutRes());
    }


    public void setToolbar(String title) {
        setToolbar(title, "", null);
    }

    public void setToolbar(String title, String skip) {
        setToolbar(title, skip, null);
    }

    public void setToolbar(String title, String skip, final View.OnClickListener callback) {
//        LinearLayout linearLayoutBack = (LinearLayout) findViewById(R.id.back);
//        LinearLayout linearLayoutDone = (LinearLayout) findViewById(R.id.done);
//
//        AppCompatTextView tvTile = (AppCompatTextView) findViewById(R.id.tv_title);
//
//        AppCompatTextView tvDone = (AppCompatTextView) findViewById(R.id.tv_done);

//        if (linearLayoutBack != null) {
//            linearLayoutBack.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onBackPressed();
//                }
//            });
//        }
//        if (tvTile != null) {
//            tvTile.setText(title);
//        }
//
//        if (!skip.equals("")) {
//            if (linearLayoutDone != null) {
//                linearLayoutDone.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        callback.onClick(v);
//                    }
//                });
//            }
//            if (tvDone != null) {
//                tvDone.setText(skip);
//            }
//        }
    }


    public void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(color);
        }
    }

    /**
     * Launch activity using class concept
     *
     * @param classType launching class
     */
    public void launchActivity(Class<? extends BaseActivity> classType, View view) {
        AppUtil.preventTwoClick(view);
        startActivity(new Intent(this, classType));
    }

    /**
     * Launch activity using class concept
     *
     * @param classType launching class
     */
    public void launchActivity(Class<? extends BaseActivity> classType) {
        startActivity(new Intent(this, classType));
    }


    /**
     * Launch activity for result using class concept
     *
     * @param classType launching class
     */
    public void launchActivity(Class<? extends BaseActivity> classType, int requestCode) {
        startActivityForResult(new Intent(this, classType), requestCode);
    }

    /**
     * Launch activity using class concept and pass data using bundle
     *
     * @param classType launching class
     */
    public void launchActivity(Class<? extends BaseActivity> classType, Bundle bundle) {
        Intent intent = new Intent(this, classType);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void launchActivity(Class<? extends BaseActivity> classType, Bundle bundle, View view) {
        AppUtil.preventTwoClick(view);
        Intent intent = new Intent(this, classType);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * Launch activity using class concept and pass data using bundle and launch activity for result
     *
     * @param classType launching class
     */
    public void launchActivity(Class<? extends BaseActivity> classType, Bundle bundle, int requestCode) {
        Intent intent = new Intent(this, classType);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * Launch activity using class concept and pass data using bundle
     *
     * @param classType launching class
     */
    public void launchActivity(Class<? extends BaseActivity> classType, Bundle bundle, View view, String transitionName) {
        Intent intent = new Intent(this, classType);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, transitionName);
        startActivity(intent, options.toBundle());
    }


    /**
     * Launch activity with clear all stack
     *
     * @param classType launching class
     */
    public void launchActivityMain(Class<? extends BaseActivity> classType) {
        Intent intent = new Intent(this, classType);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    public void showProgressBar(String msg, boolean isCancel) {
        hideProgressBar();
        if (!BaseActivity.this.isFinishing()) {
            progressDialog = ProgressDialog.show(BaseActivity.this, "", msg, false);
            if (progressDialog != null) {
                progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        hideProgressBar();
                        return false;
                    }
                });
                progressDialog.setCanceledOnTouchOutside(isCancel);
                progressDialog.setContentView(R.layout.progress_layout);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
        }
    }

    /**
     * Show progress bar with single ocurrent object
     */
    public void showProgressBar() {
        hideProgressBar();
        if (!BaseActivity.this.isFinishing()) {
            progressDialog = ProgressDialog.show(BaseActivity.this, "", "", true);
            if (progressDialog != null) {
                progressDialog.setCanceledOnTouchOutside(true);
                progressDialog.setContentView(R.layout.progress_layout);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
        }
    }

    public void hideProgressBar() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }

    public void showSnackbar(final int id) {
        Snackbar.make(findViewById(android.R.id.content),
                getString(id),
                Snackbar.LENGTH_LONG)
                .show();
    }

    public void showSnackbar(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Snackbar.make(findViewById(android.R.id.content),
                    msg,
                    Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    public boolean validateDob(long dob, TextInputLayout textInputLayoutDob) {
        if (dob == -1) {
            textInputLayoutDob.setError(getString(R.string.err_dob_blank));
            return false;
        } else {
            textInputLayoutDob.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validateGender(int gender, TextInputLayout textInputLayoutGender) {
        if (gender == -1) {
            textInputLayoutGender.setError(getString(R.string.err_gender_blank));
            return false;
        } else {
            textInputLayoutGender.setErrorEnabled(false);
        }

        return true;
    }
    /**
     * For validation with inout tvDeviceStatus layout
     *
     * @return true/false
     */

    public boolean validateEmail(String email, TextInputLayout textInputLayoutEmail) {
        if (TextUtils.isEmpty(email)) {
            textInputLayoutEmail.setError(getString(R.string.err_email_blank));
            return false;
        } else if (!AppUtil.isValidEmail(email)) {
            textInputLayoutEmail.setError(getString(R.string.err_email_valid));
            return false;
        } else {
            textInputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }


    public boolean validatePassword(String password, TextInputLayout textInputLayoutPassword) {
        if (TextUtils.isEmpty(password)) {
            textInputLayoutPassword.setError(getString(R.string.err_password_blank));
            return false;
        } else {
            textInputLayoutPassword.setErrorEnabled(false);
        }

       /* if (!password.matches(PASSWORD_REGEX)) {
            textInputLayoutPassword.setError(getString(R.string.err_password_type));
            return false;
        } else {
            textInputLayoutPassword.setErrorEnabled(false);
        }*/

        if (password.length() <= 7) {
            textInputLayoutPassword.setError(getString(R.string.err_password_lenght));
            return false;
        } else {
            textInputLayoutPassword.setErrorEnabled(false);
        }


        return true;
    }


    public boolean validateLoginPassword(String password, TextInputLayout textInputLayoutPassword) {
        if (TextUtils.isEmpty(password)) {
            textInputLayoutPassword.setError(getString(R.string.err_password_blank));
            return false;
        } else {
            textInputLayoutPassword.setErrorEnabled(false);
        }
        return true;
    }


    public boolean validatePhoneNumber(String phoneNumber, TextInputLayout textInputLayoutPassword) {

        if (TextUtils.isEmpty(phoneNumber)) {
            textInputLayoutPassword.setError(getString(R.string.err_phone_number));
            return false;

        } else if (phoneNumber.length() < 10) {
            textInputLayoutPassword.setError(getString(R.string.err_phone_number_10_lenght));
            return false;
        } else {
            textInputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }
    /**
     * This is used validation in  Login, Sign up and Edit Profile screen for input tvDeviceStatus layout
     */
    public class MyTextWatcher implements TextWatcher {

        private EditText mEditText;
        private TextInputLayout textInputLayout;

        public MyTextWatcher(EditText editText, TextInputLayout textInputLayout) {
            this.mEditText = editText;
            this.textInputLayout = textInputLayout;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }


        public void afterTextChanged(Editable editable) {
            Log.i("CCCCCCCCC", "TYPE11 " + password + " : " + mEditText.getText().toString());

            switch (mEditText.getId()) {


            }
        }
    }

}