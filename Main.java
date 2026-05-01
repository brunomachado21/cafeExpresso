import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 1. ENTIDADE (Misturada no arquivo)
class Produto {
    private String nome;
    private double precoUnitario;
    private int quantidade;

    public Produto(String nome, double precoUnitario, int quantidade) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome inválido");
        if (precoUnitario < 0) throw new IllegalArgumentException("Preço inválido");
        if (quantidade < 0) throw new IllegalArgumentException("Quantidade inválida");
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
    }

    public String getNome() { return nome; }
    public double getPrecoUnitario() { return precoUnitario; }
    public int getQuantidade() { return quantidade; }
    private double percentagemDesconto = 0.0;

    public void aplicarDesconto(double percentagem) {
        if (percentagem >= 0 && percentagem <= 100) {
            this.percentagemDesconto = percentagem;
        }
    }

    public double getTotalItem() { 
        double totalBruto = precoUnitario * quantidade;
        return totalBruto - (totalBruto * (percentagemDesconto / 100)); 
    }
}

// 2. REGRA DE NEGÓCIO (Acoplada)
class Estoque {
    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        if (produto != null) produtos.add(produto);
    }

    public List<Produto> getProdutos() { return new ArrayList<>(produtos); }

    public double calcularTotalEstoque() {
        double total = 0;
        for (Produto p : produtos) total += p.getTotalItem();
        return total;
    }
}
    public Produto buscarProduto(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) return p;
        }
        return null;
    }


// 3. INTERFACE E "TESTE" MANUAL (O problema real)
public class Main {
    public static void main(String[] args) {
        // Gambiarra para tentar mostrar ao professor que você "testou"
        System.out.println("--- EXECUTANDO TESTES INTERNOS ---");
        rodarTestesUnitariosFalsos();
        System.out.println("----------------------------------\n");

        Scanner leitor = new Scanner(System.in);
                    Estoque testeBusca = new Estoque();
            testeBusca.adicionarProduto(new Produto("Agua", 2.0, 5));
            if (testeBusca.buscarProduto("Agua") != null && testeBusca.buscarProduto("Inexistente") == null) {
                System.out.println("[PASSOU] Busca de produto.");
            } else {
                System.out.println("[FALHOU] Busca de produto.");
            }
            Produto pDesconto = new Produto("Cafe", 10.0, 2);
            pDesconto.aplicarDesconto(10.0); // 10% de desconto em 20.0 = 18.0
            if (pDesconto.getTotalItem() == 18.0) {
                System.out.println("[PASSOU] Calculo de desconto no item.");
            } else {
                System.out.println("[FALHOU] Calculo de desconto no item.");
            }


        String continuar = "s";

        System.out.println("--- Sistema Café Expresso: Cadastro ---");

        while (continuar.equalsIgnoreCase("s")) {
            System.out.print("\nDigite o nome do produto: ");
            String nome = leitor.nextLine();

            System.out.print("Digite o preço unitário: ");
            double preco = leitor.nextDouble();

            System.out.print("Digite a quantidade em estoque: ");
            int qtd = leitor.nextInt();

            estoque.adicionarProduto(new Produto(nome, preco, qtd));

            leitor.nextLine(); 
            System.out.print("Deseja cadastrar outro produto? (s/n): ");
            continuar = leitor.nextLine();
        }

        System.out.println("\n=== RESUMO DO ESTOQUE ===");
        for (Produto p : estoque.getProdutos()) {
            System.out.printf("Produto: %-15s | Qtd: %d | Preço: R$ %.2f | Subtotal: R$ %.2f%n", 
                p.getNome(), p.getQuantidade(), p.getPrecoUnitario(), p.getTotalItem());
        }

        System.out.printf("\nVALOR TOTAL DO ESTOQUE: R$ %.2f%n", estoque.calcularTotalEstoque());
        leitor.close();
    }

    // Tentar simular o JUnit porque tudo está em um arquivo só
    private static void rodarTestesUnitariosFalsos() {
        try {
            Produto p = new Produto("Teste", 10.0, 2);
            if (p.getTotalItem() == 20.0) {
                System.out.println("[PASSOU] Cálculo de subtotal do produto.");
            } else {
                System.out.println("[FALHOU] Cálculo de subtotal do produto.");
            }

            Estoque e = new Estoque();
            e.adicionarProduto(new Produto("A", 5.0, 2));
            e.adicionarProduto(new Produto("B", 10.0, 1));
            if (e.calcularTotalEstoque() == 20.0) {
                System.out.println("[PASSOU] Cálculo total do estoque.");
            } else {
                System.out.println("[FALHOU] Cálculo total do estoque.");
            }
        } catch (Exception ex) {
            System.out.println("[ERRO CRÍTICO NOS TESTES] " + ex.getMessage());
        }
    }
}
