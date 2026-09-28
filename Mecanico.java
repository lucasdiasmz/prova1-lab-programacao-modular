public class Mecanico {
    private String nome;
    private boolean vinculadoABox;

    public Mecanico(String nome, String cpf, String especialidade, String telefone) {
        this.nome = nome;
        this.vinculadoABox = false;
    }

    public String getNome() {
        return nome; 
    }
    
    public boolean isVinculadoABox() {
        return vinculadoABox; 
    }
    
    public void setVinculadoABox(boolean vinculadoABox) { 
        this.vinculadoABox = vinculadoABox; 
    }
}
