function goToMainPage() {
            // 메인 페이지 URL로 이동
            window.location.href = "HC";
}



function changeColor(element, page) {
    var items = document.querySelectorAll('.header2-item');

    // 모든 항목의 텍스트 색상 초기화
    items.forEach(function(item) {
        item.style.color = '#FFF';
    });

    // 선택된 항목에 텍스트 색상을 노란색으로 적용
    element.style.color = '#FFC436';

    // 페이지 이동 전에 선택된 항목의 정보를 로컬 스토리지에 저장
    if (page !== '') {
        localStorage.setItem('selectedItem', element.textContent);
        window.location.href = page;
    }
}

// 페이지 로드 시 로컬 스토리지에서 선택된 항목의 정보를 확인하여 스타일을 적용
document.addEventListener('DOMContentLoaded', function() {
    var selectedItemText = localStorage.getItem('selectedItem');
    if (selectedItemText) {
        var items = document.querySelectorAll('.header2-item');
        items.forEach(function(item) {
            if (item.textContent === selectedItemText) {
                item.style.color = '#FFC436';
            }
        });
    }
});

