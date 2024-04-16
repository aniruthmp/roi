package com.bridgeway.roi.costmodel;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CostFactors {
    private String guidance;
    private Infrastructure infrastructure;
    private SoftwareAndTools softwareAndTools;
    private HumanResources humanResources;
    private TrainingAndFineTuning trainingAndFineTuning;
    private OngoingMaintenance ongoingMaintenance;
    private MiscellaneousCosts miscellaneousCosts;
}
