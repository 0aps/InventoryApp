app.service('WareHouseService', function ($http) {
        
    var wareHouses = [];
    
    this.init = function (sucess){
        $http.get('http://localhost:8080/InventarioWeb/wareHouse?action=list').success(function(result){
            wareHouses = eval(result);
            sucess(wareHouses);
        });
    }
    
    function find(wareHouse){
        for (i in wareHouses){
            if( wareHouses[i].id == wareHouse.id)
                return true;
        }
        return false;
    }

    this.save = function (wareHouse) {
        if (find(wareHouse) === false) {
            wareHouses.push(wareHouse);
            
            $http.post("http://localhost:8080/InventarioWeb/wareHouse", JSON.stringify(wareHouse)).success(function(data, status) {
                if(data == "OK"){
                    toastr.success("El almacen fue agregado correctamente.");
                }
                console.log(data);
            });
            
        } else {
            for (i in wareHouses) {
                if (wareHouses[i].id == wareHouse.id) {
                    wareHouses[i] = wareHouse;
                }
            }
            delete wareHouse["$$hashKey"]
            $http.put("http://localhost:8080/InventarioWeb/wareHouse", JSON.stringify(wareHouse)).success(function(data, status) {
                if(data == "OK"){
                    toastr.success("El almacen fue modificado correctamente.");
                }
                console.log(data);
            });
        }
    }

    this.get = function (id) {
        for (i in wareHouses) {
            if (wareHouses[i].id == id) {
                return wareHouses[i];
            }
        }
    }
    
    this.delete = function (wareHouse) {
        for (i in wareHouses) {
            if (wareHouses[i].id == wareHouse.id) {
                wareHouses.splice(i, 1);
                //wareHouses[id.id] = 0;
            }
        }
        delete wareHouse["$$hashKey"];
        $http.delete("http://localhost:8080/InventarioWeb/wareHouse?id="+wareHouse.id, wareHouse).success(function(data, status) {
            if(data == "OK"){
                toastr.success("El almacen fue borrado correctamente.");
            }
            console.log(data);
        });
    }

    this.list = function () {
        return wareHouses;
    }
    
});
