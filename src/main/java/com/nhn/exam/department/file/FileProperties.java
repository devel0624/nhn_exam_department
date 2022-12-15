package com.nhn.exam.department.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "init.file")
public class FileProperties {
  private String name;
}
