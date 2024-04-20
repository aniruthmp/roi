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
        if (ObjectUtils.isNotEmpty(costFactors.getInfrastructure())) {
            infrastructureCost = costFactors.getInfrastructure().getNoOfServices() *
                    costFactors.getInfrastructure().getRate();
            costFactors.getInfrastructure().setMonthlyCost(infrastructureCost);
        }
        log.info("infrastructureCost: " + infrastructureCost);

        double softwareCost = 0f;
        if (ObjectUtils.isNotEmpty(costFactors.getSoftwareAndTools())) {
            softwareCost = costFactors.getSoftwareAndTools().getNoOfUsers() *
                    costFactors.getSoftwareAndTools().getLlmCost();
            costFactors.getSoftwareAndTools().setMonthlyCost(softwareCost);
        }
        log.info("softwareCost: " + softwareCost);

        double trainingCost = 0f;
        if (ObjectUtils.isNotEmpty(costFactors.getTrainingAndFineTuning())) {
            trainingCost = costFactors.getTrainingAndFineTuning().getNoOfHours() *
                    costFactors.getTrainingAndFineTuning().getRate();
            costFactors.getTrainingAndFineTuning().setMonthlyCost(trainingCost);
        }
        log.info("trainingCost: " + trainingCost);

        double maintenanceCost = 0f;
        if (ObjectUtils.isNotEmpty(costFactors.getOngoingMaintenance())) {
            maintenanceCost = costFactors.getOngoingMaintenance().getNoOfHours() *
                    costFactors.getOngoingMaintenance().getRate();
            costFactors.getOngoingMaintenance().setMonthlyCost(maintenanceCost);
        }
        log.info("maintenanceCost: " + maintenanceCost);

        // Calculate Total Cost of Ownership (TCO)
        double totalMonthlyCost = infrastructureCost + softwareCost + trainingCost + maintenanceCost;
        log.info("totalMonthlyCost: " + totalMonthlyCost);

        // Calculate Value
        double totalMonthlyValue = 0f;
        if (ObjectUtils.isNotEmpty(costFactors.getValue())) {
            totalMonthlyValue = costFactors.getValue().getAdditionalRevenue() +
                    costFactors.getValue().getCostReduction() +
                    (costFactors.getValue().getProductivityHoursGained() * 60) + //TODO: Check with Jesse
                    costFactors.getValue().getRiskAvoided();
            costFactors.getValue().setTotalMonthlyValue(totalMonthlyValue);
        }
        log.info("totalMonthlyValue: " + totalMonthlyValue);

        // Calculate Return on Investment (ROI)
        double roi = ((totalMonthlyValue - totalMonthlyCost) / totalMonthlyCost) * 100;
        roi = Math.round(roi * 100.0) / 100.0;

        // Output the results
        CostModelResponse costModelResponse = new CostModelResponse(totalMonthlyCost, totalMonthlyValue, roi);
        log.info("calculateCost with output: " + costModelResponse.toString());
        watch.stop();
        log.info("calculateCost time elapsed: " + watch.getTime() + " milliseconds");
        return costModelResponse;
    }

    /** For Aspect Logging
     * https://github.com/aniruthmp/library-system/
     * blob/master/common/src/main/java/io/pivotal/common/log/LoggingAspect.java
     *
     */
}
