package maa.softeam.entities;

import maa.softeam.enums.AccountType;
import maa.softeam.enums.TransactionStatus;
import maa.softeam.enums.TransactionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Account {

    private static final Logger logger = LogManager.getLogger(Account.class);
    private UUID rib;
    private String iban;
    private Double balance;
    private AccountType accountType;
    private List<Transaction> transactions = Collections.synchronizedList(new ArrayList<>());
    private double discovered;

    public Account(Double balance, AccountType accountType, double discovered) {
        this.rib = UUID.randomUUID();
        this.iban = "FR" + ((int) Math.random() * 100) + this.rib;
        this.balance = balance;
        this.accountType = accountType;
        this.discovered = discovered;
    }

    public UUID getRib() {
        return rib;
    }

    public void setRib(UUID rib) {
        this.rib = rib;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public synchronized Double getBalance() {
        return balance;
    }

    public synchronized void setBalance(Double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public synchronized List<Transaction> getTransactions() {
        return transactions;
    }

    public synchronized void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public double getDiscovered() {
        return discovered;
    }

    public void setDiscovered(double discovered) {
        this.discovered = discovered;
    }

    public synchronized void manipulate(double amount) throws IllegalArgumentException{
        Transaction transaction;
        if (amount < 0 && this.getBalance() + amount < this.discovered) {
            transaction = new Transaction(amount, TransactionType.MONEY_WITHDRAW, TransactionStatus.FAIL, this.getBalance());
            transactions.add(transaction);
            logger.error(String.format(
                    "we can't execute this operation [ Withdraw %1$,.2f ] ! you don't have a balance!\n"+
                            "you have just %2$,.2f"
                    ,amount,this.balance));
            throw new IllegalArgumentException();
        } else {
            this.setBalance(this.getBalance() + amount);
            TransactionType transactionType = amount > 0 ? TransactionType.MONEY_DEPOSIT : TransactionType.MONEY_WITHDRAW;
            transaction = new Transaction(amount, transactionType, TransactionStatus.DONE, this.getBalance());
            transactions.add(transaction);
            logger.info(String.format(
                    "we can execute this operation [  %1$,.2f ] !\n"+
                    "now you have just %2$,.2f"
                    ,amount,this.balance));
        }
    }
}
