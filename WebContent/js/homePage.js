

$(function(){
    ///轮播图
    var $carouselBox = $(".item");
    var index = 0;
    ///索引图标
    var $indexCarouselBox = $(".carousel-indicators");			//选择外层盒子
    var $carouseIndexList = $indexCarouselBox.children("li");		//选择索引图标

    ///站长心语
    var $myHeat = $(".about");
    var $words = $(".words");

	//重新设置轮播图类函数
	function clearfixCars(i){
        //遍历元素，并设置对应的类
        for(var j = 0; j < $carouselBox.length; ++j)
		{
			
			///当不处于激活状态的图片或者是索引图标的时候，设置为未激活状态
			if(j != i)
			{
				// $($carouseList[j]).removeAttr("class");
				$($carouselBox[j]).attr("class","item");
			}
			///正在播放的图片和索引图标设置为激活状态
			else{
				// $($carouseList[j]).attr("class","active");
                $($carouselBox[j]).attr("class","item active");
			}
		}
	}
    //设置前一张轮播图. i 表示下标，m表示设置的模式 . 0 正常向前模式轮播。 1向后或者跨越轮播
	function setCarsPrev(i,m){

        //设置index索引图标的位置
        var carsIndex ;

        ////正常向前轮播
        if(m == 0){
            //设置当前图片的类
            $($carouselBox[i]).attr("class","item active left");

            ///非最后一张
            if(i + 1 != $carouselBox.length){
                carsIndex =  i + 1;
                ///传递下一张图片作为轮播
                setTimeout(function(){		//setTimeout的时候注意点就是，需要使用一个function包裹调用函数，而不能直接使用 setCarsNext函数。否则不会进行延时调用。
                    setCarsNext(i + 1)
                },600);
            }
            else
            {
                carsIndex = 0;
                //最后一张图片的时候传递第一张图片作为轮播
                setTimeout(function(){
                        setCarsNext(0)},
                    600);
            }
        }
        ///向前轮播
        else{
            //设置当前图片的类
            $($carouselBox[i]).attr("class","item active right");
            if(i != 0){
                carsIndex = i - 1;
                setTimeout(function(){
                    setCarsNext(i - 1,m);
                },600);
            }
            ///否则传递最后一张
            else {
                carsIndex = 2;
                setTimeout(function () {
                    setCarsNext(2, m);
                }, 600);
            }
        }

		///提前设置下一张图片索引图标的激活状态。
		for(var j = 0; j < $carouseIndexList.length; ++j){
		    if(j != carsIndex){
                $($carouseIndexList[j]).removeAttr("class");
            }
            else
                $($carouseIndexList[j]).attr("class","active");
        }
	}
	//设置后一张轮播图. i表示下标，m表示设置的模式
	function setCarsNext(i, m){
        if(m == 0){
            $carouselBox[i].className = 'item next left';
        }
        else{
            $carouselBox[i].className = 'item prev right';
        }
		setTimeout(function(){
			clearfixCars(i)},
			300);		//重新设置轮播图类
	}

    ///轮播函数
    function carouselImg(){
		setCarsPrev(index,0);
		if(++index == $carouselBox.length){
			index = 0;
		}
    }

    ///索引图标的点击事件
    //第一个索引
    $($carouseIndexList[0]).click(function(){
        clearInterval(timer);
        /// 当轮播图定位在下一张图片的时候. 传递下一张图片的索引
        if($carouselBox[1].className == "item active")
            setCarsPrev(1,1);
        else 	///否则传递上一张图片的索引
            setCarsPrev(2,0);
        index = 0;		//记得定位到当前图片
        timer = setInterval(carouselImg,2500);
        });
    //第二个索引
    $($carouseIndexList[1]).click(function(){
        clearInterval(timer);   //清除定时器
        /// 当轮播图定位在下一张图片的时候. 传递下一张图片的索引
        if($carouselBox[2].className == 'item active')
            setCarsPrev(2,1);
        else    ///否则传递上一张图片的索引
            setCarsPrev(0,0);
        index = 1;
        timer = setInterval(carouselImg,2500);
    });

    $($carouseIndexList[2]).click(function(){
        clearInterval(timer);
        /// 当轮播图定位在下一张图片的时候. 传递下一张图片的索引
        if($carouselBox[0].className == 'item active')
            setCarsPrev(0,1);
        else///否则传递上一张图片的索引
            setCarsPrev(1,0);
        index = 2;
        timer = setInterval(carouselImg,2500);
    });

    //定时器
  	var timer = setInterval(carouselImg,2500);


    //点击按钮事件
    $myHeat.click(function(){
    	if($words.css("display") == 'none')
    	{
    		$words.css({
			display: "block"
			})
    	}
    	else{
    		$words.css({
			display: "none"
			})
    	}
	});

    
    ///点击关注
    var $follows = $(".follow");

    //点击关注按钮
    for(var i = 0; i < $follows.length; ++i){
    	$($follows[i]).click(function(){

    	    //如果未关注的话，点击则进行关注
    	    if($.trim(($(this).text())) == "关注"){
                $(this).text(" 已关注");
                $(this).prepend("<i class='iconfont icon-yiguanzhu'></i>");
                $(this).css({
                    color: "orange"
                });
            }
            //关注了之后点击则取消关注
            else
            {
                $(this).css({
                    color: "blue"
                });
                $(this).text(" 关注");
                $(this).prepend("<i class='iconfont icon-jia'></i>");
            }
    	})
    }

    var $btn_search = $(".btn-search");            //搜索按钮
    var $input_sear = $(".search-input")[0];       //输入框

    $btn_search.click(function(){
        // console.log($input_sear.value);
        $.post("http://127.0.0.1/",{name:$input_sear.value,url:"http://www.tblack.cn"},function(data,status){
           console.log("Data : " + data + "Status: " + status);
       })

    });

})


	
