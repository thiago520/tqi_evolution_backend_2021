package testetqi.tqi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import testetqi.tqi.model.LoanModel;

public interface LoanRepository extends JpaRepository<LoanModel, Long> {

}
