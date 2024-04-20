package com.bridgeway.roi.costmodel;

import lombok.Data;

@Data
public class Value {
    private double additionalRevenue;
    private double costReduction;
    private short productivityHoursGained;
    private double riskAvoided;
    private double totalMonthlyValue = 0f;
}
