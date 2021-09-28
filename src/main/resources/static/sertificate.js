/**
 * Created by Sherlock on 05.09.2021.
 */
const id = window.location.search.split('=')[1];
let data = {};

Date.prototype.addHours = function(h) {
    this.setTime(this.getTime() + (h*60*60*1000));
    return this;
}

Date.prototype.yyyymmdd = function() {
    var mm = this.getMonth() + 1; // getMonth() is zero-based
    var dd = this.getDate();
    var hh = this.getHours();
    var MM = this.getMinutes();

    return [(dd>9 ? '' : '0') + dd,
        (mm>9 ? '' : '0') + mm,
        this.getFullYear(),


    ].join('.') + " " + ((hh>9 ? '' : '0') + hh) + ":" + ((MM>9 ? '' : '0') + MM);
};

const getData = async () => {

    let response = await fetch("https://app-covidd.herokuapp.com/api/patient/" + id);

    if (response.ok) {
        let json = await response.json();
        data = json.data
    } else {
        alert("Ошибка HTTP: " + response.status);
    }
}


getData()
    .then(() => {
        console.log(data)
        document.getElementById("serial").innerHTML = data.serial;
        document.getElementById("laboratory").innerText = data.laboratory.nameEn + " / " + data.laboratory.nameRu;
        document.getElementById("place").innerText = data.place.nameEn + " / " + data.place.nameRu;
        document.getElementById("method").innerText = data.methodEn + " / " + data.methodRu;
        document.getElementById("passport").innerText = data.passportNumber
        document.getElementById("fullname").innerText = data.fullName
        document.getElementById("birthday").innerText = data.birthDate;
        document.getElementById("gender").innerText = data.gender;
        document.getElementById("analise").innerText = new Date(data.analiseDate).yyyymmdd();
        document.getElementById("result").innerText = data.status + " (" +new Date(data.analiseDate).addHours(4).yyyymmdd() + ")"
        document.getElementById("qr").src = "https://api.qrserver.com/v1/create-qr-code/?size=320x320&data=" + window.location.href;
    })


