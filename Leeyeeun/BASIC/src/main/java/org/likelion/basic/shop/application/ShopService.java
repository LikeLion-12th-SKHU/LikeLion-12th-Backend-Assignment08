package org.likelion.likelionassignmentcrud.shop.application;

import org.likelion.likelionassignmentcrud.customer.api.dto.request.CustomerUpdateReqDto;
import org.likelion.likelionassignmentcrud.customer.domain.Customer;
import org.likelion.likelionassignmentcrud.customer.domain.repository.CustomerRepository;
import org.likelion.likelionassignmentcrud.shop.api.dto.request.ShopSaveReqDto;
import org.likelion.likelionassignmentcrud.shop.api.dto.request.ShopUpdateReqDto;
import org.likelion.likelionassignmentcrud.shop.api.dto.response.ShopInfoResDto;
import org.likelion.likelionassignmentcrud.shop.api.dto.response.ShopListResDto;
import org.likelion.likelionassignmentcrud.shop.domain.Shop;
import org.likelion.likelionassignmentcrud.shop.domain.repository.ShopRepository;
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
    public void shopSave(ShopSaveReqDto shopSaveReqDto) {
        Customer customer = customerRepository.findById(shopSaveReqDto.customerId())
                .orElseThrow(IllegalArgumentException::new);

        Shop shop = Shop.builder()
                .title(shopSaveReqDto.title())
                .contents(shopSaveReqDto.contents())
                .customer(customer)
                .build();

        shopRepository.save(shop);
    }

    // 작성자에 따른 게시물 조회
    public ShopListResDto shopFindMember(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(IllegalArgumentException::new);

        List<Shop> shops = shopRepository.findByCustomer(customer);
        List<ShopInfoResDto> shopInfoResDtoList = shops.stream()
                .map(ShopInfoResDto::from)
                .toList();

        return ShopListResDto.from(shopInfoResDtoList);
    }

    @Transactional
    public void shopUpdate(Long shopId, ShopUpdateReqDto shopUpdateReqDto) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(IllegalArgumentException::new);
        shop.update(shopUpdateReqDto);
    }

    // delete
    @Transactional
    public void shopDelete(Long shopId) {
        Shop shop = shopRepository.findById(shopId).orElseThrow(IllegalArgumentException::new);
        shopRepository.delete(shop);
    }

}
