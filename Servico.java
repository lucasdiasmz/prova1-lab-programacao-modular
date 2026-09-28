public class Servico {
    private String nome;
    private double valor;
    private String categoria;

    public Servico(String nome, double valor, String categoria) {
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
    }

    public String getNome() { 
        return nome; 
    }
    public double getValor() { 
        return valor; 
    }
    public String getCategoria() { 
        return categoria;
    }
}