function toggleMenu() {
    // activate the class responsible for sliding the menu
    const menu = document.querySelector('.menu');
    menu.classList.toggle('active');

    // activate the class to toggle the rotation of the plus image
    const plusImage = document.querySelector('.menu-plus-image');
    plusImage.classList.toggle('rotate')
}