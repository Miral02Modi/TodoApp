myApp.controller('archiveController',['$scope','$controller',function($scope,$controller) 
{
	$controller('TodoController',{$scope: $scope}),
	console.log("Archive Controller");
	
	$scope.notes11=false;
	$scope.pinned11 = false;
	$scope.trash11 = false;
	$scope.archive11 = true;	
	$scope.createNotes11=false;
}]);