package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품_주문() {
        // given
        Member member = createMember();

        Book book = createBook("jpa intermediate", 10000, 10);

        int orderCount = 2;
        // when
        Long order = orderService.order(member.getId(), book.getId(), orderCount);

        // then
        Order getOrder = orderRepository.findOne(order);

        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 개수가 같아야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 상품 * 개수이다.", 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());


    }

    @Test
    public void 주문_취소() {
        // given
        Member member = createMember();
        Book book = createBook("jpa intermediate", 10000, 10);
        int orderCount = 2;
        Long order = orderService.order(member.getId(), book.getId(), orderCount);

        // when
        orderService.cancelOrder(order);

        // then
        Order getOrder = orderRepository.findOne(order);

        assertEquals("주문 취소시 상태는 cancel이 되어야 한다.", OrderStatus.CANCEL, getOrder.getStatus());
        assertEquals("주문 취소시 수량이 다시 복구되어야 한다.", 10, book.getStockQuantity()) ;

    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() {
        Member member = createMember();
        Book book = createBook("jpa intermediate", 10000, 10);
        int orderCount = 11;

        // when
        orderService.order(member.getId(), book.getId(), orderCount);

        // then
        fail("주문 수량 부족으로 예외가 발생해야 한다.");
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

}