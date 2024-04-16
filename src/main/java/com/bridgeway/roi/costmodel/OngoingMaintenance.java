package com.bridgeway.roi.costmodel;

import lombok.Data;

@Data
public class OngoingMaintenance {
    private String description;
    private double rateMin;
    private double rateMax;
}
