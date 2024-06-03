package org.likelion.likelionassignmentcrud.order.application;

import org.likelion.likelionassignmentcrud.consumer.domain.Consumer;
import org.likelion.likelionassignmentcrud.consumer.domain.repository.ConsumerRepository;
import org.likelion.likelionassignmentcrud.order.api.dto.request.OrderSaveReqDto;
import org.likelion.likelionassignmentcrud.order.api.dto.request.OrderUpdateReqDto;
import org.likelion.likelionassignmentcrud.order.api.dto.response.OrderInfoResDto;
import org.likelion.likelionassignmentcrud.order.api.dto.response.OrderListResDto;
import org.likelion.likelionassignmentcrud.order.domain.Order;
import org.likelion.likelionassignmentcrud.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final ConsumerRepository consumerRepository;
    private final OrderRepository orderRepository;

    public OrderService(ConsumerRepository consumerRepository, OrderRepository orderRepository) {
        this.consumerRepository = consumerRepository;
        this.orderRepository = orderRepository;
    }

    // Create
    @Transactional
    public void orderSave(OrderSaveReqDto orderSaveReqDto) {
        Consumer consumer = consumerRepository.findById(orderSaveReqDto.consumerId()).orElseThrow(IllegalArgumentException::new);

        Order order = Order.builder()
                .price(orderSaveReqDto.price())
                .name(orderSaveReqDto.name())
                .consumer(consumer)                                 
                .build();

        orderRepository.save(order);
    }


    public OrderListResDto orderFindConsumer(Long consumerId) {
        Consumer consumer = consumerRepository.findById(consumerId).orElseThrow(IllegalArgumentException::new);

        List<Order> orders = orderRepository.findByConsumer(consumer);
        List<OrderInfoResDto> orderInfoResDtoList = orders.stream()
                .map(OrderInfoResDto::from)
                .toList();

        return OrderListResDto.from(orderInfoResDtoList);
    }

    // Update
    @Transactional
    public void orderUpdate(Long orderId, OrderUpdateReqDto orderUpdateReqDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);

        order.update(orderUpdateReqDto);
    }

    // Delete
    @Transactional
    public void orderDelete(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);

        orderRepository.delete(order);
    }

}