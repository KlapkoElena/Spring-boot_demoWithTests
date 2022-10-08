package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import org.springframework.data.domain.Page;

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

    /**
     * Find all employees, sort them by the given list of fields, and return the page of results.
     *
     * @param page The page number to be retrieved.
     * @param size The number of records to return per page.
     * @param sortList A list of fields to sort by.
     * @param sortOrder The sort order, either "asc" or "desc".
     * @return A Page<Employee> object.
     */
    Page<Employee> findAll(int page, int size, List<String> sortList, String sortOrder);

    Page<Employee> findByName(String name, int page, int size, List<String> sortList, String sortOrder);

    Page<Employee> findByCountry(String country, int page, int size, List<String> sortList, String sortOrder);
}
