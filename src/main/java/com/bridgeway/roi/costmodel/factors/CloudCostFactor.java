package com.bridgeway.roi.costmodel.factors;

import com.bridgeway.roi.costmodel.Factor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class CloudCostFactor implements Factor {

    private double rateMin;
    private double rateMax;

    @Override
    public double calculateCost(double usageHours) {
        if(rateMax > 0)
            return rateMax * usageHours;
        else
            return rateMin * usageHours;
    }

}