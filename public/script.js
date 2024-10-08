document.addEventListener("DOMContentLoaded", function () {
    const phrases = [
        "Improve\" ",
        "Learn\" ",
        "Compete\" ",
        "Grow\" ",
        "Excel\" ",
        "Challenge\" ",
        "Achieve\" ",
        "Innovate\" ",
        "Collaborate\" ",
        "Explore\" ",
        "Succeed\" ",
        "Conquer\" ",
        "Master\" ",
        "Advance\" ",
        "Code\" ",
        "Create\" ",
        "Discover\" ",
        "Engage\" ",
        "Inspire\" "
    ];
    let i = 0; // Index for the phrases array
    let j = 0; // Index for individual characters in a phrase
    let currentPhrase = [];
    let isDeleting = false; // Flag for deletion state
    let isEnd = false; // Flag to pause after completing a phrase

    function loop() {
        const typewriter = document.getElementById('typewriter');
        typewriter.textContent = "\"Read to " + currentPhrase.join('');
        if (isDeleting) {
            currentPhrase.pop();
            j--;

            // If the word is fully deleted, move to the next word
            if (currentPhrase.length === 0) {
                isDeleting = false;
                i++;
                if (i === phrases.length) {
                    i = 0;
                }
            }
        } else {
            currentPhrase.push(phrases[i][j]);
            j++;

            // If the word is fully typed, set the pause flag
            if (currentPhrase.length === phrases[i].length) {
                isEnd = true;
                isDeleting = true;
            }
        }

        let speed = 100; // Typing speed
        if (isDeleting) speed = 200; // Slower speed for deleting
        if (isEnd) {
            speed = 2000; // 2-second pause after completing the word
            isEnd = false;
        }

        // Continue the loop
        setTimeout(loop, speed);
    }               
    loop();
});

function toggleMenu() {
    const menu = document.querySelector('.menu');
    menu.classList.toggle('active');

    const plusImage = document.querySelector('.menu-plus-image');
    plusImage.classList.toggle('rotate');
}
