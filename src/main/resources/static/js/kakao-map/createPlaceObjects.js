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
    const markers = jsonParse[0]; //ok
    const overlays = jsonParse[1]; //ok
    console.log(markers); //ok
    console.log(overlays); //ok
    /*
    jsonParse.forEach((json.forEach( (data) =>{
        const position = dramaPositions[i].position;
        const markerImage = createMarkerImage("media/dramaICON.png", new kakao.maps.Size(30, 30));

        const marker = createMarker(position, markerImage);
        const customOverlay = createCustomOverlay(position,"drama",i);

        kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map,marker,i,customOverlay));
        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(customOverlay));

        getContent().addEventListener('mouseover', makeOverListener(map,marker,i,customOverlay));
        getContent().addEventListener('mouseout', makeOutListener(customOverlay));

        const place = new NewPlace(marker, customOverlay);
        places.push(place);
    })) );

    return places;

     */

}


