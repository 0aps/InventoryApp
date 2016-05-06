app.controller('TransactionCtrl', function($scope, $location, TransactionService, ProductService, InventoryService) {
	
  $scope.isActive = function(viewLocation){
  	return viewLocation === $location.path();
  };
  $scope.transactions = [];
  $scope.transaction = {};
  $scope.asiento = {};
  
  TransactionService.init(function(transactions){
      $scope.transactions = transactions; 
  });
  
  TransactionService.getTypes(function(typesOfTransactions){
      $scope.typesOfTransactions = typesOfTransactions; 
  });    
  
  ProductService.init(function(articles){
      $scope.articles = articles; 
  });    
  
  InventoryService.init(function(typesOfInventories){
      $scope.typesOfInventories = typesOfInventories; 
  });    
 
  $scope.formatDate = function(date){
      dateObj = new Date(date);
      return dateObj.toString();
  }
  
  $scope.getAccountAmount = function(){
      debugger;
      transactionsAccounts = $scope.transactions.filter(function(transaction){
            return parseInt($scope.asiento.typeInventory.id) ==  transaction.idArticle.typeInventory.account;
      });
      amount = 0;
      
      for(transaction in transactionsAccounts){
          if(transactionsAccounts[transaction].type.description == "Entrada"){
              amount += transactionsAccounts[transaction].amount;
          }else{
              amount -= transactionsAccounts[transaction].amount;
          }
      }
      
      return amount;
  }
  
  
  $scope.saveProduct = function(){
    TransactionService.save($scope.transaction);
    $scope.transaction = {};
  };
  
  $scope.editProduct = function(transaction){
    $scope.transaction = transaction;
    $('#modal-id').modal('toggle');
  };
  
  $scope.deleteProduct = function(transaction){
    TransactionService.delete(transaction);
  };
  
  $scope.sendEntry = function(){
    TransactionService.sendEntry($scope.asiento);
  };
  
  $( "#listaT" ).fadeOut();
  $( "#listaT" ).fadeIn();
  
  $scope.typesOfMovement = [
      {id: 1, description : 'Debito'},
      {id: 2, description : 'Credito'}
  ];
  $scope.states = [{'id': 0, 'description' : 'Inactivo'}, 
                   {'id': 1, 'description' : 'Activo'}]; 

});