myApp.controller('fogetPassword', function($scope, $state,$location, forgetPassService) {

	/*
	 * if(localStorage.getItem("accesstoken") != null){ $state.go("todoHome"); }
	 */

	

	$scope.forgetPassword = function() {
		
		var obj = {
			password : $scope.fPassword
		}
		var tokenString = $location.search().Email;
		console.log(tokenString);
		var resp = forgetPassService.ForgetPassword(obj,tokenString).then(function(data) {
			console.log("Response is",data.response);
		});

	}
	// console.log("Inside the login controller ended");
});