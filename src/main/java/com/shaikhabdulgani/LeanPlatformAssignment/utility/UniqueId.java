package com.shaikhabdulgani.LeanPlatformAssignment.utility;

import java.util.Random;

public class UniqueId {

    private static final String validCharacters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int idLength = 30;
    public static String user(){
        return "usr_"+generate();
    }
    public static String review(){
        return "rev_"+generate();
    }
    public static String rate(){
        return "rte_"+generate();
    }
    public static String student(){
        return "stu_"+generate();
    }
    public static String mentor(){
        return "mnt_"+generate();
    }
    public static String recommendation(){
        return "rec_"+generate();
    }
    private static String generate(){
        StringBuilder randomId = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < idLength; i++) {
            randomId.append(validCharacters.charAt(random.nextInt(validCharacters.length())));
        }
        return randomId.toString();
    }
}
