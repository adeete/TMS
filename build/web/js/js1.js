/* global type */

$(function () {
    var type;
    var $formLogin = $('#login-form');
    var $formLost = $('#lost-form');
    var $retrieve = $('#retrieve-form');
    var $divForms = $('#div-forms');
    var $modalAnimateTime = 300;
    var $msgAnimateTime = 150;
    var $msgShowTime = 2000;

    $("form").submit(function () {
        switch (this.id) {
            case "login-form":
                var $lg_username = $('#login_username').val();
                var $lg_password = $('#login_password').val();
                return false;
                break;
            case "lost-form":
                var $ls_email = $('#lost_email').val();
                return false;
                break;
            case "retrieve":

                return false;
                break;
            default:
                return false;
        }
        return false;
    });
    $('#User').click(function(){
        window.value="user";
        console.log("user clicked");
        user();
    });
    $('#Manager').click(function(){
        window.value="man";
        manager();
    });
    $('#retrieve_login_btn').click(function () {
        modalAnimate($retrieve, $formLogin);
        document.getElementById("buttons").style.display='block';
    });
    $('#login_lost').click(function () {
        modalAnimate($formLost, $formLogin);
        document.getElementById("buttons").style.display='block';
    });
    $('#login_lost_btn').click(function () {
        document.getElementById("buttons").style.display='none';
        modalAnimate($formLogin, $formLost);
    });
    
    $('#lost_login_btn').click(function () {

        document.getElementById("buttons").style.display='none';
        var get1 = new XMLHttpRequest();
        try {
            console.log("here");
            var email = document.getElementById("lost_email").value;
            get1.open("GET", "/Livie_project1/check1?id=" + email, true);//check whether email exists
        } catch (Exception)
        {
            console.log("error11");
        }
        get1.onreadystatechange = function () {
            console.log(this.readyState + " get1 " + this.status);
            if (this.readyState === 4) {
                var JSONtopicobject = eval("(" + this.responseText + ")");
                t = JSONtopicobject.topic.name;
                if (t.toString() !== '0')
                {
                    var get2 = new XMLHttpRequest();
                    modalAnimate($formLost, $retrieve);

                    try {
                        console.log("here34");
                        get2.open("GET", "/Livie_project1/NewServlet?id=" + email, true);
                    } catch (Exception)
                    {
                        console.log("error112");
                    }
                    get2.onreadystatechange = function () {
                        console.log("open");


                    };
                    try {
                        get2.send(null);
                    } catch (Exception)
                    {
                        console.log("erro12");
                    }
                }
                else
                {
                    console.log("worng");
                    document.getElementById("val").innerHTML = "This email id does not exists";
                    document.getElementById("e-id").innerHTML = "";
                }

            }
        };
        try {
            get1.send(null);
        } catch (Exception)
        {
            console.log("erro12");
        }

    });
    $('#lost_register_btn').click(function () {
        modalAnimate($formLost, $formRegister);
    });
    $('#register_lost_btn').click(function () {
        modalAnimate($formRegister, $formLost);
    });

    function modalAnimate($oldForm, $newForm) {
        var $oldH = $oldForm.height();
        var $newH = $newForm.height();
        $divForms.css("height", $oldH);
        $oldForm.fadeToggle($modalAnimateTime, function () {
            $divForms.animate({height: $newH}, $modalAnimateTime, function () {
                $newForm.fadeToggle($modalAnimateTime);
            });
        });
    }

    function msgFade($msgId, $msgText) {
        $msgId.fadeOut($msgAnimateTime, function () {
            $(this).text($msgText).fadeIn($msgAnimateTime);
        });
    }

    function msgChange($divTag, $iconTag, $textTag, $divClass, $iconClass, $msgText) {
        var $msgOld = $divTag.text();
        msgFade($textTag, $msgText);
        $divTag.addClass($divClass);
        $iconTag.removeClass("glyphicon-chevron-right");
        $iconTag.addClass($iconClass + " " + $divClass);
        setTimeout(function () {
            msgFade($textTag, $msgOld);
            $divTag.removeClass($divClass);
            $iconTag.addClass("glyphicon-chevron-right");
            $iconTag.removeClass($iconClass + " " + $divClass);
        }, $msgShowTime);
    }
    
});
function user()
{
    document.getElementById('Manager').style.backgroundColor = "white";
    document.getElementById('User').style.backgroundColor = "#81A094";
}
function manager()
{
    document.getElementById('Manager').style.backgroundColor = "#81A094";
    document.getElementById('User').style.backgroundColor = "white";
}
function login()
{
    var email = document.getElementById("email").value;
    var pwd = document.getElementById("password").value;
    var get1 = new XMLHttpRequest();
    try {
        console.log("here1 " + email);
        get1.open("GET", "/Livie_project1/check1?id=" + email, true);
    } catch (Exception)
    {
        console.log("error11");
    }
    get1.onreadystatechange = function () {
        console.log(this.readyState + " get1 " + this.status);
        if (this.readyState === 4 && this.status === 200) {
            var JSONtopicobject = eval("(" + this.responseText + ")");
            var t = JSONtopicobject.topic.name;
            var t1 = JSONtopicobject.topic.name1;
            console.log("t : " + t + " " + t1);
            if (t.toString() !== '0')
            {
                if (pwd === t1)
                {
                    window.open("/Livie_project1/main.html?email=" + email, '_self');
                    console.log(pwd + " " + t1);

                }
                else
                {
                    event.preventDefault();
                    document.getElementById("pwd").value = '';
                    document.getElementById("text-login-msg").innerHTML = "Username or password incorrect";
                }
            }
            else
            {
                event.preventDefault();
                console.log("worng");
                document.getElementById("text-login-msg").innerHTML = "This email id does not exists";
                document.getElementById("email").value = '';
                document.getElementById("password").value = '';
            }
        }
        else
        {
            event.preventDefault();

        }
    };
    try {
        get1.send(null);
    } catch (Exception)
    {
        console.log("erro12");
    }

}
