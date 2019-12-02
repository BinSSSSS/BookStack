
$(function(){

    var $account = $('#ipt-account');    //输入的账户
    var $password = $('#password');     //输入的密码

    var $login =  $('#ipt-login');      //登录


    var $errorTips  = $account.next();     //错误提示


    $login.click(function(event){

    	/*当账户或密码长度小于8位的时候，直接显示错误*/
        if( ($account.val().length < 8) ||  $password.val().length < 8){
            $errorTips.text("账户名或密码错误");
            return false;
        }else{
          $login.disable = true;
          $("login-form").submit();
          //$login.sumbit();
          
          /* 避免重复点击， 一秒钟只能单击一次*/
          setTimeout(function(){
        	  $login.disable = false;
      	},1000);
        }

    })

})