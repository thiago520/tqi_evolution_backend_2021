package testetqi.tqi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import testetqi.tqi.exception.LoanNotFoundException;
import testetqi.tqi.model.LoanModel;
import testetqi.tqi.response.MessageResponseModel;
import testetqi.tqi.service.LoanService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {

    private LoanService loanService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseModel createLoan(@RequestBody @Valid LoanModel loanModel) {
        return loanService.createLoan(loanModel);
    }

    @GetMapping
    public List<LoanModel> listAll() {
        return loanService.listAll();
    }

    @GetMapping("/{id}")
    public LoanModel findByUserId(@PathVariable LoanModel loanModel) throws LoanNotFoundException {
        return loanService.findByUserID(loanModel);
    }

    @PutMapping("{id}")
    public MessageResponseModel updateById(@PathVariable Long id, @RequestBody @Valid LoanModel loanModel) throws LoanNotFoundException {
        return loanService.updateById(id, loanModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws LoanNotFoundException {
        loanService.delete(id);
    }

}
