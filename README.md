# CYK Algorithm.
## Roberto Cinos Vega, grupo 102, ISI + ADE
### roberto.cinosvega@usp.ceu.es

La práctica desarrollada consiste en la aplicación del algoritmo de comprobación CYK el cual es capaz de comprobar si una palabra dada pertenece al lenguaje definido por una gramática que previamente ha de ser añadida. Es importante añadir que para que este funcione las gramáticas deben ser independientes del contexto y encontrarse en la forma normal de Chomsky. La aplicación además imprime la tabla utilizada para cumplir su función de forma que el resultado puede verse.

Para la utilización de la aplicación deben definirse tests donde se especifique una gramática válida, es decir habiendo definido sus elementos terminales y no terminales previamente, su axioma y añadiendo las producciones que solo pueden estar compuestas de estos elementos ya agregados. Además de la gramática el test debe pasar como parámetro la palabra que queremos comprobar al método isDerived(), devolviendo un objeto del tipo booleano con la solución y una tabla materializando el resultado.

El algoritmo determina si una palabra es correcta si tras pasar este su axioma aparece en la última posición de la tabla.

## Instalación:
```

git clone https://github.com/roberccv/cyk-rcinosv.git

```
## Licencia

  

[Apache](https://www.apache.org/licenses/LICENSE-2.0)

  

![Licencia](https://licensebuttons.net/l/by/3.0/88x31.png "Licencia")
