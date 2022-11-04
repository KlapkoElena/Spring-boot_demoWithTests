package com.example.demowithtests.repository;

import com.example.demowithtests.domain.Employee;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
//@Component
public interface Repository extends JpaRepository<Employee, Integer> {

    //Employee findByName(String name);

    // вывод юзеров с определенным именем
    @Query(value = "SELECT * FROM users WHERE name = ?", nativeQuery = true)
    List<Employee> getEmployeeByName(String name);

    // вывод юзеров по определенной стране
    @Query(value = "SELECT * FROM users WHERE country = ?", nativeQuery = true)
    List<Employee> getEmployeeByCountry(String country);

    // вывод имен всех юзеров
    @Query(value = "SELECT name FROM users", nativeQuery = true)
    List<Employee> getAllByName(String name);

    // вывод юреза по номеру телефона
    @Query("SELECT e FROM Employee e WHERE e.phone = ?1")
    List<Employee> getEmployeeByPhone (Integer phone);

    // поиск пользователей с применением пагинации, фильтрации, сортировки
    Page<Employee> findAll(Pageable pageable);

    // поиск по имени c применением пагинации, фильтрации, сортировки
    Page<Employee> findByName(String name, Pageable pageable);

    // поиск по стране c применением пагинации, фильтрации, сортировки
    Page<Employee> findByCountry(String name, Pageable pageable);

    // поиск пользователей по логину
    Optional<Employee> findByUsername(String username);
}
