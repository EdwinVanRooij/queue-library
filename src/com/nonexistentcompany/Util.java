package com.nonexistentcompany;

public class Util {
    public static void log(String message, Object... args) {
        System.out.println(String.format(message, args));
    }
}
