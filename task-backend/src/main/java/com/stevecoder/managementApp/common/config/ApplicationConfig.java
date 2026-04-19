package com.stevecoder.managementApp.common.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration // spring boot inicia la app y escanea el proyecto para iniciarlo con esta configuracion
@EnableAsync // Se habilita el async
@EnableScheduling
@EnableCaching
public class ApplicationConfig {
}
