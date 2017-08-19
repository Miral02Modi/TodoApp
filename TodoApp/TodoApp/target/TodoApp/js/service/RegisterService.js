myApp.service('registerService', function($http) {
	console.log("inside the service");

	this.userRegister = function(registerObj) {
		console.log("inside the login1" + registerObj);
		return $http({
			method : "post",
			url : '/TodoApp/register',
			data : registerObj,
			headers: {'accToken': localStorage.getItem("accesstoken")}
		});
	}

});
