package com.in.sp.controller;

import com.in.sp.entity.Employee;
import com.in.sp.payload.EmployeeDto;
import com.in.sp.repository.EmployeeRepository;
import com.in.sp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmpolyeeController {

  private EmployeeService employeeService;

    public EmpolyeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //http://localhost:8181/api/v1/employees

    @PostMapping("/employees")
    public ResponseEntity<?> addEmployee(
            @Valid @RequestBody EmployeeDto employeeDto,
            BindingResult result
    ) {

        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

       EmployeeDto employeeDto1 =  employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(employeeDto1 , HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        List<EmployeeDto> employeeDto1 = employeeService.getEmployeege();
        return new ResponseEntity<>(employeeDto1 , HttpStatus.OK);
    }

    //http://localhost:8181/api/v1/employees/{id}
    @DeleteMapping("/employees/{id}")
    public String deleteEmployeeById(@PathVariable("id") long id  ){
        employeeService.deleteEmployeeById(id);
       return "delete record successfully";
    }

    //http://localhost:8181/api/v1/update/{id}
    @PutMapping("/employees/{id}")
    public String UpdateEmployeeById(@PathVariable("id") long id , @RequestBody Employee employee){
        employeeService.updateEmployeeById(id,employee);
        return  "update record";
    }


    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeDtoById(@PathVariable("id") long id){
        EmployeeDto employeeDto = employeeService.getEmployeeDtoById(id);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }



    //http://localhost:8080/api/v1/employees?pageSize=3&pageNo=1

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(
            @RequestParam(name="pageSize",required = false,defaultValue = "5") int pageSize,

            @RequestParam(name="pageNo",required = false,defaultValue = "0") int pageNo,

            @RequestParam(name="sortedBy",required = false,defaultValue = "0")  String sortBy

            )
    {
        List<EmployeeDto> employeesDto=employeeService.getAllEmployees(pageNo,pageSize,sortBy);

        return new ResponseEntity<>(employeesDto,HttpStatus.OK);
    }







}



