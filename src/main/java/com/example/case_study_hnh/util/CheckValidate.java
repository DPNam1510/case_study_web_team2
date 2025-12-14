package com.example.case_study_hnh.util;

import java.time.LocalDate;

public class CheckValidate {
    public static boolean checkDate(LocalDate date){
        LocalDate today = LocalDate.now();
        return !date.isAfter(today);
    }
}
