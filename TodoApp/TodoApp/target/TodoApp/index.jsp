<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Miral Keep</title>
<link rel="icon" href="imgs/keep-logo.ico" type="image/ico"
	sizes="16x16">


<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/NavbarCss.css">
<link rel="stylesheet" href="css/createNote.css">
<link rel="stylesheet" href="css/menuBar.css">
<link rel="stylesheet" href="css/dropDown.css">

<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/css/toastr.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.9.2/themes/base/jquery-ui.min.css">	
	


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="bower_components/angular/angular.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-sanitize/1.6.5/angular-sanitize.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.5.0/ui-bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.5.0/ui-bootstrap-tpls.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui/0.4.0/angular-ui.min.js"></script> -->
	
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-sortable/0.17.1/sortable.min.js"></script>	
<script type="text/javascript"
	src="bower_components/angular-ui-router/release/angular-ui-router.js"
	charset="utf-8"></script>
</head>







<body data-ng-app="todoApp" style="background-color: #e8e8e8">
	<div ui-view></div>
</body>



<script type="text/javascript" src="js/DesignJs/NavBar.js"
	charset="utf-8"></script>
	
	
<script type="text/javascript" src="js/app.js" charset="utf-8"></script>
<script type="text/javascript" src="js/controller/LoginController.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/controller/RegisterController.js"
	charset="utf-8"></script>
<!-- <script type="text/javascript" src="js/controller/showDivisionController.js" charset="utf-8"></script> -->
<script type="text/javascript" src="js/controller/TodoController.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/controller/TrashController.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/controller/ArchiveController.js"
	charset="utf-8"></script>



<!-- JavScript services -->
<script type="text/javascript" src="js/service/loginService.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/service/loginService.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/service/RegisterService.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/service/RefreshTokenService.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/service/createNoteService.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/service/DeleteNotes.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/service/updateNote.js"
	charset="utf-8"></script>





<script type="text/javascript" src="js/directive.js" charset="utf-8"></script>
<script type="text/javascript" src="js/directive/colorDirective.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/directive/pinnedDirective.js"
	charset="utf-8"></script>
<script type="text/javascript" src="js/directive/Drag_DropDirective.js"
	charset="utf-8"></script>



<script>
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
	});
</script>
</html>