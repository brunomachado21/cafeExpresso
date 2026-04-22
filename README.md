# cafeExpresso
Projeto Café Expresso

Sistema: "Café Expresso"

O "Café Expresso" é um sistema de autoatendimento a ser implementado em uma cafeteria de pequeno porte. O objetivo é modernizar e agilizar o processo de realização de pedidos, permitindo que os próprios clientes montem seus pedidos em um totem (quiosque) digital e realizem o pagamento no mesmo local, sem precisar enfrentar a fila do caixa.
O sistema visa melhorar a experiência do cliente, reduzir o tempo de espera e minimizar erros de anotação dos pedidos. Ele terá duas interfaces principais: uma voltada para o Cliente no totem de autoatendimento e outra para o Atendente (no balcão/cozinha), que irá visualizar e gerenciar os pedidos que chegam.

Requisitos Funcionais Mínimos (RF)

● RF01: O sistema deve permitir o cadastro de produtos com nome e preço.
● RF02: O sistema deve permitir adicionar múltiplos itens a um pedido.
● RF03: O sistema deve calcular automaticamente o valor total do pedido.
● RF04: O sistema deve permitir a alteração do status do pedido (Pendente, Pago, Em Preparo, Finalizado).

O sistema deve permitir que o cardápio de produtos seja consultado, possibilitando a seleção de itens específicos em quantidades variadas. É fundamental que o software realize o cálculo automático do valor total, considerando a soma de todos os componentes do carrinho. Além disso, o sistema deve gerenciar o ciclo de vida do pedido, passando por estados de validação que impedem, por exemplo, que um pedido seja enviado para a preparação na cozinha sem que a confirmação de pagamento tenha sido registrada. O fluxo se encerra quando o atendente sinaliza que o pedido foi finalizado e entregue ao cliente.

Estrutura do Sistema e Objetivos das Classes

Abaixo segue a sugestão de implementação das principais classes do sistema.
Minha sugestão é que o sistema seja estruturado em três classes principais que interagem entre si para garantir a integridade dos dados e das regras de negócio:

A primeira classe é a Produto, cujo objetivo é representar os itens disponíveis no cardápio da cafeteria. Ela serve como a base de dados do sistema, definindo a identidade e o valor unitário de cada café ou acompanhamento. Sua integridade é essencial para que nenhum item com valor inválido ou nome ausente entre no fluxo de vendas.

A segunda classe, ItemPedido, atua como um elo intermediário e é responsável por gerenciar a relação entre um produto específico e a quantidade desejada pelo cliente. O objetivo desta classe é isolar a lógica de cálculo parcial (subtotal), permitindo que o sistema saiba exatamente quanto custa um agrupamento de itens antes mesmo de fecharmos a conta total. Ela garante que a multiplicação entre quantidade e preço unitário ocorra de forma isolada e precisa.

Por fim, a classe Pedido funciona como o núcleo do sistema, sendo responsável por consolidar todos os itens selecionados pelo cliente. Seu objetivo é gerenciar o "carrinho de compras", processando a soma de todos os subtotais para gerar o valor final a ser pago. Além da parte financeira, esta classe possui a responsabilidade crítica de controlar o fluxo de estados do atendimento. Ela deve garantir que o processo siga uma ordem lógica e segura, impedindo avanços indevidos (como pular do início diretamente para a entrega) e assegurando que cada etapa do autoatendimento seja respeitada conforme as regras da cafeteria.

Diagrama de classe do “Café Expresso”
Abaixo uma sugestão de diagrama de classe para implementação
