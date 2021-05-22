/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor_Cliente;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Eduardo M
 */
public class ClienteTCP {

    public void iniciarCliente(){
        try {
            Socket cliente = new Socket("DESKTOP-TK8R334", 12345);
            new ClienteTCP().enviarArquivo("C:\\Users\\edrye\\Documents\\NetBeansProjects\\SupermercadoXML\\Cliente\\Produtos.xml", cliente);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void enviarArquivo(String caminho, Socket cliente) throws IOException {

        try {
           File Arquivo = new File(caminho);
           FileInputStream Fis = new FileInputStream(Arquivo);
           InputStream in = Fis;
           byte[] buffer = new byte[(int) Arquivo.length()];
           int tamanho = 0;
           OutputStream out = cliente.getOutputStream();
           
           DataOutputStream dos = new DataOutputStream(out);
           dos.writeUTF(Arquivo.getName());
           dos.writeLong(Arquivo.length());
           dos.flush();
           while((tamanho = in.read(buffer))> -1){
               out.write(buffer, 0, tamanho);
           }
           
           System.out.println("Arquivo " + Arquivo.getName());
           System.out.println("Enviado com successo ");
           in.close();
           dos.close();
           out.flush();
           out.close();       

        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não existe!");
        }
    }

}
