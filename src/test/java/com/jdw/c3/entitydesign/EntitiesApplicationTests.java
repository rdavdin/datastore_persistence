package com.jdw.c3.entitydesign;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.jdw.c3.entitydesign.EntitiesApplication;
import com.jdw.c3.entitydesign.entities.Delivery;
import com.jdw.c3.entitydesign.entities.Plant;
import com.jdw.c3.entitydesign.repository.DeliveryRepository;
import com.jdw.c3.entitydesign.repository.PlantRepository;

@DataJpaTest
// @SpringBootTest
// @TestPropertySource(
//   locations = "classpath:application2.properties")
class EntitiesApplicationTests {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PlantRepository plantRepository;

	@Test
	void contextLoads() {
	}

    @Test
    void testPriceLessThan(){
        Plant p = testEntityManager.persist(new Plant("Foo Leaf", BigDecimal.valueOf(4.99))); 
        testEntityManager.persist(new Plant("Bar Weed", BigDecimal.valueOf(5.01)));
   
        List<Plant> cheapPlants = plantRepository.findAllByPriceLessThan(BigDecimal.valueOf(5));
        assertEquals(1, cheapPlants.size(), "Size");
        assertEquals(p.getId(), cheapPlants.get(0).getId(), "Id");
    }

    @Test
    void testDeliveryCompleted(){
        Plant p = testEntityManager.persist(new Plant("Baz Root", BigDecimal.valueOf(9.99)));
        Delivery d = testEntityManager.persist(new Delivery("Leonard Bernstein", "234 West Side", LocalDateTime.now(), false));

        d.setPlants(Lists.newArrayList(p));
        p.setDelivery(d);

        assertFalse(plantRepository.deliveryCompleted(p.getId()));
        d.setCompleted(true);
        assertTrue(plantRepository.deliveryCompleted(p.getId()));
    }

}