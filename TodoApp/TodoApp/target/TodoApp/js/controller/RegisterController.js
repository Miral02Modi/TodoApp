myApp.controller('registerCtrl',function($scope,registerService){
	console.log("Inside sdvnshio");
	 $scope.registerController = function() {
		 console.log("Inside sdvnshio");
		 var obj={
				email:$scope.email,
				password:$scope.password,
				phone:$scope.phone
		 	}
		 console.log(obj);
         var resp = registerService.userRegister(obj).then(function(data){
        	 console.log(data);
         });
      }
	
});