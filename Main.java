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
                            Estoque estoque = new Estoque();
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n--- SISTEMA CAFE EXPRESSO ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Buscar Produto");
            System.out.println("3. Resumo do Estoque");
            System.out.println("4. Sair");
            System.out.print("Escolha: ");
            
            if (!leitor.hasNextInt()) {
                System.out.println("Opcao invalida!");
                leitor.nextLine();
                continue;
            }
            opcao = leitor.nextInt();
            leitor.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: "); String n = leitor.nextLine();
                    System.out.print("Preco: "); double p = leitor.nextDouble();
                    System.out.print("Qtd: "); int q = leitor.nextInt();
                    estoque.adicionarProduto(new Produto(n, p, q));
                    System.out.println("Cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.print("Nome do produto a buscar: ");
                    Produto buscado = estoque.buscarProduto(leitor.nextLine());
                    if (buscado != null) {
                        System.out.println("Encontrado: " + buscado.getNome() + " | Qtd: " + buscado.getQuantidade());
                    } else {
                        System.out.println("Produto nao existe no estoque.");
                    }
                    break;
                case 3:
                    System.out.println("\n=== RESUMO ===");
                    for (Produto prod : estoque.getProdutos()) {
                        System.out.printf("%s | Qtd: %d | Subtotal: R$ %.2f%n", prod.getNome(), prod.getQuantidade(), prod.getTotalItem());
                    }
                    System.out.printf("TOTAL: R$ %.2f%n", estoque.calcularTotalEstoque());
                    break;
                case 4:
                    System.out.println("A encerrar...");
                    break;
                default:
                    System.out.println("Opcao inexistente.");
            }
        }
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
