package com.bridgeway.roi.service;

import com.bridgeway.roi.costmodel.CostFactors;
import com.bridgeway.roi.response.CostModelResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CostModelService {

    public CostModelResponse calculateCost(CostFactors costFactors) {
        log.info("calculateCost with input: " + costFactors.toString());
        StopWatch watch = new StopWatch();
        watch.start();

        // Calculate costs for each category
        double infrastructureCost = 0f;
        if (ObjectUtils.isNotEmpty(costFactors.getInfrastructure()))
            infrastructureCost = getRandomValue(costFactors.getInfrastructure().getRateMin(),
                    costFactors.getInfrastructure().getRateMax());
        double trainingCost = 0f;
        if (ObjectUtils.isNotEmpty(costFactors.getTrainingAndFineTuning()))
            trainingCost = getRandomValue(costFactors.getTrainingAndFineTuning().getRateMin(),
                    costFactors.getTrainingAndFineTuning().getRateMax());
        double maintenanceCost = 0f;
        if (ObjectUtils.isNotEmpty(costFactors.getOngoingMaintenance()))
            maintenanceCost = getRandomValue(costFactors.getOngoingMaintenance().getRateMin(),
                    costFactors.getOngoingMaintenance().getRateMax());
        double miscellaneousCost = 0f;
        if (ObjectUtils.isNotEmpty(costFactors.getMiscellaneousCosts()))
            miscellaneousCost = getRandomValue(costFactors.getMiscellaneousCosts().getRateMin(),
                    costFactors.getMiscellaneousCosts().getRateMax());
        double salaryCost = 0f;
        if (ObjectUtils.isNotEmpty(costFactors.getHumanResources()))
            miscellaneousCost = getRandomValue(costFactors.getHumanResources().getSalaryMin(),
                    costFactors.getHumanResources().getSalaryMax());

        // Calculate Total Cost of Ownership (TCO)
        double tco = infrastructureCost + trainingCost + maintenanceCost + miscellaneousCost + salaryCost;
        log.info("tco: " + tco);

        // Assuming some hypothetical benefits or savings
        double netBenefits = getRandomValue(500, 25000 * 100.0); //100000
        netBenefits = Math.round(netBenefits * 100.0) / 100.0;

        // Calculate Return on Investment (ROI)
        double roi = (netBenefits / tco) * 100;
        roi = Math.round(roi * 100.0) / 100.0;

        // Output the results
        CostModelResponse costModelResponse = new CostModelResponse(netBenefits, roi);
//        CostModelResponse costModelResponse = new CostModelResponse(1010908.15, 756.3);
        log.info("calculateCost with output: " + costModelResponse.toString());
        watch.stop();
        log.info("calculateCost time elapsed: " + watch.getTime() + " milliseconds");
        return costModelResponse;
    }

    // Helper method to generate random values within the given range
    private static double getRandomValue(double min, double max) {
        return min + Math.random() * (max - min);
    }

    /** For Aspect Logging
     * https://github.com/aniruthmp/library-system/
     * blob/master/common/src/main/java/io/pivotal/common/log/LoggingAspect.java
     *
     */
}
