package org.example;

import org.springframework.batch.core.scope.StepScope;
import org.springframework.context.annotation.Bean;

//@Configuration
public class Components {
    @Bean
    public StepScope raptorStepScope() {
        StepScope scope = new StepScope();
        scope.setAutoProxy(false);
        scope.setProxyTargetClass(true);
        return scope;
    }
}
