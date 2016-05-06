app.service('InventoryService', function ($http) {
        
    var typesOfInventory = [];
    
    this.init = function (sucess){
        $http.get('http://localhost:8080/InventarioWeb/inventory?action=list').success(function(result){
            typesOfInventory = eval(result);
            sucess(typesOfInventory);
        });
    }
    
    function find(inventory){
        for (i in typesOfInventory){
            if( typesOfInventory[i].id == inventory.id)
                return true;
        }
        return false;
    }

    this.save = function (inventory) {
        if (find(inventory) === false) {
            typesOfInventory.push(inventory);
            
            $http.post("http://localhost:8080/InventarioWeb/inventory", JSON.stringify(inventory)).success(function(data, status) {
                if(data == "OK"){
                    toastr.success("El tipo de inventorio fue agregado correctamente.");
                }
                console.log(data);
            });
            
        } else {
            for (i in typesOfInventory) {
                if (typesOfInventory[i].id == inventory.id) {
                    typesOfInventory[i] = inventory;
                }
            }
            delete inventory["$$hashKey"]
            $http.put("http://localhost:8080/InventarioWeb/inventory", JSON.stringify(inventory)).success(function(data, status) {
                if(data == "OK"){
                    toastr.success("El tipo de inventorio fue modificado correctamente.");
                }
                console.log(data);
            });
        }
    }

    this.get = function (id) {
        for (i in typesOfInventory) {
            if (typesOfInventory[i].id == id) {
                return typesOfInventory[i];
            }
        }
    }
    
    this.delete = function (inventory) {
        for (i in typesOfInventory) {
            if (typesOfInventory[i].id == inventory.id) {
                typesOfInventory.splice(i, 1);
                //typesOfInventory[id.id] = 0;
            }
        }
        delete inventory["$$hashKey"];
        $http.delete("http://localhost:8080/InventarioWeb/inventory?id="+inventory.id, inventory).success(function(data, status) {
            if(data == "OK"){
                toastr.success("El tipo de inventorio fue borrado correctamente.");
            }
            console.log(data);
        });
    }

    this.list = function () {
        return typesOfInventory;
    }
    
});
