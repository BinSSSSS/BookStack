$(function(){
	
	/*@ 表单提交的时候进行触发*/
	$('form').submit(function(){
		
		
		/*@ 首先需要对表单内的数据进行验证，如果未选择文件的话， 那么肯定不能提交到后台*/
		var $files = $(this).find("input.file");   //找到input中类为file的子元素
		var hasEmpty = false;
		
		$files.each(function(){
			if(!this.files[0])
				hasEmpty = true;
		});
		
		if(hasEmpty)
			return false;
		
		var $form = $(this);
		
		$form.children("div.progress-bar").remove(); //每次点击都删除之前的进度条
		
		/*将表单封装为 FormData对象进行上传*/
		var formData =  new FormData(this);
		$.ajax({
			url:"/BookStack/bsuploadfile.ado",
			type:"POST",
			processData:false,
			contentType:false,
			data: formData,
			success: function(data){
				
				/*@插入成功的操作*/
				if(data == "Success"){
					$form.children("br").after("<div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='100'" +
				    		" aria-valuemin='0' aria-valuemax='100' style='width:100%;'>"+
					        "100%" +
					     "</div>" + 
						"</div> <br>" );
					
					$form.find("button.fileinput-remove").click();  //上传成功之后清除上传的文件
				}else{
					$form.children("br").after("<div class='progress-bar progress-bar-success space' role='progressbar' aria-valuenow='100'" +
		    		" aria-valuemin='0' aria-valuemax='100' style='width:100%;'>"+
		        data +
		     "</div>" + 
			"</div>");
				}
			}
		})
		
	});
});