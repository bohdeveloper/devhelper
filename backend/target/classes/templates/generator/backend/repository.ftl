package org.example.generated.repository;

import org.example.generated.entity.${entity.name};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ${entity.name}Repository extends JpaRepository<${entity.name}, <#list fields as f><#if f.primaryKey>${f.type}</#if></#list>> {

}
