package com.vldby.postmail.mappers;

import com.vldby.postmail.dto.PostalDeliveryRegistrationDto;
import com.vldby.postmail.entity.PostalDelivery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostalDeliveryMapper {

    PostalDeliveryMapper INSTANCE = Mappers.getMapper(PostalDeliveryMapper.class);

    PostalDelivery toEntity(PostalDeliveryRegistrationDto dto);
}
