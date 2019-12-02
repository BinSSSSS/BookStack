<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>上传文件到书栈</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/default.css">
<link href="css/fileinput.css" media="all" rel="stylesheet"
	type="text/css" />
<!--[if IE]>
  <script src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<![endif]-->
<script src="js/jquery.js"></script>
<script src="js/upload.js"></script>
</head>
<body>

	<div class="htmleaf-container">
		<header class="htmleaf-header">
			<h1>上传文件到书栈</h1>
		</header>

		<div class="container kv-main">
			<div class="page-header">
				<h2>选择文件</h2>
			</div>
			<form enctype="multipart/form-data" method="post"
				action="javascript:void(0)">
				<input class="file" type="file" data-min-file-count="1" id="upfile1"
					name="upfile1"> <br> <input type="submit"
					class="btn btn-primary" value="上传"> <input type="reset"
					class="btn btn-default" value="重置"></input>

			</form>
			<hr>
			<hr>
			<form enctype="multipart/form-data" method="post"
				action="javascript:void(0)">
				<input id="file-0a" class="file" type="file" multiple
					data-min-file-count="3">
				<hr>
				<div class="form-group">
					<input id="file-0b" class="file" type="file" name="upfile2">
				</div>
				<hr>
				<div class="form-group">
					<input id="file-1" type="file" multiple class="file"
						data-overwrite-initial="false" data-min-file-count="2">
				</div>
				<hr>
				<div class="form-group">
					<input id="file-2" type="file" class="file" readonly
						data-show-upload="false" name="upfile3">
				</div>
				<hr>
				<div class="form-group">
					<label>Preview File Icon</label> <input id="file-3" type="file"
						multiple=true name="upfile4">
				</div>
				<hr>
				<div class="form-group">
					<input id="file-4" type="file" class="file"
						data-upload-url="/BookStack/bsuploadfile.ado">
				</div>
				<hr>
				<div class="form-group">
					<button class="btn btn-warning" type="button">Disable Test</button>
					<button class="btn btn-info" type="reset">Refresh Test</button>
					<button class="btn btn-primary">Submit</button>
					<button class="btn btn-default" type="reset">Reset</button>
				</div>
				<hr>
				<div class="form-group">
					<input type="file" class="file" id="test-upload" multiple
						name="upfile5">
					<div id="errorBlock" class="help-block"></div>
				</div>
				<hr>
				<div class="form-group">
					<input id="file-5" class="file" type="file" multiple
						data-preview-file-type="any"
						data-upload-url="/BookStack/bsuploadfile.ado"
						data-preview-file-icon="">
				</div>
			</form>
		</div>

	</div>
	<script src="js/fileinput.js" type="text/javascript"></script>
	<script src="js/fileinput_locale_zh.js" type="text/javascript"></script>
	<script src="js/bootstrap.min.js" type="text/javascript"></script>
	<script>
		$("#file-0").fileinput({
			'allowedFileExtensions' : [ 'jpg', 'png', 'gif' ],
		});
		$("#file-1").fileinput({
			uploadUrl : 'http://localhost:8080/BookStack/bsuploadfile.ado', // you must set a valid URL here else you will get an error
			allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
			overwriteInitial : false,
			maxFileSize : 1000,
			maxFilesNum : 10,
			slugCallback : function(filename) {
				return filename.replace('(', '_').replace(']', '_');
			}
		});

		$("#file-3").fileinput({
			showUpload : false,
			showCaption : false,
			browseClass : "btn btn-primary btn-lg",
			fileType : "any",
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>"
		});
		$("#file-4").fileinput({
			uploadExtraData : {
				kvId : '10'
			}
		});
		$(".btn-warning").on('click', function() {
			if ($('#file-4').attr('disabled')) {
				$('#file-4').fileinput('enable');
			} else {
				$('#file-4').fileinput('disable');
			}
		});
		$(".btn-info").on('click', function() {
			$('#file-4').fileinput('refresh', {
				previewClass : 'bg-info'
			});
		});

		$(document).ready(function() {
			$("#test-upload").fileinput({
				'showPreview' : false,
				'allowedFileExtensions' : [ 'jpg', 'png', 'gif' ],
				'elErrorContainer' : '#errorBlock'
			});
		});
	</script>
</body>
</html>