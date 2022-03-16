/////////////////////////지도///////////////////////////////////
let mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.498095,127.027610), // 지도의 중심좌표(default 강남역)
        level: 3 // 지도의 확대 레벨
    };

let map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
/////////////////////////////////////////////////////////////////