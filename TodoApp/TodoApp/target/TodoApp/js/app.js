var myApp = angular.module('todoApp', ['ui.router']);


myApp.config(function($stateProvider, $urlRouterProvider) {
	
	console.log("inside the main");
	$stateProvider.state("login", {
		url : "/login",
		templateUrl : "template/login.html",
		controller : "loginCtrl"
	}).state("register",{
		url : "/register",
		templateUrl : "template/RegisterUser.html",
		controller : "registerCtrl" 
	});	
	$urlRouterProvider.otherwise('/login');
	//console.log("inside the main");
});
