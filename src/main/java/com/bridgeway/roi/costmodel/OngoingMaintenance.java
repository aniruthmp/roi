package com.bridgeway.roi.costmodel;

import lombok.Data;

@Data
public class OngoingMaintenance {
    private short noOfHours;
    private double rate;
    private double monthlyCost = 0f;
}
