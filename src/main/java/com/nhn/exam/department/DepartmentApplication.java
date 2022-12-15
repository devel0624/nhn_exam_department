package com.nhn.exam.department;

import com.nhn.exam.department.file.FileProperties;
import com.nhn.exam.department.file.component.InitDatabase;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@Slf4j
@ConfigurationPropertiesScan
@SpringBootApplication
public class DepartmentApplication {

  private final InitDatabase initDatabase;

  public DepartmentApplication(InitDatabase initDatabase) {
    this.initDatabase = initDatabase;
  }

  public static void main(String[] args) {
    SpringApplication.run(DepartmentApplication.class, args);
  }

  @Bean
  public ApplicationListener<ApplicationReadyEvent> applicationListener() {
    return (event -> {
      try {
        if (initDatabase.filePathIsPresent()) {
          initDatabase.initData();
        }
      } catch (IOException e) {
        log.info(e.getMessage());
      }
    });
  }
}
