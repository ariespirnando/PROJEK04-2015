/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.6.24 : Database - ta_zahra
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ta_zahra` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ta_zahra`;

/*Table structure for table `guru` */

DROP TABLE IF EXISTS `guru`;

CREATE TABLE `guru` (
  `id_guru` char(12) NOT NULL,
  `nama_guru` varchar(30) DEFAULT NULL,
  `jk` char(12) DEFAULT NULL,
  `alamat` text,
  PRIMARY KEY (`id_guru`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `guru` */

insert  into `guru`(`id_guru`,`nama_guru`,`jk`,`alamat`) values ('NBM949300','Syakdiyah, S.Pd','Perempuan','Jalan Baru Dwi Warna Panjang'),('NIP1010101','Achmad, S.Kom','Laki-laki','Jalan Ahmad Akuan, Kotabumi'),('NIP1010102','Aries, M.T','Laki-laki','Jalan Dewi Shinta, Kotabumi'),('NIP1010103','Pirnando, M.Si','Laki-laki','Jalan Pangeran Jinul, Kotabumi'),('NIP1010104','Aini, S.Pd','Perempuan','Jalan Lintas Sumatra, Liwa\r\n'),('NIP1010105','Rahmayati, S.Kom','Perempuan','Jalan Skuting Raya, Liwa'),('NIP1010106','Darpono,S.Pd','Laki -laki','Panjang');

/*Table structure for table `kelas` */

DROP TABLE IF EXISTS `kelas`;

CREATE TABLE `kelas` (
  `id_kelas` char(12) NOT NULL,
  `namakelas` varchar(30) DEFAULT NULL,
  `id_guru` char(12) DEFAULT NULL,
  `TghnSPP` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_kelas`),
  KEY `id_guru` (`id_guru`),
  CONSTRAINT `kelas_ibfk_1` FOREIGN KEY (`id_guru`) REFERENCES `guru` (`id_guru`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `kelas` */

insert  into `kelas`(`id_kelas`,`namakelas`,`id_guru`,`TghnSPP`) values ('IX','Kelas IX','NIP1010105',40000),('Kelas VII','Kelas VII','NIP1010106',40000),('VII','Kelas VII','NIP1010101',40000),('VIII','Kelas VIII','NIP1010105',40000);

/*Table structure for table `siswa` */

DROP TABLE IF EXISTS `siswa`;

CREATE TABLE `siswa` (
  `NIS` char(12) NOT NULL,
  `nama_siswa` varchar(30) DEFAULT NULL,
  `tglLahir` date DEFAULT NULL,
  `jk` char(12) DEFAULT NULL,
  `Angkatan` int(11) DEFAULT NULL,
  `id_kelas` char(12) DEFAULT NULL,
  `nama_ortu` varchar(30) DEFAULT NULL,
  `Notelephone` char(15) DEFAULT NULL,
  `pekerjaan` char(30) DEFAULT NULL,
  `alamat` text,
  PRIMARY KEY (`NIS`),
  KEY `id_kelas` (`id_kelas`),
  CONSTRAINT `siswa_ibfk_1` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id_kelas`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `siswa` */

insert  into `siswa`(`NIS`,`nama_siswa`,`tglLahir`,`jk`,`Angkatan`,`id_kelas`,`nama_ortu`,`Notelephone`,`pekerjaan`,`alamat`) values ('701','Adis Fitriana','2016-01-20','Perempuan',2015,'IX','Aris','08787889890','Petani','Tanjung Rame'),('702','Al Hadhi Ramadhan','2016-01-20','Laki - laki',2015,'IX','Feri','085678567890','Guru','Batu Suluh'),('704','Astika Dwi Rahmawati','2016-01-22','Perempuan',2015,'IX','Purnomo','08216789890','Guru','Serengsem'),('705','Beby Ayu Sari','2016-01-27','Perempuan',2015,'IX','Sukirman','085767887654','Nelayan','Karang Jaya'),('706','Cindy Septi Rahayu','2016-01-22','Perempuan',2015,'IX','Talen','08216789907','Nelayan','Karang Jaya'),('707','Dedy Dafitra','2016-01-18','Laki - laki',2015,'IX','Daus','08578879987','Supir','Serengsem'),('708','Devi Gusti Yulianita','2016-01-06','Perempuan',2015,'IX','Zainudin','08786554321','Buruh Bangunan','Rangai'),('709','Dinda Adel Lia','2016-01-15','Perempuan',2015,'IX','Purwoto','08217665432','Pedagang','Tanjung Rame'),('710','Divia Bharati','2016-01-21','Perempuan',2015,'IX','Zulkifli','082178654321','Guru','Garuntang'),('711','Gun Satria LS.JO','2016-01-06','Laki - laki',2015,'IX','Pranto','087765676543','Petani','Serengsem	'),('744','A Deri Dwi Saputra','2016-01-02','Laki - laki',2015,'VIII','Heru','087769987654','Wiraswasta','Panjang'),('745','Adelia','2016-01-02','Perempuan',2015,'VIII','Panji','087788778990','Pedagang','Panjang'),('746','Arief Rakhman','2016-01-04','Laki - laki',2015,'VIII','Gustino','0877699078','PNS','etung SelatanTeluk B'),('748','Ayu Andira','2016-01-06','Perempuan',2015,'VIII','Purwono','08236788789','PNS','Rangai Selatan'),('749','Bintang Rizki Agustina','2016-01-07','Perempuan',2015,'VIII','Prapto','087769907865','Buruh','Rangai'),('750','Darus Al Faruq','2016-01-08','Laki - laki',2015,'VIII','Wiranto','082179906543','Petani','Karang'),('751','Ermasari','2016-01-09','Perempuan',2015,'VIII','Sukijo','082169907876','Petani','Batu Suluh'),('752','Fika Leviana','2016-01-11','Perempuan',2015,'VIII','Marjinem','085376564545','PNS','Tanjung Raya'),('753','Fika Nidia','2016-01-11','Perempuan',2015,'VIII','Mrjinem','085376564545','PNS','Tanjung Rame'),('754','Heni Monika','2016-01-11','Perempuan',2015,'VIII','Pandi','082179987654','Buruh','Panjang'),('765','Septi Rahayu','2015-12-08','Perempuan',2015,'VIII','Budi','087868776543','Wiraswasta','Panjang'),('772','handoko','2016-01-22','Laki - laki',2015,'VIII','gero','097868786654','tani','panjang'),('783','Agung Syafta Wijaya','2016-01-01','Laki - laki',2015,'VII','Budi','08776888790','Wiraswasta','Panjang'),('784','Alifa Salsabila','2016-01-02','Perempuan',2015,'VII','Ferdi','082179909999','Wiraswasta','Rangai'),('785','Anggi Saputra','2016-01-03','Laki - laki',2015,'VII','Bedry','08776998765','Pedagang','Rangai'),('786','Ani Apriyani','2016-01-04','Perempuan',2015,'VII','Agus','081556567899','Pedagang','Teluk Betung Selatan'),('787','Berliana Junaidi','2016-01-05','Laki - laki',2015,'VII','Ferdiyan','08217990765','Buruh','Selat Malaka'),('789','Dian Ovita','2016-01-06','Perempuan',2015,'VII','Pamungkas','08217998765','Wiraswasta','Panjang'),('790','Dina Sartika','2016-01-07','Perempuan',2015,'VII','Dwiyanto','087765776543','Pedagang','Panjang'),('791','Dinda Asari','2016-01-08','Perempuan',2015,'VII','Hardiyansah','085278897890','Wiraswasta','Jalan Baru'),('792','Dini Auliani Nuro','2016-01-09','Perempuan',2015,'VII','Budi','08527998789','Wiraswasta','Rangai'),('793','Fatmawati','2016-01-11','Perempuan',2015,'VII','Hardi','082179906565','Pedagang','Panjang'),('794','Febri Atmajaya','2016-01-20','Laki - laki',2015,'VII','Benni','086778987654','Guru','Tarahan'),('795','Gilang Arya Nugraha','2016-01-07','Laki - laki',2015,'VII','Gombloh','08776544345','Wiraswasta','Tarahan'),('799','Karansyah','2016-01-15','Laki - laki',2015,'IX','Budia','08575654333','Wiraswasta','Serengsem Panjang');

/*Table structure for table `spp` */

DROP TABLE IF EXISTS `spp`;

CREATE TABLE `spp` (
  `id_transaksi` char(12) NOT NULL,
  `NIS` char(12) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `totalbayar` int(11) DEFAULT NULL,
  `jumlahbln` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_transaksi`),
  KEY `NIS` (`NIS`),
  CONSTRAINT `spp_ibfk_1` FOREIGN KEY (`NIS`) REFERENCES `siswa` (`NIS`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `spp` */

insert  into `spp`(`id_transaksi`,`NIS`,`tanggal`,`totalbayar`,`jumlahbln`) values ('TRX-SPP-0001','783','2016-01-01',80000,2),('TRX-SPP-0002','701','2016-01-06',40000,1),('TRX-SPP-0003','701','2016-01-22',40000,1),('TRX-SPP-0004','701','2016-01-14',40000,1),('TRX-SPP-0005','701','2016-01-22',40000,1),('TRX-SPP-0006','754','2016-01-11',120000,3),('TRX-SPP-0007','753','2016-01-03',40000,1),('TRX-SPP-0008','753','2016-01-15',40000,1),('TRX-SPP-0009','744','2016-01-30',80000,2),('TRX-SPP-0010','745','2015-11-02',80000,2),('TRX-SPP-0011','707','2016-01-07',40000,1),('TRX-SPP-0012','789','2016-01-07',40000,1),('TRX-SPP-0013','772','2016-01-04',80000,2),('TRX-SPP-0014','707','2016-01-08',80000,2),('TRX-SPP-0015','710','2016-01-19',40000,1),('TRX-SPP-0016','711','2016-01-12',120000,3),('TRX-SPP-0017','709','2016-01-05',160000,4),('TRX-SPP-0018','708','2016-01-09',120000,3),('TRX-SPP-0019','792','2016-01-04',80000,2),('TRX-SPP-0020','704','2016-01-14',80000,2),('TRX-SPP-0021','784','2016-01-04',80000,2),('TRX-SPP-0022','785','2016-01-12',200000,5),('TRX-SPP-0023','754','2016-01-05',40000,1),('TRX-SPP-0024','765','2016-01-08',40000,1),('TRX-SPP-0025','752','2016-01-14',40000,1),('TRX-SPP-0026','799','2016-01-08',40000,1),('TRX-SPP-0027','706','2016-01-06',40000,1),('TRX-SPP-0028','711','2016-01-21',0,0),('TRX-SPP-0029','785','2016-01-13',40000,1);

/*Table structure for table `trxdetail` */

DROP TABLE IF EXISTS `trxdetail`;

CREATE TABLE `trxdetail` (
  `id_trx` int(11) NOT NULL AUTO_INCREMENT,
  `id_transaksi` char(12) DEFAULT NULL,
  `harga` int(30) DEFAULT NULL,
  `bulan` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_trx`),
  KEY `id_transaksi` (`id_transaksi`),
  KEY `id_kelas` (`harga`),
  CONSTRAINT `trxdetail_ibfk_1` FOREIGN KEY (`id_transaksi`) REFERENCES `spp` (`id_transaksi`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=latin1;

/*Data for the table `trxdetail` */

insert  into `trxdetail`(`id_trx`,`id_transaksi`,`harga`,`bulan`) values (73,'TRX-SPP-0001',40000,'January'),(74,'TRX-SPP-0001',40000,'Februay'),(75,'TRX-SPP-0002',40000,'January'),(76,'TRX-SPP-0003',40000,'Februay'),(77,'TRX-SPP-0004',40000,'Maret'),(78,'TRX-SPP-0005',40000,'April'),(79,'TRX-SPP-0006',40000,'January'),(80,'TRX-SPP-0006',40000,'Februay'),(81,'TRX-SPP-0006',40000,'Maret'),(82,'TRX-SPP-0007',40000,'January'),(83,'TRX-SPP-0008',40000,'Februay'),(84,'TRX-SPP-0009',40000,'January'),(85,'TRX-SPP-0009',40000,'Februay'),(86,'TRX-SPP-0010',40000,'January'),(87,'TRX-SPP-0010',40000,'Februay'),(88,'TRX-SPP-0011',40000,'January'),(89,'TRX-SPP-0012',40000,'January'),(90,'TRX-SPP-0013',40000,'January'),(91,'TRX-SPP-0013',40000,'Februay'),(92,'TRX-SPP-0014',40000,'Februay'),(93,'TRX-SPP-0014',40000,'Maret'),(94,'TRX-SPP-0015',40000,'January'),(95,'TRX-SPP-0016',40000,'January'),(96,'TRX-SPP-0016',40000,'Februay'),(97,'TRX-SPP-0016',40000,'Maret'),(98,'TRX-SPP-0017',40000,'January'),(99,'TRX-SPP-0017',40000,'Februay'),(100,'TRX-SPP-0017',40000,'Maret'),(101,'TRX-SPP-0017',40000,'April'),(102,'TRX-SPP-0018',40000,'January'),(103,'TRX-SPP-0018',40000,'Februay'),(104,'TRX-SPP-0018',40000,'Maret'),(105,'TRX-SPP-0019',40000,'January'),(106,'TRX-SPP-0019',40000,'Februay'),(107,'TRX-SPP-0020',40000,'January'),(108,'TRX-SPP-0020',40000,'Februay'),(109,'TRX-SPP-0021',40000,'January'),(110,'TRX-SPP-0021',40000,'Februay'),(111,'TRX-SPP-0022',40000,'January'),(112,'TRX-SPP-0022',40000,'Februay'),(113,'TRX-SPP-0022',40000,'Maret'),(114,'TRX-SPP-0022',40000,'April'),(115,'TRX-SPP-0022',40000,'Mei'),(116,'TRX-SPP-0023',40000,'April'),(117,'TRX-SPP-0024',40000,'January'),(118,'TRX-SPP-0025',40000,'January'),(119,'TRX-SPP-0026',40000,'January'),(120,'TRX-SPP-0027',40000,'Maret'),(121,'TRX-SPP-0029',40000,'Juni');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`Username`,`Password`) values ('Admin','xx'),('Bendahara','Zahra');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
