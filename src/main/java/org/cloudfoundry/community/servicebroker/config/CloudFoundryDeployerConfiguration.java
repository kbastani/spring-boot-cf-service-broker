package org.cloudfoundry.community.servicebroker.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.dataflow.module.deployer.ModuleDeployer;
import org.springframework.cloud.dataflow.module.deployer.cloudfoundry.CloudFoundryModuleDeployerConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnMissingBean(ModuleDeployer.class)
@EnableConfigurationProperties
public class CloudFoundryDeployerConfiguration extends CloudFoundryModuleDeployerConfiguration {
}
