const toggleDarkMode = () => {
    document.body.classList.toggle('dark-mode');
}

document.getElementById('dark-mode-toggle').addEventListener('click', toggleDarkMode);