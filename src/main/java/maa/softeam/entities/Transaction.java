package maa.softeam.entities;

import maa.softeam.enums.TransactionStatus;
import maa.softeam.enums.TransactionType;

import java.time.Instant;

public class Transaction {
    private double amount;
    private TransactionType transactionType;
    private Instant creationDate;
    private TransactionStatus transactionStatus;
    private double balance;

    public Transaction(double amount, TransactionType transactionType, TransactionStatus transactionStatus, double balance) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
        this.balance = balance;
        creationDate = Instant.now();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" +
                "amount=" + amount +
                ", transactionType=" + transactionType +
                ", creationDate=" + creationDate +
                ", transactionStatus=" + transactionStatus +
                ", balance=" + balance +
                '}';
    }
}
