let content;

//커스텀오버레이 객체 생성하고 리턴하는 함수
function createCustomOverlay(location, idx, category, title, content){
    // 커스텀 오버레이에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    //일단 document.createElement로 만든 HTMLElement를 content로 넘겨주시는 방식으로 변경하셔야 합니다.

    //console.log("content",content); //ok
    return new kakao.maps.CustomOverlay({
        location: location,
        content: createContent(idx, category, title, content), // DOM 객체 생성
        //xAnchor: 0.3,
        yAnchor: 1.1
    });
}


function getContent(){
    return content;
}