package com.project.service;
/**
 * Test Spring Boot web project
 * @author Oleg Liakhovets
 */
public class UserValidate {

    public static boolean nameValidate(String name){
        int count = 0;
        for (int i = 0; i<name.length(); i++){
            if(Character.isLetter(name.charAt(i))) count++;
        }
        if(name.length() == count) return true;
        else return false;
    }
    public static boolean emailValidate(String name){
        int count = 0;
        for (int i = 0; i<name.length(); i++){
            if((Character.compare('@', name.charAt(i)))==0) count++;
        }
        if(count == 1) return true;
        else return false;
    }
    public static boolean numberValidate(String number){
        int count = 0;
        for (int i = 0; i<number.length(); i++){
            if(!Character.isLetter(number.charAt(i))) count++;
        }
        if(number.length() == count && (number.length()>9 || number.length()<20)) return true;
        else return false;
    }
}
