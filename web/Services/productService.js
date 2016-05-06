app.service('ProductService', function ($http) {
        
    var products = [];
    
    this.init = function (sucess){
        $http.get('http://localhost:8080/InventarioWeb/product?action=list').success(function(result){
            products = eval(result);
            sucess(products);
        });
    }
    
    function find(product){
        for (i in products){
            if( products[i].id == product.id)
                return true;
        }
        return false;
    }

    this.save = function (product) {
        if (find(product) === false) {
            products.push(product);
            
            $http.post("http://localhost:8080/InventarioWeb/product", JSON.stringify(product)).success(function(data, status) {
                if(data == "OK"){
                    toastr.success("El producto fue agregado correctamente.");
                }
                console.log(data);
            });
            
        } else {
            for (i in products) {
                if (products[i].id == product.id) {
                    products[i] = product;
                }
            }
            delete product["$$hashKey"]
            $http.put("http://localhost:8080/InventarioWeb/product", JSON.stringify(product)).success(function(data, status) {
                if(data == "OK"){
                    toastr.success("El producto fue modificado correctamente.");
                }
                console.log(data);
            });
        }
    }

    this.get = function (id) {
        for (i in products) {
            if (products[i].id == id) {
                return products[i];
            }
        }
    }
    
    this.delete = function (product) {
        for (i in products) {
            if (products[i].id == product.id) {
                products.splice(i, 1);
                //products[id.id] = 0;
            }
        }
        delete product["$$hashKey"];
        $http.delete("http://localhost:8080/InventarioWeb/product?id="+product.id, product).success(function(data, status) {
            if(data == "OK"){
                toastr.success("El producto fue borrado correctamente.");
            }
            console.log(data);
        });
    }

    this.list = function () {
        return products;
    }
    
});
