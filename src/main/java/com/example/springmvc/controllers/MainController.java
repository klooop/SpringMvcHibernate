package com.example.springmvc.controllers;

import com.example.springmvc.controllers.dto.CreditContractRequestDTO;
import com.example.springmvc.controllers.dto.CreditResponseDTO;
import com.example.springmvc.entities.Credit;
import com.example.springmvc.entities.CreditContract;
import com.example.springmvc.services.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/credits")
public class MainController {

    private final CreditService creditService;

    @Autowired
    public MainController(CreditService creditService) {
        this.creditService = creditService;
    }

    @GetMapping
    public String showCredits(Model model,
                              @RequestParam(value = "phone", required = false) Long phone,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "surname", required = false) String surname,
                              @RequestParam(value = "middle_name", required = false) String middle_name,
                              @RequestParam(value = "passport_series", required = false) Integer passport_series,
                              @RequestParam(value = "passport_number", required = false) Integer passport_number)

    {

        model.addAttribute("credits", creditService.creditsWithFiltering(
                phone, name,surname, middle_name, passport_series, passport_number));
        model.addAttribute("creditContract", creditService.getListOfCreditContracts());


        return "creditsPage";


    }

    @GetMapping("/createCredit")
    public String createCredit(Model model) {
        CreditResponseDTO credit = new CreditResponseDTO();
        model.addAttribute("credit", credit);
        return "createCredits";
    }

    @PostMapping("/createCredit")
    public String createCredit(@ModelAttribute(value = "credit") CreditResponseDTO creditResponseDTO ) {
        Credit credit = new Credit(creditResponseDTO);
        creditService.makeRequestOnCredit(credit);
        return "redirect:/credits";
    }

    @GetMapping("/signContract/{contractId}")
    public String signContract(@PathVariable(name = "contractId") Long contractId,
                               Model model) {
        Credit credit = creditService.getCreditByContractId(contractId);
        CreditContractRequestDTO creditContractRequestDTO = new CreditContractRequestDTO(credit, contractId);

        model.addAttribute("creditContract", creditContractRequestDTO);
        return "signContract";
    }

    @PostMapping("/signContract")
    public String signContract(@ModelAttribute(name = "creditContract")
                                           CreditContractRequestDTO creditContractRequestDTO) {

        CreditContract updatedCreditContract = creditService.getCreditContractById(
                creditContractRequestDTO.getCreditContractId());
        creditService.signContract(updatedCreditContract, creditContractRequestDTO.getConfirmStatus(),
                creditContractRequestDTO.getTime());
        return "redirect:/credits";
    }

    @GetMapping("/showCreditsWithStatusTrue")
    public String showCreditsWithStatusTrue(Model model) {

        model.addAttribute("credits", creditService.findCreditsByStatusIsTrue());
        return "trueCredits";
    }

    @GetMapping("/showCreditsContractsWithCreditStatusTrue")
    public String showCreditContractWithCreditStatusIsTrue(Model model) {

        model.addAttribute("creditContract", creditService.findCreditContractByCreditStatusIsTrue());
        return "trueCreditContracts";
    }
}
