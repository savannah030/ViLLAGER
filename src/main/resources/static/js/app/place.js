const place = {
    init : function (){
        const _this = this;
        $('#btn-findNearPlaces').on('click', function(){
            _this.findNearPlaces();
        });
    },

    getLocation : function (callback){
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(callback);
        } else {
            alert('사용자의 브라우저는 지오로케이션을 지원하지 않습니다.');
        }
    },

    // place.html의 장소불러오기 버튼을 누르면 현재 위치에서 가까운 판매글 불러옴
    findNearPlaces : function() {
        this.getLocation( function (position){

            let lat = position.coords.latitude,
                lon = position.coords.longitude;

            $.ajax({
                url : "http://localhost:8080/api/places",
                type : "GET",
                data : { lat: lat, lon:lon },
                dataType: 'text',
                success: function (data) {
                    alert('현재 위치 수집 성공!');
                },
                error: function (data) {
                    alert('현재 위치 수집 실패!');
                }
            });
        });
    }
}

place.init()