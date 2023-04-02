package com.example.spring2903.home;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private List<Employee> employees = new ArrayList<>();

    public EmployeeController() {
        employees.addAll(Arrays.asList(
                new Employee("123","Tom","Soer",20),
                new Employee("124","Tomas","Old",21),
                new Employee("125","Olga","Volkov",18),
                new Employee("126","Eva","",17),
                new Employee("127","Jeck","Slee",20)

        ));
    }

    @GetMapping
    public List<Employee> getEmployees(){
        return employees;
    }

    @GetMapping(value = "/find")
    public Optional<Employee>findEmployee(@RequestParam(required = false) String id,
                                          @RequestParam(required = false) String name){
        return employees.stream().filter(employee -> {
            if (id != null){
                if (name != null) {
                    return employee.getId().equals(id) && employee.getName().equals(name);
                } else {
                    return employee.getId().equals(id);
                }
            }
            return employee.getName().equals(name);
        }).findAny();
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(employee.getId())){
                employees.set(i, employee);
                return new ResponseEntity<>(employee, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(addEmployee(employee), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Employee> updateEmployeeSurnameAndAge(@PathVariable String id,
                                                                @RequestParam String surname,
                                                                @RequestParam int age){
        Optional<Employee> employeeToUpdate =
                employees.stream().filter(employee -> employee.getId().equals(id)).findAny();
        if (employeeToUpdate.isPresent()) {
            Employee employee = employeeToUpdate.get();
            employee.setSurname(surname);
            employee.setAge(age);
            return new ResponseEntity<>(employee, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteEmployee(@PathVariable String id){
        Employee employeeFromList =
                employees.stream().filter(e -> id.equals(e.getId())).findAny().get();
        employees.remove(employeeFromList);
    }
    // home

    @DeleteMapping(value = "/deleteage")// можно и так   ( employees.removeIf(e -> e.getAge() < age);)
    public void deleteByAge(@RequestParam Integer age){
        Employee employeeFromList =
                employees.stream().filter(e ->
                     (e.getAge()<age)).findAny().get();
        employees.remove(employeeFromList);
    }


    @GetMapping(value ="/getsize" ) //http://localhost:8080/getsize
    public int getEmployeesCount(){
        return employees.size();
    }


    @GetMapping(value ="/onechar" ) //http://localhost:8080/employees/onechar?onename=O
    public List<Employee> findNameOneChar(@RequestParam char onename){
        List<Employee> list = new ArrayList<>();

        Employee employeeFromList =
                employees.stream().filter(e ->
                        (e.getName().charAt(0)==onename)).findAny().get();
        list.add(employeeFromList);
        return list;
    }


    @PatchMapping(value = "/surnamenotDefault")
    public void surnameDefault(@RequestParam String string){
        for (Employee e : employees) {
            if (e.getSurname() == ""){
                e.setSurname(string);
            }
        }
    }
}
