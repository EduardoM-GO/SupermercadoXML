/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor_Cliente;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author Eduardo M
 */
public class ServidorTCP {

    public void iniciarServidor(){
        try {
            ServerSocket servidor = new ServerSocket(12345);
            System.out.println("Servidor ouvindo a porta 12345");
            ClienteTCP cli = new ClienteTCP();
            cli.iniciarCliente();
            while (true) {

                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());
                new ServidorTCP().ReceberArquivo("C:\\Users\\edrye\\Documents\\NetBeansProjects\\SupermercadoXML\\Servidor/", cliente);
                cliente.close();
                break;
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
        }
    }

    private void ReceberArquivo(String caminho, Socket cliente) throws IOException {

        try {
            InputStream in = cliente.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            String Destino = caminho + dis.readUTF();
            OutputStream out = new FileOutputStream(Destino);

            byte[] buffer = new byte[(int) dis.readLong()];
            int tamanho = 0;
            while ((tamanho = in.read(buffer)) > -1) {
                out.write(buffer, 0, tamanho);
            }
            System.out.println("Arquivo está em " +Destino);
            System.out.println("Recebido com sucesso");
            in.close();
            out.flush();
            out.close();
            dis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo não encontrado");
        }

    }
}
