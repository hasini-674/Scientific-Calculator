
let display = document.getElementById('display');
        let memory = 0;
        let inverse = false;

        function addToDisplay(value) {
            display.value += value;
        }

        function clearDisplay() {
            display.value = '';
        }

        function calculate() {
            try {
                display.value = eval(display.value);
                memory = parseFloat(display.value);
            } catch (error) {
                display.value = 'Error';
            }
        }

        function trigFunction(func) {
            let value = parseFloat(display.value);
            if (inverse) {
                display.value = Math[`a${func}`](value);
            } else {
                display.value = Math[func](value);
            }
        }

        function mathFunction(func) {
            let value = parseFloat(display.value);
            display.value = Math[func](value);
        }

        function factorial() {
            let n = parseInt(display.value);
            let result = 1;
            for (let i = 2; i <= n; i++) {
                result *= i;
            }
            display.value = result;
        }

        function percentage() {
            display.value = parseFloat(display.value) / 100;
        }

        function toggleInverse() {
            inverse = !inverse;
        }

        function exp() {
            display.value += 'e';
        }

        function power() {
            display.value += '**';
        }

        function answer() {
            display.value += memory;
        }