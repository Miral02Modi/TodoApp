myApp.service('forgetPassService', function($http) {
	console.log("inside the service");

	console.log("Display Note Service");
	
	this.ForgetPassword = function(obj,tokenString) {
		console.log("inside the forget service::",tokenString);
		return $http({
			method : "post",
			url : 'http://localhost:8080/TodoApp/redirectForgetPassword?&forgetPasswordToken='+tokenString,
			data : obj,
		});
	}
	
	this.sendEmailForVerifyPassword = function(obj){
		console.log("inside the forget service::",obj);
		
		return $http({
			method : "post",
			url : 'http://localhost:8080/TodoApp/sendEmailForVerifyPassword',
			data : obj
		});
	}
});
