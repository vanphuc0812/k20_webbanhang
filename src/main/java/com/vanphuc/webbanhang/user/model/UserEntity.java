package com.vanphuc.webbanhang.user.model;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserEntity {
    public class User {
        public static final String TABLE_NAME = "WBH_USER";
        public static final String USERNAME = "USERNAME";
        public static final String PASSWORD = "PASSWORD";
        public static final String EMAIL = "EMAIL";
    }
}
