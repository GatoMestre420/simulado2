

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Arquivo {

    // Método para ler o arquivo de usuários
    public Map<Integer, String> lerUsuarios(String caminhoArquivo) {
        Map<Integer, String> usuarios = new HashMap<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = leitor.readLine();

            while (linha != null) {
                String[] partes = linha.split(",\\s*"); // Divide a linha por vírgula e espaço
                int id = Integer.parseInt(partes[0]);
                String nome = partes[1];
                usuarios.put(id, nome);
                linha = leitor.readLine();
            }
        } catch (IOException erro) {
            System.out.println("Houve um problema ao ler o arquivo de usuários.");
            System.out.println(erro.getMessage());
        }

        return usuarios;
    }

    // Método para ler o arquivo de dados
    public Map<Integer, Integer> lerDados(String caminhoArquivo) {
        Map<Integer, Integer> dados = new HashMap<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha = leitor.readLine();

            while (linha != null) {
                String[] partes = linha.split(",\\s*"); // Divide a linha por vírgula e espaço
                int id = Integer.parseInt(partes[0]);
                int idade = Integer.parseInt(partes[1]);
                dados.put(id, idade);
                linha = leitor.readLine();
            }
        } catch (IOException erro) {
            System.out.println("Houve um problema ao ler o arquivo de dados.");
            System.out.println(erro.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Formato inválido no arquivo de dados.");
            System.out.println(e.getMessage());
        }

        return dados;
    }

    // Método para relacionar e escrever os dados relacionados em um novo arquivo
    public void gerarArquivoRelacionados(String caminhoSaida, Map<Integer, String> usuarios, Map<Integer, Integer> dados) {
        try (FileWriter escritor = new FileWriter(caminhoSaida)) {
            for (Map.Entry<Integer, String> usuario : usuarios.entrySet()) {
                int id = usuario.getKey();
                String nome = usuario.getValue();
                Integer idade = dados.get(id);

                if (idade != null) {
                    escritor.write("ID: " + id + ", Nome: " + nome + ", Idade: " + idade + "\n");
                } else {
                    escritor.write("ID: " + id + ", Nome: " + nome + " - Dados adicionais não encontrados.\n");
                }
            }
            System.out.println("Arquivo gerado com sucesso: " + caminhoSaida);
        } catch (IOException erro) {
            System.out.println("Houve um problema ao gerar o arquivo de saída.");
            System.out.println(erro.getMessage());
        }
    }

    public static void main(String[] args) {
        Arquivo arquivo = new Arquivo();

        // Caminhos dos arquivos que você quer ler
        String caminhoUsuarios = "C:\\_ws\\code2\\Simulado\\Simulado_leo\\usuario.csv";
        String caminhoDados = "C:\\_ws\\code2\\Simulado\\Simulado_leo\\dados.csv";

        // Caminho do arquivo de saída
        String caminhoSaida = "C:\\_ws\\code2\\Simulado\\Simulado_leo\\relacionamento.csv";

        // Ler os arquivos e obter os dados
        Map<Integer, String> usuarios = arquivo.lerUsuarios(caminhoUsuarios);
        Map<Integer, Integer> dados = arquivo.lerDados(caminhoDados);

        // Gerar o arquivo com os dados relacionados
        arquivo.gerarArquivoRelacionados(caminhoSaida, usuarios, dados);
    }
}
