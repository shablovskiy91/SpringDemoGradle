package product.star.account.manager;

import java.util.Optional;

public interface PhoneToAccountResolver {

    // Optional - так как аккаунт может быть и не найден. Тогда мы вернём пустой Optional
    Optional<Account> findAccountByPhoneNumber(String phoneNumber);

    void addMapping(String phoneNumber, Account account);
    void removeMapping(String phoneNumber);
}
