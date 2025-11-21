package com.capco.pricing.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.ProcessorMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableAspectJAutoProxy
@Slf4j
@Profile("!test")
public class MetricsConfig {
  /**
   * Creates a {@code MeterRegistryCustomizer} bean that configures common tags
   * for metrics stored in the provided {@code MeterRegistry}.
   *
   * @return A {@code MeterRegistryCustomizer} function that sets common tags.
   */

  private final JvmGcMetrics jvmGcMetrics = new JvmGcMetrics();

  @Bean
  public MeterBinder standardJvmMetrics() {
    return registry -> {
      new ClassLoaderMetrics().bindTo(registry);
      new JvmMemoryMetrics().bindTo(registry);
      new ProcessorMetrics().bindTo(registry);
      new JvmThreadMetrics().bindTo(registry);
      jvmGcMetrics.bindTo(registry);

      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try {
          jvmGcMetrics.close();
        } catch (Exception e) {
          log.error("Failed to close JvmGcMetrics: {}", e.getMessage(), e);
        }
      }));
    };
  }



  @Bean
  public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(
      @Value("${spring.profiles.active}") String environment) {
    return registry ->
        registry.config()
            .commonTags("app", "shopping-cart-app")
            .commonTags("env", environment);
  }

  /**
   * Creates a {@code TimedAspect} bean that allows automatic timing (measurement of
   * execution duration) of methods annotated with {@code @Timed}.
   *
   * @param registry The {@code MeterRegistry} where timing data will be recorded.
   * @return A {@code TimedAspect} bean for method timing instrumentation.
   */
  @Bean
  public TimedAspect timedAspect(MeterRegistry registry) {
    return new TimedAspect(registry);
  }
}