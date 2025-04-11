package ch.etmles.payroll.Repositories;

import ch.etmles.payroll.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

}
