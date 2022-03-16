

///장소 객체 초기화하는 함수
function NewPlace(marker, customOverlay){
    this.marker = marker;
    this.customOverlay = customOverlay;
}

//마커이미지의 주소와, 크기, 옵션으로 마커 이미지를 생성하여 리턴하는 함수입니다
function createMarkerImage(src, size) {
    const markerImage = new kakao.maps.MarkerImage(src, size);
    return markerImage;
}

// 좌표와 마커이미지를 받아 마커를 생성하여 리턴하는 함수입니다
function createMarker(position, image) {
    var marker = new kakao.maps.Marker({
        position: position,
        image: image
    });

    return marker;
}

function createPlaceObjects(jsonData) {

    var places = [];

    const jsonParse = JSON.parse(jsonData);
    // jsonParse.forEach((json.forEach( (data => console.log(data)))) ); //ok


    jsonParse.forEach( (json) => {
        // console.log("json"); //ok
        json.forEach((data) => {  // jsonParse.forEach((data) => {

            // console.log(data, typeof (data)); //ok // {idx: 191, categoryType: 'ELECTRONICS', title: '게시글191', latitude: 37.4965883, longitude: 126.9939492} 'object'

            const idx = data.idx; //ok
            const category = data.categoryType; //ok
            const title = data.title; //ok
            const content = data.content; //ok
            const latitude = data.latitude; //ok
            const longitude = data.longitude; //ok


            // console.log(idx); //ok
            // console.log(category); //ok
            // console.log(title); //ok
            // console.log("content",content); //ok
            // console.log(latitude,"json type:",typeof(latitude),37.4965883, typeof(37.4965883)); //ok 타입은 같은데..
            // console.log(longitude); //ok

            const location = new kakao.maps.LatLng(latitude,longitude);
            console.log("idx",idx,"location",location);
            // '<i className="fa-solid fa-clothes-hanger"></i>'
            //const markerImage = createMarkerImage("src", new kakao.maps.Size(30,30)); //FIXME

            //marker = createMarker(location, markerImage);
            var customOverlay = createCustomOverlay(location, idx, category, title, content);
            console.log("customOverlay",customOverlay);
            customOverlay.setMap(map);
            //kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map,marker,customOverlay));
            //kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(customOverlay));

        });
    });



    /*
    const jsonMarkers = jsonParse[0]; //ok
    const jsonOverlays = jsonParse[1]; //ok
    console.log(markers); //ok
    console.log(overlays); //ok

    jsonMarkers.forEach((jsonMarker) => {

        const location = new kakao.maps.LatㅜLng(jsonMarker[latitude], jsonMarker[longitude]);
        const markerImage = createMarkerImage(<i className="fa-solid fa-clothes-hanger"></i>, new kakao.maps.Size(30,30));

        const marker = createMarker(location, markerImage);

        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map,marker,i,customOverlay));
        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(customOverlay));

    });

    jsonOverlays.forEach((jsonOverlay) => {
        const location = new kakao.maps.LatLng(jsonOverlay[latitude], jsonOverlay[longitude]);
        const customOverlay = createCustomOverlay(position ,jsonOverlay[categoryType],i);
    });
     */

    /*
    getContent().addEventListener('mouseover', makeOverListener(map,marker,i,customOverlay));
    getContent().addEventListener('mouseout', makeOutListener(customOverlay));

    const place = new NewPlace(marker, customOverlay);
    places.push(place);

    */
    return places;

}



var activeId = null;
var timeoutId = null;


//커스텀오버레이를 표시하는 클로저를 만드는 함수입니다
function makeOverListener(map,marker,customOverlay) {
    return function(){
        if (timeoutId !== null && i === activeId) {
            window.clearTimeout(timeoutId);
            timeoutId = null;
            return;
        }
        customOverlay.setMap(map);
        activeId = i;
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


