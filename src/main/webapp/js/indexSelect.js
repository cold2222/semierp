var selectElement = document.getElementById("mySelect");

selectElement.addEventListener("change", function () {
  var selectedOption = selectElement.value;

  if (selectedOption == "AdminPage") {
    window.location.href = "AdminPageC";
    window.open("AdminPageC", "_blank");
  }
});
