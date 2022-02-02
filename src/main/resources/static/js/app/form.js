var main = {
    init : function () {
        var _this = this;
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
    save : function () {
        var jsonData = JSON.stringify({
            idx: $('#board_idx').val(),
            title: $('#board_title').val(),
            content: $('#board_content').val(),
            // here 위치(latitude,longitude)는 geolocation으로 가져오기 !
        });
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
    },
    // here 저장 버튼 누르고 게시글 목록으로 돌아간 다음 '뒤로 가기' 누르면 잘못된 접근입니다. 띄워줘야함

    update : function () {
        var jsonData = JSON.stringify({
            title: $('#board_title').val(),
            content: $('#board_content').val(),
            // here 위치(latitude,longitude)는 geolocation으로 가져오기 !
        });

        var idx = $('#board_idx').val();

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
        var idx = $('#board_idx').val();

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

main.init();


