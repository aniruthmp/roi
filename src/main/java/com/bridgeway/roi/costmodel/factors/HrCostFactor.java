package com.bridgeway.roi.costmodel.factors;

import com.bridgeway.roi.costmodel.Factor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class HrCostFactor implements Factor {

    private double baseSalary;

    @Override
    public double calculateCost(double numEmployees) {
        return numEmployees * baseSalary;
    }

}