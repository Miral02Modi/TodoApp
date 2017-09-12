myApp.controller('socialRedirectCtrl', function($scope,$location,$http,$state) {
	var tokenString = $location.search().tokenData;
	var data ={};
	data.token = tokenString;
	$http({
		method : "get",
		url : 'redirectUrl',
		data: data,
		headers : {
			'token' : tokenString
		}
	}).then(function successCallback(data) {
		
		
		if (data.status === 200) {
			console.log("Data is ",data.status);
			localStorage.setItem("accesstoken", data.data.accessToken);
			localStorage.setItem("refreshtoken", data.data.refreshToken);
			$state.go("todoHome");
		} 
	});
});