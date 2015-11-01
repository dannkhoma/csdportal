var myImage = document.getElementById("mainImage");
var imageArray = ["csdportal/img/provider.jpg", "csdportal/img/Nurses.jpg",
    "csdportal/img/harare.jpeg", "csdportal/img/parirenyatwa.jpg",
    "csdportal/img/service.jpeg"];
var imageIndex = 0;

function changeImage() {
    myImage.setAttribute("src", imageArray[imageIndex]);
    imageIndex++;
    if (imageIndex >= imageArray.length) {
        imageIndex = 0;
    }
}

//seInterval is also in milliseconds
setInterval(changeImage, 4000);


