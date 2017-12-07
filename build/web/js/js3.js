function add_vehicle()
{
    var from = document.getElementById('FROM').value;
    var to = document.getElementById('TO').value;
    var no = document.getElementById('car_no').value;
    var model = document.getElementById('model').value;
    var get2 = new XMLHttpRequest();
    try {
        get2.open("GET", "/TMS/vehicle?id1=" + from + "&id2=" + to + "&id3=" + no + "&id4=" + model, true);
    } catch (Exception)
    {
        console.log("error11");
    }
    get2.onreadystatechange = function () {
        console.log(this.readyState + " get2 " + this.status);
        if (this.readyState === 4 && this.status === 200) {
            
            event.preventDefault();
            var toast = $('.toast');

            //Set the content of the Toast
            toast.text("Vehicle Added");


            //Show the Toast
            toast.fadeIn(400).delay(3000).fadeOut(400);
            document.getElementById("myForm").reset();




        }

    };
    try {
        get2.send(null);
    } catch (Exception)
    {
        console.log("erro12");
    }
}