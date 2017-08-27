myApp.controller('trashController', [ '$scope', '$controller',
		function($scope, $controller, deleteNoteService) {
			$controller('TodoController', {
				$scope : $scope
			}), console.log("Trash Controller");

			$scope.notes11 = false;
			$scope.pinned11 = false;
			$scope.trash11 = true;
			$scope.archive11 = false;
			$scope.createNotes11 = false;
			
			$scope.bgColorNavbar = "rgb(99, 99, 99)";
			$scope.borderColorNavbar = "rgb(99, 99, 99)";
			
		} ]);