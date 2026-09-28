public class OrdemServico {
    private int codigo;
    private String cliente;
    private StatusOrdem status = StatusOrdem.ABERTA;
    private Servico servico;
    private Box boxAtribuido;

    public OrdemServico(int codigo, String cliente, Servico servico) {
    this.codigo = codigo;
    this.cliente = cliente;
    this.servico = servico;

    }

    public int getCodigo() { 
        return codigo;
    }
    public StatusOrdem getStatus() { 
        return status; 
    }
    public void setStatus(StatusOrdem status) {
        this.status = status; 
    }
    
    public Servico getServico() {   
        return servico; 
    }
    public Box getBoxAtribuido() { 
        return boxAtribuido; 
    }
    public void setBoxAtribuido(Box box) { 
        this.boxAtribuido = box; 
    }

    public void exibir() {
    System.out.println("OS: " + codigo + " | Cliente: " + cliente + " | Status: " + status +
    " | Box: " + (boxAtribuido != null ? boxAtribuido.getNumero() : "Nenhum") +
    " | Mecânico: " + (boxAtribuido != null && boxAtribuido.getMecanicoResponsavel() != null
    ? boxAtribuido.getMecanicoResponsavel().getNome() : "Nenhum"));
    }
}
