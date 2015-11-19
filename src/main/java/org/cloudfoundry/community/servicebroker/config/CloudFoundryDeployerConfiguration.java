package org.cloudfoundry.community.servicebroker.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.dataflow.module.deployer.cloudfoundry.CloudFoundryModuleDeployerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("cloud")
@EnableConfigurationProperties
public class CloudFoundryDeployerConfiguration extends CloudFoundryModuleDeployerConfiguration {
}
