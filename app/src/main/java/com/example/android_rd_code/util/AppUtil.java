package com.example.android_rd_code.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_rd_code.R;
import com.example.android_rd_code.base.App;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.Objects;
import java.util.TimeZone;

public class AppUtil {

    public static boolean isConnection() {
        return isConnection(false);
    }


    public static boolean isConnection(boolean notShowMsg) {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = Objects.requireNonNull(connectivityManager)
                .getActiveNetworkInfo();

        boolean isNewwork = (activeNetworkInfo != null && activeNetworkInfo.isConnected());
        if (!isNewwork && !notShowMsg) {
            AppUtil.showToast(App.getInstance().getResources().getString(R.string.msg_network_connection));
        }
        return isNewwork;
    }

    public static int getColor(int color) {
        return ContextCompat.getColor(App.getInstance(), color);
    }

    public static void showToast(String msg) {
        Toast.makeText(App.getInstance(), msg, Toast.LENGTH_LONG).show();
    }


    public static void loadImageCircle(ImageView view, File file) {
        if (view != null && file != null)
            Glide.with(App.getInstance())
                    .load(file)
                    .apply(new RequestOptions()
                            .circleCrop()
                            .skipMemoryCache(false)
                            .placeholder(R.drawable.ic_profile)
                            .error(R.drawable.ic_profile)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(view);
    }

    public static void loadImage(ImageView view, String url) {
        if (view != null && url != null) {
            Glide.with(App.getInstance())
                    .load(url)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_placeholder)
                            .skipMemoryCache(false)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(view);
        }
    }

    public static void loadImage(ImageView view, int url) {
        if (view != null) {

            Glide.with(App.getInstance())
                    .load(url)
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_placeholder)
                            .skipMemoryCache(false)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(view);
        }
    }

    public static void loadImage(ImageView view, String url, int placeholder) {
        if (view != null && url != null)
            Glide.with(App.getInstance())
                    .load(url)
                    .apply(new RequestOptions()
                            .placeholder(placeholder)
                            .skipMemoryCache(false)
                            .diskCacheStrategy(DiskCacheStrategy.NONE))
                    .into(view);
    }


    public static void loadImageCircle(ImageView view, String url) {
        if (view != null && url != null)
            Glide.with(App.getInstance())
                    .load(url)
                    .apply(new RequestOptions()
                            .circleCrop()
                            .skipMemoryCache(false)
                            .placeholder(R.drawable.ic_profile)
                            .error(R.drawable.ic_profile)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(view);
    }

    public static void loadImageCircle(ImageView view, String url, int placeholder) {
        if (view != null && url != null)
            Glide.with(App.getInstance())
                    .load(url)
                    .apply(new RequestOptions()
                            .circleCrop()
                            .skipMemoryCache(false)
                            .placeholder(placeholder)
                            .error(placeholder)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(view);
    }


    public static int getDimens(int dp_10) {
        int size = App.getInstance().getResources().getDimensionPixelSize(dp_10);
        return size;
    }


    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String getValue(int month) {
        if (month > 9) {
            return String.valueOf(month);
        }
        return "0" + month;
    }

    public static void setBg(LinearLayout layout, int i) {
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            layout.setBackgroundDrawable(ContextCompat.getDrawable(App.getInstance(), i));
        } else {
            layout.setBackground(ContextCompat.getDrawable(App.getInstance(), i));
        }
    }

    public static String getDeviceId() {
        return Settings.Secure.getString(App.getInstance().getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static String getDate(String mDate) {
        String parsedDate = "";
        DateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
        DateFormat outputFormat = new SimpleDateFormat("MMM d");
        Date date = null;
        try {
            date = inputFormat.parse(mDate);
            parsedDate = outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parsedDate;
    }

    public static boolean isJoinEndedAndVotingStartedt(String mJoinEndDate, String mVoteStartDate) {
        DateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
        Date joinEndDate = null, voteStartDate = null;
        try {
            joinEndDate = inputFormat.parse(mJoinEndDate);
            voteStartDate = inputFormat.parse(mVoteStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (getCurrentDate().after(joinEndDate)) {

        } else {

        }

        return getCurrentDate().after(joinEndDate);
    }

    public static Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        Date date = calendar.getTime();
        return date;
    }

    public static boolean isVoteNowDialogShownForToday() {
        if (!AppUtil.getCurrentDate().toString().equals(PreferenceKeeper.getInstance().getVoteNowDialogShownTodayStatus(AppConstant.PKN.VOTE_DIALOG_SHOWN))) {
            PreferenceKeeper.getInstance().setVoteNowDialogShownTodayStatus(AppConstant.PKN.VOTE_DIALOG_SHOWN, AppUtil.getCurrentDate().toString());
            return true;
        }
        return false;
    }

    public static String getStartDateEndDate(String startDate, String endDate) {
        return getDate(startDate) + " - " + getDate(endDate);
    }

    public static long timeStampToMillis(String timeStamp) {
        if (timeStamp != null) {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = null;
            try {
                date = inputFormat.parse(timeStamp);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date.getTime();
        }
        return 0;
    }


    public static void preventTwoClick(final View view) {

        view.setEnabled(false);
        view.postDelayed(new Runnable() {
            public void run() {
                view.setEnabled(true);
            }
        }, 800);
    }
}
