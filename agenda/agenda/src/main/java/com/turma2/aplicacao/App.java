package com.turma2.aplicacao;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.turma2.bean.Contato;
import com.turma2.bean.ContatoEscolar;
import com.turma2.bean.ContatoFamiliar;
import com.turma2.bean.Aniversario;
import com.turma2.bean.Email;
import com.turma2.bean.RedeSocial;
import com.turma2.bean.Usuario;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;

public class App {
    public static void main(String[] args) {

        try {
            // Criação de um objeto EntityManagerFactory
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("agenda");
            // Criação de um objeto EntityManager
            EntityManager em = emf.createEntityManager();
            Scanner leitura = new Scanner(System.in);
            // Lendo quantos contatos o usuário quer cadastrar
            CLS.limparTela();
            App aplicacao = new App();
            // Se o método usuarioEstaAutenticado é verdadeiro (true), o usuário pode
            // visualizar o menu
            if (aplicacao.usuarioEstaAutenticado(em, leitura))
                aplicacao.exibirMenu(em, leitura);
            else
                System.out.println("Sai para lá intruso....");
            em.close();
            emf.close();
            leitura.close();
        } catch (Exception erro) {
            System.out.println(erro);
        }

    }

    public void exibirMenu(EntityManager em, Scanner leitura) {
        Integer intOpcao;
        do {
            CLS.limparTela();
            System.out.println("O que você quer fazer?");
            System.out.println("1 - Cadastrar contatos.");
            System.out.println("2 - Buscar contato por ID.");
            System.out.println("3 - Buscar contato por nome.");
            System.out.println("4 - Listar todos os contatos.");
            System.out.println("5 - Sair.\n");
            intOpcao = leitura.nextInt();
            leitura.nextLine();
            switch (intOpcao) {
            case 1:
                this.cadastrarNovoContato(em, leitura);
                break;
            case 2:
                this.buscarContatoPorId(em, leitura);
                break;
            case 3:
                this.buscarContatoPorNome(em, leitura);
                break;
            case 4:
                this.listarTodosContatos(em, leitura);
                break;
            default:
                break;
            }
        } while (intOpcao >= 1 && intOpcao <= 4);
    }

    public void cadastrarNovoContato(EntityManager em, Scanner leitura) {
        CLS.limparTela();
        System.out.println("Quantos contatos você quer cadastrar?");
        int numContatos = leitura.nextInt();
        leitura.nextLine();
        em.getTransaction().begin();
        for (int i = 0; i < numContatos; i++) {
            CLS.limparTela();
            System.out.println("Nome: ");
            String nome = leitura.nextLine();
            System.out.println("Apelido: ");
            String apelido = leitura.nextLine();
            System.out.println("Telefone: ");
            String telefone = leitura.nextLine();
            System.out.println("Endereço: ");
            String endereco = leitura.nextLine();
            // Lendo dados sobre o aniversário
            System.out.println("Dia do Aniversário: ");
            Integer diaAniversario = leitura.nextInt();
            System.out.println("Mês do Aniversário: ");
            Integer mesAniversario = leitura.nextInt();
            System.out.println("Ano do Aniversário: ");
            Integer anoAniversario = leitura.nextInt();
            Aniversario objAniversario = new Aniversario(diaAniversario, mesAniversario, anoAniversario);
            leitura.nextLine();
            // Lendo dados sobre o email
            System.out.println("Email principal: ");
            String emailPrincipal = leitura.nextLine();
            System.out.println("Email secundário: ");
            String emailSecundario = leitura.nextLine();
            Email objEmail = new Email(emailPrincipal, emailSecundario);

            // Lendo se o contato é familiar ou escolar
            System.out.println("Digite F para Contato Familiar ou E para Contato Escolar: ");
            String tipoContato = leitura.nextLine();
            Contato objContato = null;
            if (tipoContato.equals("F")) {
                System.out.println("Grau de parentesco: ");
                String grauParentesco = leitura.nextLine();
                objContato = new ContatoFamiliar(null, nome, telefone, grauParentesco);
            } else {
                if (tipoContato.equals("E")) {
                    System.out.println("O contato é colega? (S - Sim | N Não)");
                    String ehColega = leitura.nextLine();
                    boolean behColega = false;
                    boolean behProfessor = false;
                    boolean behAmigo = false;
                    if (ehColega.equals("S"))
                        behColega = true;
                    System.out.println("O contato é professor? (S - Sim | N Não)");
                    String ehProfessor = leitura.nextLine();
                    if (ehProfessor.equals("S"))
                        behProfessor = true;
                    System.out.println("O contato é amigo? (S - Sim | N Não)");
                    String ehAmigo = leitura.nextLine();
                    if (ehAmigo.equals("S"))
                        behAmigo = true;
                    objContato = new ContatoEscolar(null, nome, telefone, behColega, behProfessor, behAmigo);
                } else {
                    System.out.println("Tipo de Contato equivocado, ele não será gravado.");
                }
            }
            objContato.setApelido(apelido);
            objContato.setEndereco(endereco);
            objContato.setAniversario(objAniversario);
            objContato.setEmail(objEmail);
            em.persist(objContato);

            // Lendo as redes sociais do contato
            String temRedeSocial;
            RedeSocial objRedeSocial = null;
            do {
                CLS.limparTela();
                System.out.println("Qual o nome da rede social do " + nome + "?");
                String nomeRedeSocial = leitura.nextLine();
                System.out.println("Qual o nome do perfil?");
                String nomePerfil = leitura.nextLine();
                objRedeSocial = new RedeSocial(null, nomeRedeSocial, nomePerfil, objContato);
                em.persist(objRedeSocial);
                System.out.println("O contato tem outra rede social? (S/N)");
                temRedeSocial = leitura.nextLine();
            } while (temRedeSocial.equals("S") || temRedeSocial.equals("s"));
            System.out.println("Contato cadastrado, tecle enter.");
            leitura.nextLine();
        }
        em.getTransaction().commit();
    }

