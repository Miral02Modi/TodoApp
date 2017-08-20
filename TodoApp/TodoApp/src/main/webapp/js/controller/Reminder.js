myApp.controller('reminderController',['$scope','$controller',function($scope,$controller) 
{
	$controller('TodoController',{$scope: $scope}),
	console.log("reminder Controller");
	
	$scope.notes11=false;
	$scope.pinned11 = false;
	$scope.trash11 = false;
	$scope.archive11 = false;	
	$scope.createNotes11=false;
	$scope.reminder11 =true;
}]);