package product.star.account.manager;


public class InMemoryAccountService implements AccountService {

    private final PhoneToAccountResolver phoneToAccountResolver;
    private final AccountDao accountDao;

    public InMemoryAccountService(PhoneToAccountResolver phoneToAccountResolver, AccountDao accountDao) {
        this.phoneToAccountResolver = phoneToAccountResolver;
        this.accountDao = accountDao;
    }


    public void transfer(long fromId, long toId, long amount) {
        var accountFrom = accountDao.getAccount(fromId);
        var accountTo = accountDao.getAccount(toId);
        if (accountFrom.getAmount() < amount) {
            throw new IllegalStateException("Not enough money on account");
        }
        accountDao.setAmount(fromId, accountFrom.getAmount() - amount);
        accountDao.setAmount(toId, accountTo.getAmount() + amount);
    }


    public void transferByPhoneNumber(long fromId, String phoneNumber, long amount) {
        var to = phoneToAccountResolver.findAccountByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found by phone: " + phoneNumber));
        transfer(fromId, to.getId(), amount);
    }
}