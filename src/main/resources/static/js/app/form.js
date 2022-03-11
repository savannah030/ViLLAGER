const form = {
    init : function () {
        const _this = this;
        $('#btn-save').on('click', function() {
            _this.save();
        });
        $('#btn-update').on('click', function() {
            _this.update();
        });
        $('#btn-delete').on('click', function() {
            _this.delete();
        });
    },

    getLocation : function (){
        let lat = 37.498095, //강남역
            lon = 127.027610;
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position){
                lat = position.coords.latitude;
                lon = position.coords.longitude;
            });
        } else {
            alert("Geolocation is not supported by this browser.");
        }
        return [lat,lon];
    },

    save : function () {

        let [lat,lon] = this.getLocation();
        console.log("currentPos",lat,lon);

        const jsonData = JSON.stringify({
            //idx: $('#board_idx').val(), // 기본 키 생성은 데이터베이스에 위임
            categoryType: $('#board_category').val(),
            title: $('#board_title').val(),
            content: $('#board_content').val(),
            /**
             * NOTE: Board 엔티티에 Address를 임베디드 타입으로 선언했으므로
             *  json도 중첩문으로 써야함
             */
            address: {
                latitude: lat,
                longitude: lon,
            }
        });
        console.log("jsonData: ",jsonData); //ok
        $.ajax({
                    url: "http://localhost:8080/api/boards",
                    type: "POST",
                    data: jsonData,
                    contentType: "application/json",
                    dataType: "json",
                        success: function () {
                            alert('저장 성공!');
                            location.href = '/board/list';
                    },
                    error: function () {
                        alert('저장 실패!');
                        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    }
               });
    },
    // TODO 저장 버튼 누르고 게시글 목록으로 돌아간 다음 '뒤로 가기' 누르면 잘못된 접근입니다. 띄워줘야함

    update : function () {

        let [lat,lon] = this.getLocation();
        console.log("currentPos",lat,lon);

        const jsonData = JSON.stringify({
            categoryType: $('#board_category').val(),
            title: $('#board_title').val(),
            content: $('#board_content').val(),
            address: {
                latitude: lat,
                longitude: lon,
            }
        });

        const idx = $('#board_idx').val();

        $.ajax({
           url: "http://localhost:8080/api/boards/" + idx,
           type: "PUT",
           data: jsonData,
           contentType: "application/json",
           dataType: "json",
           success: function () {
               alert('수정 성공!');
               location.href = '/board/list';
           },
           error: function () {
               alert('수정 실패!');
           }
       });
    },

    delete : function () {
        const idx = $('#board_idx').val();

        $('#btn-delete').click(function () {
            $.ajax({
               url: "http://localhost:8080/api/boards/" + idx,
               type: "DELETE",
               success: function () {
                   alert('삭제 성공!');
                   location.href = '/board/list';
               },
               error: function () {
                   alert('삭제 실패!');
               }
           });
        });
    }
};

form.init();


