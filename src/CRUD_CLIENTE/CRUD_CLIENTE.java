package CRUD_CLIENTE;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author Eduardo M
 */
public abstract class CRUD_CLIENTE<T> {

    private EntityManager em;
    private Class<T> classe;

    public CRUD_CLIENTE(Class<T> classe) {
        this.classe = classe;
        if (em == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SupermercadoXMLPU_Cliente");
            em = emf.createEntityManager();
        }
    }

    public void salvar(T objeto) {
        try {
            em.getTransaction().begin();
            em.merge(objeto);
            em.flush();
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");

        } catch (Exception e) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro");
        }
    }

    public void excluir(T objeto) {
        try {
            em.getTransaction().begin();
            em.remove(objeto);
            em.flush();
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso");
        } catch (Exception e) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Ocorreu um erro");
        }
    }

    public T recuperar(int id) {
        return em.find(classe, id);
    }

    public List Lista() {

        return em.createQuery("From " + classe.getSimpleName() + " e order by e.codigo").getResultList();
    }

    public T max() {
        List<T> aux;
        aux = em.createQuery("From " + classe.getSimpleName() + " e order by e.codigo ").getResultList();
        if (aux.isEmpty() == true) {
            return null;
        }
        return aux.get(aux.size() - 1);

    }

    public void close() {
        em.close();
    }
}
