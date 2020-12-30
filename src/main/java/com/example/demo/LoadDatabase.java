package com.example.demo;

import com.example.demo.model.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(DeviceRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Device("Device 1", "12-1222", "abc")));
            log.info("Preloading " + repository.save(new Device("Device 2", "3455670-22222", "xyz")));
        };
    }
}
