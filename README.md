Resposta do exercício 1: o uso de um padrão DTO foi necessário por que o objeto que precisávamos entregar
não pertencia ao domínio. Ou seja, era formado por uma série de cálculos e combinações de informações.
Sempre que precisamos devolver um objeto com propriedades customizadas em que essas irão originar de uma combinação de proriedades de um domínio, o padrão DTO é uma ótimo solução.