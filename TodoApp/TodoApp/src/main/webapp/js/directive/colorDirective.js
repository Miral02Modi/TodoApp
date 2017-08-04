myApp.directive("colorbox", function() {
	console.log("inside the directive");
    return {
        restrict : "AE",
        templateUrl : "template/colorDiv.html"
    };
});