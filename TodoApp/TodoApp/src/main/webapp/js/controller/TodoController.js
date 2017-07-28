myApp.controller('TodoController', function($scope, createNoteService,deleteNoteService,
		refreshTokenService, $http) {
		/*showDividion*/
	$http({
		method : "get",
		url : 'http://localhost:8080/TodoApp/getTodoList',
		headers : {
			'accToken' : localStorage.getItem("accesstoken")
		}
	}).then(function successCallback(data) {
		$scope.notes = data.data.list;
	});

	
	
	
	console.log("show division controller...");
	// This will hide the DIV by default.
	$scope.IsVisible = false;
	$scope.IsVisible1 = true;

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
		console.log("delete function..."+id);
		
		deleteNoteService.deleteNote(id).then(function successCallback(data) {
			
			console.log("sdgsd"+data.data.list);
			
			if(data.data.status == 1){
				$scope.notes = data.data.list.reverse();
			}	
				
			
			/*if (data.data.status === "-4") {
				console.log("Inside the data status");
				refreshTokenService.refreshToken(localStorage
						.getItem("refreshtoken"));
			}*/
			
		});
	};
	
	
	
	$scope.createNote = function() {

		$scope.IsVisible = false;
		$scope.IsVisible1 = true;

		var obj = {
			title : $scope.title1,
			description : $scope.description1
		}

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

	};

});