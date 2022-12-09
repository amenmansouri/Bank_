package maa.softeam;

import maa.softeam.entities.Account;
import maa.softeam.entities.Address;
import maa.softeam.entities.Costumer;
import maa.softeam.enums.AccountType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;

/**
 * App!
 */
public class App {

    private static final Logger logger = LogManager.getLogger(App.class);
    public static void main(String[] args) {
        List<Account> accounts = List.of(
                new Account(0d, AccountType.CURRENT, -500d)
        );
        Costumer costumer = new Costumer("Amen allah", "Mansouri", LocalDate.of(1995, 2, 18), new Address("62 Rue Joseph Vallier", "Grenoble", 38000, "Apt 64"), "0758265491", "Consultant", accounts);
       try {
           // deposit money
           costumer.getAccounts().get(0).manipulate(500);
           // Withdraw money
           costumer.getAccounts().get(0).manipulate(-500);
           // Not passed Operation
           costumer.getAccounts().get(0).manipulate(-1500);
       }
       catch (IllegalArgumentException illegalArgumentException){
           // we have a lot of credit plan try to contact us !
       }
        // see Transaction/Operation history
        logger.info("Transaction/Operation history !");
        costumer.getAccounts().get(0).getTransactions().stream().forEach(System.out::println);
    }
}
