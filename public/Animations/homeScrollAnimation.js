document.addEventListener('DOMContentLoaded', function () {
    // Create an Intersection Observer
    const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
            // Add the class when element is in view
            if (entry.isIntersecting) {
                entry.target.classList.add('scroll-animate');
            }
        });
    }, {
        threshold: 0.1 // Trigger when even 10% of the element is visible
    });

    // Observe all elements you want to animate
    const elements = document.querySelectorAll('.name, .menu-nav, .header-nav, .logo, .about, .image-main, .section, footer');
    elements.forEach(el => observer.observe(el));
});