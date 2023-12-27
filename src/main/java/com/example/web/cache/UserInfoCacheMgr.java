package com.example.web.cache;

import java.util.HashMap;
import java.util.Map;
import com.example.web.model.User;
public enum UserInfoCacheMgr {
    INSTANCE;

    private static Map<Long,User> userId2UserCacheMgr = new HashMap<>();

    public static User getUserByUserId(Long userId)
    {
        return userId2UserCacheMgr.get(userId);
    }

    public static void setUserId2User(Long userId,User user)
    {
        userId2UserCacheMgr.put(userId, user);
    }

    public static void clearCache()
    {
        userId2UserCacheMgr.clear();
    }
}
