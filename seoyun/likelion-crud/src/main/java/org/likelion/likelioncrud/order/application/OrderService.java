package org.likelion.likelioncrud.order.application;

import org.likelion.likelioncrud.common.error.ErrorCode;
import org.likelion.likelioncrud.common.exception.NotFoundException;
import org.likelion.likelioncrud.consumer.domain.Consumer;
import org.likelion.likelioncrud.consumer.domain.repository.ConsumerRepository;
import org.likelion.likelioncrud.order.api.dto.request.OrderSaveReqDto;
import org.likelion.likelioncrud.order.api.dto.request.OrderUpdateReqDto;
import org.likelion.likelioncrud.order.api.dto.response.OrderInfoResDto;
import org.likelion.likelioncrud.order.api.dto.response.OrderListResDto;
import org.likelion.likelioncrud.order.domain.Order;
import org.likelion.likelioncrud.order.domain.repository.OrderRepository;
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
    public OrderInfoResDto orderSave(OrderSaveReqDto orderSaveReqDto) {
        Consumer consumer = consumerRepository.findById(orderSaveReqDto.consumerId()).orElseThrow(IllegalArgumentException::new);

        Order order = Order.builder()
                .price(orderSaveReqDto.price())
                .name(orderSaveReqDto.name())
                .consumer(consumer)
                .build();

        orderRepository.save(order);
        return OrderInfoResDto.from(order);
    }

    // Read
    public OrderListResDto orderFindConsumer(Long consumerId) {
        Consumer consumer = consumerRepository.findById(consumerId).orElseThrow(
                () -> new NotFoundException(ErrorCode.CONSUMERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.CONSUMERS_NOT_FOUND_EXCEPTION.getMessage() + consumerId)
        );

        List<Order> orders = orderRepository.findByConsumer(consumer);
        List<OrderInfoResDto> orderInfoResDtoList = orders.stream()
                .map(OrderInfoResDto::from)
                .toList();

        return OrderListResDto.from(orderInfoResDtoList);


    }

    // Read All
    public OrderListResDto orderFindAll() {
        List<Order> orders = orderRepository.findAll();

        List<OrderInfoResDto> orderInfoResDtoList = orders.stream()
                .map(OrderInfoResDto::from)
                .toList();

        return OrderListResDto.from(orderInfoResDtoList);
    }



    // Read One
    public OrderInfoResDto orderFindById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException(ErrorCode.ORDERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.CONSUMERS_NOT_FOUND_EXCEPTION.getMessage() + orderId)
        );

        return OrderInfoResDto.from(order);
    }

    // Update
    @Transactional
    public OrderInfoResDto orderUpdate(Long orderId, OrderUpdateReqDto orderUpdateReqDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException(ErrorCode.ORDERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.ORDERS_NOT_FOUND_EXCEPTION.getMessage() + orderId)
        );

        order.update(orderUpdateReqDto);
        return OrderInfoResDto.from(order);
    }

    // Delete
    @Transactional
    public OrderInfoResDto orderDelete(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new NotFoundException(ErrorCode.ORDERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.ORDERS_NOT_FOUND_EXCEPTION.getMessage() + orderId)
        );

        orderRepository.delete(order);
        return OrderInfoResDto.from(order);
    }

}
