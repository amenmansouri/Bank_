package maa.softeam.entities;

import maa.softeam.repositories.CostumerRepository;

import java.time.LocalDate;
import java.util.List;

public class Costumer {
    public static CostumerRepository costumerRepository = new CostumerRepository();
    private static int ID_COSTUMER = 0;
    private final int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDay;
    private Address address;
    private String phoneNumber;
    private String job;
    private List<Account> accounts;

    public Costumer(String firstName, String lastName, LocalDate birthDay, Address address, String phoneNumber, String job, List<Account> accounts) {
        ID_COSTUMER += 1;
        this.id = ID_COSTUMER;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.job = job;
        this.accounts = accounts;
    }

    public static final void reset() {
        ID_COSTUMER = 0;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
