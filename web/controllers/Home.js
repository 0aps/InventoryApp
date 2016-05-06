app.controller('HomeCtrl', function($scope, $location) {
	
  $scope.isActive = function(viewLocation){
  	return viewLocation === $location.path();
  }

    

  
  $( "#lista" ).fadeOut();
  $( "#lista" ).fadeIn();

});