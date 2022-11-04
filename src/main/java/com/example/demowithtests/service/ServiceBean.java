package com.example.demowithtests.service;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.repository.Repository;
import com.example.demowithtests.util.config.EmployeeDetails;
import com.example.demowithtests.util.exeption.ResourceNotFoundException;
import com.example.demowithtests.util.exeption.ResourceWasDeletedException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@org.springframework.stereotype.Service
public class ServiceBean implements Service, UserDetailsService {

    private final Repository repository;

    @Override
    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public Employee getById(Integer id) {
        Employee employee = repository.findById(id)
                // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceNotFoundException::new);
         /*if (employee.getIsDeleted()) {
            throw new EntityNotFoundException("Employee was deleted with id = " + id);
        }*/
        return employee;
    }

    @Override
    public Employee getByCountry(String country) {
        return null;
    }

    @Override
    public Employee updateById(Integer id, Employee employee) {
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(employee.getName());
                    entity.setEmail(employee.getEmail());
                    entity.setCountry(employee.getCountry());
                    entity.setAdress(employee.getAdress());
                    entity.setPhone(employee.getPhone());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
    }

    @Override
    public void removeById(Integer id) {
        //repository.deleteById(id);
        Employee employee = repository.findById(id)
                // .orElseThrow(() -> new EntityNotFoundException("Employee not found with id = " + id));
                .orElseThrow(ResourceWasDeletedException::new);
        //employee.setIsDeleted(true);
        repository.delete(employee);
        //repository.save(employee);
    }

    @Override
    public void removeAll() {
        repository.deleteAll();

    }

    @Override
    public List<Employee> getCountry(String country) {
        return repository.getEmployeeByCountry(country);
    }

    @Override
    public Employee updateByCountry(String country) {
        return null;
    }

    @Override
    public Employee updateByCountry(String country, Employee employee) {
        return repository.findById(Integer.valueOf(country)).map(entity -> {
                    entity.setName(employee.getName());
                    entity.setEmail(employee.getEmail());
                    entity.setCountry(employee.getCountry());
                    entity.setAdress(employee.getAdress());
                    entity.setPhone(employee.getPhone());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found from country = " + country));
    }

    @Override
    public Employee updateByName(String name) {
        return null;
    }

    @Override
    public List<Employee> getName(String name) {
        return repository.getEmployeeByName(name);
    }

    @Override
    public Employee updateByName(String name, Employee employee) {
        return repository.findById(Integer.valueOf(name)).map(entity -> {
                    entity.setName(employee.getName());
                    entity.setEmail(employee.getEmail());
                    entity.setCountry(employee.getCountry());
                    entity.setAdress(employee.getAdress());
                    entity.setPhone(employee.getPhone());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with name = " + name));
    }

    @Override
    public List<Employee> getByName(String name) {
        return null;
    }

    @Override
    public List<Employee> getAllName(String name) {
        return repository.getAllByName(name);
    }

    @Override
    public List<Employee> getNameByPhone(Integer phone) {
        return repository.getEmployeeByPhone(phone);
    }

    @Override
    public Page<Employee> findAll(int page, int size, List<String> sortList, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        return repository.findAll(pageable);
    }

    @Override
    public Page<Employee> findByName(String name, int page, int size, List<String> sortList, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        return repository.findByName(name, pageable);
    }

    @Override
    public Page<Employee> findByCountry(String country, int page, int size, List<String> sortList, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        return repository.findByCountry(country, pageable);
    }

    private List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }

    @Override
    public List<String> findStreamName() {
        List<Employee> allEmployee = repository.findAll();
        return allEmployee.stream()
                .map(n -> n.getName())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findStreamCountry() {
        List<Employee> allEmployee = repository.findAll();
        return allEmployee.stream()
                .map(n -> n.getCountry())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findNameAndCountry() {
        List<Employee> nameAndCountry = repository.findAll();
        return nameAndCountry.stream()
                .map(n -> "name-" + n.getName() + " (country: " + n.getCountry() + ")")
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findNamePhoneAddress() {
        List<Employee> namePhoneAddress = repository.findAll();
        return namePhoneAddress.stream()
                .map(n -> "name-" + n.getName() + " (phone: " + n.getPhone() + ")" + " (address: " + n.getAdress() + ")")
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = repository.findByUsername(username);

        if (employee.isEmpty())
            throw new UsernameNotFoundException("Employee not found");
        return new EmployeeDetails(employee.get());
    }

}
