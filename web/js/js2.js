function sub()
{
    var e = document.getElementById("src");
    console.log(e.selectedIndex);
var from = e.options[e.selectedIndex].value;
 var f = document.getElementById("to");
var to = f.options[f.selectedIndex].value;
var get1 = new XMLHttpRequest();
    try {
        console.log("here1 " + from+" "+to);
        get1.open("GET", "/TMS/Demand_car?from=" + from +"&to=" +to, true);
    } catch (Exception)
    {
        console.log("error11");
    }
    get1.onreadystatechange = function () {
        console.log(this.readyState + " get1 " + this.status);
        if (this.readyState === 4 && this.status === 200) {
            console.log("gajdgj");
            console.log(this.responseText);
            document.getElementById("table").innerHTML=this.responseText;
        }
    };
    try {
        get1.send(null);
    } catch (Exception)
    {
        console.log("erro12");
    }

}

function submit()
{
    var checkboxes = document.querySelectorAll("td input[type=checkbox]");
    var i=0;
    var no = checkboxes[i].value;
    while(i<checkboxes.length)
    {
        if(checkboxes[i].checked)
        {
            var get1 = new XMLHttpRequest();
    try {
        get1.open("GET", "/TMS/Delete_car?car_no=" + checkboxes[i].value, true);
    } catch (Exception)
    {
        console.log("error11");
    }
    get1.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            window.open("/TMS/Booking_confirm.html?car_no="+ no,'_self');
        }
    };
    try {
        get1.send(null);
    } catch (Exception)
    {
        console.log("erro12");
    }
        }
        i=i+1;
    }
}
