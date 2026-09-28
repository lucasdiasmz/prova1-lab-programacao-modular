import java.util.ArrayList;
import java.util.List;

public class Box {
    private int numero;
    private String tipoServicoPermitido;
    private int capacidadeMaxima;
    private Mecanico mecanicoResponsavel;
    private List<OrdemServico> ordensAtivas = new ArrayList<>();
    private int ordensFinalizadas = 0;

    public Box(int numero, String tipoServicoPermitido, int capacidadeMaxima) {
        this.numero = numero;
        this.tipoServicoPermitido = tipoServicoPermitido;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getNumero() { return numero; }
    public String getTipoServicoPermitido() { 
        return tipoServicoPermitido; 
    }
    public Mecanico getMecanicoResponsavel() { 
        return mecanicoResponsavel; 
    }
    public void setMecanicoResponsavel(Mecanico m) { 
        this.mecanicoResponsavel = m; 
    }
    public List<OrdemServico> getOrdensAtivas() { 
        return ordensAtivas; 
    }
    public int getOrdensFinalizadas() { 
        return ordensFinalizadas; 
    }

    public boolean adicionarOrdem(OrdemServico os) {
        if (ordensAtivas.size() < capacidadeMaxima) {
            ordensAtivas.add(os);
            return true;
        }
        return false;
    }

    public void removerOrdem(OrdemServico os) {
        if (ordensAtivas.remove(os)) ordensFinalizadas++;
    }
}