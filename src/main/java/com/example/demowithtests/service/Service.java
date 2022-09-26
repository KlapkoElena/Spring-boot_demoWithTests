package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;

import java.util.List;

public interface Service {

    Employee create(Employee employee);

    List<Employee> getAll();

    Employee getById(Integer id);

    Employee getByCountry(String country);

    Employee updateById(Integer id, Employee plane);

    void removeById(Integer id);

    void removeAll();

    List<Employee> getCountry(String country);

    Employee updateByCountry(String country);

    Employee updateByCountry(String country, Employee employee);

    List<Employee> getName(String name);

    Employee updateByName(String name);

    Employee updateByName(String name, Employee employee);

    List<Employee> getByName(String name);

    List<Employee> getAllName(String name);

    List<Employee> getNameByPhone(Integer phone);

}
