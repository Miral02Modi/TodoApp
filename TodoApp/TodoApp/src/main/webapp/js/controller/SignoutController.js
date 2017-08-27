myApp.controller('signoutContr', function($scope, $state) {
	localStorage.clear();
	console.log("inside the signout");
	$state.go("todoHome");
});