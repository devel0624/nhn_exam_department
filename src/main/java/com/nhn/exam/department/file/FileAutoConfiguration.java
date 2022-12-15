package com.nhn.exam.department.file;

import com.nhn.exam.department.file.component.InitDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@ConditionalOnClass(InitDatabase.class)
@ConditionalOnProperty("init.file.name")
@EnableConfigurationProperties(FileProperties.class)
public class FileAutoConfiguration {
  private final FileProperties fileProperties;

  public FileAutoConfiguration(FileProperties fileProperties) {
    this.fileProperties = fileProperties;
  }

}
