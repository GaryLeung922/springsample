package com.xiaojiaqi.mapper;

import com.xiaojiaqi.pojo.SimpleDestination;
import com.xiaojiaqi.pojo.SimpleSource;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-05-13T19:19:19+0800",
    comments = "version: 1.3.0.Beta2, compiler: javac, environment: Java 1.8.0_221 (Oracle Corporation)"
)
public class SimpleSourceDestinationMapperImpl implements SimpleSourceDestinationMapper {

    @Override
    public SimpleDestination sourceToDestination(SimpleSource source) {
        if ( source == null ) {
            return null;
        }

        SimpleDestination simpleDestination = new SimpleDestination();

        return simpleDestination;
    }

    @Override
    public SimpleSource destinationToSource(SimpleDestination destination) {
        if ( destination == null ) {
            return null;
        }

        SimpleSource simpleSource = new SimpleSource();

        return simpleSource;
    }
}
