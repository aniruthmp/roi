package com.bridgeway.roi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties("costmodel")
@Getter
@Setter
public class FactorProperties {
    private Map<String, CostModelProperties> factors;
}
