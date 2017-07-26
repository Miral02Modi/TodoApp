myApp.service('loginService', function($http) {
	console.log("inside the service");

	this.userLogin = function(loginObj) {
		//console.log("inside the login1" + loginObj.email);
		return $http({
			method : "post",
			url : 'http://localhost:8080/TodoApp/login',
			data : loginObj
		});
	}

});
