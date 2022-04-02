package com.yixin.aggrid.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@ComponentScan("com.yixin")
@SpringBootApplication
public class AgGridApplication {

    private static final Logger LOG = LoggerFactory.getLogger(AgGridApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AgGridApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("Success start");
        LOG.info("Url：\thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
