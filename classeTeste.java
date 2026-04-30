import java.util.ArrayList;
import java.util.List;

/**
 * Classe para testar o funcionamento do cadastro de produtos.
 */
public class CafeExpressoTeste {
    public static void main(String[] args) {
        // Criando uma lista para simular o cadastro de produtos (Cardápio)
        List<Produto> cardapio = new ArrayList<>();

        // RF01: Cadastrando produtos com nome e preço
        cardapio.add(new Produto("Café Expresso", 5.50));
        cardapio.add(new Produto("Cappuccino", 8.90));
        cardapio.add(new Produto("Pão de Queijo", 4.00));
        cardapio.add(new Produto("Fatia de Bolo", 7.50));

        // Exibindo os produtos cadastrados para conferência
        System.out.println("=== CADASTRO DE PRODUTOS (CARDÁPIO) ===");
        for (Produto p : cardapio) {
            System.out.println("Produto: " + p.getNome() + " | Preço: R$ " + p.getPrecoUnitario());
        }
        
        System.out.println("\nTotal de produtos cadastrados: " + cardapio.size());
    }
}