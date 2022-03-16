

///장소 객체 초기화하는 함수
function NewPlace(marker, customOverlay){
    this.marker = marker;
    this.customOverlay = customOverlay;
}

//마커이미지의 주소와, 크기, 옵션으로 마커 이미지를 생성하여 리턴하는 함수입니다
function createMarkerImage(src, size) {
    return new kakao.maps.MarkerImage(src, size);
}

// 좌표와 마커이미지를 받아 마커를 생성하여 리턴하는 함수입니다
function createMarker(position, image) {

    return new kakao.maps.Marker({
        position: position,
        image: image
    });
}

function createPlaceObjects(jsonData) {

    var places = [];

    const jsonParse = JSON.parse(jsonData);

    jsonParse.forEach( (json) => {

        json.forEach((data) => {

            let idx = data.idx; //ok
            let category = data.categoryType; //ok
            let title = data.title; //ok
            let content = data.content; //ok
            let latitude = data.latitude; //ok
            let longitude = data.longitude; //ok

            let location = new kakao.maps.LatLng(latitude,longitude);

            let markerImage = createMarkerImage('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png', new kakao.maps.Size(30,30));
            let marker = createMarker(location, markerImage);

            let customOverlay = createCustomOverlay(location, idx, category, title, content);

            kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map,marker,idx,customOverlay));
            kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(customOverlay));

            //console.log("getContent()",getContent()); //ok
            getContent().addEventListener('mouseover', makeOverListener(map,marker,idx,customOverlay));
            getContent().addEventListener('mouseout', makeOutListener(customOverlay));

            let place = new NewPlace(marker, customOverlay);
            places.push(place);

        });
    });
    //console.log("places:",places); // ok
    return places;

}


var activeId = null;
var timeoutId = null;

//커스텀오버레이를 표시하는 클로저를 만드는 함수입니다
function makeOverListener(map,marker,idx,customOverlay) {
    return function(){
        if (timeoutId !== null && idx === activeId) {
            window.clearTimeout(timeoutId);
            timeoutId = null;
            return;
        }
        customOverlay.setMap(map);
        activeId = idx;
    }
}

// 커스텀오버레이를 닫는 클로저를 만드는 함수입니다
function makeOutListener(customOverlay) {
    return function(){
        timeoutId = window.setTimeout(function() {
            customOverlay.setMap(null);
            activeId = null;
            timeoutId = null;
        }, 50);
    }
}


