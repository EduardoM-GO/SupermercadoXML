/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercadoxml;

import java.util.List;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Eduardo M
 */
public class GerarXml {

    public void CriarArquivoXMl(List<Produtos> prod) {
        try {
            DocumentBuilderFactory docBuilFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilFact.newDocumentBuilder();

            Document documentoXML = docBuilder.newDocument();

            Element supermercado = documentoXML.createElement("Supermercado");
            documentoXML.appendChild(supermercado);

            for (int i = 0; i < prod.size(); i++) {

                Element produto = documentoXML.createElement("Produto");
                Attr codigo = documentoXML.createAttribute("Codigo");

                codigo.setValue(String.valueOf(prod.get(i).getCodigo()));
                produto.setAttributeNode(codigo);

                supermercado.appendChild(produto);

                Element nome = documentoXML.createElement("Nome");
                nome.appendChild(documentoXML.createTextNode(prod.get(i).getDescricao()));
                produto.appendChild(nome);

                Element quantidade = documentoXML.createElement("Quantidade");
                quantidade.appendChild(documentoXML.createTextNode(String.valueOf(prod.get(i).getQuantidade())));
                produto.appendChild(quantidade);

                Element precoCompra = documentoXML.createElement("Preco_Compra");
                precoCompra.appendChild(documentoXML.createTextNode(String.valueOf(prod.get(i).getPrecoDeCompra())));
                produto.appendChild(precoCompra);

                Element precoVenda = documentoXML.createElement("Preco_Venda");
                precoVenda.appendChild(documentoXML.createTextNode(String.valueOf(prod.get(i).getPrecoDeVenda())));
                produto.appendChild(precoVenda);

            }
            TransformerFactory transFormFact = TransformerFactory.newInstance();
            Transformer transformer = transFormFact.newTransformer();

            DOMSource documentoFonte = new DOMSource(documentoXML);

            StreamResult documentFinal = new StreamResult("C:\\Users\\edrye\\Documents\\NetBeansProjects\\SupermercadoXML\\Cliente\\Produtos.xml");

            transformer.transform(documentoFonte, documentFinal);
        } catch (ParserConfigurationException | DOMException | TransformerException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        JOptionPane.showMessageDialog(null, "Exportado com sucesso!");

    }
}
