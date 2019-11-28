package com.example.android_rd_code.util;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Class is used to contain application constant.
 */
public class AppConstant {


    public static final int PLATFORM = 2;
    public static final String DUMMY_DEVICE_TOKEN = "asd";
    public static final String DUMMY_DEVICE_ID = "asd";
    public static final String FORGET_PASS_SCREEN = "forget_pass_screen";
    public static final String TIME_STAMP_EXERCISE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final long SPLASH_DELAY = 100;
    public static final int MAX_SELECTION_STORAGE = 3;
    public static final int MAX_VIDEO_RECORD_DURATION = 1000 * 30;
    public static final int DEFAULT_ANIMATION_DURATION = 750;
//    public static UserProfileile USER_PROFILE;
    public static SimpleDateFormat TODAY_TIME_STAMP = new SimpleDateFormat(AppConstant.TIME_STAMP_EXERCISE, Locale.getDefault());
    public static SimpleDateFormat TODAY_DATE = new SimpleDateFormat("EEE, MMM dd, yyyy", Locale.getDefault());


    public interface PKN {
        String FCM_TOKEN = "login";
        String ACCESS_TOKEN = "access_token";
        String USER_DATA = "user_data";
        String USER_LOGGED_IN = "user_logged_in";
        String VOTE_DIALOG_SHOWN = "vote_dialog_shown";
        String TUTORIAL_ONE = "tutorial_one";
    }

    public interface DEFAULT {
        int PAGE_NUMBER = 1;
        int LIMIT = 100;
    }


    public interface BK {
        String USER = "user";
        String STORY_DATA = "stories";
        String CHALLENGE_ID = "challengeID";
        String POST_ID = "postID";
        String OPENED_FROM = "opened_from";
        String CHALLENGE_DATA = "challenge_data";
        String IS_JOINING_PENDING = "join_pending";
        String WINNER_DATA = "winner_data";
        String GALLERY_DATA = "gallery_data";
        String GALLERY_INTENT = "gallery_intent";
        String JOINED_USER_INTENT = "joined_user_intent";
        String TOOLBAR_TITLE = "title_data";
        String STORAGE_ALL_DATA = "storage_all_data";
        String SOCIAL_POST_DATA = "social_post_data";
        String STORAGE_SINGLE_DATA = "storage_single_data";
        String FULLSCREEN_IMAGE_INTENT = "full_screen_image_intent";
        String USER_CLICKED_POSITION = "position_user_clicked";
        String IS_MULTIPLE_STORAGE = "is_multiple";
        String IS_VIDEO_STORAGE = "is_video_storage";
        String EDIT_JOINED_CHALLENGE = "edit_join_challenge";
        String IS_VOTING_ENABLED = "is_voting_enabled";
        String CHALLENG_ID = "challenge_id";
        String EDIT_POST = "edit_post";
        String MAX_SELECTION_STORAGE = "max_selection_storage";
        String STORAGE_SINGLE_DATA_POS = "ssd_pos";
        String STORAGE_SINGLE_DATA_CROP_PATH = "ssd_croppath";
        String EDIT_PROFILE_DATA = "edit_profile_DATA";
        String EDIT_GENDER = "edit_gender";
        String GENDER_DATA = "gender_data";
        String SELECTED_PURPOSE_DATA = "purpose_selected";
        String MAKER_DATA = "make_data";
        String SELECTED_CAR_MAKER_DATA = "car_maker_selected_data";
        String SELECTED_VEHICLE_POSITION = "car_maker_selected_data_position";
        String FORUM_SINGLE_DATA = "forum_single_data";
        String SELECTED_PURPOSE = "s_purpose";
        String FORUM_SINGLE_COMMENT_DATA = "forum_single_comment_data";
        String FORUM_ID = "forumId";
        String FROM_LIKE = "from_like";
        String FROM_ADD_FORUM = "from_add_forum";
        String VEHICLES_DATA = "vehicles_data";
        String FROM_SOCIAL_POST = "from_social_post";
        String USER_ID = "user_id";
        String IS_FROM_PROFILE_FORUM = "is_from_profile_forum";
        String CAR_DATA = "car_data";
        String WEB_TILE = "web_title";
        String IS_FROM_ADD_CAR = "is_from_add_car";
        String VEHICLES_ID = "vehicle_id";
    }

    public interface API_CODE {
        String INCOMPLETE_PROFILE = "101";
        String PHONE_NOT_VERIFY = "102";
        String COMPLETE_PROFILE = "103";
    }

    public interface JOIN_CHALLENGE_STATUS {
        int JOIN_GOING_ON = 1;
        int JOIN_ENDED = 2;
        int VOTING_STARTED = 3;
        int VOTING_ENDED = 4;
    }

    public interface CHALLENGE_TYPE {
        String CURRENT = "C";
        String PAST = "P";
        String JOINED = "J";
    }

    public interface LIKE_TYPE {
        String SOCIAL = "S";
        String FOROUM = "F";
        String CHALLENGE = "C";
    }

    public interface CHALLENGE_CATEGORY {
        String WEEKLY = "W";
        String MONTHLY = "M";
        String RANDOM = "R";

        String WEEKLY_TAG = "Weekly";
        String WEEK_TAG = "Week";
        String MONTHLY_TAG = "Monthly";
        String MONTH_TAG = "Month";
        String RANDOM_TAG = "Random";

    }


    public interface REQUEST_CODE {
        int CAPTURE_IMAGE = 0;
        int GALLARY_IMAGE = 1;
        int CAMERA_PERMISSION = 2;
        int WRITE_EXTERNAL_PERMISSION = 3;
        int CHALLENGE_JOINED = 1001;
        int VOTE_DONE = 1102;
        int CREATE_SOCIAL_POST = 1103;
        int EDIT_JOIN_CHALLENGE = 1104;
        int EDIT_SOCIAL_POST = 1105;
        int ADD_STORY_REQ = 1106;
        int APP_SETTING = 1109;
        int CROP_VIDEO = 2200;

        int CAMERA_REQUEST_CODE = 7500;
        int CAMERA_VIDEO_REQUEST_CODE = 7501;
        int GALLERY_REQUEST_CODE = 7502;
        int MULTIPLE_IMAGE_VIDEOS_GALLERY_REQUEST_CODE = 7503;
        int ON_STORY_CLOSE_REQ_CODE = 7504;
        int CROP_IMAGE = 1545;
        int SELECTED_VEHICLE_REQUEST_CODE = 8001;
        int SELECT_PURPOSE_REQUEST_CODE = 8002;
        int FORUM_ADDED_REQUEST_CODE = 8003;
        int EDIT_USER_PROFILE = 7505;
        int EDIT_GENDER = 7506;
    }

    public interface VOTE_TYPE {
        int FREE_VOTE = 1;
        int PAID_VOTE = 2;
    }
}
