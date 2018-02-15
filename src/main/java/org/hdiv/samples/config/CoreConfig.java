package org.hdiv.samples.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "classpath:dataAccess-config.xml" })
public class CoreConfig {

}
