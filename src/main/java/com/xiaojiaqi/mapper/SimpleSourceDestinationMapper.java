package com.xiaojiaqi.mapper;

import com.xiaojiaqi.pojo.SimpleDestination;
import com.xiaojiaqi.pojo.SimpleSource;
import org.mapstruct.Mapper;

/**
 * @Author: liangjiaqi
 * @Date: 2020/3/20 8:50 PM
 */
@Mapper
public interface SimpleSourceDestinationMapper {
    SimpleDestination sourceToDestination(SimpleSource source);
    SimpleSource destinationToSource(SimpleDestination destination);
}
