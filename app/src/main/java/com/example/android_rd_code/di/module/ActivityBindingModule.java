package com.example.android_rd_code.di.module;


import com.example.android_rd_code.ui.auth.login.LoginActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector()
    abstract LoginActivity bindLoginActivity();

}
