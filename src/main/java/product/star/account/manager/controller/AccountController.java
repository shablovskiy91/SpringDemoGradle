package product.star.account.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import product.star.account.manager.facade.AccountFacade;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountFacade accountFacade;

    @Autowired
    public AccountController(AccountFacade accountFacade) {
        this.accountFacade = accountFacade;
    }

    @PostMapping
    public AccountDto createAccount(
            @RequestParam long amount
    ) {
        return accountFacade.createAccount(amount);
    }

    @GetMapping("/{accountId}")
    public AccountDto getAccount(
            @PathVariable long accountId
    ) {
        return accountFacade.getAccount(accountId);
    }

    @PostMapping("/transfers")
    public TransactionResponse transfer(
            @RequestBody TransactionDto transactionDto
    ) {
        return accountFacade.transfer(transactionDto);
    }
}
