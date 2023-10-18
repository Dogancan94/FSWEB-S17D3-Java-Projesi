package com.workintech.zoo.entity;

import lombok.Data;

@Data
public class Kangaroo extends Animal{
    private double height;
    private boolean isAggressive;
    public Kangaroo(int id, String name, double weight, Gender gender, double height, boolean isAggressive) {
        super(id, name, weight, gender);
        this.height = height;
        this.isAggressive = isAggressive;
    }
}
