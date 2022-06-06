package org.yagamipaul.alife.simulator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.yagamipaul.alife.entity.Ant;
import org.yagamipaul.alife.entity.BaseEntity;

import javax.annotation.PostConstruct;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Simulator {

    private List<BaseEntity> entities;

    @PostConstruct
    void init(){
        entities = new ArrayList<>();
        entities.add(new Ant());
        entities.add(new Ant());

        this.start();
    }

    void start(){

        log.info("Starting simulator");

        for(BaseEntity e : entities){
            log.info("creating entity: {}", e);
        }

    }

    public void update(){

    }


}
