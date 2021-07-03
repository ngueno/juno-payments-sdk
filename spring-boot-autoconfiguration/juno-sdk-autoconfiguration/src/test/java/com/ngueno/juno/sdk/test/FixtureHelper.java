package com.ngueno.juno.sdk.test;

import static com.ngueno.juno.sdk.resources.base.model.LegalRepresentativeType.INDIVIDUAL;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import com.ngueno.juno.sdk.resources.base.model.Address;
import com.ngueno.juno.sdk.resources.base.model.BankAccountHolder;
import com.ngueno.juno.sdk.resources.base.model.ChargeBilling;
import com.ngueno.juno.sdk.resources.base.model.CompanyMember;
import com.ngueno.juno.sdk.resources.base.model.CreditCardDetails;
import com.ngueno.juno.sdk.resources.base.model.LegalRepresentative;

import org.mockserver.model.MediaType;

public class FixtureHelper {

    public static final String EMAIL = "mysdk@developer.com.br";
    public static final String PHONE = "41999002102";
    public static final String DOCUMENT_CPF = "56489652064";
    public static final String DOCUMENT_CNPJ = "99391775000100";
    public static final String DAC_ID = "dac_FE92DCAC4561C7CE";
    public static final String NAME = "Stigandr Ebbe";
    public static final String MOTHER_NAME = "Mother of Stigandr Ebbe";
    public static final String DESCRIPTION = "Some random description to be used in any purpose";
    public static final String AGENCY_NUMBER = "5496";
    public static final String ACCOUNT_NUMBER = "100000016";
    public static final String BANK_NUMBER = "033";
    public static final String ACCOUNT_NUMBER_COMPLEMENT = "01";
    public static final String LINE_OF_BUSINESS = "Random line of business";
    public static final String CNAE = "6204000";
    public static final Long BUSINESS_AREA_ID = 1024L;
    public static final LocalDate BIRTH_DATE = LocalDate.of(1994, 9, 6);
    public static final LocalDate ESTABLISHMENT_DATE = LocalDate.of(2020, 9, 6);
    public static final BigDecimal MONTHLY_INCOME_OR_REVENUE = new BigDecimal("50000.00");

    public static final String STREET = "Av. Sete de Setembro";
    public static final String ADDRESS_NUMBER = "1075";
    public static final String CITY = "Curitiba";
    public static final String STATE = "PR";
    public static final String ZIP_CODE = "80730390";

    public static final String AMOUNT_STR = "1000.00";
    public static final BigDecimal AMOUNT = new BigDecimal(AMOUNT_STR);

    public static final String PLAN_ID = "pln_D539CC5AF0E87FB1";
    public static final String SUBSCRIPTION_ID = "sub_EDA3F6CA13DFEC4C";
    public static final String CHARGE_ID = "chr_575A9ADE6C14BFD4330B28BA60253850";
    public static final String PAYMENT_ID = "pay_4F1ED802EC7E5CDA1985C4A892725E94";

    public static final String CREDIT_CARD_ID = "a1724bea-b9d7-4ca9-9992-76488af3a006";

    public static final String RESOURCE_TOKEN = "dummyResourceToken";
    public static final String BEARER_AUTHENTICATION_ACCESS_TOKEN = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJub3J0b250Z3Vlbm9AaG90bWFpbC5jb20iLCJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNjE2MzY1MjMxLCJqdGkiOiIxcDZ4cVlhdXJxOGYyRmpLbnE5dnhLaUthVDgiLCJjbGllbnRfaWQiOiIyaGZCdXd5UjIxQ1liNUU4In0.ECSOVi1ICvvkw4oV7C3kGVQYRDAsft7u8jJWGqptx8dKqnOEJgUBWl9MB5EjHFWfYmEALFCgmHWYpGgKASDJmk3yJubEg_SaMpxTdsMSdnj3dxvGtXOj3hNi-2CsbPecckya8KjARAFWxLOJxgo813dPHNjSg5cU5JfkjHQaaR5swUap1h_aG9jJ3boRcHl9n_L10DfOlhZcX5NmdtNgpoPhOYRbS7b_Lzromy_kmBVl_d3-sp3zb3Sgio_FbF4A1LDxH44tz2EkiNeBlLRJvD3NzIPY8Uz06e58K1qq8TA6KDp-LY_saQgIrs1ww4GTtAZac1PqQvsHsMbm0JuGZA";
    public static final String BEARER_AUTHENTICATION = "Bearer " + BEARER_AUTHENTICATION_ACCESS_TOKEN;
    public static final String BASIC_AUTHENTICATION_ENCODED = "ZHVtbXlDbGllbnRJZDpkdW1teUNsaWVudFNlY3JldA==";
    public static final String BASIC_AUTHENTICATION = "Basic " + BASIC_AUTHENTICATION_ENCODED;
    public static final String TOKEN_TYPE = "bearer";
    public static final String SCOPE = "all";
    public static final String USER_NAME = "mysdk@somerandomemail.com";
    public static final String JTI = "XPNkRjUk2vAgAZHhdsh9l5pocPkl";
    public static final int EXPIRES_IN = 86399;
    public static final Long GENERATED_AT = 1000L;

    public static final MediaType MEDIA_TYPE_FORM_URLENCODED = new MediaType("application", "x-www-form-urlencoded;charset=UTF-8");
    public static final MediaType MEDIA_TYPE_JSON = new MediaType("application", "json;charset=UTF-8");
    public static final MediaType MEDIA_TYPE_TEXT_PLAIN = new MediaType("text", "plain;charset=UTF-8");

    public static String getResource(String... resources) {
        Path resourcePath = getBaseResourcePath();

        for (String resource : resources) {
            resourcePath = resourcePath.resolve(resource);
        }

        return readFromResource(resourcePath);
    }

    private static Path getBaseResourcePath() {
        return Paths.get("src", "test", "resources");
    }

    private static String readFromResource(Path path) {
        try {
            return Files.readString(path);
        } catch (IOException e) {
            fail("Unable to load resource", e);
        }
        return null;
    }

    public CreditCardDetails createCreditCardDetails() {
        return CreditCardDetails.withCreditCardId(CREDIT_CARD_ID);
    }

    public ChargeBilling createChargeBilling() {
        return new ChargeBilling(NAME, DOCUMENT_CPF, EMAIL, createAddress());
    }

    public Address createAddress() {
        return new Address(STREET, ADDRESS_NUMBER, CITY, STATE, ZIP_CODE);
    }

    public LegalRepresentative createLegalRepresentative() {
        return new LegalRepresentative(NAME, DOCUMENT_CPF, BIRTH_DATE, MOTHER_NAME, INDIVIDUAL);
    }

    public BankAccountHolder createBankAccountHolder() {
        return new BankAccountHolder(NAME, DOCUMENT_CPF);
    }

    public CompanyMember createCompanyMember() {
        return new CompanyMember(NAME, DOCUMENT_CPF, BIRTH_DATE);
    }
}
