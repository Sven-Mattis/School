package DB;

import java.sql.SQLException;

public class Install {

    private final String createUserQuery = "CREATE TABLE `LaHa`.`users` ( `ID` INT NOT NULL AUTO_INCREMENT , `USERNAME` VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL , `PASSWORD` VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL , `FirstName` VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL , `LastName` VARCHAR(256) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL , `UserGroup` INT(16) NOT NULL , `Gender` VARCHAR(16) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL , PRIMARY KEY (`ID`)) ENGINE = InnoDB;",
    createProducts = "CREATE TABLE `laha`.`products` ( `ID` INT NOT NULL AUTO_INCREMENT , `ArticleNumber` VARCHAR(256) NOT NULL , `ArticleName` VARCHAR(256) NOT NULL , `UNITS` INT NOT NULL , `SellPrice` BINARY(255) NOT NULL , `BuyPrice` BINARY(255) NOT NULL , `xPoints` INT NOT NULL , `yPoints` INT NOT NULL , `Steuersatz` INT NOT NULL , `AMOUNT` INT NOT NULL , `minBestand` INT NOT NULL , `maxBestand` INT NOT NULL , `melBestand` INT NOT NULL , `ordered` BOOLEAN NOT NULL , `DeliveryDate` DATE NOT NULL , `LagerOrt` BINARY(255) NOT NULL , PRIMARY KEY (`ID`)) ENGINE = InnoDB;";


    public Install () throws SQLException {
        new Connection("","","");
    }


}
