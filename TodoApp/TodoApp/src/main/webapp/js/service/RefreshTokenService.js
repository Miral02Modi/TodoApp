myApp.service('refreshTokenService', function($http) {
	console.log("inside refreshToken the service");

	this.refreshToken = function(refreshToken) {
		console.log("Inside the refresh Token"+refreshToken);
		// console.log("inside the login1" + loginObj.email);
		return $http({
			method : "post",
			url : '/TodoApp/refreshToken',
			data : refreshToken,
		}).then(function mySuccess(data) {
			localStorage.setItem("accesstoken", data.headers('accToken'));
			localStorage.setItem("refreshtoken", data.data.refreshToken);
			console.log("Inside the my success"+data);
		});
	}

});
