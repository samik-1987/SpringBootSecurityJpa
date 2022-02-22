package com.example.security;

import java.net.UnknownHostException;
import java.time.Duration;

import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.export.simple.SimpleMetricsExportAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.config.MeterFilter;
import io.micrometer.core.instrument.util.NamedThreadFactory;
import io.micrometer.newrelic.NewRelicConfig;
import io.micrometer.newrelic.NewRelicMeterRegistry;

@Configuration
@AutoConfigureBefore({ CompositeMeterRegistryAutoConfiguration.class, SimpleMetricsExportAutoConfiguration.class })
@AutoConfigureAfter(MetricsAutoConfiguration.class)
@ConditionalOnClass(NewRelicMeterRegistry.class)
public class MicrometerConfig {
    @Bean
    public NewRelicConfig newRelicConfig() {
        return new NewRelicConfig() {
            @Override
            public String get(String key) {
                return null;
            }
            @Override
            public String accountId() {
                return "3417101";
            }
            @Override
            public String apiKey() {
                return "002bb6b4c0451b9b7c8b356d1dd64fde8b86NRAL";
            }
//          @Override
//          public String uri() {
//              return "https://metric-api.newrelic.com/metric/v1";
//          }
            @Override
            public Duration step() {
                return Duration.ofSeconds(5);
            }
            @Override
            public String eventType() {
                return "SpringBootSecurity20";
            }
        };
    }
    @Bean
    public NewRelicMeterRegistry newRelicMeterRegistry(NewRelicConfig config) throws UnknownHostException {
//    Attributes commonAttributes=new Attributes();
//    commonAttributes.put("host", InetAddress.getLocalHost().getHostName());
        NewRelicMeterRegistry newRelicRegistry = NewRelicMeterRegistry.builder(config).build();
        newRelicRegistry.config().meterFilter(MeterFilter.ignoreTags("plz_ignore_me"));
        newRelicRegistry.config().meterFilter(MeterFilter.denyNameStartsWith("jvm.threads"));
        newRelicRegistry.start(new NamedThreadFactory("newrelic.micrometer.registry"));
        return newRelicRegistry;
    }
}