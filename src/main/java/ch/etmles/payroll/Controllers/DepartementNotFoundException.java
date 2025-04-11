package ch.etmles.payroll.Controllers;

public class DepartementNotFoundException extends RuntimeException {
    DepartementNotFoundException(Long id) {
        super("Could not find departement " + id);
    }
}
