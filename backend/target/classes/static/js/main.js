// Utility functions
const ClipboardUtil = {
    copyToClipboard: function(text) {
        navigator.clipboard.writeText(text).then(() => {
            // Reutilizamos el sistema de toast de bootstrap (requiere HTML previo para el toast en el DOM)
            ToastUtil.showSuccess('Copiado al portapapeles');
        }).catch(err => {
            console.error('Error copiando al portapapeles: ', err);
            ToastUtil.showError('No se pudo copiar');
        });
    }
};

const ToastUtil = {
    showSuccess: function(msg) {
        alert("✅ " + msg); // Temporariamente fallback
    },
    showError: function(msg) {
        alert("❌ " + msg); // Temporariamente fallback
    }
};

// Auto-init tooltips or default datatables if needed
$(document).ready(function() {
    console.log("Devhelper initialized");
});
