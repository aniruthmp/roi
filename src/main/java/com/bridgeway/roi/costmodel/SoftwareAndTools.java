package com.bridgeway.roi.costmodel;

import lombok.Data;

@Data
public class SoftwareAndTools {
    private int noOfUsers;
    private double llmCost;
    private double monthlyCost = 0f;
}
