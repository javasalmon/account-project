package com.qa.account.demo.accountproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.account.demo.accountproject.dao.Account;
import com.qa.account.demo.accountproject.dao.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rajeevsachdeva on 02/05/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AccountResource.class)
public class AccountResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountRepository accountRepository;

    @Test
    public void givenAccounts_whenGetAccounts_thenReturnJsonArray() throws Exception {

        Account account = new Account(1, "Steven", "Doe", "1111");
        List<Account> accounts = Arrays.asList(account);

        given(accountRepository.findAll()).willReturn(accounts);

        mockMvc.perform(get("/rest/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Steven")))
                .andExpect(jsonPath("$[0].secondName", is("Doe")))
                .andExpect(jsonPath("$[0].accountNumber", is("1111")));

        verify(accountRepository, times(1)).findAll();
        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    public void givenAccounts_whenGetAccountById_thenReturnJson() throws Exception {

        Account account = new Account(1, "Steven", "Doe", "1111");

        given(accountRepository.findById(1)).willReturn(Optional.of(account));

        mockMvc.perform(get("/rest/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("firstName", is("Steven")))
                .andExpect(jsonPath("secondName", is("Doe")))
                .andExpect(jsonPath("accountNumber", is("1111")));

        verify(accountRepository, times(1)).findById(1);
        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    public void givenNewAccount_whenCreateAccount_thenReturnUniqueId() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Account account = new Account(100, "Steven", "Doe", "1111");
        given(accountRepository.save(any(Account.class))).willReturn(account);

        mockMvc.perform(post("/rest/accounts").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(account)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));

        verify(accountRepository, times(1)).save(any(Account.class));
        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    public void givenAccounts_whenDeleteAccountById_thenReturnJson() throws Exception {

        willDoNothing().given(accountRepository).deleteById(1);

        mockMvc.perform(delete("/rest/accounts/1"))
                .andExpect(status().isNoContent());

        verify(accountRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(accountRepository);
    }

    @Test
    public void givenAccounts_whenGetAccountByInvalidId_thenReturn404() throws Exception {

        given(accountRepository.findById(1)).willReturn(Optional.empty());

        mockMvc.perform(get("/rest/accounts/1"))
                .andExpect(status().is4xxClientError());

        verify(accountRepository, times(1)).findById(1);
        verifyNoMoreInteractions(accountRepository);
    }
}
