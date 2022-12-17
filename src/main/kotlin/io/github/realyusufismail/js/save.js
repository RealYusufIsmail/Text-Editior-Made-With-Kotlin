document.getElementsByName("save")[0].addEventListener("click", function() {
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "/save");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify({ code: this.getAttribute("code") }));
});