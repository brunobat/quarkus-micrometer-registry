package io.quarkiverse.micrometer.registry.influx;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.interceptor.Interceptor;

import io.micrometer.core.instrument.Clock;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;
import io.quarkus.arc.Priority;

@Singleton
public class ConditionalRegistryProducer {
    /**
     * This producer is added as a bean by the Processor IFF the default registry
     * instance has been enabled.
     */
    @Produces
    @Singleton
    @Alternative
    @Priority(Interceptor.Priority.APPLICATION + 100)
    public InfluxMeterRegistry registry(InfluxConfig config, Clock clock) {
        return new InfluxMeterRegistry(config, clock);
    }
}
