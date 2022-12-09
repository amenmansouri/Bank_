package maa.softeam;

import maa.softeam.entities.Account;
import maa.softeam.entities.Address;
import maa.softeam.entities.Costumer;
import maa.softeam.enums.AccountType;
import maa.softeam.enums.TransactionStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class CostumerTest {
    Costumer costumer;

    @BeforeEach
    void init() {
        List<Account> accounts = List.of(
                new Account(0d, AccountType.CURRENT, -500d)
        );
        costumer = new Costumer("Amen allah", "Mansouri", LocalDate.of(1995, 2, 18), new Address("62 Rue Joseph Vallier", "Grenoble", 38000, "Apt 64"), "0758265491", "Consultant", accounts);
        Costumer.costumerRepository.save(costumer);

    }

    @AfterEach
    void destroy() {
        Costumer.costumerRepository.cache.clear();
        Costumer.reset();
    }

    @Test
    void saveOrUpdate() {
        costumer.setFirstName("Amen");
        Costumer.costumerRepository.save(costumer);
        Assertions.assertEquals(costumer.getFirstName(), Costumer.costumerRepository.findById(1).get().getFirstName());
    }

    @Test
    void findOneExist() {
        Optional<Costumer> opt = Costumer.costumerRepository.findById(1);
        Assertions.assertEquals(costumer, opt.get());
    }

    @Test
    void findOneNotExist() {
        Optional<Costumer> opt = Costumer.costumerRepository.findById(2);
        Assertions.assertEquals(Optional.empty(), opt);
    }

    @Test
    void depositOrWithdraw() {
        costumer.getAccounts().get(0).manipulate(500);
        Assertions.assertEquals(500, costumer.getAccounts().get(0).getBalance());
        Assertions.assertEquals(TransactionStatus.DONE, costumer.getAccounts().get(0).getTransactions().get(0).getTransactionStatus());
        Assertions.assertThrows(IllegalArgumentException.class,()->{costumer.getAccounts().get(0).manipulate(-1500);});
        Assertions.assertEquals(500, costumer.getAccounts().get(0).getBalance());
        Assertions.assertEquals(TransactionStatus.FAIL, costumer.getAccounts().get(0).getTransactions().get(1).getTransactionStatus());
    }

    @Test
    void findAll() {
        Assertions.assertEquals(1, Costumer.costumerRepository.findAll().size());
    }

    @Test
    void deleteById() {
        costumer.costumerRepository.deleteById(1);
        Assertions.assertFalse(Costumer.costumerRepository.cache.containsKey(1));
    }

    @Test
    void getHistories() {
        for (int i = 0; i < 10; i++) {
            costumer.getAccounts().get(0).manipulate(i % 2 == 0 ? -i * 100 : i * 100);
        }
        Assertions.assertEquals(10, costumer.getAccounts().get(0).getTransactions().size());
    }
}
