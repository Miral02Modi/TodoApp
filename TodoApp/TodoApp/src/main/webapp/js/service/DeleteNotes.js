myApp.service('deleteNoteService', function($http) {
	console.log("inside the service");

	console.log("Display Note Service");

	this.deleteNote = function(id) {
		console.log("inside the login1" + id);
		return $http({
			method : "post",
			url : 'http://localhost:8080/TodoApp/rest/deleteNotes',
			data : id,
			headers : {
				'accToken' : localStorage.getItem("accesstoken")
			}
		});
	}

});
