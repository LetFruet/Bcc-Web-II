package exemploJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CadastroCliente {
	public static void main(String... args) {
		  EntityManagerFactory entityManagerFactory 
		        = Persistence.createEntityManagerFactory("Clientes-PU");
		  EntityManager entityManager = entityManagerFactory.createEntityManager();
		     
		  // Estado novo
		  Cliente cliente = new Cliente();
		  cliente.setNome("Construtora Silva");
		 
		  entityManager.getTransaction().begin();
		 
		  // Estado gerenciado
		  entityManager.persist(cliente);
		 
		  // Estado desanexado (nenhuma operação será feita)
		  entityManager.detach(cliente);
		 
		  // Volta ao estado gerenciado 
		  cliente = entityManager.merge(cliente);
		 
		  // Estado removido (será removido da base de dados)
		  entityManager.remove(cliente);
		 
		  entityManager.getTransaction().commit();
		 
		  entityManager.close();
		  entityManagerFactory.close();
		}
}
