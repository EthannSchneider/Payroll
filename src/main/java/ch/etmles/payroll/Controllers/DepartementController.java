package ch.etmles.payroll.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.etmles.payroll.Entities.Departement;
import ch.etmles.payroll.Repositories.DepartementRepository;

@RestController
public class DepartementController {
    private final DepartementRepository repository;

    DepartementController(DepartementRepository repository){
        this.repository = repository;
    }

    /* curl sample :
    curl -i localhost:8080/departements
    */
    @GetMapping("/departements")
    List<Departement> all() {
        return repository.findAll();
    }

    /* curl sample :
    curl -i -X POST localhost:8080/departements ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"IT\"}"
    */
    @PostMapping("/departements")
    Departement newDepartement(@RequestBody Departement newDepartement) {
        return repository.save(newDepartement);
    }

    /* curl sample :
    curl -i localhost:8080/departements/1
    */
    @GetMapping("/departements/{id}")
    Departement one(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new DepartementNotFoundException(id));
    }

    /* curl sample :
    curl -i -X PUT localhost:8080/departements/2 ^
        -H "Content-type:application/json" ^
        -d "{\"name\": \"HR\"}"
     */
    @PutMapping("/departements/{id}")
    @PatchMapping("/departements/{id}")
    Departement replaceDepartement(@RequestBody Departement newDepartement, @PathVariable Long id) {
        return repository.findById(id)
                .map(departement -> {
                    departement.setName(newDepartement.getName());
                    return repository.save(departement);
                })
                .orElseGet(() -> {
                    newDepartement.setId(id);
                    return repository.save(newDepartement);
                });
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/departements/2
     */
    @DeleteMapping("/departements/{id}")
    void deleteDepartement(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
