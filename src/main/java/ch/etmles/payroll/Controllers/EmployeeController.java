package ch.etmles.payroll.Controllers;

import ch.etmles.payroll.Entities.Employee;
import ch.etmles.payroll.Repositories.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }

    /* curl sample :
    curl -i localhost:8080/employees
    */
    @GetMapping
    List<Employee> all(){
        return repository.findAll();
    }

    /* curl sample :
    curl -i -X POST localhost:8080/employees \
        -H "Content-type:application/json" \
        -d "{\"name\": \"Russel George\", \"role\": \"gardener\", \"email\": \"Russel.George@example.com\", \"departement\": {\"id\": 1}}"
    */
    @PostMapping
    Employee newEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    }

    /* curl sample :
    curl -i localhost:8080/employees/1
    */
    @GetMapping("{id}")
    Employee one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/employees/2 \
        -H "Content-type:application/json" \
        -d "{\"name\": \"Samwise Bing\", \"role\": \"peer-to-peer\", \"email\": \"Samwise.Bing@example.com\", \"departement\": {\"id\": 2}}"
     */
    @PutMapping("{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    employee.setDepartement(newEmployee.getDepartement());
                    employee.setEmail(newEmployee.getEmail());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/employees/2
    */
    @DeleteMapping("{id}")
    void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }
}
