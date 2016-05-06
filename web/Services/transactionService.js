app.service('TransactionService', function ($http) {
        
    var transactions = [];
    
    this.init = function (sucess){
        $http.get('http://localhost:8080/InventarioWeb/transaction?action=list').success(function(result){
            transactions = eval(result);
            sucess(transactions);
        });
    }
    
    function find(transaction){
        for (i in transactions){
            if( transactions[i].id == transaction.id)
                return true;
        }
        return false;
    }

    this.save = function (transaction) {
        if (find(transaction) === false) {
            transactions.push(transaction);
            
            $http.post("http://localhost:8080/InventarioWeb/transaction", JSON.stringify(transaction)).success(function(data, status) {
                if(data == "OK"){
                    toastr.success("El transactiono fue agregado correctamente.");
                }
                console.log(data);
            });
            
        } else {
            for (i in transactions) {
                if (transactions[i].id == transaction.id) {
                    transactions[i] = transaction;
                }
            }
            delete transaction["$$hashKey"]
            $http.put("http://localhost:8080/InventarioWeb/transaction", JSON.stringify(transaction)).success(function(data, status) {
                if(data == "OK"){
                    toastr.success("El transactiono fue modificado correctamente.");
                }
                console.log(data);
            });
        }
    }

    this.get = function (id) {
        for (i in transactions) {
            if (transactions[i].id == id) {
                return transactions[i];
            }
        }
    }
    
    this.getTypes = function (sucess){
        $http.get('http://localhost:8080/InventarioWeb/transaction?action=listTypes').success(function(result){
            typesOfTransactions = eval(result);
            sucess(typesOfTransactions);
        });
    }
    
    this.delete = function (transaction) {
        for (i in transactions) {
            if (transactions[i].id == transaction.id) {
                transactions.splice(i, 1);
                //transactions[id.id] = 0;
            }
        }
        delete transaction["$$hashKey"];
        $http.delete("http://localhost:8080/InventarioWeb/transaction?id="+transaction.id, transaction).success(function(data, status) {
            if(data == "OK"){
                toastr.success("El transactiono fue borrado correctamente.");
            }
            console.log(data);
        });
    }

    this.list = function () {
        return transactions;
    }
    
    this.sendEntry = function(entry){
        //to do
//        $http.post("http://localhost:8080/InventarioWeb/transaction", JSON.stringify(transaction)).success(function(data, status) {
//                if(data == "OK"){
//                    toastr.success("Asiento enviado exitosamente.");
//                }
//                console.log(data);
//        });
    }
});
