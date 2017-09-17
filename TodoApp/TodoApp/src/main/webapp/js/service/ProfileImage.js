myApp.service('profileImgUpdate',function($http){
	
	this.imageUpload= function(obj){
		
		console.log("inside the image upload");
		return $http({
			method : "post",
			url : '/TodoApp/updateImage',
			data : obj,
			headers: {'accToken': localStorage.getItem("accesstoken")}
		});
	}
});