package com.example.android_rd_code.api;

/**
 * All API Response code
 */
public interface APIStatusCode {
    int OK       				= 200;
    int CREATED      			= 201;
    int NO_CONTENT      	    = 204;
    int BAD_REQUEST             = 400;
    int AUTHENTICATION_FAILED   = 401;
    int RECORD_NOT_FOUND        = 404;
    int CONFLICT                = 409;
    int SERVER_ERROR            = 500;
    int SLOW_NETWORK_ERROR      = 600;
    int DELETED  				= 410;
}
