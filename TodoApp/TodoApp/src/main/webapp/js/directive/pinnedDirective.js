myApp.directive("pinneddirect", function() {
	console.log("inside the directive");
    return {
        restrict : "AE",
        templateUrl : "template/pinNotes.html"
    };
});