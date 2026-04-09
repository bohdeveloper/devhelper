<!-- Formulario generado para: ${entityName} -->
<div class="card shadow-sm border-0">
    <div class="card-header bg-white border-bottom-0 py-3">
        <h6 class="m-0 font-weight-bold text-primary">Formulario de ${entityName}</h6>
    </div>
    <div class="card-body">
        <form action="/${entityName?lower_case}/save" method="POST">
            <div class="row">
<#list fields as field>
    <#assign fieldId = field?lower_case?replace(" ", "_")>
                <div class="col-md-6 mb-3">
                    <label for="${fieldId}" class="form-label fw-bold">${field?cap_first}</label>
                    <input type="text" class="form-control" id="${fieldId}" name="${fieldId}" placeholder="Ingrese ${field}" required>
                </div>
</#list>
            </div>
            <div class="d-flex justify-content-end mt-3">
                <button type="button" class="btn btn-secondary me-2">Cancelar</button>
                <button type="submit" class="btn btn-primary"><i class="bi bi-save me-1"></i>Guardar</button>
            </div>
        </form>
    </div>
</div>
