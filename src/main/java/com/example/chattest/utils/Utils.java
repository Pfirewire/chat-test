package com.example.chattest.utils;

import com.example.chattest.models.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    public static Long currentUserId() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
