INSERT INTO `USERENTITY` (`ID`, `EMAIL`, `FIRSTNAME`, `LASTNAME`, `PASSWORD`, `USERGROUP`, `USERSTATUS`, `UUID`)
VALUES 
        (1,'cem@aripd.com','Cem','aripd','cem','Administrators','Approved',NULL),
        (2,'dev@aripd.com','Dev','aripd','dev','Administrators','Approved',NULL);

INSERT INTO `CATEGORYENTITY` (`ID`, `CODE`, `SORTORDER`, `PARENT_ID`)
VALUES 
(1,'Electronics',1,NULL),
(2,'Home, Office',1,NULL),
(3,'Auto, Garden',1,NULL),
(4,'Mother, Baby',1,NULL),
(5,'Sports',1,NULL),
(6,'Beauty Products',1,NULL),
(7,'Supermarket',1,NULL),
(8,'Book, Music, Movie, Hobby',1,NULL),
(9,'Dress, Accessories, Watches',1,NULL),
(10,'Computers',1,1),
(11,'Printers',1,1),
(12,'Phones',1,1),
(13,'Furnitures',1,2),
(14,'Home Textiles',1,2),
(15,'Home Decorations',1,2),
(16,'Auto Accesories',1,3),
(17,'Security',1,3),
(18,'Baby Nutrition',1,4),
(19,'Baby Puset',1,4),
(20,'Sports Equipments',1,5),
(21,'Sports Nutrition',1,5),
(22,'Perfumes',1,6),
(23,'Make-up',1,6),
(24,'Paper Products',1,7),
(25,'Diapers',1,7),
(26,'Books, Magazines',1,8),
(27,'Music Equipments',1,8),
(28,'Women',1,9),
(29,'Men',1,9),
(30,'Laptop',1,10),
(31,'Tablet',1,10),
(32,'Desktop',1,10),
(33,'Laser Printers',1,11),
(34,'Scanners',1,11),
(35,'Mobile Phones',1,12),
(36,'Accessories',1,12),
(37,'Bedroom',1,13),
(38,'Living Room',1,13),
(39,'Living Room Products',1,14),
(40,'Carpets',1,14),
(41,'Design Products',1,15),
(42,'Wall Clocks',1,15),
(43,'Auto Accessory Products',1,16),
(44,'Auto Audio and Video Products',1,16),
(45,'Fitness',1,20),
(46,'Body Building',1,20),
(47,'Sports Branches',1,20),
(48,'Electronics Devices',1,30),
(49,'Mobile Phone Accessories',1,30),
(50,'Cases',1,30),
(51,'Kategori4',1,30),
(52,'Chargers',1,31),
(53,'Data Cables',1,31),
(54,'Kategori7',1,31),
(55,'Kategori8',1,31),
(56,'Kategori9',1,31),
(57,'Batteries',1,31),
(58,'Memory Cards',1,32),
(59,'Keyboards',1,32),
(60,'Kategori13',1,32),
(61,'Kategori14',1,32),
(62,'Kategori15',1,33),
(63,'Kategori16',1,33),
(64,'Covers',1,34),
(65,'Kategori18',1,34),
(66,'Screen Protection',1,36),
(67,'Earphones',1,36),
(68,'Power Banks',1,36),
(69,'Arm Bands',1,36),
(70,'Under Water Protection',1,37),
(71,'Car Hoders',1,37),
(72,'Stylus',1,38),
(73,'Selfie',1,39),
(74,'Cables',1,39),
(75,'Kategori28',1,39),
(76,'Kategori29',1,40),
(77,'Textile',1,40),
(78,'Home & Life',1,40),
(79,'Mother and Baby',1,40),
(80,'Cosmetics',1,41),
(81,'Jewels and Watches',1,41),
(82,'Sports Outdoor',1,42),
(83,'Book, Music, Movie, Game',1,43),
(84,'Vacation and Entertainment',1,43),
(85,'Otomotive and Motorcycles',1,43),
(86,'Kategori39',1,44),
(87,'Kategori40',1,44),
(88,'Kategori41',1,44),
(89,'Kategori42',1,44),
(90,'Kategori43',1,44),
(91,'Kategori44',1,44),
(92,'Kategori45',1,45),
(93,'Kategori46',1,45),
(94,'Kategori47',1,46),
(95,'Kategori48',1,47),
(96,'Kategori49',1,47),
(97,'Kategori50',1,47);


INSERT INTO `PRODUCTENTITY` (`ID`, `CODE`, `DESCRIPTION`, `NAME`, `PRODUCTSTATUS`, `TAXRATE`, `CATEGORY_ID`)
VALUES 
        (1,'2000000035951','Lorem ipsum dolor sit amet, consectetur adipiscing elit.','Lorem ipsum1','ACTIVE',.18,2),
        (2,'2000000035952','Lorem ipsum dolor sit amet, consectetur adipiscing elit.','Lorem ipsum2','ACTIVE',.18,2),
        (3,'2000000035953','Lorem ipsum dolor sit amet, consectetur adipiscing elit.','Lorem ipsum3','ACTIVE',.18,2);

INSERT INTO `PRICEENTITY` (`ID`, `AMOUNT`, `CURRENCY`, `QUANTITY`, `PRODUCT_ID`) 
VALUES 
        (1,100.00,'TRY',1,1),
        (2,200.00,'TRY',1,2),
        (3,300.00,'TRY',1,3);

INSERT INTO `PAGEENTITY` (`ID`, `CONTENT`, `DESCRIPTION`, `NAME`) 
VALUES 
        (1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name1'),
        (2,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name2'),
        (3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name3');

INSERT INTO `BANNERENTITY` (`ID`, `BANNERTYPE`, `BYTES`, `DESCRIPTION`, `NAME`, `SORTORDER`, `STATUS`, `TIMEEND`, `TIMESTART`)
VALUES
	(1,'TOP','','Banner1 Content','Banner1',1,1,NOW() + INTERVAL 15 DAY,NOW()),
	(2,'BOTTOM','','Banner2 Content','Banner2',2,1,NOW() + INTERVAL 15 DAY,NOW()),
	(3,'BOTTOM','','Banner3 Content','Banner3',3,1,NOW() + INTERVAL 15 DAY,NOW()),
	(4,'BOTTOM','','Banner4 Content','Banner4',4,1,NOW() + INTERVAL 15 DAY,NOW());


