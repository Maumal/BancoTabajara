package main;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Conta;



public class Transferencia {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemploPU");
	EntityManager em = emf.createEntityManager();
        
        Scanner entrada= new Scanner(System.in);
        
        Conta conta1=em.find(Conta.class,1L);
        if (conta1==null) {
            System.out.println("Digite o saldo inicial da consta 1: ");
            Double saldoInicialConta1=entrada.nextDouble();
            conta1=new Conta();
            conta1.setSaldo(saldoInicialConta1);
        }
        
          Conta conta2=em.find(Conta.class,1L);
        if (conta2==null) {
            System.out.println("Digite o saldo inicial da consta 2: ");
            Double saldoInicialConta2=entrada.nextDouble();
            conta2=new Conta();
            conta2.setSaldo(saldoInicialConta2);
        }
        em.getTransaction().begin();
        em.persist(conta1);
        em.persist(conta2);
        em.getTransaction().commit();
        
        System.out.println("-----------------------");
        System.out.println("Digite o valor a retirar da conta 1 e depositar na conta 2: ");
        
        Double valorTransferencia=entrada.nextDouble();
        
        em.getTransaction().begin();
        conta1.setSaldo(conta1.getSaldo() - valorTransferencia);
        conta2.setSaldo(conta2.getSaldo() +valorTransferencia);
        
        if (conta1.getSaldo()>0) {
            em.getTransaction().commit();
            System.out.println("Transferencia Relizada com suceso");
            
        }else{
            em.getTransaction().rollback();
           System.out.println("Transferência não realizada, saldo insulficiente");
        }
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
}
