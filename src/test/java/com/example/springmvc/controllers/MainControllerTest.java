package com.example.springmvc.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.springmvc.entities.Credit;
import com.example.springmvc.entities.CreditContract;
import com.example.springmvc.services.CreditService;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {MainController.class})
@ExtendWith(SpringExtension.class)
public class MainControllerTest {
    @MockBean
    private CreditService creditService;

    @Autowired
    private MainController mainController;

    @Test
    public void testCreateCredit() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/credits/createCredit");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("credit"))
                .andExpect(MockMvcResultMatchers.view().name("createCredits"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("createCredits"));
    }

    @Test
    public void testCreateCredit2() throws Exception {
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/credits/createCredit");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("credit"))
                .andExpect(MockMvcResultMatchers.view().name("createCredits"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("createCredits"));
    }

    @Test
    public void testShowCreditContractWithCreditStatusIsTrue() throws Exception {
        when(this.creditService.findCreditContractByCreditStatusIsTrue()).thenReturn(new ArrayList<CreditContract>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/credits/showCreditsContractsWithCreditStatusTrue");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("creditContract"))
                .andExpect(MockMvcResultMatchers.view().name("trueCreditContracts"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("trueCreditContracts"));
    }

    @Test
    public void testShowCreditContractWithCreditStatusIsTrue2() throws Exception {
        when(this.creditService.findCreditContractByCreditStatusIsTrue()).thenReturn(new ArrayList<CreditContract>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/credits/showCreditsContractsWithCreditStatusTrue");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("creditContract"))
                .andExpect(MockMvcResultMatchers.view().name("trueCreditContracts"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("trueCreditContracts"));
    }

    @Test
    public void testShowCreditsWithStatusTrue() throws Exception {
        when(this.creditService.findCreditsByStatusIsTrue()).thenReturn(new ArrayList<Credit>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/credits/showCreditsWithStatusTrue");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("credits"))
                .andExpect(MockMvcResultMatchers.view().name("trueCredits"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("trueCredits"));
    }

    @Test
    public void testShowCreditsWithStatusTrue2() throws Exception {
        when(this.creditService.findCreditsByStatusIsTrue()).thenReturn(new ArrayList<Credit>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/credits/showCreditsWithStatusTrue");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("credits"))
                .andExpect(MockMvcResultMatchers.view().name("trueCredits"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("trueCredits"));
    }

    @Test
    public void testSignContract() throws Exception {
        Credit credit = new Credit();
        credit.setWork_organization("Work organization");
        credit.setSum(1);
        credit.setName("Name");
        credit.setWork_period_months(1);
        credit.setAddress("42 Main St");
        credit.setPassport_series(1);
        credit.setSurname("Doe");
        credit.setPhone(1L);
        credit.setStatus(true);
        credit.setCreditContract(new CreditContract());
        credit.setIs_married(true);
        credit.setWork_position("Work position");
        credit.setMiddle_name("Middle name");
        credit.setApprovedSum(1);
        credit.setId(123L);
        credit.setDays(1);
        credit.setPassport_number(10);

        CreditContract creditContract = new CreditContract();
        creditContract.setContractId(123L);
        creditContract.setTime(LocalDate.ofEpochDay(1L));
        creditContract.setCredit(credit);
        creditContract.setCreditStatus("Credit Status");
        creditContract.setCreditSum(1);

        Credit credit1 = new Credit();
        credit1.setWork_organization("Work organization");
        credit1.setSum(1);
        credit1.setName("Name");
        credit1.setWork_period_months(1);
        credit1.setAddress("42 Main St");
        credit1.setPassport_series(1);
        credit1.setSurname("Doe");
        credit1.setPhone(1L);
        credit1.setStatus(true);
        credit1.setCreditContract(creditContract);
        credit1.setIs_married(true);
        credit1.setWork_position("Work position");
        credit1.setMiddle_name("Middle name");
        credit1.setApprovedSum(1);
        credit1.setId(123L);
        credit1.setDays(1);
        credit1.setPassport_number(10);

        CreditContract creditContract1 = new CreditContract();
        creditContract1.setContractId(123L);
        creditContract1.setTime(LocalDate.ofEpochDay(1L));
        creditContract1.setCredit(credit1);
        creditContract1.setCreditStatus("Credit Status");
        creditContract1.setCreditSum(1);

        Credit credit2 = new Credit();
        credit2.setWork_organization("Work organization");
        credit2.setSum(1);
        credit2.setName("Name");
        credit2.setWork_period_months(1);
        credit2.setAddress("42 Main St");
        credit2.setPassport_series(1);
        credit2.setSurname("Doe");
        credit2.setPhone(1L);
        credit2.setStatus(true);
        credit2.setCreditContract(creditContract1);
        credit2.setIs_married(true);
        credit2.setWork_position("Work position");
        credit2.setMiddle_name("Middle name");
        credit2.setApprovedSum(1);
        credit2.setId(123L);
        credit2.setDays(1);
        credit2.setPassport_number(10);
        when(this.creditService.getCreditByContractId((Long) any())).thenReturn(credit2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/credits/signContract/{contractId}",
                123L);
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("creditContract"))
                .andExpect(MockMvcResultMatchers.view().name("signContract"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("signContract"));
    }

    @Test
    public void testSignContract2() throws Exception {
        Credit credit = new Credit();
        credit.setWork_organization("Work organization");
        credit.setSum(1);
        credit.setName("Name");
        credit.setWork_period_months(1);
        credit.setAddress("42 Main St");
        credit.setPassport_series(1);
        credit.setSurname("Doe");
        credit.setPhone(1L);
        credit.setStatus(true);
        credit.setCreditContract(new CreditContract());
        credit.setIs_married(true);
        credit.setWork_position("Work position");
        credit.setMiddle_name("Middle name");
        credit.setApprovedSum(1);
        credit.setId(123L);
        credit.setDays(1);
        credit.setPassport_number(10);

        CreditContract creditContract = new CreditContract();
        creditContract.setContractId(123L);
        creditContract.setTime(LocalDate.ofEpochDay(1L));
        creditContract.setCredit(credit);
        creditContract.setCreditStatus("Credit Status");
        creditContract.setCreditSum(1);

        Credit credit1 = new Credit();
        credit1.setWork_organization("Work organization");
        credit1.setSum(1);
        credit1.setName("Name");
        credit1.setWork_period_months(1);
        credit1.setAddress("42 Main St");
        credit1.setPassport_series(1);
        credit1.setSurname("Doe");
        credit1.setPhone(1L);
        credit1.setStatus(true);
        credit1.setCreditContract(creditContract);
        credit1.setIs_married(true);
        credit1.setWork_position("Work position");
        credit1.setMiddle_name("Middle name");
        credit1.setApprovedSum(1);
        credit1.setId(123L);
        credit1.setDays(1);
        credit1.setPassport_number(10);

        CreditContract creditContract1 = new CreditContract();
        creditContract1.setContractId(123L);
        creditContract1.setTime(LocalDate.ofEpochDay(1L));
        creditContract1.setCredit(credit1);
        creditContract1.setCreditStatus("Credit Status");
        creditContract1.setCreditSum(1);

        Credit credit2 = new Credit();
        credit2.setWork_organization("Work organization");
        credit2.setSum(1);
        credit2.setName("Name");
        credit2.setWork_period_months(1);
        credit2.setAddress("42 Main St");
        credit2.setPassport_series(1);
        credit2.setSurname("Doe");
        credit2.setPhone(1L);
        credit2.setStatus(true);
        credit2.setCreditContract(creditContract1);
        credit2.setIs_married(true);
        credit2.setWork_position("Work position");
        credit2.setMiddle_name("Middle name");
        credit2.setApprovedSum(1);
        credit2.setId(123L);
        credit2.setDays(1);
        credit2.setPassport_number(10);
        when(this.creditService.getCreditByContractId((Long) any())).thenReturn(credit2);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/credits/signContract/{contractId}", 123L);
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.mainController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("creditContract"))
                .andExpect(MockMvcResultMatchers.view().name("signContract"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("signContract"));
    }
}

