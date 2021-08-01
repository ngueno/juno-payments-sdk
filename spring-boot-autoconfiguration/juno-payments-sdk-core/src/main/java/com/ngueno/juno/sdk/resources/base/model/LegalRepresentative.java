package com.ngueno.juno.sdk.resources.base.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.ngueno.juno.sdk.config.JunoApiFormats;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Atenção a regra para o campo companyType vs type:
 * INDIVIDUAL: Empresário/ME Individual, somente para EI, MEI, EIRELI;
 * ATTORNEY: Procurador, somente para EI, MEI, EIRELI, LTDA, SA, INSTITUTION_NGO_ASSOCIATION;
 * DESIGNEE: Mandatário", somente para EI, MEI, EIRELI, LTDA, SA, INSTITUTION_NGO_ASSOCIATION;
 * MEMBER: Sócio, somente para LTDA, SA;
 * DIRECTOR: Diretor, somente para INSTITUTION_NGO_ASSOCIATION;
 * PRESIDENT: Presidente, somente para INSTITUTION_NGO_ASSOCIATION.
 */
@Getter
@ToString
@RequiredArgsConstructor
public class LegalRepresentative extends JunoBaseModel {

    private final String name;
    private final String document;
    @JsonFormat(shape = Shape.STRING, pattern = JunoApiFormats.API_DATE_PATTERN)
    private final LocalDate birthDate;
    private final String motherName;
    private final LegalRepresentativeType type;
}
