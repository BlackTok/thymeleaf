angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8081';

    $scope.pageNumber = 1;

    $scope.productsPage = function (pageNumber) {
            $http({
                url: contextPath + "/products",
                method: "GET",
                params: {
                    pageNumber: pageNumber
                }
            }).then(function (response) {
                $scope.ProductsList = response.data;
            });
        }

    $scope.deleteProduct = function (id) {
        $http({
            url: contextPath + "/products/delete",
            method: "GET",
            params: {
                id: id
            }
        }).then(function (response) {
            $scope.productsPage($scope.pageNumber);
        });
    }

    $scope.changeCost = function (id, delta) {
        $http({
            url: contextPath + "/products/change",
            method: "GET",
            params: {
                id: id,
                delta: delta
            }
        }).then(function (response) {
            $scope.productsPage($scope.pageNumber);
        });
    }

    $scope.addProduct = function (title, cost) {
        $http({
            url: contextPath + "/products/add",
            method: "GET",
            params: {
                title: title,
                cost: cost
            }
        }).then(function (response) {
            $scope.productsPage($scope.pageNumber);
        });
    }

    $scope.changePage = function (deltaPage) {
            if (deltaPage > 0)
                $scope.pageNumber = $scope.pageNumber + deltaPage;
            else if ($scope.pageNumber > 1)
                $scope.pageNumber = $scope.pageNumber + deltaPage;

            $scope.productsPage($scope.pageNumber);
        }

    $scope.productsPage($scope.pageNumber);

    console.log("111");
});