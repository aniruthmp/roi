package com.bridgeway.roi.config;

import lombok.Data;

@Data
public class CostModelProperties {
    private String className;
    private double baseSalary;
    private double rateMin;
    private double rateMax;
}
