package cn.cc.dawn.open.auth.dto;

import java.util.ArrayList;
import java.util.List;

public class UserRole {

    public static String ROLE_ROOT = "ROLE_ROOT";
    public static String ROLE_USER = "ROLE_USER";
    public static String ROLE_TEST = "ROLE_TEST";
    public static String ROLE_GUEST = "ROLE_GUEST";
    public static String ROLE_ADMIN = "ROLE_ADMIN";

    public static List<String> stringList = new ArrayList<>();

    static {
        stringList.add(ROLE_ROOT);
        stringList.add(ROLE_ADMIN);
        stringList.add(ROLE_TEST);
        stringList.add(ROLE_GUEST);
    }

}
