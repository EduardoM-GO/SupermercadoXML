/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercadoxml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import CRUD_SERVIDOR.Produto_CRUD;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo M
 */
public class LerXML {

    public void lerXML() {
        try {
            DocumentBuilderFactory docBuilFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilFact.newDocumentBuilder();

            Document documentoXML = docBuilder.parse("C:\\Users\\edrye\\Documents\\NetBeansProjects\\SupermercadoXML\\Servidor\\Produtos.xml");

            NodeList ListaProduto = documentoXML.getElementsByTagName("Produto");

            Produto_CRUD prod = new Produto_CRUD();

            for (int i = 0; i < ListaProduto.getLength(); i++) {

                Node NoProduto = ListaProduto.item(i);

                if (NoProduto.getNodeType() == Node.ELEMENT_NODE) {

                    Element ElementoProduto = (Element) NoProduto;

                    Produtos p = new Produtos();

                    p.setCodigo(Integer.parseInt(ElementoProduto.getAttribute("Codigo")));

                    NodeList ListaProdutoFilhos = ElementoProduto.getChildNodes();

                    for (int j = 0; j < ListaProdutoFilhos.getLength(); j++) {

                        Node NoProdutoFilhos = ListaProdutoFilhos.item(j);

                        if (NoProdutoFilhos.getNodeType() == Node.ELEMENT_NODE) {

                            Element Elementofilho = (Element) NoProdutoFilhos;

                            switch (Elementofilho.getTagName()) {
                                case "Nome":
                                    p.setDescricao(Elementofilho.getTextContent());

                                    break;
                                case "Quantidade":
                                    p.setQuantidade(Integer.parseInt(Elementofilho.getTextContent()));
                                    break;
                                case "Preco_Compra":
                                    p.setPrecoDeCompra(Double.parseDouble(Elementofilho.getTextContent()));
                                    break;
                                case "Preco_Venda":
                                    p.setPrecoDeVenda(Double.parseDouble(Elementofilho.getTextContent()));
                                    break;
                            }

                        }
                    }
                    prod.salvar(p);
                }
            }
            prod.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        JOptionPane.showMessageDialog(null, "Importado com sucesso!");

    }

}
