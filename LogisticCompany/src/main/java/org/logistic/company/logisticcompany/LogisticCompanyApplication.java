package org.logistic.company.logisticcompany;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.logistic.company.logisticcompany.persistance.repos.UserRepository;
import org.logistic.company.logisticcompany.persistance.service.PackageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LogisticCompanyApplication {

    private static final Logger log = LogManager.getLogger(LogisticCompanyApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LogisticCompanyApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(PackageService pkgService, UserRepository userRepo) {
        return (args) -> {
            pkgService.getPackagesSendBy("alice_walker").forEach(pkg -> {
                log.info("KKKKKK{}", pkg.getPrice());
                log.info(pkg.getEmployee());
            });

        };
    }

}
