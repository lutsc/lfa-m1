package buscapadraoweb;

import buscaweb.CapturaRecursosWeb;
import java.util.ArrayList;

/**
 * @author Santiago
 */
public class Main {

    // busca char em vetor e retorna indice
    public static int get_char_ref (char[] vet, char ref ){
        for (int i=0; i<vet.length; i++ ){
            if (vet[i] == ref){
                return i;
            }
        }
        return -1;
    }

    // busca string em vetor e retorna indice
    public static int get_string_ref (String[] vet, String ref ){
        for (int i=0; i<vet.length; i++ ){
            if (vet[i].equals(ref)){
                return i;
            }
        }
        return -1;
    }    

    //retorna o próximo estado, dado o estado atual e o símbolo lido
    public static int proximo_estado(char[] alfabeto, int[][] matriz,int estado_atual,char simbolo){
        int simbol_indice = get_char_ref(alfabeto, simbolo);
        if (simbol_indice != -1){
            return matriz[estado_atual][simbol_indice];
        }else{
            return -1;
        }
    }

	// função auxiliar nas conexões pq precisa comparar muitas letras
    public static void liga_hex(int[][] matriz, char[] alfabeto, int estado_origem, int estado_destino){
        char[] alfabeto_hex = {
			'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','a','b','c','d','e','f'
		};
        for (char h : alfabeto_hex){
            matriz[estado_origem][get_char_ref(alfabeto, h)] = estado_destino;
        }
    }

    public static void main(String[] args) {
        //instancia e usa objeto que captura código-fonte de páginas Web
        CapturaRecursosWeb crw = new CapturaRecursosWeb();

        // crw.getListaRecursos().add("https://www.univali.br/");
        crw.getListaRecursos().add("https://arcus.readthedocs.io/en/stable/MacAddress.html");
		crw.getListaRecursos().add("https://unstop.com/blog/what-is-a-mac-address");
		crw.getListaRecursos().add("https://slts.osu.edu/articles/whats-a-mac-address-and-how-do-i-find-it/");
		
        ArrayList<String> listaCodigos = crw.carregarRecursos();

        //mapa do alfabeto
        char[] alfabeto = {
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'a', 'b', 'c', 'd', 'e', 'f', ':'
		};

        //mapa de estados
        String[] estados = {
			"q0", "q1", "q2", "q3", "q4", "q5", "q6", "q7", "q8", "q9", "q10", "q11", "q12", "q13", "q14", "q15", "q16", "q17" 
		};

        String estado_inicial = "q0";

        //estados finais
        String[] estados_finais = new String[1];
        estados_finais[0] = "q17";

        //tabela de transição de AFD para reconhecimento números de dois dígitos
        int[][] matriz = new int[estados.length][alfabeto.length];

		// preenche todas as transições como -1 antes de fazer as conexões
		for (int[] linha : matriz){
			java.util.Arrays.fill(linha, -1);
        }

		// q0 -hex-> q1 -hex-> q2
		liga_hex(matriz, alfabeto, get_string_ref(estados, "q0"), get_string_ref(estados, "q1"));
		liga_hex(matriz, alfabeto, get_string_ref(estados, "q1"), get_string_ref(estados, "q2"));

		// q2 -:-> q3
		matriz[get_string_ref(estados, "q2")][get_char_ref(alfabeto, ':')] = get_string_ref(estados, "q3");

		// q3 -hex-> q4 -hex-> q5
		liga_hex(matriz, alfabeto, get_string_ref(estados, "q3"), get_string_ref(estados, "q4"));
		liga_hex(matriz, alfabeto, get_string_ref(estados, "q4"), get_string_ref(estados, "q5"));

		// q5 -:-> q6
		matriz[get_string_ref(estados, "q5")][get_char_ref(alfabeto, ':')] = get_string_ref(estados, "q6");

		// q6 -hex-> q7 -hex-> q8
		liga_hex(matriz, alfabeto, get_string_ref(estados, "q6"), get_string_ref(estados, "q7"));
		liga_hex(matriz, alfabeto, get_string_ref(estados, "q7"), get_string_ref(estados, "q8"));

		// q8 -:-> q9
		matriz[get_string_ref(estados, "q8")][get_char_ref(alfabeto, ':')] = get_string_ref(estados, "q9");

		// q9 -hex-> q10 -hex-> q11
		liga_hex(matriz, alfabeto, get_string_ref(estados, "q9"), get_string_ref(estados, "q10"));
		liga_hex(matriz, alfabeto, get_string_ref(estados, "q10"), get_string_ref(estados, "q11"));

		// q11 -:-> q12
		matriz[get_string_ref(estados, "q11")][get_char_ref(alfabeto, ':')] = get_string_ref(estados, "q12");

		// q12 -hex-> q13 -hex-> q14
		liga_hex(matriz, alfabeto, get_string_ref(estados, "q12"), get_string_ref(estados, "q13"));
		liga_hex(matriz, alfabeto, get_string_ref(estados, "q13"), get_string_ref(estados, "q14"));

		// q14 -:-> q15
		matriz[get_string_ref(estados, "q14")][get_char_ref(alfabeto, ':')] = get_string_ref(estados, "q15");

		// q15 -hex-> q16 -hex-> q17
		liga_hex(matriz, alfabeto, get_string_ref(estados, "q15"), get_string_ref(estados, "q16"));
		liga_hex(matriz, alfabeto, get_string_ref(estados, "q16"), get_string_ref(estados, "q17"));

		// percorre cada link listado anteriormente
		for (int i = 0; i<listaCodigos.size(); i++){
	
			String codigoHTML = listaCodigos.get(i);
			System.out.println(crw.getListaRecursos().get(i));

			int estado = get_string_ref(estados, estado_inicial);
			int estado_anterior = -1;
			ArrayList<String> palavras_reconhecidas = new ArrayList<String>();

			String palavra = "";

			//varre o código-fonte de um código
			for (int j=0; j<codigoHTML.length(); j++){
				estado_anterior = estado;
				estado = proximo_estado(alfabeto, matriz, estado, codigoHTML.charAt(j));
				//se o não há transição
				if (estado == -1){
					//pega estado inicial
					estado = get_string_ref(estados, estado_inicial);
					// se o estado anterior foi um estado final
					if (get_string_ref(estados_finais, estados[estado_anterior]) != -1){
						//se a palavra não é vazia adiciona palavra reconhecida
						if (!palavra.equals("")){
							palavras_reconhecidas.add(palavra);
						}
						// se ao analisar este caracter não houve transição
						// teste-o novamente, considerando que o estado seja inicial
						j--;
					}
					//zera palavra
					palavra = "";
				}else{
					//se houver transição válida, adiciona caracter a palavra
					palavra += codigoHTML.charAt(j);
				}
			}

			//foreach no Java para exibir todas as palavras reconhecidas
			for (String p : palavras_reconhecidas){
				System.out.println(p);
			}
			System.out.println();
		}
	}
}
