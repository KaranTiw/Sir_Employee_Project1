package com.in.sp.service;
import com.in.sp.entity.Employee;
import com.in.sp.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import com.in.sp.payload.EmployeeDto;
import com.in.sp.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private  ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        Employee employee = mapToEntity(employeeDto);
        Employee save = employeeRepository.save(employee);
        EmployeeDto employeeDto1 = mapToDto(employee);
        return employeeDto1;
    }


    public List<EmployeeDto> getEmployeege() {
        List<Employee> employee = employeeRepository.findAll();
        List<EmployeeDto> employeesDto= employee.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        return employeesDto;
    }


    public void deleteEmployeeById(long id) {
      employeeRepository.deleteById(id);


    }



    public void updateEmployeeById(long id, Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
//        employeeDto.setId(employee.getId());
//        employeeDto.setName(employee.getName());
//        employeeDto.setEmail(employee.getEmail());
//        employeeDto.setMobile(employee.getMobile());
//
        employee.setId(id);
        employeeRepository.save(employee);
    }


    public EmployeeDto mapToDto(Employee employee) {

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
//
//        Employee employee = new Employee();
//        employee.setId(employeeDto.getId());
//        employee.setName(employeeDto.getName());
//        employee.setEmail(employeeDto.getEmail());
//        employee.setMobile(employeeDto.getMobile());

        return employeeDto;
    }

    public Employee mapToEntity(EmployeeDto employeeDto) {

        Employee employee = modelMapper.map(employeeDto, Employee.class);
//        Employee employee = new Employee();
//        employee.setId(employeeDto.getId());
//        employee.setName(employeeDto.getName());
//        employee.setEmail(employeeDto.getEmail());
//        employee.setMobile(employeeDto.getMobile());
        return employee;
    }

    public EmployeeDto getEmployeeDtoById(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Record not Found with id:"+id));
       EmployeeDto employeeDto =  mapToDto(employee);
        return employeeDto;

    }

    public List<EmployeeDto> getAllEmployees(int pageNo, int pageSize, String sortBy) {

        Pageable page = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Employee> all = employeeRepository.findAll(page);

        List<Employee> employees = all.getContent();
        List<EmployeeDto> employeeDtos = employees.stream().map(e->mapToDto(e)).collect(Collectors.toList());

         return employeeDtos;
    }
}
