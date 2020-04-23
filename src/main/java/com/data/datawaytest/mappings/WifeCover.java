package com.data.datawaytest.mappings;

import com.data.datawaytest.entity.MyWife;
import com.data.datawaytest.entity.MyWifeVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/** @author Baijl
 * @date 2020-04-23
 * @time 09:35
 * @description
 */
@Mapper(componentModel = "spring")
public interface WifeCover {
   WifeCover INSTANCE =Mappers.getMapper(WifeCover.class);

   MyWife toCoverMyWifeV0(MyWifeVO myWife);
    List<MyWife> toCoverMyWifeV0List(List<MyWifeVO> myWife);
}
