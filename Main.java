import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Importação necessária para ler o teclado
//RF01: O sistema deve permitir o cadastro de produtos com nome e preço.

class Produto {
    private String nome;
    private double precoUnitario;
    private int quantidade;

    public Produto(String nome, double precoUnitario, int quantidade) {
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public String getNome() { return nome; }
    public double getPrecoUnitario() { return precoUnitario; }
    public int getQuantidade() { return quantidade; }

    // Método para calcular o total deste item (Preço * Quantidade)
    public double getTotalItem() {
        return precoUnitario * quantidade;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        List<Produto> cardapio = new ArrayList<>();
        String continuar = "s";

        System.out.println("--- Sistema Café Expresso: Cadastro ---");

        while (continuar.equalsIgnoreCase("s")) {
            // Entrada do Nome
            System.out.print("\nDigite o nome do produto: ");
            String nome = leitor.nextLine();

            // Entrada do Preço
            System.out.print("Digite o preço unitário: ");
            double preco = leitor.nextDouble();

            // Entrada da Quantidade
            System.out.print("Digite a quantidade em estoque: ");
            int qtd = leitor.nextInt();

            // Criando o objeto com os dados digitados e guardando na lista
            cardapio.add(new Produto(nome, preco, qtd));

            // Limpeza de buffer (necessário após ler números)
            leitor.nextLine(); 

            System.out.print("Deseja cadastrar outro produto? (s/n): ");
            continuar = leitor.nextLine();
        }

        // Exibição do Relatório Final
        System.out.println("\n=== RESUMO DO ESTOQUE ===");
        double totalGeral = 0;

        for (Produto p : cardapio) {
            System.out.printf("Produto: %-15s | Qtd: %d | Preço: R$ %.2f | Subtotal: R$ %.2f%n", 
                p.getNome(), p.getQuantidade(), p.getPrecoUnitario(), p.getTotalItem());
            totalGeral += p.getTotalItem();
        }

        System.out.printf("\nVALOR TOTAL DO ESTOQUE: R$ %.2f%n", totalGeral);
        
        leitor.close(); // Fecha o "ouvido" do programa
    }
}