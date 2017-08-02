myApp.controller('TodoController', function($scope, createNoteService,
		updateNoteService, deleteNoteService, refreshTokenService, $http,
		$uibModal) {
	/* showDividion */
	$http({
		method : "get",
		url : 'http://localhost:8080/TodoApp/getTodoList',
		headers : {
			'accToken' : localStorage.getItem("accesstoken")
		}
	}).then(function successCallback(data) {
		$scope.notes = data.data.list.reverse();
	});

	console.log("show division controller...");
	// This will hide the DIV by default.
	$scope.IsVisible = false;
	$scope.IsVisible1 = true;
	
	$scope.listImage = false;
	$scope.gridImage = true;
	$scope.chnageView="col-lg-4 col-sm-9 col-md-4 col-xs-12";
	$scope.addSpace3="";
	
	
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

	$scope.deleteNote = function(id) {
		console.log("delete function..." + id);

		deleteNoteService.deleteNote(id).then(function successCallback(data) {

			console.log("sdgsd" + data.data.list);

			if (data.data.status == 1) {
				$scope.notes = data.data.list.reverse();
			}

			/*
			 * if (data.data.status === "-4") { console.log("Inside the data
			 * status"); refreshTokenService.refreshToken(localStorage
			 * .getItem("refreshtoken")); }
			 */

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
	 * $scope.notes = data.data.list; });
	 *  }
	 */

	$scope.createNote = function() {

		$scope.IsVisible = false;
		$scope.IsVisible1 = true;

		var obj = {
			title : $scope.title1,
			description : $scope.description1
		}

		if (obj.title == "" && obj.description == "") {
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

					});

		}
	};

	
	
	$scope.createNote = function() {

		$scope.IsVisible = false;
		$scope.IsVisible1 = true;

		var obj = {
			title : $scope.title1,
			description : $scope.description1
		}

		if (obj.title == "" && obj.description == "") {
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

							$scope.notes = data.data.list;
							console.log(data.data.list);
						}

					});

		}
	};

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

				console.log("Id" + this.title);
				console.log("Id" + this.description);

				this.updateData = function(noteId) {

					console.log("inside the update id:::" + noteId);
					$uibModalInstance.dismiss('Done');

					var obj = {
						title : this.title1,
						description : this.description1,
						id : noteId
					}

					console.log("update note data" + obj.title);
					console.log("update note data" + obj.description);

					if (obj.title1 == "" && obj.description1 == "") {
						return;
					} else {
						updateNoteService.updateNote(obj).then(
								function success() {
									console.log("update data success");
								});
					}
				};
			},
			controllerAs : "$update"
		});

	};

	$scope.updateNotes = function(noteId) {
		var obj = {
			title : $scope.updateTitle,
			description : $scope.updateDescription,
			id : noteId
		}

		console.log("Data is" + obj);

		/*
		 * if(obj.title === "" || obj.description === ""){ console.log("inside
		 * empty data"+obj.id); obj.title = ""; obj.description = "";
		 * console.log("inside empty data"+obj.title); console.log("inside empty
		 * data"+obj.description); return; }
		 */

	};

	$scope.chngBackgroundOfSearch = function() {
		$scope.myObj = {
			"background-color" : "white"
		}
	}
	
	

	$scope.hideTitle = function(){
		console.log("inside the on blur");
		$scope.IsVisible1=true;
		$scope.IsVisible=false;
	};
	
	
	$scope.showGridImage = function() {
		console.log("inside the grid");
		$scope.listImage = false;
		$scope.gridImage = true;
		$scope.chnageView="col-lg-4 col-sm-9 col-md-4 col-xs-12";
		$scope.addSpace3="";
	}
	

	$scope.showListImage = function() {
		console.log("inside the list");
		$scope.gridImage = false;
		$scope.listImage = true;
		$scope.chnageView="col-sm-12 col-lg-8 col-xs-12 col-md-5";
		$scope.addSpace3="col-lg-3";
	}
	
	$scope.refresh = function() {
		console.log("inside the refresh");
		 window.location.reload(); 
	}
});
