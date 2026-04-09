/**
 * Devhelper Theme Manager
 * Maneja el cambio entre el modo claro y oscuro usando Bootstrap 5 data-bs-theme.
 */
(function () {
    const THEME_STORAGE_KEY = 'devhelper-theme';

    const getPreferredTheme = () => {
        const storedTheme = localStorage.getItem(THEME_STORAGE_KEY);
        if (storedTheme) {
            return storedTheme;
        }
        return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
    };

    const setTheme = theme => {
        if (theme === 'auto' && window.matchMedia('(prefers-color-scheme: dark)').matches) {
            document.documentElement.setAttribute('data-bs-theme', 'dark');
        } else {
            document.documentElement.setAttribute('data-bs-theme', theme);
        }
    };

    // Aplicar inmediatamente para evitar 'flashing' (FOUC)
    setTheme(getPreferredTheme());

    window.addEventListener('DOMContentLoaded', () => {
        updateToggleButton(getPreferredTheme());

        document.getElementById('theme-toggle').addEventListener('click', () => {
            const currentTheme = document.documentElement.getAttribute('data-bs-theme');
            const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
            
            localStorage.setItem(THEME_STORAGE_KEY, newTheme);
            setTheme(newTheme);
            updateToggleButton(newTheme);
        });
    });

    function updateToggleButton(theme) {
        const icon = document.getElementById('theme-icon');
        if (!icon) return;
        
        if (theme === 'dark') {
            icon.classList.remove('bi-moon-stars');
            icon.classList.add('bi-sun');
        } else {
            icon.classList.remove('bi-sun');
            icon.classList.add('bi-moon-stars');
        }
    }
})();
