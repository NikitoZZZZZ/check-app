package com.netcracker.checkapp.server;

import com.netcracker.checkapp.server.model.Check;
import com.netcracker.checkapp.server.model.Item;
import com.netcracker.checkapp.server.model.User;
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

            /*
            here you can use one of addCheckX methods
             */

            int count = checkRepository.findAll().size();
            System.out.println("Checks found:  " + count);

            List<Check> checkList = checkRepository.findByLoginAndPwd("rogeenok","123456");
            for (Check checkvar: checkList)
                System.out.println(checkvar);

        };
    }

//  that method works correctly, saving in DB _id, classtype and not null fields!
    private static void addCheck1(CheckRepository checkRepository) {
            Check check = new Check();
            check.setFiscalSign("19320");
            check.setFiscalDocumentNumber("3832291243");
            check.setFiscalDriveNumber("8710000100555238");
            Check checkreturned = checkRepository.save(check);
    }
    
//  that code block add check with all parameters, include items (2 items here)    
    private static void addCheck2(CheckRepository checkRepository) {
            Check check = new Check();
            check.setFiscalSign("19321");
            check.setFiscalDocumentNumber("3832871245");
            check.setFiscalDriveNumber("8710000100387129");
            check.setTotalSum("298.00");
            check.setDateTime(LocalDateTime.of(2017, 10, 12, 10, 34));

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

            check.setItem(items);

            checkRepository.save(check);
    }

//	that code block adds check with user params
    private static void addCheck3(CheckRepository checkRepository) {
        Check check = new Check();
        check.setFiscalSign("143676");
        check.setFiscalDocumentNumber("2404980075");
        check.setFiscalDriveNumber("8710000100373468");
        check.setTotalSum("102.00");
        check.setDateTime(LocalDateTime.of(2017, 10, 12, 10, 27));

        Item item = new Item();
        item.setPrice("102.00");
        item.setQuantity("1");
        item.setNdsSum("0");
        item.setName("Льготный 2605 <=> У");

        User user = new User();
        user.setLogin("rogeenok");
        user.setPwd("123456");

        List<Item> items = new ArrayList<Item>();
        items.add(item);
        check.setItem(items);

        check.setUser(user);

        checkRepository.save(check);
    }
}
