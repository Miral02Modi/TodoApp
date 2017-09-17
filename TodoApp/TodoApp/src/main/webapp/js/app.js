var myApp = angular.module('todoApp', ['ui.router','ngSanitize','ui.bootstrap','ngImgCrop']);

myApp.config(function($stateProvider, $urlRouterProvider) {
	
	console.log("inside the app js");
	$stateProvider.state("login", {
		url : "/login",
		templateUrl : "template/login.html",
		controller : "loginCtrl"
	}).state("forgetPassword",{
		url:"/forgetPassword",
		templateUrl:"template/ForgetPassword.html",
		controller : "fogetPassword"
	}).state("fbRedirect",{
		url:"/fbRedirect",
		templateUrl:"template/socialRedirect.html",
		controller : "socialRedirectCtrl"
	}).state("register",{
		url : "/register",
		templateUrl : "template/RegisterUser.html",
		controller : "registerCtrl" 
	}).state("todoHome",{
		url:"/todoHome",
		templateUrl:"template/TodoHome.html",
		controller : "TodoController"
	}).state("trash",{
		url:"/trash",
		templateUrl:"template/TodoHome.html",
		controller : "trashController"
	}).state("archive",{
		url:"/archive",
		templateUrl:"template/TodoHome.html",
		controller : "archiveController"
	}).state("reminder",{
		url:"/reminder",
		templateUrl:"template/TodoHome.html",
		controller : "reminderController"
	}).state("signout",{
		url:"signout",
		controller : "signoutContr"
	});	
	$urlRouterProvider.otherwise('/login');
});
