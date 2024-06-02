package org.likelion.likelionassignmentcrud.orders.application;

import org.likelion.likelionassignmentcrud.common.error.ErrorCode;
import org.likelion.likelionassignmentcrud.common.exception.NotFoundException;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersSaveReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.request.OrdersUpdateReqDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersInfoResDto;
import org.likelion.likelionassignmentcrud.orders.api.dto.response.OrdersListResDto;
import org.likelion.likelionassignmentcrud.orders.domain.Orders;
import org.likelion.likelionassignmentcrud.orders.domain.repository.OrdersRepository;
import org.likelion.likelionassignmentcrud.users.domain.Users;
import org.likelion.likelionassignmentcrud.users.domain.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)

public class OrdersService {
    private final UsersRepository usersRepository;
    private final OrdersRepository ordersRepository;

    public OrdersService(UsersRepository usersRepository, OrdersRepository ordersRepository) {
        this.usersRepository = usersRepository;
        this.ordersRepository = ordersRepository;
    }
    //CREATE
    @Transactional
    public OrdersInfoResDto ordersSave(OrdersSaveReqDto ordersSaveReqDto) {
        Users users = usersRepository.findById(ordersSaveReqDto.usersId()).orElseThrow(
                () -> new NotFoundException(ErrorCode.USERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.USERS_NOT_FOUND_EXCEPTION.getMessage()
                        + ordersSaveReqDto.usersId())
                );

        Orders orders = Orders.builder()
                .address(ordersSaveReqDto.address())
                .price(ordersSaveReqDto.price())
                .users(users)
                .build();

        ordersRepository.save(orders);
        return OrdersInfoResDto.from(orders);
    }

    // READ ALL 추가
    public OrdersListResDto ordersFindAll() {
        List<Orders> orders = ordersRepository.findAll();

        List<OrdersInfoResDto> ordersInfoResDtoList = orders.stream()
                .map(OrdersInfoResDto::from)
                .toList();

        return OrdersListResDto.from(ordersInfoResDtoList);
    }
    // READ
    public OrdersListResDto ordersFindUsers(Long usersId){
        Users users = usersRepository.findById(usersId).orElseThrow(
                () -> new NotFoundException(ErrorCode.USERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.USERS_NOT_FOUND_EXCEPTION.getMessage()
                + usersId)
                );

            List<Orders> ordersList = ordersRepository.findByUsers(users);
            List<OrdersInfoResDto> ordersInfoResDtoList = ordersList.stream()
                    .map(OrdersInfoResDto::from)
                    .toList();

            return OrdersListResDto.from(ordersInfoResDtoList);
    }

    //READ ONE(주문id에 따른 게시글 하나 조회)
    public OrdersInfoResDto ordersFindById(Long ordersId) {
        Orders orders = ordersRepository.findById(ordersId).orElseThrow(
                () -> new NotFoundException(ErrorCode.ORDERS_NOT_FOUND_EXCEPTION, ErrorCode.ORDERS_NOT_FOUND_EXCEPTION.getMessage()
                        + ordersId)
        );
        return  OrdersInfoResDto.from(orders);
    }

    //Update
    @Transactional
    public OrdersInfoResDto ordersUpdate(Long ordersId, OrdersUpdateReqDto ordersUpdateReqDto) {
        Orders orders = ordersRepository.findById(ordersId).orElseThrow(
                () -> new NotFoundException(ErrorCode.ORDERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.ORDERS_NOT_FOUND_EXCEPTION.getMessage()
                + ordersId)
        );

        orders.update(ordersUpdateReqDto);
        return OrdersInfoResDto.from(orders);
    }

    //Delete
    @Transactional
    public OrdersInfoResDto ordersDelete(Long ordersId) {
        Orders orders = ordersRepository.findById(ordersId).orElseThrow(
                () -> new NotFoundException(ErrorCode.ORDERS_NOT_FOUND_EXCEPTION,
                        ErrorCode.ORDERS_NOT_FOUND_EXCEPTION.getMessage()
                + ordersId)
        );

        ordersRepository.delete(orders);
        return OrdersInfoResDto.from(orders);
    }
}
