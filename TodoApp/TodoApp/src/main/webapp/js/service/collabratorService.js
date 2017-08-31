myApp.service('collbratorService', function($http) {
	console.log("inside the service");

	this.sharedData = function(obj) {
		console.log("inside the collabrator ");
		return $http({
			method : "post",
			url : 'http://localhost:8080/TodoApp/collabrator',
			data : obj,
			headers: { 
				'accToken': localStorage.getItem("accesstoken")
			}
		});
	}

});
