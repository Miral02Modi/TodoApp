

myApp.controller('TodoController', function($scope, createNoteService,
		updateNoteService, deleteNoteService, refreshTokenService, $http,
		$state, $uibModal) {
	/* showDividion */
	

	if (localStorage.getItem("accesstoken") == null) {
		console.log("inside the todoHome");
		$state.go("login");
	}

	$http({
		method : "get",
		url : 'http://localhost:8080/TodoApp/getTodoList',
		headers : {
			'accToken' : localStorage.getItem("accesstoken")
		}
	}).then(function successCallback(data) {
		$scope.notes = data.data.list.reverse();

		/*
		 * $scope.fff = data.data.list.color == "#fff"; $scope.rgb255138128 =
		 * data.data.list.color == "rgb(255,138,128)"; $scope.rgb255209128 =
		 * data.data.list.color == "rgb(255,209,128)"; $scope.rgb255255141 =
		 * data.data.list.color == "rgb(255,255,141)";
		 * 
		 * $scope.rgb207216220 = data.data.list.color == "rgb(207,216,220)";
		 * $scope.rgb(128,216,255 = data.data.list.color == "rgb(128,216,255)";
		 * $scope.rgb = data.data.list.color == "rgb(167,255,235)"; $scope.rgb =
		 * data.data.list.color == "rgb(204,255,144)";
		 */
	});

	console.log("show division controller...");
	// This will hide the DIV by default.
	$scope.IsVisible = false;
	$scope.IsVisible1 = true;

	$scope.listImage = false;
	$scope.gridImage = true;
	$scope.chnageView = "col-lg-4 col-sm-9 col-md-4 col-xs-12";
	$scope.addSpace3 = "";

	$scope.createPin = "false";
	$scope.createColor = "#ff";

	$scope.notes11 = true;
	$scope.pinned11 = true;
	$scope.archive11 = false;
	$scope.trash11 = false;
	$scope.createNotes11 = true;

	$scope.ShowHide = function() {
		console.log("hide and show function...");
		// If DIV is visible it will be hidden and vice versa.
		$scope.IsVisible = !$scope.IsVisible;
		$scope.IsVisible1 = !$scope.IsVisible1;
	};

	$scope.ShowHide1 = function() {
		console.log("onblur hide and show 1 function...");
		// If DIV is visible it will be hidden and vice versa.
		$scope.IsVisible = !$scope.IsVisible;
		$scope.IsVisible1 = !$scope.IsVisible1;
	};

	// Delete the notes and it will go inside the trash
	$scope.deleteNote = function(data) {

		var obj = {
			title : data.title,
			description : data.description,
			id : data.id,
			color : data.color,
			pinned : "false",
			isTrash : "true",
			archive : "false"
		}

		updateNoteService.updateNote(obj).then(
				function successCallback(data) {
					
					$scope.success("Note is Trashed");
					console.log("sdgsd" + data.data.list);

					if (data.data.status == 1) {
						$scope.notes = data.data.list.reverse();
					}

					if (data.data.status === "-4") {
						console.log("Inside the data status");
						refreshTokenService.refreshToken(localStorage
								.getItem("refreshtoken"));
					}

				});
	};

	/*
	 * $scope.searchData = function(){
	 * 
	 * $http({ method : "get", url :
	 * 'http://localhost:8080/TodoApp/getTodoList', headers : { 'accToken' :
	 * localStorage.getItem("accesstoken") } }).then(function
	 * successCallback(data) {
	 * 
	 * 
	 * $scope.notes = data.data.list; }); }
	 */

	// Create a new notes
	$scope.createNote = function() {

		$scope.IsVisible = false;
		$scope.IsVisible1 = true;
		console.log("create pinned::" + $scope.description1);
		console.log("create pinned::" + $scope.title1);

		var obj = {
			title : $scope.title1,
			description : $scope.description1,
			pinned : $scope.createPin,
			color : $scope.createColor,
			isTrash : "false"
		}
		$scope.title1="";
	    $scope.description1="";
		
		if (obj.title == "" && obj.description == "" && obj.description!== 'undefined' 
			&& obj.title !== 'undefined') {
			console.log("create note");

		} else {

			createNoteService.createNote(obj).then(
					function(data) {
						console.log("Inside the http response" + data.data);
						 
						console.log(data.data.status === "-4");
						if (data.data.status === "-4") {
							console.log("Inside the data status");
							refreshTokenService.refreshToken(localStorage
									.getItem("refreshtoken"));
						}

						console.log(data.data.status === 1);
						if (data.data.status === 1) {
							
							$("#presentationNote").html('');
							$("#presentationTitle").html("");
							$scope.notes = data.data.list;
							console.log(data.data.list);
						}

						$scope.createPin = "false";
						$scope.createColor = "#ff";
					});

		}
	};

	// Two time i Have return
	/*
	 * $scope.createNote = function() {
	 * 
	 * $scope.IsVisible = false; $scope.IsVisible1 = true;
	 * 
	 * var obj = { title : $scope.title1, description : $scope.description1 }
	 * 
	 * if (obj.title == "" && obj.description == "") { console.log("create
	 * note"); } else {
	 * 
	 * createNoteService.createNote(obj).then( function(data) {
	 * console.log("Inside the http response" + data.data);
	 * console.log(data.data.status === "-4"); if (data.data.status === "-4") {
	 * console.log("Inside the data status");
	 * refreshTokenService.refreshToken(localStorage .getItem("refreshtoken")); }
	 * 
	 * console.log(data.data.status === 1); if (data.data.status === 1) {
	 * 
	 * $scope.notes = data.data.list; console.log(data.data.list); }
	 * 
	 * }); } };
	 */

	// Display pop up for updating
	$scope.displayPopupData = function(x) {

		console.log("Title::" + x.title);
		console.log("description::" + x.description);
		$scope.updatNote = x;

		var modalInstance = $uibModal.open({

			templateUrl : "template/popup.html",
			controller : function($uibModalInstance) {

				console.log("inside the controller")
				var $update = this;
				this.id = x.id;
				this.title = x.title;
				this.description = x.description;
				this.color = x.color;
				this.pinned = x.pinned;
				this.archive = x.archive;
				this.isTrash = x.isTrash;

				console.log("title" + this.title);
				console.log("description" + this.description);
				console.log("color" + this.color);
				console.log("color" + this.pinned);

				this.colorChange = function(noteId, bgcolor) {
					
					
					console.log("inside the color change"+bgcolor);
					console.log("inside the color trash"+this.isTrash);
					console.log("inside the color archive"+this.archive);
					console.log("inside the color pinned"+this.pinned);
					/*
					 * console.log("inside the color change
					 * controller::"+this.pinned);
					 */

					console.log("inside the color" + this.pinned);

					this.color = bgcolor;
					var obj = {};

					obj.title = this.title;
					obj.description = this.description;
					obj.color = this.color;
					obj.id = noteId;
					obj.pinned = this.pinned;
					obj.isTrash = this.isTrash;
					obj.archive = this.archive;

					updateNoteService.updateNote(obj).then(
							function success(data) {
								console.log("update data success"
										+ data.data.list);
								$scope.notes = data.data.list;
							});
				};

				this.updateData = function(noteId,color) {

					console.log("inside the update id:::" + this.color);

					var obj = {};

					obj.title = this.title;
					obj.pinned = this.pinned;
					obj.description = this.description;
					obj.color = this.color;
					obj.id = noteId;
					obj.isTrash = this.isTrash;
					obj.archive = this.archive;

					console.log("update note data" + obj.title);
					console.log("update note data" + obj.description);
					$uibModalInstance.dismiss('Done');

					if (obj.title1 == "" && obj.description1 == "") {
						return;
					} else {
						updateNoteService.updateNote(obj).then(
								function success(data) {
									console.log("update data success"
											+ data.data.list);
									$scope.notes = data.data.list;
								});
					}
				};
			},
			controllerAs : "$update"
		});

	};

	/*
	 * $scope.updateNotes = function(noteId) { var obj = { title :
	 * $scope.updateTitle, description : $scope.updateDescription, id : noteId,
	 * isTrash : "false" }
	 * 
	 * console.log("Data is" + obj); };
	 */

	$scope.chngBackgroundOfSearch = function() {
		$scope.myObj = {
			"background-color" : "white"
		}
	}

	$scope.hideTitle = function() {
		console.log("inside the on blur");
		$scope.IsVisible1 = true;
		$scope.IsVisible = false;
	};

	$scope.showGridImage = function() {
		console.log("inside the grid");
		$scope.listImage = false;
		$scope.gridImage = true;
		$scope.chnageView = "col-lg-4 col-sm-9 col-md-4 col-xs-12";
		$scope.addSpace3 = "";
	}

	// Swapping the grid to list to grid
	$scope.showListImage = function() {
		console.log("inside the list");
		$scope.gridImage = false;
		$scope.listImage = true;
		$scope.chnageView = "col-sm-12 col-lg-8 col-xs-12 col-md-5";
		$scope.addSpace3 = "col-lg-3";
	}

	$scope.refresh = function() {
		console.log("inside the refresh");
		window.location.reload();
	}

	// color change of the notes
	$scope.colorChange = function(data, color) {

		console.log("Update color" + data + "color id" + color);

		if (data == null || data == "") {
			$scope.createColor = color;
			return;
		}

		var obj = {
			title : data.title,
			description : data.description,
			id : data.id,
			color : color,
			pinned : data.pinned,
			isTrash : data.isTrash,
			archive : data.archive
		}

		updateNoteService.updateNote(obj).then(function success(data) {
			console.log("update data success" + data.data.list);
			$scope.notes = data.data.list;
		});

		/*
		 * if(color.localeCompare("#fff") == 0){
		 * 
		 * $scope.fff = true; $scope.rgb255138128 = false; $scope.rgb255209128 =
		 * false; $scope.rgb255255141 = false; $scope.rgb207216220 = false;
		 * $scope.rgb128216255 = false; $scope.rgb167255235 = false; return; }
		 * 
		 * 
		 * rgb(255, 138, 128) if(color == "rgb(255,138,128)" ){
		 * 
		 * $scope.fff = false; $scope.rgb255138128 = true; $scope.rgb255209128 =
		 * false; $scope.rgb255255141 = false; $scope.rgb207216220 = false;
		 * $scope.rgb128216255 = false; $scope.rgb167255235 = false;
		 * $scope.rgb204255144 = false;
		 * 
		 * return; }
		 * 
		 * rgb(255, 209, 128) rgb(255,209,128) if(color == "rgb(255,209,128)"){
		 * 
		 * $scope.fff = false; $scope.rgb255138128 = false; $scope.rgb255209128 =
		 * true; $scope.rgb255255141 = false; $scope.rgb207216220 = false;
		 * $scope.rgb128216255 = false; $scope.rgb167255235 = false;
		 * $scope.rgb204255144 = false;
		 * 
		 * return; }
		 * 
		 * rgb(255, 255, 141) if(color == "rgb(255,255,141)"){
		 * 
		 * 
		 * $scope.fff = false; $scope.rgb255138128 = false; $scope.rgb255209128 =
		 * false; $scope.rgb255255141 = true; $scope.rgb207216220 = false;
		 * $scope.rgb128216255 = false; $scope.rgb167255235 = false;
		 * $scope.rgb204255144 = false;
		 * 
		 * return; }
		 * 
		 * 
		 * rgb(207, 216, 220) if(color == "rgb(207,216,220)" ){
		 * 
		 * $scope.rgb207216220 = true; $scope.fff = false; $scope.rgb255138128 =
		 * false; $scope.rgb255209128 = false; $scope.rgb128216255 = false;
		 * $scope.rgb167255235 = false; $scope.rgb204255144 = false;
		 * 
		 * return; }
		 * 
		 * rgb(128, 216, 255) if(color == "rgb(128,216,255)" ){
		 * 
		 * 
		 * $scope.rgb207216220 = false; $scope.fff = false; $scope.rgb255138128 =
		 * false; $scope.rgb255209128 = false; $scope.rgb255255141 = false;
		 * $scope.rgb128216255 = true; $scope.rgb167255235 = false;
		 * $scope.rgb204255144 = false; return; }
		 * 
		 * 
		 * rgb(167, 255, 235) if(color == "rgb(167,255,235)" ){
		 * 
		 * $scope.fff = false; $scope.rgb255138128 = false; $scope.rgb255209128 =
		 * false; $scope.rgb255255141 = false; $scope.rgb207216220 = false;
		 * $scope.rgb128216255 = false; $scope.rgb167255235 = true;
		 * $scope.rgb204255144 = false;
		 * 
		 * return; }
		 * 
		 * rgb(204, 255, 144) if(color == "rgb(204,255,144)"){
		 * 
		 * $scope.fff = false; $scope.rgb255138128 = false; $scope.rgb255209128 =
		 * false; $scope.rgb255255141 = false; $scope.rgb207216220 = false;
		 * $scope.rgb128216255 = false; $scope.rgb167255235 = false;
		 * $scope.rgb204255144 = true; }
		 */
	};

	// Create a pinned
	$scope.pinned = function(obj, pin) {

		console.log("update pinned" + obj);
		var obj = {
			title : obj.title,
			description : obj.description,
			id : obj.id,
			color : obj.color,
			pinned : pin,
			isTrash : "false",
			archive : "false"
		}
		console.log("update pinned" + obj);

		updateNoteService.updateNote(obj).then(function success(data) {
			console.log("update data success" + data.data.list);
			$scope.notes = data.data.list;
		});
	};

	$scope.generatePin = function(value) {
		console.log("inside the genreate pin");
		$scope.createPin = value;
	};

	$scope.trashCaller = function() {
		console.log("inside the trashCaller");
		$state.go("trash");
	};

	/* delete Notes Permanentely */
	$scope.deletepermanent = function(id) {

		console.log("Inside the permanent Delete");

		deleteNoteService.deleteNote(id).then(
				function successCallback(data) {

					console.log("sdgsd" + data.data.list);

					if (data.data.status == 1) {
						$scope.notes = data.data.list.reverse();
					}

					if (data.data.status === "-4") {
						console.log("Inside the data status");
						refreshTokenService.refreshToken(localStorage
								.getItem("refreshtoken"));
					}

				});

	};

	$scope.archiveNote = function(data) {

		var obj = {
			title : data.title,
			description : data.description,
			id : data.id,
			color : data.color,
			pinned : "false",
			isTrash : "false",
			archive : "true"
		}

		updateNoteService.updateNote(obj).then(
				function successCallback(data) {

					console.log("sdgsd" + data.data.list);
					$scope.success("Archive Successfully");
					
					if (data.data.status == 1) {
						$scope.notes = data.data.list.reverse();
					}

					if (data.data.status === "-4") {
						console.log("Inside the data status");
						refreshTokenService.refreshToken(localStorage
								.getItem("refreshtoken"));
						
					}

				});
	};

	$scope.restoreNote = function(data) {

		
		if(data.isTrash == "true")
			$scope.success("restore Successfully");
		if(data.archive == "true")
			$scope.success("UnArchive Successfully");
		
		var obj = {
			title : data.title,
			description : data.description,
			id : data.id,
			color : data.color,
			pinned : "false",
			isTrash : "false",
			archive : "false"
		}

		updateNoteService.updateNote(obj).then(
				function successCallback(data) {

					console.log("sdgsd" + data.data.list);

					if (data.data.status == 1) {
						$scope.notes = data.data.list.reverse();
					}

					if (data.data.status === "-4") {
						console.log("Inside the data status");
						refreshTokenService.refreshToken(localStorage
								.getItem("refreshtoken"));
					}

				});
	};

	$scope.success = function(message) {
		toastr.success(message);
	};

	$scope.setOptions = function() {
		toastr.options.positionClass = "toast-bottom-left";
		toastr.options.closeButton = true;
		toastr.options.showMethod = 'slideDown';
		toastr.options.hideMethod = 'slideUp';
		// toastr.options.newestOnTop = false;
	};

	$scope.setOptions();
	
	
	$scope.createReminder = function(note,day,time){
		
		if(day == "Today"){
			
			
		}
		
	};
});






