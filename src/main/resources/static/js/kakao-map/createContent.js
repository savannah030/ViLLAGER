function createContent(idx,category,title,content){
    let div = document.createElement("div");
    div.setAttribute("class", "overlaybox"); // overlay_info

    let a = document.createElement("a");
    a.setAttribute("class","link");
    a.setAttribute("href", '/board/form?idx='+idx);
    a.setAttribute("target","_blank");
    div.appendChild(a);

    let strong = document.createElement("strong");
    strong.innerHTML = title;
    a.appendChild(strong);

    let div2 = document.createElement("div");
    div2.setAttribute("class", "desc");
    div.appendChild(div2);
    /*
    let img = document.createElement("img");
    img.setAttribute("class", "max-small");
    img.setAttribute("src","FIXME"); // TODO: 사진...
    img.setAttribute("alt","사진없음");
    div2.appendChild(img);
    */
    let br = document.createElement("br");
    div2.appendChild(br);

    let br2 = document.createElement("br");
    div2.appendChild(br2);

    let a2 = document.createElement("a");
    a2.setAttribute("href", '/board/form?idx='+idx); // FIXME 일단은 게시판으로 이동하도록
    a2.setAttribute("target", "_blank");
    div2.appendChild(a2);

    let span = document.createElement("span");
    span.setAttribute("class","address");
    span.innerHTML = content;
    a2.appendChild(span);

    return div;

    /*
     * <div>
     *   <a><strong>
     *   <div2>
     *   	<img>
     *   	<span>
     */

}