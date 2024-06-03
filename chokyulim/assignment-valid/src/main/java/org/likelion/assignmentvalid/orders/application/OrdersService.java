package org.likelion.assignmentvalid.orders.application;

import org.hibernate.query.Order;
import org.likelion.assignmentvalid.common.error.ErrorCode;
import org.likelion.assignmentvalid.common.exception.NotFoundException;
import org.likelion.assignmentvalid.orders.api.dto.request.OrdersSaveReqDto;
import org.likelion.assignmentvalid.orders.api.dto.request.OrdersUpdateReqDto;
import org.likelion.assignmentvalid.orders.api.dto.response.OrdersInfoResDto;
import org.likelion.assignmentvalid.orders.api.dto.response.OrdersListResDto;
import org.likelion.assignmentvalid.orders.domain.Orders;
import org.likelion.assignmentvalid.orders.domain.repository.OrdersRepository;
import org.likelion.assignmentvalid.product.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final org.likelion.assignmentvalid.product.domain.repository.ProductRepository productRepository;

    public OrdersService(OrdersRepository ordersRepository, org.likelion.assignmentvalid.product.domain.repository.ProductRepository productRepository) {
        this.ordersRepository = ordersRepository;
        this.productRepository = productRepository;
    }


    // Create
    @Transactional
    public OrdersInfoResDto ordersSave(OrdersSaveReqDto ordersSaveReqDto) {
        Orders orders = Orders.builder()
                .custName(ordersSaveReqDto.custName())
                .location(ordersSaveReqDto.location())
                .build();

        ordersRepository.save(orders);
        return OrdersInfoResDto.from(orders);
    }

    // 상품에 따른 주문 조회
    public OrdersListResDto ordersFindProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(null);
        List<Orders> ordersList =  ordersRepository.findByProduct(product);
        List<OrdersInfoResDto> ordersInfoResDtos =
        ordersList.stream()
                .map(OrdersInfoResDto::from)
                .toList();

        return OrdersListResDto.from(ordersInfoResDtos);
    }

    // 업데이트
    @Transactional
    public OrdersInfoResDto ordersUpdate(Long ordersId, OrdersUpdateReqDto ordersUpdateReqDto) {
        Orders orders = ordersRepository.findById(ordersId).orElseThrow
                (() -> new NotFoundException(ErrorCode.ORDERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.ORDERS_NOT_FOUND_EXCEPTION.getMessage() + ordersId));
        orders.update(ordersUpdateReqDto);
        return OrdersInfoResDto.from(orders);
    }

    // 삭제
    @Transactional
    public OrdersInfoResDto ordersDelete(Long ordersId) {
        Orders orders = ordersRepository.findById(ordersId).orElseThrow
                (() -> new NotFoundException(ErrorCode.ORDERS_NOT_FOUND_EXCEPTION,
                ErrorCode.ORDERS_NOT_FOUND_EXCEPTION.getMessage() + ordersId));
        ordersRepository.delete(orders);
        return OrdersInfoResDto.from(orders);
    }
}
