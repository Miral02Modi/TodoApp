var myApp = angular.module('todoApp', ['ui.router','ngSanitize']);


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
	}).state("todoHome",{
		url:"/todoHome",
		templateUrl:"template/TodoHome.html",
		controller : "showDivision"
	});	
	$urlRouterProvider.otherwise('/login');
	//console.log("inside the main");
});
