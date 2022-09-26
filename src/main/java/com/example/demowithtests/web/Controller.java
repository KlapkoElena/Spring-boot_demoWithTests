package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.*;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.util.config.EmployeeConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Employee", description = "Employee API")
public class Controller {

    private final Service service;
    private final EmployeeConverter converter;


    //Операция сохранения юзера в базу данных
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
        public EmployeeSave1Dto saveEmployee(@RequestBody @Valid EmployeeSave1Dto requestForSave) {

        Employee employee = converter.getMapperFacade().map(requestForSave, Employee.class);
        EmployeeSave1Dto dto = converter.toSaveDto(service.create(employee));

        return dto;
    }

    //save dto2
    @PostMapping("/users/save2")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeSave2Dto saveEmployee2(@RequestBody @Valid EmployeeSave2Dto requestForSave) {

        var employee = converter.getMapperFacade().map(requestForSave, Employee.class);
        var dto = converter.toSave2Dto(service.create(employee));

        return dto;
    }

    //Получение списка юзеров
    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {
        return service.getAll();
    }

    //Получения юзера по id
    @GetMapping("/users/dto1/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeRead1Dto getEmployeeById(@PathVariable Integer id) {
        log.debug("getEmployeeById() Controller - start: id = {}", id);
        var employee = service.getById(id);
        log.debug("getById() Controller - to dto start: id = {}", id);
        var dto = converter.toReadDto(employee);
        log.debug("getEmployeeById() Controller - end: name = {}", dto.name);
        return dto;
    }

    @GetMapping("/users/dto2/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeRead2Dto getEmployeeById2 (@PathVariable Integer id) {
        log.debug("getEmployeeById() Controller - start: id = {}", id);
        var employee = service.getById(id);
        log.debug("getById() Controller - to dto start: id = {}", id);
        var dto = converter.toRead2Dto(employee);
        log.debug("getEmployeeById() Controller - end: name = {}", dto.name);
        return dto;
    }

    //Получения юзеров по имени
    @GetMapping(value = "users", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getName(@RequestParam(value = "name") String name) {
        return service.getName(name);
    }

    //Получения юзеров по стране
    @GetMapping(value = "users", params = {"country"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getCountry(@RequestParam(value = "country") String country) {
        return service.getCountry(country);
    }

    //Обновление юзеров по стране
    @PutMapping("/users/{country}")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateCountry(@PathVariable("country") String country, @RequestBody Employee employee) {
        return service.updateById(Integer.valueOf(country), employee);
    }

    // Получение имен всех юзеров
    @GetMapping(value = "/users", params = {"allname"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllName(@RequestParam(value = "name") String name) {
        return service.getAllName(name);
    }

    //Получения юзеров по номеру телефона
    @GetMapping(value = "/users", params = {"phone"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getNameByPhone(@RequestParam(value = "phone") Integer phone) {
        return service.getNameByPhone(phone);
    }

    //Обновление юзера
    @PatchMapping("/users/update1/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeUpdate1Dto refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        EmployeeUpdate1Dto dto = converter.toUpdateDto(service.updateById(id, employee));
        return dto;
    }

    //Обновление юзерa по стране2
    @PutMapping("/users/update2/{country}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeUpdate2Dto refreshEmployee2(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        EmployeeUpdate2Dto dto = converter.toUpdate2Dto(service.updateById(id, employee));
        return dto;
    }

    //Удаление по id
    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable Integer id) {
        service.removeById(id);
    }

    //Удаление всех юзеров
    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        service.removeAll();
    }
}