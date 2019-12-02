$(function(){
	
	$ipt_password =  $("#ipt-password");
	$ipt_username = $("#ipt-username");
    $err_tips = $(".error-tips");



	//var info_not_null = {0,0}; //表示在输入栏输入的数据是否合法， 为0表示不合法，1表示合法

    var url = window.location.href; //拿到当前请求的完整url
    var resp ;	//得到的响应数据

    
    /*@给登录按钮绑定回车事件*/
    $(document).keydown(function(e) {  

    	 if((e.keyCode || e.which)==13) {  
    	   	$('input[type="submit"]').trigger("click");  //触发登录按钮
    	}  

    }); 
    
    /*当点击登录按钮的时候，设置登陆栏的移动效果， 并将数据提交给后台。*/
	$('input[type="submit"]').click(function (event) {

        $err_tips.text("");
        resp = "";

	    /*@当用户名或密码没有输入的时候， 弹出错误信息*/
	    if($ipt_password.val().length == 0 || $ipt_username.val().length == 0){

	        $err_tips.text("用户名和密码不能为空");
	        return false;
        }

        /*@拿到结束的位置*/
        var endInd =  url.lastIndexOf("/");


	    if(endInd == -1){
	        endInd = url.length;
        }

	    var post = url.substring(0,endInd);
	    
	    
	    console.log("Post: " + post);

        /*@将数据转发给后台进行处理*/
        $.post(post + "/bslogin.ado",{
            username:$ipt_username.val(),
            password:$ipt_password.val()

        },function (data,status) {
        	
        	
            /*当拿到后台返回的数据的时候才进行跳转*/
        	resp = data;
            console.log("Resp:" + resp);

            
            //将验证标识设置透明度并回到初始的位置
            $('.authent').animate({ opacity: 0 }, {
                duration: 200,
                queue: false
            }).addClass('visible');
            /*登录栏回到原来的位置*/
            $('.login').removeClass('testtwo');
            $(".authent").css("display","none");


            //根据响应的数据来决定是否跳转
            setTimeout(function () {
                $('.login').removeClass('test');
                /*@如果拿到的请求数据是准许登录,跳转到后台首页*/
                if(resp == "APPROVE"){
                	
                	//插入新元素到页面，添加跳转到主页的a标签
                	$("body").append("<a href='/BookStack/bs/' id='stage' target='_self'></a>");
                	
                	/*@单击跳转按钮*/
                    document.getElementById("stage").click();
                }else {
                    $err_tips.text(resp);
                }
            }, 200);
        });


        /*每次点击的时候，将按钮向下倾斜*/
        $('.login').addClass('test');
        
        /*并且在之后设置回到初始的位置*/
        setTimeout(function () {
            $('.login').addClass('testtwo');
        }, 200);
        
        $(".authent").css("display","block");
        
        /*验证栏向右偏移*/
        setTimeout(function () {
        	
            $('.authent').show().animate({ right: -320 }, {
                duration: 600,
                queue: false
            });
            /*如果已经接收到了返回的数据的话， 那么就无需要再次向右移动了*/
            if(resp.length == 0){
            	 $('.authent').animate({ opacity: 1 }, {
                     duration: 200,
                     queue: false
                 }).addClass('visible');
            } 
        }, 200);
        /*验证栏向回移动*/
        setTimeout(function () {
        		$('.authent').show().animate({ right: 90 }, {
	                duration: 600,
	                queue: false
	            });
        }, 2500);

    });
	
	
	
    $('input[type="text"],input[type="password"]').focus(function () {
        $(this).prev().animate({ 'opacity': '1' }, 200);
    });
    $('input[type="text"],input[type="password"]').blur(function () {
        $(this).prev().animate({ 'opacity': '.5' }, 200);
    });
    $('input[type="text"],input[type="password"]').keyup(function () {
        if (!$(this).val() == '') {
            $(this).next().animate({
                'opacity': '1',
                'right': '30'
            }, 200);
        } else {
            $(this).next().animate({
                'opacity': '0',
                'right': '20'
            }, 200);
        }
    });
    var open = 0;
    $('.tab').click(function () {
        $(this).fadeOut(200, function () {
            $(this).parent().animate({ 'left': '0' });
        });
    });
})
