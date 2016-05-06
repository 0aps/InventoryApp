var app = angular.module('Inventory', ['ngRoute']);

  
app.config(function ($routeProvider) {

  $routeProvider
    .when('/Products', {
      controller:'ProductCtrl',
      templateUrl:'views/products.html'
    })
    .when('/Inventories', {
      controller:'InventoryCtrl',
      templateUrl:'views/invetories.html'
    })
    .when('/WareHouses', {
      controller:'WareHouseCtrl',
      templateUrl:'views/warehouses.html'
    })
    .when('/Transactions', {
      controller:'TransactionCtrl',
      templateUrl:'views/transactions.html'
    })
    .when('/About', {
      controller:'AboutCtrl',
      templateUrl:'views/about.html'
    });/*
    .otherwise({
      redirectTo:'/'
    });*/

});
