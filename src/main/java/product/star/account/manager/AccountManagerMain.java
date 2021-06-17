package product.star.account.manager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AccountManagerMain {
    public static void main(String[] args) {
        // Создаём App Context, передавая ему класс AccountManagerConfig
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AccountManagerConfig.class);
        // получим bean из ап контекст
        var phoneToCardResolver = applicationContext.getBean(PhoneToAccountResolver.class);
        var accountService = applicationContext.getBean(AccountService.class);

        //Создадим несколько аккаунтов
        Account account1 = new Account(1, 1000L);
        Account account2 = new Account(2, 2000L);

        var accountDao =  applicationContext.getBean(AccountDao.class);

        accountDao.addAccount(account1);
        accountDao.addAccount(account2);

        System.out.println(account1);

        // Перевод по телефонному номеру
        var phoneNumber = "375298887755";
        phoneToCardResolver.addMapping(phoneNumber, account2);

        accountService.transferByPhoneNumber(account1.getId(), phoneNumber, 500);

        System.out.println(account1);
        System.out.println(account2);

        // Просто перевод денежных средств
        accountService.transfer(account1.getId(), account2.getId(), 276);

        System.out.println(account1);
        System.out.println(account2);

        // Перевод по неизвестному номеру - IllegalArgumentException
        //accountService.transferByPhoneNumber(account2.getId(), "3752885667", 500);

    }
}
