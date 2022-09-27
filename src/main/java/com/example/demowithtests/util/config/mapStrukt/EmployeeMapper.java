package com.example.demowithtests.util.config.mapStrukt;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeSave1Dto employeeSave2Dto(Employee employee);

    Employee employeeSave2Dto(EmployeeSave2Dto employeeSave2DtoDto);

    EmployeeRead1Dto employeeReadDto(Employee employee);

    Employee employeeReadDto(EmployeeRead1Dto employeeReadDto);

    EmployeeUpdate1Dto employeeUpdateDto(Employee employee);

    Employee employeeUpdateDto(EmployeeUpdate1Dto employeeUpdateDto);

}