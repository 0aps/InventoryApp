app.controller('InventoryCtrl', function($scope, $location, InventoryService) {
	
  $scope.isActive = function(viewLocation){
  	return viewLocation === $location.path();
  };
  $scope.typesOfInventory = [];
  $scope.inventory = {};
  $scope.search = {};
  
  $scope.states = [{'id': 0, 'description' : 'Inactivo'}, 
                   {'id': 1, 'description' : 'Activo'}];

  InventoryService.init(function(typesOfInventory){
      $scope.typesOfInventory = typesOfInventory;
  });
  
  $scope.saveProduct = function(){
    InventoryService.save($scope.inventory);
    $scope.inventory = {};
  };
  
  $scope.editProduct = function(inventory){
    $scope.inventory = inventory;
    $('#modal-id').modal('toggle');
  };
  
  $scope.deleteProduct = function(inventory){
    InventoryService.delete(inventory);
  };
  
  $( "#listaI" ).fadeOut();
  $( "#listaI" ).fadeIn();

});