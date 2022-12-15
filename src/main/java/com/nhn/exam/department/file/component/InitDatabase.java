package com.nhn.exam.department.file.component;

import com.nhn.exam.department.file.FileProperties;
import com.nhn.exam.department.file.request.InitDepartmentInfo;
import com.nhn.exam.department.service.DepartmentInfoService;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InitDatabase {

  private final FileProperties fileProperties;
  private final DepartmentInfoService departmentInfoService;

  public InitDatabase(FileProperties fileProperties, DepartmentInfoService departmentInfoService) {
    this.fileProperties = fileProperties;
    this.departmentInfoService = departmentInfoService;

    log.info("Init Database Constructor with FilePath : " + fileProperties.getName());
  }

  public void initData() throws IOException {


    try {
      FileReader fileReader = new FileReader(fileProperties.getName());
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;
      while (Objects.nonNull(line = bufferedReader.readLine())) {
        StringTokenizer tokenizer = new StringTokenizer(line, "\t-| ");

        if (tokenizer.hasMoreTokens()) {

          InitDepartmentInfo info = new InitDepartmentInfo();
          
          info.setEmployeeId(tokenizer.nextToken());
          info.setEmployeeName(tokenizer.nextToken());
          info.setDepartmentName(tokenizer.nextToken());
          info.setDepartmentId(tokenizer.nextToken());

          log.info("Employee : \n" + info);

          departmentInfoService.registerDepartmentInfo(info);
        }
      }
    } catch (FileNotFoundException e) {
      log.info("Exception : " + e.getMessage());
    }

  }

  public boolean filePathIsPresent() {
    return !Objects.isNull(fileProperties.getName());
  }

}
