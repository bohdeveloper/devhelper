package org.example.generated.dto;

import lombok.Data;
<#list fields as field>
    <#if field.type == 'BigDecimal'>
import java.math.BigDecimal;
    </#if>
    <#if field.type == 'LocalDate'>
import java.time.LocalDate;
    </#if>
    <#if field.type == 'LocalDateTime'>
import java.time.LocalDateTime;
    </#if>
</#list>

@Data
public class ${entity.name}DTO {

<#list fields as field>
    private ${field.type} ${field.name};
</#list>

}
