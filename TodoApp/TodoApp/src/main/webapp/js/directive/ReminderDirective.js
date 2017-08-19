myApp.directive("reminder", function() {
	console.log("inside the directive");
    return {
        restrict : "AE",
        templateUrl : "template/Reminder.html"
    };
});