    public void buscarContatoPorId(EntityManager em, Scanner leitura) {
        String resposta;
        do {
            CLS.limparTela();
            System.out.println("Qual o código do contato?");
            int idContato = leitura.nextInt();
            leitura.nextLine();
            // Buscando o contato na tabela através do id informado
            Contato objContato = em.find(Contato.class, idContato);

            if (objContato == null) {
                System.out.println("O contato não existe.");
            } else {
                System.out.println("Nome: " + objContato.getNome());
                System.out.println("Telefone: " + objContato.getTelefone());
                System.out.println("Email principal: " + objContato.getEmail().getEmailPrincipal());
                System.out.println("Aniversário: " + objContato.getAniversario().getAniversarioFormatado());

                if (objContato instanceof ContatoFamiliar) {
                    ContatoFamiliar objContatoFamiliar = (ContatoFamiliar) objContato;
                    System.out.println("Grau de Parentesco: " + objContatoFamiliar.getGrauParentesco());
                } else if (objContato instanceof ContatoEscolar) {
                    ContatoEscolar objContatoEscolar = (ContatoEscolar) objContato;
                    if (objContatoEscolar.getEhAmigo())
                        System.out.println("Amigo");
                    if (objContatoEscolar.getEhColega())
                        System.out.println("Colega");
                    if (objContatoEscolar.getEhProfessor())
                        System.out.println("Professor");
                }
            }
            System.out.println("Você quer buscar mais usuários? (S/N)");
            resposta = leitura.nextLine();
        } while (resposta.equals("S") || resposta.equals("s"));
    }

    public void buscarContatoPorNome(EntityManager em, Scanner leitura) {
        String resposta;
        do {
            CLS.limparTela();
            // Interagindo com o usuário
            System.out.println("Qual o nome do contato a ser encontrado?");
            String nomeContato = leitura.nextLine();
            // Buscando o contato na tabela através do nomeContato informado
            TypedQuery<Contato> consulta = em.createQuery("select c from Contato c where c.nome like (?1)",
                    Contato.class);
            List<Contato> listaContatos = consulta.setParameter(1, nomeContato).getResultList();
            // Imprimindo os dados dos objetos Contato
            if (listaContatos.isEmpty()) {
                System.out.println("O contato não existe.");
            } else {
                for (Contato objContato : listaContatos) {
                    System.out.println("Nome: " + objContato.getNome());
                    System.out.println("Telefone: " + objContato.getTelefone());
                    System.out.println("Email principal: " + objContato.getEmail().getEmailPrincipal());
                    System.out.println("Aniversário: " + objContato.getAniversario().getAniversarioFormatado());
                    if (objContato instanceof ContatoFamiliar) {
                        ContatoFamiliar objContatoFamiliar = (ContatoFamiliar) objContato;
                        System.out.println("Grau de Parentesco: " + objContatoFamiliar.getGrauParentesco());
                    } else if (objContato instanceof ContatoEscolar) {
                        ContatoEscolar objContatoEscolar = (ContatoEscolar) objContato;
                        if (objContatoEscolar.getEhAmigo())
                            System.out.println("Amigo");
                        if (objContatoEscolar.getEhColega())
                            System.out.println("Colega");
                        if (objContatoEscolar.getEhProfessor())
                            System.out.println("Professor");
                    }
                }
            }
            System.out.println("Você quer buscar mais usuários? (S/N)");
            resposta = leitura.nextLine();
        } while (resposta.equals("S") || resposta.equals("s"));
    }

    public void listarTodosContatos(EntityManager em, Scanner leitura) {
        CLS.limparTela();
        // Buscando todos os contatos na tabela tbl_contato com junção
        // às redes sociais
        TypedQuery<Contato> consulta = em.createQuery("select r.contato from RedeSocial r", Contato.class);
        List<Contato> listaContatos = consulta.getResultList();
        Integer idContato = -1;
        for (Contato objContato : listaContatos) {
            if (idContato != objContato.getId()) {
                System.out
                        .println(objContato.getId() + " | " + objContato.getNome() + " | " + objContato.getTelefone());
                for (RedeSocial redeSocial : objContato.getRedesSociais())
                    System.out.println(redeSocial.getRede() + " - " + redeSocial.getPerfil());
                System.out.println("\n");
                idContato = objContato.getId();
            }
        }
        System.out.println("Tecle enter para retornar ao menu.");
        leitura.nextLine();
    }

    public boolean usuarioEstaAutenticado(EntityManager em, Scanner leitura) {

        try {
            // Capturando os dados do usuário
            CLS.limparTela();
            System.out.println("********* LOGIN *********");
            System.out.println("E-mail: ");
            String email = leitura.nextLine();
            System.out.println("Senha: ");
            String senha = leitura.nextLine();
            // Convertendo a senha para MD5
            MessageDigest objMd = MessageDigest.getInstance("MD5");
            BigInteger hash = new BigInteger(1, objMd.digest(senha.getBytes()));
            senha = hash.toString(16);
            // Verificando se os dados inseridos pelo usuário combinam com o que está
            // registrado
            // na tabela tbl_contato, dentro do banco de dados.
            TypedQuery<Usuario> consulta = em
                    .createQuery("select u from Usuario u where u.email = (?1) and u.senha = (?2)", Usuario.class);
            List<Usuario> listaUsuarios = consulta.setParameter(1, email).setParameter(2, senha).getResultList();
            if (listaUsuarios.isEmpty())
                return false;
            else
                return true;
        } catch (Exception erro) {
            System.out.println(erro);
            return false;
        }
    }

}
