INSERT INTO `USERENTITY` (`ID`, `EMAIL`, `FIRSTNAME`, `LASTNAME`, `MAILING`, `PASSWORD`, `USERGROUP`, `USERSTATUS`, `UUID`)
VALUES 
        (1,'cem@aripd.com','Cem','aripd',TRUE,'cem','Administrators','Approved',NULL),
        (2,'dev@aripd.com','Dev','aripd',TRUE,'dev','Members','Approved',NULL);

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


INSERT INTO `PRODUCTENTITY` (`ID`, `CODE`, `DESCRIPTION`, `NAME`, `STATUS`, `TAXRATE`, `CATEGORY_ID`)
VALUES 
        (1,'2000000035951','Lorem ipsum dolor sit amet, consectetur adipiscing elit.','Lorem ipsum1',TRUE,.18,2),
        (2,'2000000035952','Lorem ipsum dolor sit amet, consectetur adipiscing elit.','Lorem ipsum2',TRUE,.18,2),
        (3,'2000000035953','Lorem ipsum dolor sit amet, consectetur adipiscing elit.','Lorem ipsum3',TRUE,.18,2);

INSERT INTO `PRICEENTITY` (`ID`, `AMOUNT`, `CURRENCY`, `QUANTITY`, `PRODUCT_ID`) 
VALUES 
        (1,100.00,'TRY',1,1),
        (2,200.00,'TRY',1,2),
        (3,300.00,'TRY',1,3);

INSERT INTO `IMAGEENTITY` (`ID`, `BANNER`, `CONTENTTYPE`, `NAME`, `NAMEORIGINAL`, `SIZE`, `SORTORDER`, `PRODUCT_ID`)
VALUES
	(1,FALSE,'image/jpeg','banner1.jpg','banner1.jpg',10166,0,1),
	(2,FALSE,'image/jpeg','banner2.jpg','banner2.jpg',26957,0,2),
	(3,FALSE,'image/jpeg','banner3.jpg','banner3.jpg',21062,0,3),
	(4,TRUE,'image/jpeg','banner4.jpg','banner4.jpg',51111,0,1),
	(5,TRUE,'image/jpeg','banner5.jpg','banner5.jpg',13728,0,2),
	(6,TRUE,'image/jpeg','banner6.jpg','banner6.jpg',36761,0,3);

INSERT INTO `PAGEENTITY` (`ID`, `CONTENT`, `DESCRIPTION`, `NAME`) 
VALUES 
        (1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name1'),
        (2,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name2'),
        (3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name3');

INSERT INTO `POSTENTITY` (`ID`, `CONTENT`, `CREATEDAT`, `DESCRIPTION`, `NAME`) 
VALUES 
        (1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name1'),
        (2,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name2'),
        (3,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name3'),
        (4,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name4'),
        (5,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name5'),
        (6,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name6'),
        (7,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name7'),
        (8,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name8'),
        (9,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name9'),
        (10,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name10'),
        (11,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name11'),
        (12,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name12'),
        (13,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name13'),
        (14,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name14'),
        (15,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name15'),
        (16,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name16'),
        (17,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name17'),
        (18,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name18'),
        (19,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name19'),
        (20,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis. Nullam consectetur sapien consectetur, tempus nisl vitae, vulputate metus. Donec eros tortor, varius quis quam et, tristique efficitur felis. In feugiat, neque nec finibus blandit, nisi ex tristique eros, non egestas magna ex in ipsum. Nulla congue mattis eros, et lacinia nunc sodales quis. Nam semper tempor orci, vitae ullamcorper felis ultricies eu. Fusce ut pharetra ante. Pellentesque lobortis mi et tortor facilisis, a blandit lacus dignissim. Vivamus aliquet hendrerit justo ut iaculis.',NOW(),'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sollicitudin tincidunt turpis, ut porta felis eleifend quis.','Name20');




INSERT INTO `ADDRESSENTITY` (`ID`, `ADDRESS`, `CITY`, `COUNTRY`, `COUNTY`, `FIRSTNAME`, `LASTNAME`, `PHONE`, `ZIPCODE`, `CREATEDBY_ID`)
VALUES
	(1,'adres','il','ülke','ilçe','dev','aripd','5551234567','34000',2);

INSERT INTO `BASKETITEMENTITY` (`ID`, `NOTE`, `QUANTITY`, `CREATEDBY_ID`, `PRODUCT_ID`)
VALUES
	(1,NULL,1,2,3),
	(2,'paket olsun lütfen',2,2,2),
	(3,'kırmızı olanı istiyorum',3,2,1);

INSERT INTO `SALEENTITY` (`ID`, `COMPLETE_DATE`, `IPN_INSTALLMENTS_NUMBER`, `IPN_INSTALLMENTS_PROGRAM`, `IPN_PAID_AMOUNT`, `PAYMENTDATE`, `REFNOEXT`, `SALEDATE`, `BANKTRANSACTIONNUMBER`, `BILLCITY`, `BILLCOUNTRY`, `BILLFIRSTNAME`, `BILLLASTNAME`, `BILLLINE`, `BILLPHONE`, `BILLZIPCODE`, `CREATEDAT`, `CURRENCY`, `DELIVERYCITY`, `DELIVERYCOUNTRY`, `DELIVERYFIRSTNAME`, `DELIVERYLASTNAME`, `DELIVERYLINE`, `DELIVERYPHONE`, `DELIVERYZIPCODE`, `PAYMENTMETHOD`, `SALESTATUS`, `CREATEDBY_ID`)
VALUES
	(1,'','','',0.00,'','20170329135953','2017-03-29 13:59:53',NULL,'il','ülke','dev','aripd','adres','5551234567','34000','2017-03-29 16:59:54','TRY','il','ülke','dev','aripd','adres','5551234567','34000','WIRE','WAITING_FOR_PAYMENT',2);

INSERT INTO `SALELINEENTITY` (`ID`, `IPN_PCODE`, `IPN_PID`, `IPN_PNAME`, `IPN_PRICE`, `IPN_QTY`, `IPN_TOTAL`, `IPN_VAT`, `CARGOTRACKINGLINK`, `NOTE`, `SALESTATUS`, `PRODUCT_ID`, `SALE_ID`)
VALUES
	(1,'2000000035952','2','Lorem ipsum2',200.00,2,236.00,36.00,'http://www.yurticikargo.com/bilgi-servisleri/sayfalar/kargom-nerede.aspx','paket olsun lütfen','WAITING_FOR_PAYMENT',2,1),
	(2,'2000000035953','3','Lorem ipsum3',300.00,1,354.00,54.00,'http://www.yurticikargo.com/bilgi-servisleri/sayfalar/kargom-nerede.aspx',NULL,'WAITING_FOR_PAYMENT',3,1),
	(3,'2000000035951','1','Lorem ipsum1',100.00,3,118.00,18.00,NULL,'kırmızı olanı istiyorum','WAITING_FOR_PAYMENT',1,1);
