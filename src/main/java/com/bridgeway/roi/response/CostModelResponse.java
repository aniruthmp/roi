package com.bridgeway.roi.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CostModelResponse {
    double monthlyCost;
    double monthlyGain;
    double roi;
}
