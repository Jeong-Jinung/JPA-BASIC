package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // 맨 처음에 한번

        EntityManager em = emf.createEntityManager(); // 트랜잭션당

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            em.clear();//영속성 컨텍스트를 통째로 지워버린다.
//            em.detach(member);

            Member member2 = em.find(Member.class, 150L);

            /*
            Member member = new Member(200L, "member200");
            em.persist(member);
            em.flush();
            */

            /*
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ"); // 찾아온값을 변경하면 알아서 값이 변경된다.
            */

            /*
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            em.persist(member1);
            em.persist(member2);

            System.out.println("===========================");
             */


            /*
            Member findMember1 = em.find(Member.class, 101L);// DB에서 정보를 가지고옴
            Member findMember2 = em.find(Member.class, 101L);// 1차 캐시에서 정보를 가져옴

            System.out.println("result = " + (findMember1 == findMember2)); // 영속성의 동일성을 보장해준다.
            */
            /*
            //비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            //영속
            System.out.println("-------BEFORE------");
            em.persist(member);
            System.out.println("-------AFTER------");

            Member findMember1 = em.find(Member.class, 101L);// 1차 캐시에서 정보를 가져옴
            Member findMember2 = em.find(Member.class, 101L);// 1차 캐시에서 정보를 가져옴
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());
            */

            System.out.println("===========================");
            tx.commit(); // 영속성 컨텍스트에 있는 쿼리가 날라간다.

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();



    }
}
