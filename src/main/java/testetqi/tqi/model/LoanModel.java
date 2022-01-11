package testetqi.tqi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="loan")
public class LoanModel {

    public static final Integer MIN_INSTALLMENTS = 1;
    public static final Integer MAX_INSTALLMENTS = 60;
    public static final Integer MAX_MONTHS = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate firstDate;

    @Column(nullable = false)
    private Integer qInstallments;

    @Column(nullable = false)
    private double valueLoan;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "client", referencedColumnName = "id")
    private UserModel user;
}
