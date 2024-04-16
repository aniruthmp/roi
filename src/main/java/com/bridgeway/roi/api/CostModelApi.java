package com.bridgeway.roi.api;

import com.bridgeway.roi.costmodel.CostFactors;
import com.bridgeway.roi.response.CostModelResponse;
import com.bridgeway.roi.service.CostModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CostModelApi {

    @Autowired
    private CostModelService costModelService;

    @PostMapping("/api/roi")
    public CostModelResponse calculateCost(@RequestBody CostFactors request) {
        return costModelService.calculateCost(request);
    }
}
