package com.qa.account.demo.accountproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rajeevsachdeva on 02/05/2018.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
