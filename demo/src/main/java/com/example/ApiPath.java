package com.example;

public class ApiPath {

    public static final String API_VERSION = "v1";

    public static final String ROOT = "/" + API_VERSION;

    public static final String BUSINESS = ROOT + "/business";

    public static final String BUSINESSES = BUSINESS + "/get";

    public static final String BUSINESSID = BUSINESS +"/get/id/" ;

    public static final String BUSINESSNAME = BUSINESS +"/get/name/" ;

    public static final String BUSINESSLNAME = BUSINESS + "/get/lname/";

    public static final String BUSINESSABN = BUSINESS + "/get/acn/";

    public static final String BUSINESSIDACN = BUSINESS +"/get/acn/" ;

    public static final String ADDRECORD = BUSINESS +"/post" ;

    public static final String DELETE_RECORD = BUSINESS +"/delete/" ;

    public static final String UPDATE_RECORD = BUSINESS +"/update/" ;

    public static final String SEARCH = BUSINESS +"/search/" ;
}

