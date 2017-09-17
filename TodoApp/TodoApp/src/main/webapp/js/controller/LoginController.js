myApp.controller('loginCtrl', function($scope, loginService, $state,
		forgetPassService) {

	/*
	 * if(localStorage.getItem("accesstoken") != null){ $state.go("todoHome"); }
	 */

	$scope.loginController = function() {

		var obj = {
			email : $scope.email,
			password : $scope.password
		}

		var resp = loginService.userLogin(obj).then(function(data) {

			console.log(data);
			console.log(data.headers('accToken'));

			if (localStorage.getItem("accesstoken") == null) {
				$state.go("login");
			}

			if (data.status === 200) {
				localStorage.setItem("accesstoken", data.headers('accToken'));
				localStorage.setItem("refreshtoken", data.data.refreshToken);
				$state.go("todoHome");
			} else {
				$state.go("login");
			}

		});
	}

	$scope.forgetPassword = function() {
		console.log("Email id is:::"+$scope.email);
		
		var obj = {
			email : $scope.email
		}
		forgetPassService.sendEmailForVerifyPassword(obj).then(function(data) {
			console.log("Verify password");
		});

	}

	// console.log("Inside the login controller ended");
});
