myApp.controller('loginCtrl', function($scope, loginService, $state) {

	$scope.loginController = function() {

		var obj = {
			email : $scope.email,
			password : $scope.password
		}

		var resp = loginService.userLogin(obj).then(function(data) {

			console.log(data);
			console.log(data.headers('accToken'));

			if (data.status === 200) {
				localStorage.setItem("accesstoken", data.headers('accToken'));
				localStorage.setItem("refreshtoken", data.data.refreshToken);
				$state.go("todoHome");
			} else {
				$state.go("login");
			}

		});

	}

	// console.log("Inside the login controller ended");
});