package product.star.account.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import product.star.account.manager.*;

import java.util.Set;

@Configuration
@Import(PropertiesConfiguration.class)
public class AccountManagerConfig {

    @Value("#{'10,13,15'.split(',')}")
    private Set<Long> blockedAccounts;

    @Bean
    public AccountDao accountDao() {
        return new InMemoryAccountDao();
    }

    @Bean
    public PhoneToAccountResolver phoneToAccountResolver() {
        return new InMemoryPhoneToAccountResolver();
    }

    @Bean
    public BlocklistResolver blocklistResolver() {
        return new InMemoryBlocklistResolver(
                blockedAccounts
        );
    }

    @Bean
    public AccountService accountService() {
        return new InMemoryAccountService(
                phoneToAccountResolver(),
                accountDao(),
                blocklistResolver());
    }
}
