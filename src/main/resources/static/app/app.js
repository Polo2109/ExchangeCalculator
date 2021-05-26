angular.module('app', ['ngResource'])
    .factory('ExchangeValues', function ($resource) {
        return $resource('/api/calculator');
    })
    .service('Exchange', function (ExchangeValues) {
        this.getAll = function () {
            return ExchangeValues.query();
        }
        this.calculate = function (exchangeValues) {
            return exchangeValues.$save();
        }
    })
    .controller('ExchangeController', function (ExchangeValues, Exchange) {
        var vm = this;
        vm.exchangeRates = Exchange.getAll();
        vm.defaultRate = 'AUD';
        vm.exchangeValues = new ExchangeValues();
        vm.exchangeValues.currency = vm.defaultRate;

        vm.calculate = () => {
            Exchange.calculate(vm.exchangeValues)
                .then(calculateCallback)
                .catch(errorCallback);
        }
        const calculateCallback = () => {
            vm.values = vm.exchangeValues;
            vm.exchangeValues = new ExchangeValues();
            vm.result = true;
            vm.msg = false;
            vm.exchangeValues.currency = vm.defaultRate;
        }
        const errorCallback = err => {
            vm.result = false;
            vm.msg = `Błąd obliczeń: ${err.data.message}`;
        }




    })