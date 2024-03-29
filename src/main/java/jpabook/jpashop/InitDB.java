package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * userA
 *  JPA1 BOOK
 *  JPA2 BOOK
 * userB
 *  SPRING1 BOOK
 *  SPRING2 BOOK
 */
@Component
@RequiredArgsConstructor
public class InitDB {

    private final SampleService service;

    @PostConstruct
    public void init(){
        service.dbInit1();
        service.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class SampleService{
        private final EntityManager em;

        public void dbInit1(){
            Member member = getMember("UserA", "서울", "1", "1111");
            em.persist(member);

            Book book1 = getBook("JPA1 BOOK", 10000, 100);
            em.persist(book1);

            Book book2 = getBook("JPA2 BOOK", 20000, 100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);
            Order order = Order.createOrder(member, createDelivery(member), orderItem1, orderItem2);
            em.persist(order);
        }

        public void dbInit2(){
            Member member = getMember("UserB", "진주", "2", "2222");
            em.persist(member);

            Book book1 = getBook("SPRING BOOK1", 20000, 200);
            em.persist(book1);

            Book book2 = getBook("SPRING BOOK2", 40000, 300);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 40000, 4);
            Order order = Order.createOrder(member, createDelivery(member), orderItem1, orderItem2);
            em.persist(order);
        }

        private static Book getBook(String book, int price, int stockQuantity) {
            Book book1 = new Book();
            book1.setName(book);
            book1.setPrice(price);
            book1.setStockQuantity(stockQuantity);
            return book1;
        }

        private static Member getMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

    }
}
