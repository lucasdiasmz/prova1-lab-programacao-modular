import java.util.*;

public class AtividadeAvaliativa1 {
    private static List<Mecanico> mecanicos = new ArrayList<>();
    private static List<Box> boxes = new ArrayList<>();
    private static List<OrdemServico> ordens = new ArrayList<>();
    private static Scanner s = new Scanner(System.in);
    private static int contadorOS = 1;

    public static void main(String[] args) {
        mecanicos.add(new Mecanico("Adalberto", "111.222.333-44", "Motor", "37998104441"));
        mecanicos.add(new Mecanico("Marcos", "222.333.444-55", "Suspensão", "319944402741"));
        mecanicos.add(new Mecanico("Jair", "333.444.555-66", "Elétrica", "32995122248"));

        boxes.add(new Box(1, "Motor", 2));
        boxes.add(new Box(2, "Suspensão", 2));
        boxes.add(new Box(3, "Elétrica", 1));

        int opt = -1;
        while (opt != 0) {
            System.out.println("\n1- Cadastrar Ordem de Serviço | 2- Vincular Mecânico | 3 - Atribuir/Finalizar Ordem de Serviço");
            System.out.println("4- Ordens Box   | 5- Qntd Ordens Finalizadas/Box   | 6- Busca Status | 7- Detalhes Ordem de Serviço | 0- Sair do Sistema");
            System.out.print("Opção: ");
            opt = Integer.parseInt(s.nextLine());

            switch (opt) {
                case 1 -> cadastrarOS();
                case 2 -> vincularMecanico();
                case 3 -> gerenciarOS();
                case 4 -> exibirOrdensBox();
                case 5 -> exibirFinalizadas();
                case 6 -> buscarPorStatus();
                case 7 -> buscarPorCodigo();
            }
        }
    }
    
    private static void cadastrarOS() {
        System.out.print("Cliente: "); String c = s.nextLine();
        System.out.print("Serviço: "); String serv = s.nextLine();
        System.out.print("Categoria: "); String cat = s.nextLine();
        System.out.print("Valor: "); double v = Double.parseDouble(s.nextLine());
        ordens.add(new OrdemServico(contadorOS++, c, new Servico(serv, v, cat)));
        System.out.println("OS cadastrada com sucesso!");
    }

    private static void vincularMecanico() {
        for (Mecanico m : mecanicos) {
            System.out.println(mecanicos.indexOf(m) + ". " + m.getNome() + (m.isVinculadoABox() ? " [Ocupado]" : ""));
        }
        System.out.print("Número do Mecânico: "); int mIdx = Integer.parseInt(s.nextLine());
        Mecanico m = mecanicos.get(mIdx);

        if (m.isVinculadoABox()) {
            System.out.println("Erro: Mecânico já possui box!");
            return;
        }

        Box b = buscarBox(pedirInt("Número do Box (1 a 3): "));
        if (b != null) {
            b.setMecanicoResponsavel(m);
            m.setVinculadoABox(true);
            System.out.println("Mecânico vinculado ao Box " + b.getNumero());
        }
    }

    private static void gerenciarOS() {
        OrdemServico os = buscarOS(pedirInt("Código da OS: "));
        if (os == null || os.getStatus() == StatusOrdem.FINALIZADA) {
            System.out.println("OS inválida ou já finalizada.");
            return;
        }

        int acao = pedirInt("1. Em Execução | 2. Finalizar: ");
        if (acao == 1) {
            Box b = buscarBox(pedirInt("Número do Box: "));
            if (b != null && b.getTipoServicoPermitido().equalsIgnoreCase(os.getServico().getCategoria()) && b.adicionarOrdem(os)) {
                os.setBoxAtribuido(b);
                os.setStatus(StatusOrdem.EM_EXECUCAO);
                System.out.println("Ordem de Serviço iniciada no Box " + b.getNumero());
            } else {
                System.out.println("Erro: Serviço incompatível ou Box cheio!!");
            }
        } else if (acao == 2) {
            if (os.getBoxAtribuido() != null) os.getBoxAtribuido().removerOrdem(os);
            os.setStatus(StatusOrdem.FINALIZADA);
            System.out.println("Ordem de Serviço finalizada.");
        }
    }

    private static void exibirOrdensBox() {
        Box b = buscarBox(pedirInt("Número do Box: "));
        if (b != null) {
            for (OrdemServico os : b.getOrdensAtivas()) os.exibir();
            System.out.println("Número total de ordens ativas: " + b.getOrdensAtivas().size());
        }
    }

    private static void exibirFinalizadas() {
        for (Box b : boxes) {
            System.out.println("Box " + b.getNumero() + ": " + b.getOrdensFinalizadas() + " finalizadas!");
        }
    }

    private static void buscarPorStatus() {
        int st = pedirInt("1. ABERTA | 2. EM_EXECUCAO | 3. FINALIZADA: ");
        StatusOrdem target = StatusOrdem.values()[st - 1];
        for (OrdemServico os : ordens) {
            if (os.getStatus() == target) os.exibir();
        }
    }

    private static void buscarPorCodigo() {
        OrdemServico os = buscarOS(pedirInt("Código da Ordem de Serviço escolhida: "));
        if (os != null) os.exibir();
    }

    private static Box buscarBox(int num) {
        for (Box b : boxes) if (b.getNumero() == num) return b;
        return null;
    }

    private static OrdemServico buscarOS(int cod) {
        for (OrdemServico os : ordens) if (os.getCodigo() == cod) return os;
        return null;
    }

    private static int pedirInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(s.nextLine());
    }
}
