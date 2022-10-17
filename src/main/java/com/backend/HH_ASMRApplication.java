package com.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@SpringBootApplication
public class HH_ASMRApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(HH_ASMRApplication.class, args);

		} catch (Throwable e) {
			if (e.getClass().getName().contains("SilentExitException")) {
				LOGGER.debug("Spring is restarting the main thread - See spring-boot-devtools");
			} else {
				LOGGER.error("Application crashed!", e);
			}
		}
	}
}
