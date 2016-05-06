app.controller('ProductCtrl', function($scope, $location, ProductService, InventoryService) {
	
  $scope.isActive = function(viewLocation){
  	return viewLocation === $location.path();
  };
  $scope.products = [];
  $scope.product = {};
  $scope.search = {};
  
  $scope.getInventoryValue = function(type){
      return $scope.typesOfInventories.filter(function(elem){ 
          if (elem.key == type){ return elem} })[0].value;
  }
  $scope.typesOfInventories = [];
  $scope.states = [{'id': 0, 'description' : 'Inactivo'}, 
                   {'id': 1, 'description' : 'Activo'}];

  ProductService.init(function(products){
      $scope.products = products; 
  });
  
  InventoryService.init(function(inventories){
      $scope.typesOfInventories = inventories.filter(function(elem){
          return elem.state === 1;
      });
  });
  
  $scope.saveProduct = function(){
    ProductService.save($scope.product);
    $scope.product = {};
  };
  
  $scope.editProduct = function(product){
    $scope.product = product;
    $('#modal-id').modal('toggle');
  };
  
  $scope.deleteProduct = function(product){
    ProductService.delete(product);
  };
  
  $( "#lista" ).fadeOut();
  $( "#lista" ).fadeIn();

});