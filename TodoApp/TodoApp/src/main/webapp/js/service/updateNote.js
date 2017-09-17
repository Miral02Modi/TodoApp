myApp.service('updateNoteService', function($http) {
	console.log("inside the service");

	console.log("update Note Service");

	this.updateNote = function(obj) {
		
		console.log("update Note Service::"+obj.pinned);
		return $http({
			method : "PUT",
			url : 'http://localhost:8080/TodoApp/rest/updateNotes',
			data : obj,
			headers : {
				'accToken' : localStorage.getItem("accesstoken")
			}
		});
	}

});
