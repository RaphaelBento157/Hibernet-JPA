package aplicacao;



import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {
	
		String nome=null;
		String email;
		int caso=0;
		int id;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("escolha uma opção a seguir\n 1 - cadastrar usuario\n 2 - procurar usuario\n 3 - deletar usuario");
		caso=sc.nextInt();
		sc.nextLine();
		switch(caso) {
		
		case 1:
			System.out.println("insira o nome :");
			nome=sc.nextLine();
			System.out.println("insira seu email:");
			email=sc.nextLine();
			sc.close();
			
			/*essa parte persiste as informações no banco de dados*/
			/*começa aqui*/
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
			EntityManager em = emf.createEntityManager();
			Pessoa p =new Pessoa(0,nome,email);
			/*inicia atransação para persistir os dados*/
			em.getTransaction().begin();
			em.persist(p);/*Objeto a ser persistido*/
			em.getTransaction().commit();
			/*e termina aqui*/
			System.out.println("Pronto");
			em.close();
			emf.close();
			break;
			
		case 2:
		
			System.out.println("insira o id que desejar achar :");
			id=sc.nextInt();
			
			sc.close();
			Pessoa P =new Pessoa(id,null,null);
			/*essa parte procura pelo Id no banco de dados*/
			/*começa aqui*/
			EntityManagerFactory EMF = Persistence.createEntityManagerFactory("exemplo-jpa");
			EntityManager EM = EMF.createEntityManager();
			
			Pessoa P2 =EM.find(Pessoa.class, id);
			/*verifica se Id existe no banco de dados*/
			if(P2==null) {
				System.out.println("Id informado não existe");
			}
			else {
			System.out.println(P2);
			}
			/*e termina aqui*/
			System.out.println("Pronto");
			EM.close();
			EMF.close();
			break;
			
		case 3:
			/*Apartir daqui o código deleta o usuario do banco de dados*/
			System.out.println("Insira o Id que deseja deletar");
			id=sc.nextInt();
			/*começa aqui*/
			EntityManagerFactory Emf = Persistence.createEntityManagerFactory("exemplo-jpa");
			EntityManager Em = Emf.createEntityManager();
			
			Pessoa P3 = Em.find(Pessoa.class, id);
			Em.getTransaction().begin();
			Em.remove(P3);
			Em.getTransaction().commit();
			
			System.out.println("Pronto");
			Em.close();
			Emf.close();
			/*Termina aqui*/
			break;
		}
	}
}
