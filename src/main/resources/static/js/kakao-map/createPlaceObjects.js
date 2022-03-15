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
        console.log("json");
        json.forEach((data) => {  // jsonParse.forEach((data) => {

            // console.log(data, typeof (data)); //ok // {idx: 191, categoryType: 'ELECTRONICS', title: '게시글191', latitude: 37.4965883, longitude: 126.9939492} 'object'
            console.log("hi");
            const idx = data.idx;
            const category = data.categoryType;
            const title = data.title;
            const latitude = data.latitude;
            const longitude = data.longitude;

            console.log(idx); //ok
            console.log(category); //ok
            console.log(title); //ok
            console.log(latitude); //ok
            console.log(longitude); //ok

            /*
            const location = new kakao.maps.LatLng(data["latitude"], data["longitude"]);
            const markerImage = createMarkerImage(<i className="fa-solid fa-clothes-hanger"></i>, new kakao.maps.Size(30,30));

            const marker = createMarker(location, markerImage);
            const customOverlay = createCustomOverlay(location, data);

            kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map,marker,i,customOverlay));
            kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(customOverlay));
            */
        });
    });

    /*
    const jsonMarkers = jsonParse[0]; //ok
    const jsonOverlays = jsonParse[1]; //ok
    console.log(markers); //ok
    console.log(overlays); //ok

    jsonMarkers.forEach((jsonMarker) => {

        const location = new kakao.maps.LatLng(jsonMarker[latitude], jsonMarker[longitude]);
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


