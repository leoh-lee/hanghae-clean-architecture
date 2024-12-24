package com.leoh.hhweek2;

import org.springframework.boot.SpringApplication;

public class TestHhWeek2Application {

    public static void main(String[] args) {
        SpringApplication.from(HhWeek2Application::main).with(TestcontainersConfiguration.class).run(args);
    }

}
