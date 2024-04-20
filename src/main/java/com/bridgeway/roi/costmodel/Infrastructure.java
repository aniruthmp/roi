package com.bridgeway.roi.costmodel;

import lombok.Data;

@Data
public class Infrastructure {
    private int noOfServices;
    private double rate;
    private double monthlyCost = 0f;
}
