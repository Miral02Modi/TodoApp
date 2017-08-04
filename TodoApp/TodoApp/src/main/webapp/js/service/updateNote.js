myApp.service('updateNoteService', function($http) {
	console.log("inside the service");

	console.log("update Note Service");

	this.updateNote = function(obj) {
		return $http({
			method : "PUT",
			url : 'http://localhost:8080/TodoApp/updateNotes',
			data : obj,
			headers : {
				'accToken' : localStorage.getItem("accesstoken")
			}
		});
	}

});
