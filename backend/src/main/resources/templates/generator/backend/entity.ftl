package org.example.generated.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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

@Entity
@Table(name = "${entity.name?lower_case}s")
@Getter
@Setter
public class ${entity.name} {

<#list fields as field>
    <#if field.primaryKey>
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ${field.type} ${field.name};
    <#else>
    @Column(name = "${field.name?lower_case}"<#if !field.nullable>, nullable = false</#if>)
    private ${field.type} ${field.name};
    </#if>

</#list>
}
