package com.vf.bugmanagment.util;

public final class ApiPaths {

    private static final String BASE_PATH="/api";

    public static final class BugCtrl{
        public static final String CTRL=BASE_PATH+"/bug";
    }

    public static final class ProjectCtrl{
        public static final String CTRL=BASE_PATH+"/project";
    }
    public static final class UserCtrl{
        public static final String CTRL=BASE_PATH+"/users";
    }

}
