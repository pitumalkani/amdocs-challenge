package com.amdocs.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The Class Application.
 */
@ComponentScan("com.amdocs.app")
@SpringBootApplication
public class Application {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main( String... args ) {
        SpringApplication.run( Application.class, args );

    }

}
