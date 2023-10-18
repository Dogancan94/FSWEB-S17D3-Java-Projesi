package com.workintech.zoo.exceptions;

import com.workintech.zoo.entity.Kangaroo;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class ZooValidation {

    public static void isIdValid(int id) {
        if (id <= 0) {
            throw new ZooException("Id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkKangarooExistence(Map<Integer, Kangaroo> animals, int id, boolean isExist){
        if(isExist){
            if(animals.containsKey(id)){
                throw new ZooException("Id is already exist: " + id, HttpStatus.BAD_REQUEST);
            }
        } else{
            if(!animals.containsKey(id)){
                throw new ZooException("Id is not exist: " + id, HttpStatus.NOT_FOUND);
            }
        }
    }

    public static void checkKangarooWeight(double weight) {
        if (weight <= 0) {
            throw new ZooException("Weight must be greater zero: " + weight, HttpStatus.BAD_REQUEST);
        }
    }

}
