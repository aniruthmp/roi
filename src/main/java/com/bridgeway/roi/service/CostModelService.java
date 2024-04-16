package com.bridgeway.roi.service;

import com.bridgeway.roi.config.CostModelProperties;
import com.bridgeway.roi.config.FactorProperties;
import com.bridgeway.roi.costmodel.Factor;
import com.bridgeway.roi.costmodel.factors.CloudCostFactor;
import com.bridgeway.roi.costmodel.factors.HrCostFactor;
import com.bridgeway.roi.request.CostModelRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class CostModelService {

    //    private final InfrastructureProperties infrastructureProperties;
//    private final HrProperties hrProperties;
    private final FactorProperties factorProperties;

    @Autowired
    public CostModelService(FactorProperties factorProperties) {
        this.factorProperties = factorProperties;
    }

    public double calculateCost_(CostModelRequest request) {

        CostModelProperties infrastructureFactor = factorProperties.getFactors().get("infrastructure");
        log.info("className: " + infrastructureFactor.getClassName());

        CloudCostFactor cloudCostFactor = getCostModelFactor(infrastructureFactor.getClassName());
        cloudCostFactor.setRateMax(infrastructureFactor.getRateMax());
        cloudCostFactor.setRateMin(infrastructureFactor.getRateMin());
        log.info("minRate: " + cloudCostFactor.getRateMin());
        log.info("maxRate: " + cloudCostFactor.getRateMax());

        CostModelProperties hrFactor = factorProperties.getFactors().get("hr");
        log.info("hrClassName: " + hrFactor.getClassName());
        HrCostFactor hrCostFactor = getCostModelFactor(hrFactor.getClassName());
        hrCostFactor.setBaseSalary(hrFactor.getBaseSalary());
        log.info("baseSalary: " + hrCostFactor.getBaseSalary());

        return 0f;
    }


    public double calculateCost(CostModelRequest request) {
        StopWatch watch = new StopWatch();
        watch.start();
        double totalCost = 0;
        for (Map.Entry<String, Double> entry : request.getFactors().entrySet()) {
            Factor factor = getCostModelFactor(String.valueOf(factorProperties.getFactors().get(entry.getKey()).getClassName()));
            if (factor != null) {
                totalCost += factor.calculateCost(entry.getValue());
            } else {
                // Handle unknown factors (e.g., throw exception or log warning)
            }
        }
        watch.stop();
        log.info(" Time Elapsed: " + watch.getTime() + " milliseconds");

        return totalCost;
    }

    private <T extends Factor> T getCostModelFactor(String className) {
        try {
            Class<T> factorClass = (Class<T>) Class.forName(className);
            return factorClass.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error creating CostModelFactor instance", e);
        }
    }

}
