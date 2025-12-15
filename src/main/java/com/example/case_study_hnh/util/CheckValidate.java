package com.example.case_study_hnh.util;

public class CheckValidate {
//    public static boolean checkDate(LocalDate date){
//        LocalDate today = LocalDate.now();
//        return !date.isAfter(today);
//    }
    public static boolean checkName(String name){
        String regexName = "^[A-Z]\\w+(_\\w+)*$";
        return name.matches(regexName);
    }
    public static boolean checkPass(String pass){
        String regexPass = "^\\w+(\\w+)*$";
        return pass.matches(regexPass);
    }
}
