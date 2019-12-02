$(function(){


    $del = $(".delete");    //删除按钮点击

    console.log($del);

    var url = window.location.href;
    var endIndex = url.lastIndexOf("/");
    if(endIndex ==  -1) endIndex = url.length;
    url = url.substring(0,endIndex);


    /*点击删除按钮的时候*/
    $del.click(function(){

       $.post(url + this.value,{}, function(data,status){

           if(!window.confirm("是否确定删除?")){
               return;
           }

           /*删除成功了,页面进行重新加载*/
           if(data == "Success"){
               window.location.reload();  //重新加载该页面
           }
           else{
               alert("删除失败!");
           }
       });
    });
});