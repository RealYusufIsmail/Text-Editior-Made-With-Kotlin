document.getElementsByName("download")[0].addEventListener("click", function() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/download");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.responseType = "blob";
    xhr.onload = function(e) {
        if (this.status === 200) {
            var blob = new Blob([this.response], { type: "text/plain" });
            var link = document.createElement("a");
            link.href = window.URL.createObjectURL(blob);
            link.download = "code.txt";
            link.click();
        }
    };
    xhr.send();
});