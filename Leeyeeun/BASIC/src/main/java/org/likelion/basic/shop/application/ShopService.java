package org.likelion.basic.shop.application;

import org.likelion.basic.common.error.ErrorCode;
import org.likelion.basic.common.exception.NotFoundException;
import org.likelion.basic.customer.domain.Customer;
import org.likelion.basic.customer.domain.repository.CustomerRepository;
import org.likelion.basic.shop.api.dto.request.ShopSaveReqDto;
import org.likelion.basic.shop.api.dto.request.ShopUpdateReqDto;
import org.likelion.basic.shop.api.dto.response.ShopInfoResDto;
import org.likelion.basic.shop.api.dto.response.ShopListResDto;
import org.likelion.basic.shop.domain.Shop;
import org.likelion.basic.shop.domain.repository.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ShopService {
    private final ShopRepository shopRepository;
    private final CustomerRepository customerRepository;

    public ShopService(ShopRepository shopRepository, CustomerRepository customerRepository) {
        this.shopRepository = shopRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public ShopInfoResDto shopSave(ShopSaveReqDto shopSaveReqDto) {
        Customer customer = customerRepository.findById(shopSaveReqDto.customerId())
                .orElseThrow(()-> new NotFoundException(ErrorCode.CUSTOMER_NOT_FOUND_EXCEPTION, ErrorCode.CUSTOMER_NOT_FOUND_EXCEPTION.getMessage()
                                + shopSaveReqDto.customerId()));

        Shop shop = Shop.builder()
                .title(shopSaveReqDto.title())
                .contents(shopSaveReqDto.contents())
                .customer(customer)
                .build();

        shopRepository.save(shop);
        return ShopInfoResDto.from(shop);
    }

    public ShopListResDto shopFindAll() {
        List<Shop> shops = shopRepository.findAll();

        List<ShopInfoResDto> shopInfoResDtoList = shops.stream()
                .map(ShopInfoResDto::from)
                .toList();

        return ShopListResDto.from(shopInfoResDtoList);
    }

    // 작성자에 따른 게시물 조회
    public ShopListResDto shopFindMember(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.CUSTOMER_NOT_FOUND_EXCEPTION, ErrorCode.CUSTOMER_NOT_FOUND_EXCEPTION.getMessage()
                                + customerId));

        List<Shop> shops = shopRepository.findByCustomer(customer);
        List<ShopInfoResDto> shopInfoResDtoList = shops.stream()
                .map(ShopInfoResDto::from)
                .toList();

        return ShopListResDto.from(shopInfoResDtoList);
    }

    public ShopInfoResDto shopFindById(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.SHOP_NOT_FOUND_EXCEPTION, ErrorCode.SHOP_NOT_FOUND_EXCEPTION.getMessage()
                                + shopId));

        return ShopInfoResDto.from(shop);
    }


    @Transactional
    public ShopInfoResDto shopUpdate(Long shopId, ShopUpdateReqDto shopUpdateReqDto) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.SHOP_NOT_FOUND_EXCEPTION, ErrorCode.SHOP_NOT_FOUND_EXCEPTION.getMessage()
                               + shopId));
        shop.update(shopUpdateReqDto);
        return ShopInfoResDto.from(shop);
    }

    // delete
    @Transactional
    public ShopInfoResDto shopDelete(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.SHOP_NOT_FOUND_EXCEPTION, ErrorCode.SHOP_NOT_FOUND_EXCEPTION.getMessage()
                                + shopId));
        shopRepository.delete(shop);
        return ShopInfoResDto.from(shop);
    }

}
