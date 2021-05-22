/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercadoxml;

import CRUD_CLIENTE.Produto_CRUD_Cliente;
import Servidor_Cliente.*;
import com.sun.security.ntlm.Client;

/**
 *
 * @author Eduardo M
 */
public class Teste {

    public static void main(String[] args) throws InterruptedException {
        Produto_CRUD_Cliente Prod = new Produto_CRUD_Cliente();
       /* Produtos p = new Produtos();
        if (Prod.max() == null) {
            p.setCodigo(1);
        } else {
            p.setCodigo(Prod.max().getCodigo() + 1);
        }
        p.setDescricao("Primeiro Produto");
        p.setPrecoDeCompra(10.6);
        p.setPrecoDeVenda(50.99);
        p.setQuantidade(5);
        Prod.salvar(p);
        Prod.close();*/
       GerarXml g = new GerarXml();
       g.CriarArquivoXMl(Prod.Lista());
       ServidorTCP ser = new ServidorTCP();
       
       ser.iniciarServidor();
       LerXML ler = new LerXML();
       ler.lerXML();
    }

}
