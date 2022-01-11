package testetqi.tqi.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testetqi.tqi.exception.LoanNotFoundException;
import testetqi.tqi.model.LoanModel;
import testetqi.tqi.repository.LoanRepository;
import testetqi.tqi.repository.UserRepository;
import testetqi.tqi.response.MessageResponseModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoanService {

    private LoanRepository repository;
    private UserRepository userRepository;

    public List<LoanModel> listAll() {
        return repository.findAll();
    }

    public MessageResponseModel createLoan(LoanModel loanModel) {

        LocalDate dateMonth = LocalDate.now().plusMonths(LoanModel.MAX_MONTHS);

        if((loanModel.getQInstallments() <= LoanModel.MAX_INSTALLMENTS) &&
                (loanModel.getFirstDate().compareTo(dateMonth) <= 0)) {
            LoanModel savedLoan = repository.save(loanModel);
            return createMessageResponse(savedLoan.getId(), "Created loan with ID ");
        } else {
            return createMessageResponse(loanModel.getId(), "Loan not found!");
        }
    }

    public void delete(Long id) throws LoanNotFoundException {
        verifyIfExists(id);
        repository.deleteById(id);
    }

    public LoanModel findByUserID(LoanModel loanModel) throws LoanNotFoundException {
        Optional<LoanModel> loanModelOptional = null;
        if(verifyUser(loanModel.getUser().getId())) {
            loanModelOptional = repository.findById(loanModel.getId());
        }
        return loanModelOptional.orElseThrow(() -> new LoanNotFoundException(loanModel.getId()));
    }

    public MessageResponseModel updateById(Long id, LoanModel loanModel) throws LoanNotFoundException {
        verifyIfExists(id);
        LoanModel updateLoan = repository.save(loanModel);
        return createMessageResponse(updateLoan.getId(), "Updated loan with ID ");
    }

    private LoanModel verifyIfExists(Long id) throws LoanNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new LoanNotFoundException(id));
    }

    private boolean verifyUser(Long userId) {
        if(userRepository.findById(userId).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    private MessageResponseModel createMessageResponse(Long id, String message) {
        return MessageResponseModel
                .builder()
                .message(message + id)
                .build();
    }



}
