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

    getLocation : function (callback){
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(callback);
        } else {
            alert('사용자의 브라우저는 지오로케이션을 지원하지 않습니다.');
        }
    },

    // NOTE: 콜백함수 흐름 공부하기...
    save : function () {
        this.getLocation(function (position){

            let lat = position.coords.latitude,
                lon = position.coords.longitude;

            const jsonData = JSON.stringify({
                //idx: $('#board_idx').val(), // 기본 키 생성은 데이터베이스에 위임
                categoryType: $('#board_category').val(),
                title: $('#board_title').val(),
                content: $('#board_content').val(),
                // 처음 글 올릴때는 무조건 판매중 따라서 statusType 필요없음
                latitude: lat,
                longitude: lon,
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
                }
            });
        });

    },
    // TODO 저장 버튼 누르고 게시글 목록으로 돌아간 다음 '뒤로 가기' 누르면 잘못된 접근입니다. 띄워줘야함

    update : function () {
        this.getLocation(function (position) {

            let lat = position.coords.latitude,
                lon = position.coords.longitude;

            const jsonData = JSON.stringify({
                categoryType: $('#board_category').val(),
                title: $('#board_title').val(),
                content: $('#board_content').val(),
                statusType: $('#board_status').val(),
                latitude: lat,
                longitude: lon,
            });
            console.log("jsonData: ",jsonData); //ok
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


