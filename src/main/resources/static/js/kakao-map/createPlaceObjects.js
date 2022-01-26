
//마커이미지의 주소와, 크기, 옵션으로 마커 이미지를 생성하여 리턴하는 함수입니다
function createMarkerImage(src, size) {
    var markerImage = new kakao.maps.MarkerImage(src, size);
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
/////////// here jparepository에서 갖고 온 장소 어떻게 띄우지? model 이용!!

function createPlaces() {

	var places = [];

	for (var i = 0; i < 11; i++) {

		var position = dramaPositions[i].position;
    	var markerImage = createMarkerImage('https://localimg.daum-img.net/localimages/07/2009/map/icon/blog_icon01_on.png',
    	                                        new kakao.maps.Size(30, 30));


        var	marker = createMarker(position, markerImage);

        /*
    	var customOverlay = createCustomOverlay(position,"drama",i);

    	kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map,marker,i,customOverlay));
    	kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(customOverlay));

    	getContent().addEventListener('mouseover', makeOverListener(map,marker,i,customOverlay));
    	getContent().addEventListener('mouseout', makeOutListener(customOverlay));

    	var place = new NewPlace(marker, customOverlay);
    	places.push(place);

	}
	return places;

}


/*
//영화 마커를 생성하고 영화 마커 배열에 추가하는 함수입니다
function createMoviePlaces() {

	var places = [];

	for (var i = 0; i < moviePositions.length; i++) {

		var position = moviePositions[i].position;
    	var markerImage = createMarkerImage("media/filmICON.png", new kakao.maps.Size(30, 30));


        var	marker = createMarker(position, markerImage);
    	var customOverlay = createCustomOverlay(position,"movie",i);

    	kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map,marker,i,customOverlay));
        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(customOverlay));

        getContent().addEventListener('mouseover', makeOverListener(map,marker,i,customOverlay));
    	getContent().addEventListener('mouseout', makeOutListener(customOverlay));

    	var place = new NewPlace(marker, customOverlay);
    	places.push(place);

	}
	return places;
}


//아이돌 마커를 생성하고 아이돌 마커 배열에 추가하는 함수입니다
function createKpopPlaces() {

	var places = [];

	for (var i = 0; i < 7; i++) {


		var position = kpopPositions[i].position;
    	var markerImage = createMarkerImage("media/musicICON.png", new kakao.maps.Size(30, 30));

        var	marker = createMarker(position, markerImage);
    	var customOverlay = createCustomOverlay(position,"kpop",i);

    	kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map,marker,i,customOverlay));
        kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(customOverlay));

        getContent().addEventListener('mouseover', makeOverListener(map,marker,i,customOverlay));
    	getContent().addEventListener('mouseout', makeOutListener(customOverlay));

    	var place = new NewPlace(marker, customOverlay);
    	places.push(place);


	}
	return places;
}

var activeId = null;
var timeoutId = null;


//커스텀오버레이를 표시하는 클로저를 만드는 함수입니다
function makeOverListener(map,marker,i,customOverlay) {
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
*/