package com.example.demowithtests.util.config;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.*;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

    private final MapperFacade mapperFacade;

    public EmployeeConverter(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    public MapperFacade getMapperFacade() {
        return mapperFacade;
    }

    public EmployeeSave1Dto toSaveDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeSave1Dto.class);
    }
    public EmployeeSave2Dto toSave2Dto(Employee entity) {
        return mapperFacade.map(entity, EmployeeSave2Dto.class);
    }

    public EmployeeRead1Dto toReadDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeRead1Dto.class);
    }
    public EmployeeRead2Dto toRead2Dto(Employee entity) {
        return mapperFacade.map(entity, EmployeeRead2Dto.class);
    }

    public EmployeeUpdate1Dto toUpdateDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeUpdate1Dto.class);
    }

    public EmployeeUpdate2Dto toUpdate2Dto(Employee entity) {
        return mapperFacade.map(entity, EmployeeUpdate2Dto.class);
    }

}