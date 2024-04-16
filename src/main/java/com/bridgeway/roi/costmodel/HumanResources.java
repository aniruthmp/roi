package com.bridgeway.roi.costmodel;

import lombok.Data;

import java.util.List;

@Data
public class HumanResources {
    private String description;
    private List<String> roles;
    private double salaryMin;
    private double salaryMax;
}
