var selectElement = document.getElementById("mySelect");

selectElement.addEventListener("change", function () {
  var selectedOption = selectElement.value;

  if (selectedOption == "AdminPage") {
    window.location.href = "AdminPageC";
    window.open("AdminPageC", "_blank");
  } else if (selectedOption == "ITA") {
   window.open("https://www.trade.gov/", "_blank");
  } else if (selectedOption == "JETRO") {
    window.open("https://www.jetro.go.jp/", "_blank");
  } else if (selectedOption == "KOTRA") {
    window.open("https://www.kotra.or.kr/index.do", "_blank"); 
  } else if (selectedOption == "Logout"){
	 window.location.href = "LogoutC"; 
  }
});
