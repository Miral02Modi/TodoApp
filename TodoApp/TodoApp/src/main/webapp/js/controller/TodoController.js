myApp.controller('TodoController', function($scope, createNoteService,
		updateNoteService, deleteNoteService, refreshTokenService, $http,
		$state, $uibModal, fileReader, collbratorService, $timeout,
		profileImgUpdate) {
	/* showDividion */

	if (localStorage.getItem("accesstoken") == null) {
		console.log("inside the todoHome");
		$state.go("login");
	}
	$http({
		method : "get",
		url : 'http://localhost:8080/TodoApp/rest/getTodoList',
		headers : {
			'accToken' : localStorage.getItem("accesstoken")
		}
	}).then(function successCallback(data) {
		$scope.notes = data.data.list.reverse();
		$scope.userInfo = data.data.list[0];
		$scope.userImage = $scope.userInfo.profilleImage;
		// $scope.userImage = data.data.list[0].user.

		isPinnedCounted(data)
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
	$scope.reminder11 = false;
	$scope.bgColorNavbar = "rgb(255, 187, 0)";
	$scope.borderColorNavbar = "rgb(255, 187, 0)";

	$scope.imageSrc = null;
	$scope.showImage = false;

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
			archive : "false",
			image : data.image
		}

		updateNoteService.updateNote(obj).then(
				function successCallback(data) {

					$scope.success("Note is Trashed");
					console.log("sdgsd" + data.data.list);

					if (data.data.status == 1) {
						isPinnedCounted(data);
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
		console.log("Image is::" + $scope.imageSrc);
		if ($scope.imageSrc == null) {
			$scope.imageSrc = "";
		}

		if ($scope.description1 == undefined) {
			if ($scope.title1 == undefined) {
				return;
			}
		}

		var obj = {
			title : $scope.title1,
			description : $scope.description1,
			pinned : $scope.createPin,
			color : $scope.createColor,
			isTrash : "false",
			image : $scope.imageSrc,
			archive : "false"
		}

		$scope.title1 = "";
		$scope.description1 = "";

		if ((obj.title == "" || obj.description == "")
				&& (obj.description == undefined || obj.title == undefined)) {
			console.log("create note");
		} else {

			createNoteService.createNote(obj).then(
					function(data) {
						console.log("Inside the create note response"
								+ data.data);

						console.log(data.data.status === "-4");
						if (data.data.status === "-4") {
							console.log("Inside the data status");
							refreshTokenService.refreshToken(localStorage
									.getItem("refreshtoken"));
						}

						console.log(data.data.status === 1);
						if (data.data.status === 1) {

							isPinnedCounted(data);
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

	$scope.copyNote = function(x) {

		console.log("Inside the copy note");

		var obj = {
			title : x.title,
			description : x.description,
			pinned : x.pinned,
			color : x.color,
			isTrash : x.isTrash,
			image : x.image,
			archive : x.archive
		}

		$scope.title1 = "";
		$scope.description1 = "";

		if ((obj.title == "" || obj.description == "")
				&& (obj.description == undefined || obj.title == undefined)) {
			console.log("create note");
		} else {

			createNoteService.createNote(obj).then(
					function(data) {
						console.log("Inside the create note response"
								+ data.data);

						console.log(data.data.status === "-4");
						if (data.data.status === "-4") {
							console.log("Inside the data status");
							refreshTokenService.refreshToken(localStorage
									.getItem("refreshtoken"));
						}

						console.log(data.data.status === 1);
						if (data.data.status === 1) {

							isPinnedCounted(data);
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
				this.image = x.image;
				this.reminderTime = x.reminderTime;
				this.scrapers = x.scrapers;

				console.log("title" + this.title);
				console.log("description" + this.description);
				console.log("color" + this.color);
				console.log("color" + this.pinned);
				console.log("reminder::" + this.reminderTime);
				console.log("scrappers:::" + this.scrapers);

				this.colorChange = function(noteId, bgcolor) {

					console.log("inside the color change" + bgcolor);
					console.log("inside the color trash" + this.isTrash);
					console.log("inside the color archive" + this.archive);
					console.log("inside the color pinned" + this.pinned);
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
					obj.image = this.image;

					updateNoteService.updateNote(obj).then(
							function success(data) {
								console.log("update data success"
										+ data.data.list);
								isPinnedCounted(data);
								$scope.notes = data.data.list.reverse();
							});
				};

				this.copyNote = function() {

					console.log("Inside the copy note");

					var obj = {
						title : this.title,
						description : this.description,
						pinned : this.pinned,
						color : this.color,
						isTrash : this.isTrash,
						image : this.image,
						archive : this.archive
					}
					
					
					
					createNoteService.createNote(obj).then(
							function(data) {
								console.log("Inside the create note response"
										+ data.data);
								console.log(data.data.status === "-4");
								if (data.data.status === "-4") {
									console.log("Inside the data status");
									refreshTokenService
											.refreshToken(localStorage
													.getItem("refreshtoken"));
								}
									
								console.log(data.data.status === 1);
								if (data.data.status === 1) {

									$scope.notes = data.data.list;
									console.log(data.data.list);
								}

								$scope.createPin = "false";
								$scope.createColor = "#ff";
							});
				};

				this.updateData = function(noteId, color) {

					console.log("inside the update id:::" + this.color);

					var obj = {};

					obj.title = this.title;
					obj.pinned = this.pinned;
					obj.description = this.description;
					obj.color = this.color;
					obj.id = noteId;
					obj.isTrash = this.isTrash;
					obj.archive = this.archive;
					obj.image = this.image;

					console.log("update note data" + obj.title);
					console.log("update note data" + obj.description);
					$uibModalInstance.dismiss('Done');

					if (obj.title1 == "" && obj.description1 == "") {
						return;
					} else {
						updateNoteService.updateNote(obj).then(
								function success(data) {
									isPinnedCounted(data);
									console.log("update data success"
											+ data.data.list.reverse());
									$scope.notes = data.data.list;
								});
					}
				};

				this.addToArchive = function(noteId) {
					console.log("inside the update id:::", noteId);

					var obj = {};

					obj.title = this.title;
					obj.pinned = "false";
					obj.description = this.description;
					obj.color = this.color;
					obj.id = noteId;
					obj.isTrash = this.isTrash;
					obj.archive = "true";
					obj.image = this.image;

					console.log("update note data" + obj.title);
					console.log("update note data" + obj.description);
					$uibModalInstance.dismiss('Done');

					if (obj.title1 == "" && obj.description1 == "") {
						return;
					} else {
						updateNoteService.updateNote(obj).then(
								function success(data) {
									isPinnedCounted(data);
									$uibModalInstance.dismiss('Done');
									console.log("update data success"
											+ data.data.list.reverse());
									$scope.notes = data.data.list;
								});
					}
				};
				this.addToTrash = function(noteId) {
					console.log("inside the update id:::", noteId);

					var obj = {};

					obj.title = this.title;
					obj.pinned = "false";
					obj.description = this.description;
					obj.color = this.color;
					obj.id = noteId;
					obj.isTrash = "true";
					obj.archive = "false";
					obj.image = this.image;

					console.log("update note data" + obj.title);
					console.log("update note data" + obj.description);
					$uibModalInstance.dismiss('Done');

					if (obj.title1 == "" && obj.description1 == "") {
						return;
					} else {
						updateNoteService.updateNote(obj).then(
								function success(data) {
									isPinnedCounted(data);
									$uibModalInstance.dismiss('Done');
									console.log("update data success"
											+ data.data.list.reverse());
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
			archive : data.archive,
			reminderTime : data.reminderTime,
			image : data.image
		}

		updateNoteService.updateNote(obj).then(function success(data) {
			console.log("update data success" + data.data.list);
			$scope.notes = data.data.list.reverse();
			isPinnedCounted(data);
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
			archive : "false",
			reminderTime : obj.reminderTime,
			image : obj.image
		}
		console.log("update pinned" + obj);

		updateNoteService.updateNote(obj).then(function success(data) {
			console.log("update data success" + data.data.list);
			$scope.notes = data.data.list.reverse();
			isPinnedCounted(data);
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
						isPinnedCounted(data);
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
			archive : "true",
			reminderTime : data.reminderTime,
			image : data.image
		}

		updateNoteService.updateNote(obj).then(
				function successCallback(data) {

					console.log("sdgsd" + data.data.list);
					$scope.success("Archive Successfully");

					if (data.data.status == 1) {
						isPinnedCounted(data);
						$scope.notes = data.data.list.reverse();
					}

					if (data.data.status === "-4") {
						console.log("Inside the data status");
						refreshTokenService.refreshToken(localStorage
								.getItem("refreshtoken"));

					}

				});
	};

	$scope.addToArchive = function() {

		$scope.IsVisible = false;
		$scope.IsVisible1 = true;

		if ($scope.imageSrc == null) {
			$scope.imageSrc = "";
		}

		var todoNoteInfo = {
			title : $scope.title1,
			description : $scope.description1,
			pinned : "false",
			color : $scope.createColor,
			isTrash : "false",
			image : $scope.imageSrc,
			archive : "true"
		}

		createNoteService.createNote(todoNoteInfo).then(
				function(data) {
					console.log("Inside the create note response" + data.data);

					console.log(data.data.status === "-4");
					if (data.data.status === "-4") {
						console.log("Inside the data status");
						refreshTokenService.refreshToken(localStorage
								.getItem("refreshtoken"));
					}

					console.log(data.data.status === 1);
					if (data.data.status === 1) {

						isPinnedCounted(data);
						$("#presentationNote").html('');
						$("#presentationTitle").html("");
						$scope.notes = data.data.list;
						console.log(data.data.list);
					}

					$scope.createPin = "false";
					$scope.createColor = "#ff";
				});

	}

	$scope.restoreNote = function(data) {

		if (data.isTrash == "true")
			$scope.success("restore Successfully");
		if (data.archive == "true")
			$scope.success("UnArchive Successfully");

		var obj = {
			title : data.title,
			description : data.description,
			id : data.id,
			color : data.color,
			pinned : "false",
			isTrash : "false",
			archive : "false",
			reminderTime : null,
			image : data.image
		}

		updateNoteService.updateNote(obj).then(
				function successCallback(data) {

					console.log("sdgsd" + data.data.list);

					if (data.data.status == 1) {
						isPinnedCounted(data);
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

	$scope.createReminder = function(data, day) {

		$scope.day = day;

		var remDate = new Date();
		console.log("Miral Modi:::::" + day);
		if (day == "Today") {
			remDate.setHours(20, 0, 0);
			console.log("today is::" + remDate);
		} else if (day == "Tomorrow") {
			remDate.setDate(remDate.getDate() + 1);

			console.log("tomorrow is::" + remDate);
		} else if (day == "Next Week") {
			remDate.setDate(remDate.getDate() + 7);
			console.log("Next Week is::" + remDate);
		} else if (day == "null") {
			$scope.success("Reminder delete Successfully");
			remDate = null;
		}
		console.log("timer is::" + remDate);

		var remider = {
			title : data.title,
			description : data.description,
			id : data.id,
			color : data.color,
			pinned : data.pinned,
			isTrash : data.isTrash,
			archive : data.archive,
			reminderTime : remDate,
			image : data.image
		}
		updateNoteService.updateNote(remider).then(
				function successCallback(data) {

					console.log("sdgsd" + data.data.list);

					if (data.data.status == 1) {
						isPinnedCounted(data);
						$scope.notes = data.data.list.reverse();
					}

					if (data.data.status === "-4") {
						console.log("Inside the data status");
						refreshTokenService.refreshToken(localStorage
								.getItem("refreshtoken"));

					}

				});

	};

	/* facebook Sharing using javascript */

	$scope.facebookshare = function(data) {
		console.log("facebook share::" + data)
		data.description = data.description.replace("<br>", " ");
		data.description = data.description.replace("<div>", " ");
		data.description = data.description.replace("</div>", " ");
		console.log($("#presentationNote").val());

		FB.init({
			appId : '462155094166865',
			status : true,
			xfbml : true,
			version : 'v2.7'
		});

		FB.ui({
			method : 'share_open_graph',
			action_type : 'og.shares',
			action_properties : JSON.stringify({
				object : {
					'og:title' : data.title,
					'og:description' : data.description,
				}
			})
		});

	}
	$scope.addImage = function() {
		document.getElementById("my_Img").click();
		$scope.showImage = true;
	}

	function isPinnedCounted(data) {

		console.log("Inside the pinnedCounted");
		var countPinned = 0;
		var countOther = 0;
		for (var i = 0; i < data.data.list.length; i++) {
			if (data.data.list[i].pinned == "true"
					&& data.data.list[i].isTrash == "false"
					&& data.data.list[i].archive == "false") {
				countPinned++;
			} else {
				countOther++;
			}
		}

		if (countPinned != 0) {

			/*
			 * if (countOther == 0) { console.log("Inside the pinned and
			 * other"); $scope.pinnedCounted = true; $scope.otherCounted =
			 * false; return } else {
			 */
			$scope.pinnedCounted = true;
			$scope.otherCounted = true;
			/* } */
		} else {
			$scope.pinnedCounted = false;
			$scope.otherCounted = false;
		}
		return countPinned;
	}

	$scope.updateImagePopup = function() {
		console.log("Inside the Update image");

		var modalInstance = $uibModal.open({

			templateUrl : "template/uploadImage.html",
			controller : function($uibModalInstance) {
				console.log("inside the controller");
				this.myCroppedImage = '';
				// var that = this;
				this.profileImage = null;

				this.getProfileImage = function() {
					console.log("Inside the getProfile");
					$timeout(function() {
						document.getElementById("changeProfile").click();
					}, 100);
				};
				this.save = function() {
					var imageObj = {
						image : this.myCroppedImage
					}

					profileImgUpdate.imageUpload(imageObj).then(
							function successCallback(data) {
								$scope.userImage = data.data.image;
								if (data.status == 200) {
									console.log("Status is", data.status);
								}
							});
					$uibModalInstance.dismiss('Done');
				}

			},
			controllerAs : "$imageUpload"
		});
	}

	// Display pop up for updating
	$scope.collabratorPopup = function(x) {

		console.log("Title::" + x.title);
		console.log("description::" + x.description);
		$scope.updatNote = x;

		var modalInstance = $uibModal.open({

			templateUrl : "template/CollabratorPopup.html",
			controller : function($uibModalInstance) {

				console.log("inside the controller")
				var $collabrate = this;
				this.id = x.id;
				this.title = x.title;
				this.description = x.description;
				this.color = x.color;
				this.pinned = x.pinned;
				this.archive = x.archive;
				this.isTrash = x.isTrash;
				this.image = x.image;
				this.reminderTime = x.reminderTime;

				console.log("title" + this.title);
				console.log("description" + this.description);
				console.log("color" + this.color);
				console.log("color" + this.pinned);
				console.log("reminder::" + this.reminderTime);

				$collabrate.getEmailId = function() {
					var obj = {
						id : this.id,
						sharedEmail : $collabrate.getEmail
					}
					console.log("shared email data::", $collabrate.getEmail);

					collbratorService.sharedData(obj).then(
							function successCallback(data) {

								console.log("sdgsd" + data.data.list);

								if (data.data.status == 1) {
									isPinnedCounted(data);
									$scope.notes = data.data.list.reverse();
								}

								if (data.data.status === "-4") {
									console.log("Inside the data status");
									refreshTokenService
											.refreshToken(localStorage
													.getItem("refreshtoken"));
								}

							});
					$uibModalInstance.dismiss('Done');
				};
			},
			controllerAs : "$collabrate"
		});

	};

});
