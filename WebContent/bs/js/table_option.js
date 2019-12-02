$(function(){
	//删除按钮点击的时候触发
	$('.delete').click(function(){
		
		console.log("delete");
		$.ajax({
			url: this.rel,
			type:"POST",
			processData:false,
			contentType:false,
			success: function(data){
				if(data == "Success"){
					window.location.reload();
				}else{
					alert(data);
				}
			}
		});
	});
	//下载按钮点击的时候触发
	$('.download').click(function(){
		
		console.log("download");
		$.ajax({
			url: this.rel,
			type:"POST",
			processData:false,
			contentType:false,
			success: function(data){
				
				console.log("data: " + data);
			
				if(data != "404"){
					$("body").after("<a id='download' href='" + data+ "' download=''></a>");
//					$("#download").remove();
					document.getElementById("download").click();
					$("#download").remove();
//					$.ajax({
//						url: data,
//						type:"GET",
//						processData:false,
//						contentType:false});
				}
					
			}
		});
	});
})