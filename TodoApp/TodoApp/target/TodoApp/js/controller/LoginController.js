myApp.controller('loginCtrl',function($scope,loginService){

	 $scope.loginController = function() {
		 var obj={
				email:$scope.email,
				password:$scope.password
		 	}
		 //console.log($scope.email);
		// console.log(obj); 	
		// console.log("inside the controller");
         var resp = loginService.userLogin(obj).then(function(data){
        	 console.log(data);
         });
      }
	
	//console.log("Inside the login controller ended");
});