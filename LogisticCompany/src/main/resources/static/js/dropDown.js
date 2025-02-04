document.addEventListener("DOMContentLoaded", function () {
    const dropdownButton = document.querySelector(".dropdown-button");
    const dropdownMenu = document.querySelector(".dropdown-menu");

    dropdownButton.addEventListener("click", function (event) {
        event.stopPropagation(); // Prevent immediate closure
        dropdownMenu.classList.toggle("show");
    });

    document.addEventListener("click", function () {
        dropdownMenu.classList.remove("show");
    });
});
