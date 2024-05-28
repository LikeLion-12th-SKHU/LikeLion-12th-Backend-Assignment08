package org.likelion.likelionassignmentcrud.shop.api.dto.request;

import org.likelion.likelionassignmentcrud.customer.domain.Part;

public record ShopSaveReqDto(

        Long customerId,
        String title,
        String contents

) {
}
