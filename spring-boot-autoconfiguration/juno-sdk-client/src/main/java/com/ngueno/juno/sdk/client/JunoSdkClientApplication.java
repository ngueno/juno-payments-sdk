package com.ngueno.juno.sdk.client;

import com.ngueno.juno.sdk.resources.additionaldata.JunoAdditionalDataService;
import com.ngueno.juno.sdk.resources.balance.JunoBalanceService;
import com.ngueno.juno.sdk.resources.balance.model.BalanceResource;
import com.ngueno.juno.sdk.resources.charges.JunoChargeService;
import com.ngueno.juno.sdk.resources.charges.model.JunoChargeListRequest;
import com.ngueno.juno.sdk.resources.transfers.JunoTransferService;
import com.ngueno.juno.sdk.resources.transfers.model.JunoDefaultBankAccountTransferRequest;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class JunoSdkClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = new SpringApplicationBuilder() //
                .sources(JunoSdkClientApplication.class) //
                .web(WebApplicationType.NONE) //
                .run(args);

        JunoBalanceService balanceService = ctxt.getBean(JunoBalanceService.class);
        BalanceResource balance = balanceService.getBalance();
        log.info("-- getBalance: {}", balance);

        JunoChargeService chargeService = ctxt.getBean(JunoChargeService.class);
        log.info("-- listCharges: {}", chargeService.listCharges(new JunoChargeListRequest()));
        log.info("-- findCharge: {}", chargeService.findCharge("chr_6CEE68E7CB97C3B35D2CB439B625D7DC"));

        JunoAdditionalDataService additionalDataService = ctxt.getBean(JunoAdditionalDataService.class);
        log.info("-- listBanks: {}", additionalDataService.listBanks());

        JunoTransferService transferService = ctxt.getBean(JunoTransferService.class);
        log.info("-- createTransfer: {}", transferService.createTransfer(new JunoDefaultBankAccountTransferRequest()));
    }

}
