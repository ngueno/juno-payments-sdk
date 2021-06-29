package com.ngueno.juno.sdk.resources.transfers;

import static com.ngueno.juno.sdk.resources.base.model.BankAccountType.CHECKING;
import static com.ngueno.juno.sdk.test.FixtureHelper.ACCOUNT_NUMBER;
import static com.ngueno.juno.sdk.test.FixtureHelper.ACCOUNT_NUMBER_COMPLEMENT;
import static com.ngueno.juno.sdk.test.FixtureHelper.AGENCY_NUMBER;
import static com.ngueno.juno.sdk.test.FixtureHelper.AMOUNT;
import static com.ngueno.juno.sdk.test.FixtureHelper.BANK_NUMBER;
import static com.ngueno.juno.sdk.test.FixtureHelper.DAC_ID;
import static com.ngueno.juno.sdk.test.FixtureHelper.DOCUMENT;
import static com.ngueno.juno.sdk.test.FixtureHelper.NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;

import com.ngueno.juno.sdk.resources.base.model.BankAccount;
import com.ngueno.juno.sdk.resources.base.model.Recipient;
import com.ngueno.juno.sdk.resources.base.model.TransferStatus;
import com.ngueno.juno.sdk.resources.transfers.model.JunoBankAccountTransferRequest;
import com.ngueno.juno.sdk.resources.transfers.model.JunoDefaultBankAccountTransferRequest;
import com.ngueno.juno.sdk.resources.transfers.model.JunoP2pTransferRequest;
import com.ngueno.juno.sdk.resources.transfers.model.JunoPixTransferRequest;
import com.ngueno.juno.sdk.resources.transfers.model.TransferResource;
import com.ngueno.juno.sdk.test.AbstractSpringBootTest;

class JunoTransferServiceTest extends AbstractSpringBootTest {

    @Test
    void createP2pTransfer() {
        mockServerManager().expectP2pTransfer();

        JunoP2pTransferRequest request = new JunoP2pTransferRequest(NAME, DOCUMENT, AMOUNT,
                BankAccount.forP2pTransfer(DAC_ID));

        TransferResource resource = service.createTransfer(request);

        assertEquals("p2p_5EDA3E85F3D4D0C8", resource.getId());
        assertEquals("dac_BF7FCE967887DCF4", resource.getDigitalAccountId());
        assertEquals(parseDateTime("2021-04-16 23:47:09"), resource.getCreationDate());
        assertBigDecimal("50.00", resource.getAmount());
        assertEquals(TransferStatus.REQUESTED, resource.getStatus());
        assertNull(resource.getTransferDate());

        Recipient recipient = resource.getRecipient();
        assertEquals("Norton Tanner Gueno", recipient.getName());
        assertEquals("99999999999", recipient.getDocument());

        BankAccount bankAccount = recipient.getBankAccount();
        assertEquals("dac_FE92DCAC4561C7CE", bankAccount.getAccountNumber());
        assertNull(bankAccount.getIspb());
        assertNull(bankAccount.getBankNumber());
        assertNull(bankAccount.getAccountType());
        assertNull(bankAccount.getAgencyNumber());
        assertNull(bankAccount.getAccountComplementNumber());
    }

    @Test
    void createDefaultBankAccountTransfer() {
        mockServerManager().expectDefaultBankAccountTransfer();

        JunoDefaultBankAccountTransferRequest request = new JunoDefaultBankAccountTransferRequest(AMOUNT);
        TransferResource resource = service.createTransfer(request);

        assertEquals("trf_AACAA6C85789FED2", resource.getId());
        assertEquals("dac_BF7FCE967887DCF4", resource.getDigitalAccountId());
        assertEquals(parseDateTime("2021-04-17 00:00:32"), resource.getCreationDate());
        assertBigDecimal("100.00", resource.getAmount());
        assertEquals(TransferStatus.REQUESTED, resource.getStatus());
        assertNull(resource.getTransferDate());

        Recipient recipient = resource.getRecipient();
        assertEquals("Norton Tanner Gueno", recipient.getName());
        assertEquals("99999999999", recipient.getDocument());

        BankAccount bankAccount = recipient.getBankAccount();
        assertEquals("4698562", bankAccount.getAccountNumber());
        assertEquals("260", bankAccount.getBankNumber());
        assertEquals(CHECKING, bankAccount.getAccountType());
        assertEquals("0001", bankAccount.getAgencyNumber());
        assertNull(bankAccount.getAccountComplementNumber());
        assertNull(bankAccount.getIspb());
    }

    @Test
    void createBankAccountTransfer() {
        mockServerManager().expectBankAccountTransfer();

        JunoBankAccountTransferRequest request = new JunoBankAccountTransferRequest(NAME, DOCUMENT, AMOUNT,
                BankAccount.forBankAccountTranfer(BANK_NUMBER, AGENCY_NUMBER, ACCOUNT_NUMBER, ACCOUNT_NUMBER_COMPLEMENT,
                        CHECKING));
        TransferResource resource = service.createTransfer(request);

        assertEquals("trf_537F7647F48B35F7", resource.getId());
        assertEquals("dac_BF7FCE967887DCF4", resource.getDigitalAccountId());
        assertEquals(parseDateTime("2021-04-16 23:49:30"), resource.getCreationDate());
        assertBigDecimal("56.00", resource.getAmount());
        assertEquals(TransferStatus.REQUESTED, resource.getStatus());
        assertNull(resource.getTransferDate());

        Recipient recipient = resource.getRecipient();
        assertEquals("Norton Tanner Gueno", recipient.getName());
        assertEquals("99999999999", recipient.getDocument());

        BankAccount bankAccount = recipient.getBankAccount();
        assertEquals("4698562", bankAccount.getAccountNumber());
        assertEquals("260", bankAccount.getBankNumber());
        assertEquals(CHECKING, bankAccount.getAccountType());
        assertEquals("0001", bankAccount.getAgencyNumber());
        assertNull(bankAccount.getAccountComplementNumber());
        assertNull(bankAccount.getIspb());
    }

    @Test
    void createPixTransfer() {
        mockServerManager().expectPixTransfer();

        JunoPixTransferRequest request = new JunoPixTransferRequest(NAME, DOCUMENT, AMOUNT,
                BankAccount.forPixTransfer(AGENCY_NUMBER, BANK_NUMBER, DAC_ID, DOCUMENT, NAME, CHECKING));
        TransferResource resource = service.createTransfer(request);

        assertEquals("pix_D67A21E89B8B00F9", resource.getId());
        assertEquals("dac_BF7FCE967887DCF4", resource.getDigitalAccountId());
        assertEquals(parseDateTime("2021-04-16 23:51:31"), resource.getCreationDate());
        assertEquals(parseDateTime("2021-04-16 23:51:31"), resource.getTransferDate());
        assertBigDecimal("50.00", resource.getAmount());
        assertEquals(TransferStatus.AWAITING_EXECUTION, resource.getStatus());

        Recipient recipient = resource.getRecipient();
        assertEquals("Norton Tanner Gueno", recipient.getName());
        assertEquals("99999999999", recipient.getDocument());

        BankAccount bankAccount = recipient.getBankAccount();
        assertEquals("1221431-0", bankAccount.getAccountNumber());
        assertEquals("001", bankAccount.getBankNumber());
        assertEquals(CHECKING, bankAccount.getAccountType());
        assertEquals("6525", bankAccount.getAgencyNumber());
        assertEquals("0", bankAccount.getIspb());
        assertNull(bankAccount.getAccountComplementNumber());
    }

    @Resource
    private JunoTransferService service;
}
