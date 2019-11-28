package com.example.android_rd_code.di.module;

import com.example.android_rd_code.api.APIInterceptor;
import com.example.android_rd_code.api.IApiService;
import com.example.android_rd_code.di.module.GsonModule;
import com.example.android_rd_code.di.module.ViewModelModule;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module(includes = {ViewModelModule.class, GsonModule.class})
public class ApplicationModule {

    private static final String BASE_URL = "https://devapi.carbuddyapp.com/";


    /*
     * @param cache
     *        Fetch Cache object from 'Cache provideOkHttpCache' method
     * */
    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new APIInterceptor()).addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    /*
     * @param gson
     *        Fetch Gson object from 'GsonModule' method
     *
     * @param okHttpClient
     *        Fetch OkHttpClient object from 'OkHttpClient provideOkHttpClient' method
     * */
    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build();
    }

    /*
     * @param retrofit
     *        Fetch retrofit class from gradle dependency
     *        This is IApiService interface to used inject in activity class
     * */
    @Provides
    @Singleton
    IApiService provideIApiService(Retrofit retrofit) {
        IApiService service = retrofit.create(IApiService.class);
        return service;
    }

}
