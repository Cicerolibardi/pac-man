package Labirinto;

import Ator.*;

public class Labirinto implements ILabirinto{
    protected Sala[][] labirinto;
    int linhas;
    int colunas;

    public void montaLabirinto(int linhas, int colunas){
        labirinto = new Sala[linhas][colunas];
        for (int i = 0; i < linhas; i++){
            for (int j = 0; j < colunas; j++){
                labirinto[i][j] = new Sala();
            }
        }
        this.linhas = linhas;
        this.colunas = colunas;
    }

    //conecta os atores que não podem se movem à sala ij do labirinto, de acordo com a posição lida no CSV
    public void connect(IAtorEstatico ator, int i, int j){
        char caractere = ator.getChar();
        if (caractere == 'W')
            labirinto[i][j].setMuro(ator);
        else if (caractere == 'O')
            labirinto[i][j].setOuro(ator);
        else if (caractere == 'C')
            labirinto[i][j].setCereja(ator);
    }

    //conecta o Pacman à sala ij do labirinto, de acordo com a posição lida no CSV
    public void connect(IPacman ator, int i, int j){
        labirinto[i][j].setPacman(ator);
    }

    //conecta o Fantasma de índice idx à sala ij do labirinto, de acordo com a posição lida no CSV
    public void connect(IFantasma ator, int i, int j, int idx){
        labirinto[i][j].setFantasma(idx, ator);
    }

    //atualiza a condição da pastilha na sala[i][j] do labirinto.
    public void atualizaPastilha(int i, int j, boolean condicao){
        this.labirinto[i][j].setPastilha(condicao);
    }

    //retorna se há pastilha na sala[i][j] do labirinto.
    public boolean haPastilha(int i, int j){
        return labirinto[i][j].getPastilha();
    }

    /*
    Retorna uma matriz de String, onde cada String representa o que aparecerá na interface gráfica, de acordo
    com o que há na sala labirinto[i][j].
    OBS.: essa aparição segue a seguinte propriedade: Parede > Pacman > Fantasma > Cereja = Ouro > Pastilha
   */
    public String[][] labirintoToString(){
        String[][] matrizRetorno = new String[linhas][colunas];

        for (int i = 0; i < linhas; i++){
            for (int j = 0; j < colunas; j++){
                if (labirinto[i][j].getMuro() != null){
                    matrizRetorno[i][j] = "W";
                    continue;
                }
                else if (labirinto[i][j].getPacman() != null){
                    matrizRetorno[i][j] = "P";
                    continue;
                }
                else if (labirinto[i][j].getFantasma(0) != null){
                    matrizRetorno[i][j] = "F";
                    continue;
                }
                else if (labirinto[i][j].getFantasma(1) != null){
                    matrizRetorno[i][j] = "F";
                    continue;
                }
                else if (labirinto[i][j].getFantasma(2) != null){
                    matrizRetorno[i][j] = "F";
                    continue;
                }
                else if (labirinto[i][j].getFantasma(3) != null){
                    matrizRetorno[i][j] = "F";
                    continue;
                }
                else if (labirinto[i][j].getCereja() != null){
                    matrizRetorno[i][j] = "C";
                    continue;
                }
                else if (labirinto[i][j].getOuro() != null){
                    matrizRetorno[i][j] = "O";
                    continue;
                }
                else if (labirinto[i][j].getPastilha()){
                    matrizRetorno[i][j] = "p";
                    continue;
                }
                else{
                    matrizRetorno[i][j] = "-";
                    continue;
                }
            }
        }
        return matrizRetorno;
    }

}
