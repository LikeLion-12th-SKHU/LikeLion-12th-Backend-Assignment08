package org.likelion.likelionassignmentcrud.orders.application;

import org.likelion.likelionassignmentcrud.common.error.ErrorCode;
import org.likelion.likelionassignmentcrud.common.exception.NotFoundException;
import org.likelion.likelionassignmentcrud.food.domain.Food;
import org.likelion.likelionassignmentcrud.food.domain.repository.FoodRepository;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersSaveReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersUpdateReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersInfoResDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersListResDto;
import org.likelion.likelionassignmentcrud.orders.domain.Orders;
import org.likelion.likelionassignmentcrud.orders.domain.repository.OrdersRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OrdersService {
    private final FoodRepository foodRepository;
    private final OrdersRepository ordersRepository;

    public OrdersService(FoodRepository foodRepository, OrdersRepository ordersRepository) {
        this.foodRepository = foodRepository;
        this.ordersRepository = ordersRepository;
    }

    // Create
    @Transactional
    public OrdersInfoResDto ordersSave(OrdersSaveReqDto ordersSaveReqDto) {
        Food food = foodRepository.findById(ordersSaveReqDto.foodId()).orElseThrow(
                () -> new NotFoundException(ErrorCode.FOODS_NOT_FOUND_EXCEPTION
                , ErrorCode.FOODS_NOT_FOUND_EXCEPTION.getMessage()
                + ordersSaveReqDto.foodId())
                );

        Orders orders = Orders.builder()
                .shippingAddress(ordersSaveReqDto.shippingAddress())
                .paymentInfo(ordersSaveReqDto.paymentInfo())
                .food(food)
                .build();

        Orders savedOrders = ordersRepository.save(orders);
        return OrdersInfoResDto.from(savedOrders);
    }

    public OrdersListResDto ordersFindFood(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(
                () -> new NotFoundException(ErrorCode.FOODS_NOT_FOUND_EXCEPTION
                , ErrorCode.FOODS_NOT_FOUND_EXCEPTION.getMessage()
                + foodId));

        List<Orders> ordersList = ordersRepository.findByfood(food);
        List<OrdersInfoResDto> ordersInfoResDtoList = ordersList.stream()
                .map(OrdersInfoResDto::from)
                .collect(Collectors.toList());

        return OrdersListResDto.from(ordersInfoResDtoList);
    }

    @Transactional
    public OrdersInfoResDto ordersUpdate(Long ordersId, OrdersUpdateReqDto ordersUpdateReqDto) {
        Orders orders = ordersRepository.findById(ordersId).orElseThrow(
                () -> new NotFoundException(ErrorCode.ORDERSS_NOT_FOUND_EXCEPTION
                        , ErrorCode.ORDERSS_NOT_FOUND_EXCEPTION.getMessage()
                        + ordersId)
        );
        orders.update(ordersUpdateReqDto);
        return OrdersInfoResDto.from(orders);
    }

    @Transactional
    public OrdersInfoResDto ordersDelete(Long ordersId) {
        Orders orders = ordersRepository.findById(ordersId).orElseThrow(
                () -> new NotFoundException(ErrorCode.ORDERSS_NOT_FOUND_EXCEPTION
                        , ErrorCode.ORDERSS_NOT_FOUND_EXCEPTION.getMessage()
                        + ordersId)
        );

        ordersRepository.delete(orders);
        return OrdersInfoResDto.from(orders);
    }

}
