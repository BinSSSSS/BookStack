window.onload =  function(){

    // 使用 jQuery的美元符号
    var jq = jQuery.noConflict(true);
    // DOM
    const $ = document.querySelector.bind(document);


    /* @表单提交的时候进行触发 */
    jq("form").submit(function(){

        // 拿到本表单的文件域input
        var files = jq(this).find("input.file")[0].files;
        var $form = jq(this);  // 拿到本次提交的表单
        /* 当文件为空， 不进行上传! */
        if(files == null || files.length == 0){
        	jq(".error-tips").text("未选择文件!");
            return false;
        }
        jq(".error-tips").text();  //清空错误提示
        // 显示上传的进度条
        Object.keys(files).forEach(file => {

            $(`.file--${file}`).querySelector(".progress").classList.remove("stop");
            $(`.file--${file}`).querySelector(".progress").classList.add("active");
            $(`.file--${file}`).querySelector(".done").classList.add("anim");

        });

        // 使用aJax进行文件的上传操作
        var formData =  new FormData(this);  // 将表单对象封装为FormData对象

        jq.ajax({
            url:"/BookStack/uploadfile.do",
            type:"POST",
            processData:false,
            contentType:false,
            data: formData,
            success: function(data){
                // 判断上传是否成功
                if(data == "Success"){
                    // 上传成功的时候显示完成
                    Object.keys(files).forEach(file => {
                        $(`.file--${file}`).querySelector(".progress").classList.remove("active");
                        $(`.file--${file}`).querySelector(".done").classList.add("anim");
                    });
                }
                // 上传失败
                else{
                    Object.keys(files).forEach(file => {
                        $form.find("embed").attr("src","./fonts/shibaibiaoqing.svg");
                        $(`.file--${file}`).querySelector(".progress").classList.remove("active");
                        $(`.file--${file}`).querySelector(".done").classList.add("anim");

                    });
                    /*添加标签并显示失败原因*/
                    jq(".error-tips").text(data);
                }
            }
        })
    });


    function init(files){

        $("#drop").classList.add("hidden");   // 隐藏文件拖拽界面
        $("footer").classList.add("hasFiles"); // 当前存在文件
        jq(".importar").css("display","block"); // 显示重新选择按钮

        // 显示上传文件信息的子元素
        let template = `${Object.keys(files)
            .map(
                file => `<div class="file file--${file}">
     <div class="name"><span>${files[file].name}</span></div>
     <div class="progress stop"></div>
     <div class="done">
     <a href="" target="_blank">
        <embed class="icon" src="./fonts/xiaolianchenggong.svg" type="image/svg+xml" />
     </a>
    </div>
    </div>`
            )
            .join("")}`;

        // 在延时400ms之后插入到页面元素中
        setTimeout(() => {
            $(".list-files").innerHTML = template;
        }, 400);
        

        jq(".divider").css("display","block");   // 显示上传文件按钮
    }

    // app
    let App = {};
    App.init = (function() {
        // 初始化文件选择器
        function handleFileSelect(evt) {
            const files = evt.target.files; // 文件列表
            init(files);
        }

        // trigger input
        // 文件选择的时候触发文件表单的改变事件
        $("#triggerFile").addEventListener("click", evt => {
            evt.preventDefault();
            $("input[type=file]").click();
        });

        // drop events
        $("#drop").ondragleave = evt => {
            $("#drop").classList.remove("active");
             evt.preventDefault();
        };
        $("#drop").ondragover = $("#drop").ondragenter = evt => {
            $("#drop").classList.add("active");
            evt.preventDefault();
        };
        /* 当移动文件到了页面之中的时候 */
        $("#drop").ondrop = evt => {

            // 将拖拽进入窗口的文件列表设置为文件表单域的文件列表对象
            $("input[type=file]").files = evt.dataTransfer.files;
            evt.preventDefault();
            init($("input[type=file]").files);  // 初始化文件对象
            console.log( $('input[type=file]').files);
        };

        // upload more
        /* @更新上传的文件 */
        $(".importar").addEventListener("click", () => {
            $(".list-files").innerHTML = "";  // 清空文件列表子元素
            $("footer").classList.remove("hasFiless"); // 移除存在文件
            jq(".importar").css("display","none");  // 本按钮设置为隐藏
            setTimeout(() => {
                $("#drop").classList.remove("hidden");
            }, 500);
            $("#drop").classList.remove("active"); // 设置为未存在文件
            $("input[type=file]").value = null;  // 清空之前选择的文件
            jq(".divider").css("display","none"); // 隐藏上传文件按钮
            console.log("importar click");
        });

        // 文件域表单被改变的时候则调用文件上传处理函数
        $("input[type=file]").addEventListener("change", handleFileSelect);
    })();



}