let params = {
    lat: latitude,
    lon: longitude
};

let url = new URL("'http://localhost:8080/api/places'"),
    params = {
        lat: latitude,
        lon: longitude;
    }

let latitude, longitude;
fetch('http://localhost:8080/api/places?' + new URLSearchParams({
    lat : latitude,     // undefined
    lon : longitude,    // undefined
    //lat: 37.4865883,
    //lon: 126.9839492,
})) // // TODO: 배포시 주소 바꾸기// http://localhost:8080/api/places?lat=37.4865883&lon=126.9839492
    .then((json) => {
        console.log(json);
        console.log(latitude);
        console.log(longitude);
})
    .catch((err) => {
        console.error(err);
});


//api/places?lat=37.4865883&lon=126.9839492
//https://velog.io/@luna238/Javascript-%EC%84%9C%EB%B2%84%EC%97%90-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%A5%BC-%ED%98%B8%EC%B6%9C%EC%9A%94%EC%B2%AD%ED%95%98%EB%8A%94-fetch%ED%95%A8%EC%88%98#method-get%EC%9D%B8%EB%8D%B0-parameter%EB%A5%BC-%EC%A0%84%EB%8B%AC%ED%95%B4%EC%95%BC-%ED%95%98%EB%8A%94-%EA%B2%BD%EC%9A%B0
let latitude,longitude;
fetch(`http://localhost:8080/api/places?lat=${latitude}&lon=${longitude}`)
    .then(res => console.log(res));



//api/places?lat=37.4865883&lon=126.9839492
//https://velog.io/@luna238/Javascript-%EC%84%9C%EB%B2%84%EC%97%90-%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%A5%BC-%ED%98%B8%EC%B6%9C%EC%9A%94%EC%B2%AD%ED%95%98%EB%8A%94-fetch%ED%95%A8%EC%88%98#method-get%EC%9D%B8%EB%8D%B0-parameter%EB%A5%BC-%EC%A0%84%EB%8B%AC%ED%95%B4%EC%95%BC-%ED%95%98%EB%8A%94-%EA%B2%BD%EC%9A%B0

let latitude,longitude;
fetch(`http://localhost:8080/api/places?lat=37.4865883&lon=126.9839492`)
    .then(res => console.log(res));




