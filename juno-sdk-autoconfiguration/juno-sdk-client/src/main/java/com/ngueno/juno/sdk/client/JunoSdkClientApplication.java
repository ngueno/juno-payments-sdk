package com.ngueno.juno.sdk.client;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.ngueno.juno.sdk.resources.balance.JunoBalanceService;
import com.ngueno.juno.sdk.resources.charges.JunoChargeService;
import com.ngueno.juno.sdk.resources.charges.model.JunoChargeListRequest;

@SpringBootApplication
public class JunoSdkClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = new SpringApplicationBuilder() //
                .sources(JunoSdkClientApplication.class) //
                .web(WebApplicationType.NONE) //
                .run(args);

        System.out.println();
        System.out.println();
        JunoBalanceService balanceService = ctxt.getBean(JunoBalanceService.class);
        System.out.println(balanceService.getBalance());
        System.out.println();
        JunoChargeService chargeService = ctxt.getBean(JunoChargeService.class);
        System.out.println(chargeService.listCharges(new JunoChargeListRequest()));
        System.out.println();
        System.out.println();
        System.out.println(chargeService.findCharge("chr_6CEE68E7CB97C3B35D2CB439B625D7DC"));
        System.out.println();
        System.out.println();
        chargeService.cancelCharge("chr_6CEE68E7CB97C3B35D2CB439B625D7DC");
    }

}
