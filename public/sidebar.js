function toggleSidebar() {
  const sidebar = document.getElementById('sidebar');
  const backdrop = document.getElementById('backdrop');
  const toggleButton = document.getElementById('sidebar-toggle');

  sidebar.classList.toggle('show');

  if (sidebar.classList.contains('show')) {
    toggleButton.textContent = 'Hide Articles';
    backdrop.style.display = 'block';
  } else {
    toggleButton.textContent = 'Show Articles';
    backdrop.style.display = 'none';
  }
}
