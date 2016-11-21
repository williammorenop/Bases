/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entities.Grupo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Historial;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author willi
 */
public class GrupoJpaController implements Serializable {

    public GrupoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grupo grupo) throws PreexistingEntityException, Exception {
        if (grupo.getHistorialList() == null) {
            grupo.setHistorialList(new ArrayList<Historial>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Historial> attachedHistorialList = new ArrayList<Historial>();
            for (Historial historialListHistorialToAttach : grupo.getHistorialList()) {
                historialListHistorialToAttach = em.getReference(historialListHistorialToAttach.getClass(), historialListHistorialToAttach.getHistorialPK());
                attachedHistorialList.add(historialListHistorialToAttach);
            }
            grupo.setHistorialList(attachedHistorialList);
            em.persist(grupo);
            for (Historial historialListHistorial : grupo.getHistorialList()) {
                Grupo oldGrupoOfHistorialListHistorial = historialListHistorial.getGrupo();
                historialListHistorial.setGrupo(grupo);
                historialListHistorial = em.merge(historialListHistorial);
                if (oldGrupoOfHistorialListHistorial != null) {
                    oldGrupoOfHistorialListHistorial.getHistorialList().remove(historialListHistorial);
                    oldGrupoOfHistorialListHistorial = em.merge(oldGrupoOfHistorialListHistorial);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findGrupo(grupo.getGrupoId()) != null) {
                throw new PreexistingEntityException("Grupo " + grupo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grupo grupo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo persistentGrupo = em.find(Grupo.class, grupo.getGrupoId());
            List<Historial> historialListOld = persistentGrupo.getHistorialList();
            List<Historial> historialListNew = grupo.getHistorialList();
            List<String> illegalOrphanMessages = null;
            for (Historial historialListOldHistorial : historialListOld) {
                if (!historialListNew.contains(historialListOldHistorial)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Historial " + historialListOldHistorial + " since its grupo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Historial> attachedHistorialListNew = new ArrayList<Historial>();
            for (Historial historialListNewHistorialToAttach : historialListNew) {
                historialListNewHistorialToAttach = em.getReference(historialListNewHistorialToAttach.getClass(), historialListNewHistorialToAttach.getHistorialPK());
                attachedHistorialListNew.add(historialListNewHistorialToAttach);
            }
            historialListNew = attachedHistorialListNew;
            grupo.setHistorialList(historialListNew);
            grupo = em.merge(grupo);
            for (Historial historialListNewHistorial : historialListNew) {
                if (!historialListOld.contains(historialListNewHistorial)) {
                    Grupo oldGrupoOfHistorialListNewHistorial = historialListNewHistorial.getGrupo();
                    historialListNewHistorial.setGrupo(grupo);
                    historialListNewHistorial = em.merge(historialListNewHistorial);
                    if (oldGrupoOfHistorialListNewHistorial != null && !oldGrupoOfHistorialListNewHistorial.equals(grupo)) {
                        oldGrupoOfHistorialListNewHistorial.getHistorialList().remove(historialListNewHistorial);
                        oldGrupoOfHistorialListNewHistorial = em.merge(oldGrupoOfHistorialListNewHistorial);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = grupo.getGrupoId();
                if (findGrupo(id) == null) {
                    throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Grupo grupo;
            try {
                grupo = em.getReference(Grupo.class, id);
                grupo.getGrupoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grupo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Historial> historialListOrphanCheck = grupo.getHistorialList();
            for (Historial historialListOrphanCheckHistorial : historialListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Grupo (" + grupo + ") cannot be destroyed since the Historial " + historialListOrphanCheckHistorial + " in its historialList field has a non-nullable grupo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(grupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Grupo> findGrupoEntities() {
        return findGrupoEntities(true, -1, -1);
    }

    public List<Grupo> findGrupoEntities(int maxResults, int firstResult) {
        return findGrupoEntities(false, maxResults, firstResult);
    }

    private List<Grupo> findGrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grupo.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Grupo findGrupo(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getGrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grupo> rt = cq.from(Grupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public int getMaxId()
    {
        EntityManager em = getEntityManager();
        Query query = em.createNativeQuery("SELECT MAX(grupo_id),MIN(grupo_id) FROM grupo");
        List<Object[]> results = query.getResultList();
        BigDecimal max = (BigDecimal) results.get(0)[0];
        
        return Integer.parseInt(max.toString());
    }
    
                            public List<String> elregresodelabestia(String nickname) {
                EntityManager em = getEntityManager();
                
                Query query;
        //query = em.createNativeQuery("SELECT grupo.nombre from grupo");
                query = em.createNativeQuery("select grupo.nombre, usuario.nombre " +
"from usuario inner join miembro on (usuario.NICK_NAME = miembro.USUARIO_NICK_NAME) inner join historial on (miembro.MIEMBRO_ID = historial.MIEMBRO_MIEMBRO_ID) inner join grupo on (historial.GRUPO_GRUPO_ID = grupo.GRUPO_ID) " +
"where usuario.NICK_NAME= ?");
                query.setParameter(1, nickname);
                List<String> results = new ArrayList<String>();
                List< Object[] > r = query.getResultList();
                                System.out.println(r.size());
           for (int i = 0 ; i < r.size() ; ++i ) {
          //  System.out.println("aaaa"+ r.get(i).length);
            
            String a=new String((String) r.get(i)[0]);
            results.add(a);
            //        System.out.println("a2a2a2a");

        }
                  System.out.println("controller.GrupoJpaController.pipiloco()");
                return results;
                
    }
}
