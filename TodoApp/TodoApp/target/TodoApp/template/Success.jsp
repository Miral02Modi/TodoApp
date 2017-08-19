<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="http://bootstrapdocs.com/v3.1.1/docs/dist/css/bootstrap.min.css" />
<style>
:focus {
	outline: 2px solid #FF0000 !important;
}
</style>




<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.24/angular.min.js"></script>
<script
	src="http://bootstrapdocs.com/v3.1.1/docs/dist/js/bootstrap.min.js"></script>
<script
	src="http://rawgit.com/obogo/angular-focus-manager/master/build/angular-focusmanager.js"></script>


</head>
<body>

sdfgilSHOFHS
<!-- 	<div class="modal-bg">
		<div class="dialog">
			Large modal
			<button focus-index="1" class="btn btn-primary" data-toggle="modal"
				focus-element="autofocus" data-target=".bs-example-modal-lg">Large
				modal</button>

			<div class="modal fade bs-example-modal-lg" focus-group
				focus-group-head="loop" focus-group-tail="loop"
				focus-stacktabindex="-1" role="dialog"
				aria-labelledby="myLargeModalLabel" aria-hidden="true">
				
				
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Modal title</h4>
						</div>
						
						<div class="modal-body" contenteditable>
							eeeeeeee <br /> dddddddd eeeeeeeeeee<br /> rrrrrrrrrrrrr
							tttttttttttt<br /> yyyyyyyyyyyyy
						</div>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								focus-element="autofocus" data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary">Save
								changes</button>
						</div>
					</div>
				</div>
				
				
				
			</div>
		</div>
	</div>
	</div>
 -->

	<script>
		angular.module('app', [ 'fm' ]).controller('AppCtrl', function() {
			this.showModal = false;
			this.showView = false;
			this.counter = 1;
			this.toggleDialog = function() {
				this.showModal = !this.showModal;
			}
			this.toggleView = function() {
				this.showView = !this.showView;
			}
			this.changeDisplay = function() {
				this.counter++;
			}
		});

		angular.module('plunker', [ 'ui.bootstrap' ]);
		var ModalDemoCtrl = function($scope, $modal, $log) {

			$scope.items = [ 'item1', 'item2', 'item3' ];

			$scope.open = function() {

				var modalInstance = $modal.open({
					templateUrl : 'myModalContent.html',
					controller : ModalInstanceCtrl,
					resolve : {
						items : function() {
							return $scope.items;
						},
						selected : function() {
							return $scope.selected;
						}
					}
				});

				modalInstance.result.then(function(selectedItem) {
					$scope.selected = selectedItem;
				}, function() {
					$log.info('Modal dismissed at: ' + new Date());
				});
			};
		};

		var ModalInstanceCtrl = function($scope, $modalInstance, items,
				selected) {

			$scope.items = items;
			$scope.selected = {
				item : selected || items[0]
			};

			$scope.ok = function() {
				$modalInstance.close($scope.selected.item);
			};

			$scope.cancel = function() {
				$modalInstance.dismiss('cancel');
			};
		};
	</script>


</body>
</html>