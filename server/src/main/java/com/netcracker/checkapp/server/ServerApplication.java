package com.netcracker.checkapp.server;

import com.netcracker.checkapp.server.model.Check;
import com.netcracker.checkapp.server.model.Item;
import com.netcracker.checkapp.server.persistance.CheckRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

    @Bean
	CommandLineRunner init(CheckRepository checkRepository) {

        return args -> {

            Check check = checkRepository.findCustomByFiscalSign("19321");
            System.out.println("-------");
            System.out.println("Getting check by fiscalSign:");
            System.out.println(check);
            System.out.println("-------");

//            that code works correctly, saving in DB _id, classtype and not null fields!
/*            Check check2 = new Check();
            check2.setFiscalSign("19320");
            check2.setFiscalDocumentNumber("3832291243");
            check2.setFiscalDriveNumber("8710000100555238");
            Check check3 = checkRepository.save(check2);*/

//          that code block add check with all parameters, include items (2 items here)
/*            Check check3 = new Check();
            check3.setFiscalSign("19321");
            check3.setFiscalDocumentNumber("3832871245");
            check3.setFiscalDriveNumber("8710000100387129");
            check3.setTotalSum("298.00");
            check3.setDateTime(LocalDateTime.of(2017, 10, 12, 10, 34));

            Item item_1 = new Item();
            item_1.setName("Sandwich 15 sm");
            item_1.setNdsSum("0");
            item_1.setQuantity("1");
            item_1.setPrice("129.00");
            Item item_2 = new Item();
            item_2.setPrice("40.00");
            item_2.setName("Bubble gum");
            item_2.setNdsSum("0");
            item_2.setQuantity("1");

            List<Item> items = new ArrayList<>();
            items.add(item_1);
            items.add(item_2);

            check3.setItem(items);

            checkRepository.save(check3);*/


            int count = checkRepository.findAll().size();
            System.out.println("Checks found:  " + count);

        };
    }
}
