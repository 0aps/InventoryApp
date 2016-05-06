app.controller('WareHouseCtrl', function($scope, $location, WareHouseService) {
	
  $scope.isActive = function(viewLocation){
  	return viewLocation === $location.path();
  };
  $scope.wareHouses = [];
  $scope.wareHouse = {};
  $scope.states = [{'id': 0, 'description' : 'Inactivo'}, 
                   {'id': 1, 'description' : 'Activo'}];

  WareHouseService.init(function(wareHouses){
      $scope.wareHouses = wareHouses; 
  });
  
  $scope.saveProduct = function(){
    WareHouseService.save($scope.wareHouse);
    $scope.wareHouse = {};
  };
  
  $scope.editProduct = function(wareHouse){
    $scope.wareHouse = wareHouse;
    $('#modal-id').modal('toggle');
  };
  
  $scope.deleteProduct = function(wareHouse){
    WareHouseService.delete(wareHouse);
  };
  
  $( "#listaW" ).fadeOut();
  $( "#listaW" ).fadeIn();

});