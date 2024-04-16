package com.bridgeway.roi.api;

import com.bridgeway.roi.request.CostModelRequest;
import com.bridgeway.roi.service.CostModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CostModelApi {

    @Autowired
    private CostModelService costModelService;

    @PostMapping("/cost-model")
    public double calculateCost(@RequestBody CostModelRequest request) {
        return costModelService.calculateCost(request);
    }
}
