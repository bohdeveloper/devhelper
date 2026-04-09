<!-- Vista DataTables generada para: ${entityName} -->
<div class="card shadow-sm border-0">
    <div class="card-header bg-white py-3 d-flex justify-content-between align-items-center">
        <h6 class="m-0 font-weight-bold text-primary">Listado de ${entityName}</h6>
        <button class="btn btn-sm btn-primary"><i class="bi bi-plus-circle me-1"></i>Nuevo</button>
    </div>
    <div class="card-body">
        <div class="table-responsive">
            <table id="${entityName?lower_case}Table" class="table table-striped table-hover w-100">
                <thead>
                    <tr>
<#list columns as col>
                        <th>${col?cap_first}</th>
</#list>
                        <th class="text-center">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Table Body (AJAX or Server-side SSR) -->
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- Script de inicialización DataTable -->
<script>
    $(document).ready(function() {
        $('#${entityName?lower_case}Table').DataTable({
            language: { url: '//cdn.datatables.net/plug-ins/1.13.7/i18n/es-ES.json' },
            ajax: {
                url: '${endpoint}',
                dataSrc: '' // o 'data' dependiendo de tu API REST
            },
            columns: [
<#list columns as col>
                { data: '${col?lower_case?replace(" ", "_")}' },
</#list>
                {
                    data: null,
                    orderable: false,
                    className: 'text-center',
                    render: function (data, type, row) {
                        return '<button class="btn btn-sm btn-outline-primary me-1"><i class="bi bi-pencil"></i></button>' +
                               '<button class="btn btn-sm btn-outline-danger"><i class="bi bi-trash"></i></button>';
                    }
                }
            ],
            responsive: true,
            pageLength: 25
        });
    });
</script>
