function x (valor, valorEsperado) {
 var assert = false;
    if (valor != valorEsperado) {
        assert = false;
        }
    if(valor === valorEsperado) {
       assert = true;
      }
      return assert;
}