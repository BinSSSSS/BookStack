$(function () {
    var $submit =  $("#submit");
    var $userName  = $("#userName");
    var $password = $("#password");
    var $repetitionPass =  $("#repetitionPass");
    var $phoneNumber = $("#phoneNumber");
    var $account;


    $submit.click(function () {
        console.log("post");
        $.post(window.location.href,{
            type: 'REGISTER',
            userName: $userName.val(),
            password: $password.val(),
            phoneNumber: $phoneNumber.val()
        },function (data,status) {
            // console.log(data +  status);
            $account =  parseInt(data);

            ///账号创建成功的时候-需要进行元素的删除和设置
            if(!isNaN($account)){

                //删除整个的注册元素并添加新的元素
                $(".center-box").empty();


                ///添加新的元素
                var $succDiv = $("<div class='succ'><div></div></div>")

                var $succLog = $("<div class='succ-logo'></div>");
                var $succText = $("<div class='reg-succ'>注册成功</div>");
                var $div = $("<div></div>");
                var $accDiv = $("<div class='account'></div>")
                var $accSpan = $("<span></span>").text($account);
                var $logBtn =  $("<button class='login'>立即登录</button>");

                $succDiv.append($succLog);
                $succDiv.append($succText);
                $accDiv.append($accSpan);
                $div.append($accDiv);
                $div.append($logBtn);
                $succDiv.append($div);
                $('.center-box').append($succDiv);
                $logBtn.click(function(){
                    document.getElementById("login-face").click();
                })
            }else{
                if(data == "PHONEEXISTS"){
                    alert("该手机号已注册!")
                }else if(data == "ERROR"){
                    alert("服务器正忙-请稍后再试");
                }else{
                    alert("未知错误-请重新尝试!");
                }
            }
        });
    });
});