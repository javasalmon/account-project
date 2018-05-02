package com.qa.account.demo.accountproject.dao;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * Created by rajeevsachdeva on 02/05/2018.
 */
@ApiModel(description = "Domain object to maintain Account details")
@Entity
public class Account {

    @ApiModelProperty(name = "id", value = "Account holder unique account id")
    @Id
    @GeneratedValue()
    private Integer id;

    @Size(min=2)
    @ApiModelProperty(name= "firstName", value = "Account holder first name")
    private String firstName;

    @ApiModelProperty(name= "secondName", value = "Account holder second name")
    private String secondName;

    @Size(min=2)
    @ApiModelProperty(name= "accountNumber", value = "Account holder unique account number")
    private String accountNumber;

    protected Account() {

    }

    public Account(Integer id, String firstName, String secondName, String accountNumber) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.accountNumber = accountNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
