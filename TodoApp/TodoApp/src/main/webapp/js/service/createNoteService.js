myApp.service('createNoteService', function($http) {
	console.log("inside the service");

	this.createNote = function(obj) {
		//console.log("inside the login1" + loginObj.email);
		return $http({
			method : "post",
			url : 'http://localhost:8080/TodoApp/createNote',
			data : obj,
			headers: { 
				'accToken': localStorage.getItem("accesstoken")
			}
		});
	}

});
