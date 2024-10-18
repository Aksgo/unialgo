// JavaScript code to toggle the sidebar visibility
function toggleSidebar() {
    const sidebar = document.getElementById('sidebar');
    const toggleButton = document.getElementById('sidebar-toggle');
    const contentSections = document.querySelectorAll(' .faq-section, .comment-section, .more-articles-section');
    
    // Toggle the sidebar's visibility
    sidebar.classList.toggle('show');
    
    // Adjust the button text
    if (sidebar.classList.contains('show')) {
      toggleButton.textContent = 'Hide Articles';
      contentSections.forEach(section => section.classList.add('expanded'));
    } else {
      toggleButton.textContent = 'Show Articles';
      contentSections.forEach(section => section.classList.remove('expanded'));
    }
  }

  