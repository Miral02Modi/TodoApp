myApp.controller('registerCtrl', function($scope, registerService,
		refreshTokenService) {

	$scope.phoneNumber = /^\+?\d{10}$/;

	console.log("Inside sdvnshio");
	$scope.registerController = function() {

		var obj = {
			name : $scope.name,
			email : $scope.email,
			password : $scope.password,
			phone : $scope.phone
		}

		/*
		 * var obj ={}; obj.name=$scope.name; obj.email=$scope.email;
		 * obj.password=$scope.password; obj.phone=$scope.phone;
		 */
		console.log(obj);
		var resp = registerService.userRegister(obj).then(
				function(data) {
					console.log("Inside the http response"+data.data);
					console.log(data.data.status === "-4");
					if (data.data.status === "-4") {
						console.log("Inside the data status");
						refreshTokenService.refreshToken(localStorage
								.getItem("refreshtoken"));
					}
				}, function errorCallback(data) {
					// called asynchronously if an error occurs
					// or server returns response with an error status.
				});
		console.log(resp);
	}

});