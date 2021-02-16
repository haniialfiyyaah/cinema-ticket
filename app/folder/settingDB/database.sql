/*
SQLyog Community v9.63 
MySQL - 5.5.5-10.1.30-MariaDB : Database - dbbioskop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dbbioskop` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `dbbioskop`;

/*Table structure for table `dummyhelp` */

DROP TABLE IF EXISTS `dummyhelp`;

CREATE TABLE `dummyhelp` (
  `akses` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `dummyhelp` */

insert  into `dummyhelp`(`akses`) values ('KRY0000001');

/*Table structure for table `dummykursi` */

DROP TABLE IF EXISTS `dummykursi`;

CREATE TABLE `dummykursi` (
  `Kursi` varchar(100) DEFAULT NULL,
  `Jumlah` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `dummykursi` */

/*Table structure for table `dummytikethelp` */

DROP TABLE IF EXISTS `dummytikethelp`;

CREATE TABLE `dummytikethelp` (
  `judul` varchar(50) DEFAULT NULL,
  `studio` varchar(10) DEFAULT NULL,
  `tanggal` varchar(10) DEFAULT NULL,
  `jam` varchar(5) DEFAULT NULL,
  `harga` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `dummytikethelp` */

/*Table structure for table `film` */

DROP TABLE IF EXISTS `film`;

CREATE TABLE `film` (
  `ID_Film` varchar(10) NOT NULL,
  `Judul_Film` varchar(50) DEFAULT NULL,
  `Tahun_Film` year(4) DEFAULT NULL,
  `ID_Genre` varchar(10) DEFAULT NULL,
  `DurasiFilm` int(11) DEFAULT NULL,
  `Sutradara` varchar(25) DEFAULT NULL,
  `Deskripsi` varchar(1000) DEFAULT NULL,
  `Poster` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_Film`),
  UNIQUE KEY `Judul_Film` (`Judul_Film`),
  KEY `film_ibfk_1` (`ID_Genre`),
  CONSTRAINT `film_ibfk_1` FOREIGN KEY (`ID_Genre`) REFERENCES `film.genre` (`ID_Genre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `film` */

/*Table structure for table `film.aktor` */

DROP TABLE IF EXISTS `film.aktor`;

CREATE TABLE `film.aktor` (
  `ID_Film` varchar(10) DEFAULT NULL,
  `Aktor` varchar(25) DEFAULT NULL,
  KEY `film.aktor_ibfk_1` (`ID_Film`),
  CONSTRAINT `film.aktor_ibfk_1` FOREIGN KEY (`ID_Film`) REFERENCES `film` (`ID_Film`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `film.aktor` */

/*Table structure for table `film.genre` */

DROP TABLE IF EXISTS `film.genre`;

CREATE TABLE `film.genre` (
  `ID_Genre` varchar(10) NOT NULL,
  `Nama_Genre` varchar(15) NOT NULL,
  PRIMARY KEY (`ID_Genre`),
  UNIQUE KEY `Nama_Genre` (`Nama_Genre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `film.genre` */

/*Table structure for table `jadwal` */

DROP TABLE IF EXISTS `jadwal`;

CREATE TABLE `jadwal` (
  `ID_Jadwal` varchar(10) NOT NULL,
  `ID_Film` varchar(10) DEFAULT NULL,
  `Studio` varchar(10) DEFAULT NULL,
  `Harga` double DEFAULT NULL,
  PRIMARY KEY (`ID_Jadwal`),
  UNIQUE KEY `Studio` (`Studio`),
  KEY `jadwal_ibfk_1` (`ID_Film`),
  CONSTRAINT `jadwal_ibfk_1` FOREIGN KEY (`ID_Film`) REFERENCES `film` (`ID_Film`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `jadwal` */

/*Table structure for table `jadwal.jam` */

DROP TABLE IF EXISTS `jadwal.jam`;

CREATE TABLE `jadwal.jam` (
  `ID_Jadwal` varchar(10) DEFAULT NULL,
  `Jam_Tayang` time DEFAULT NULL,
  KEY `jadwal.jam_ibfk_1` (`ID_Jadwal`),
  CONSTRAINT `jadwal.jam_ibfk_1` FOREIGN KEY (`ID_Jadwal`) REFERENCES `jadwal` (`ID_Jadwal`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `jadwal.jam` */

/*Table structure for table `karyawan` */

DROP TABLE IF EXISTS `karyawan`;

CREATE TABLE `karyawan` (
  `ID_Karyawan` varchar(10) NOT NULL,
  `Nama_Lengkap` varchar(50) DEFAULT NULL,
  `No_Telepon` varchar(14) DEFAULT NULL,
  `Tempat_Lahir` varchar(25) DEFAULT NULL,
  `Tanggal_Lahir` date DEFAULT NULL,
  `Jenis_Kelamin` varchar(10) DEFAULT NULL,
  `Jalan` varchar(25) DEFAULT NULL,
  `No` varchar(5) DEFAULT NULL,
  `Provinsi` varchar(25) DEFAULT NULL,
  `Kab_Kota` varchar(25) DEFAULT NULL,
  `Kecamatan` varchar(25) DEFAULT NULL,
  `Kelurahan` varchar(25) DEFAULT NULL,
  `RT` varchar(5) DEFAULT NULL,
  `RW` varchar(5) DEFAULT NULL,
  `Status_Kawin` varchar(15) DEFAULT NULL,
  `Tanggal_Masuk_Kerja` date DEFAULT NULL,
  `Jabatan` varchar(20) DEFAULT NULL,
  `Tanggal_Keluar_Kerja` date DEFAULT NULL,
  `Status_Aktif` varchar(11) DEFAULT NULL,
  `Foto` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_Karyawan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `karyawan` */

insert  into `karyawan`(`ID_Karyawan`,`Nama_Lengkap`,`No_Telepon`,`Tempat_Lahir`,`Tanggal_Lahir`,`Jenis_Kelamin`,`Jalan`,`No`,`Provinsi`,`Kab_Kota`,`Kecamatan`,`Kelurahan`,`RT`,`RW`,`Status_Kawin`,`Tanggal_Masuk_Kerja`,`Jabatan`,`Tanggal_Keluar_Kerja`,`Status_Aktif`,`Foto`) values ('KRY0000001','HANI ALFIYYAH N','081322897367','SUMBAWA','1997-05-16','PEREMPUAN','SEROJA','13','JAWA BARAT','SUBANG','SUBANG','KARANGANYAR','69','19','BELUM MENIKAH','2017-12-01','STAFF ADMINISTRASI',NULL,'AKTIF','PassFoto1.jpg'),('KRY0000002','JOHN','089611493897','Bekasi','1997-01-26','LAKI-LAKI','Anyelir','17','Jawa Barat','Bandung','Coblong','Lebakgede','01','01','MENIKAH','2018-01-26','KASIR',NULL,'AKTIF','PassFoto.jgp');

/*Table structure for table `karyawan.akunlogin` */

DROP TABLE IF EXISTS `karyawan.akunlogin`;

CREATE TABLE `karyawan.akunlogin` (
  `Username` varchar(10) NOT NULL,
  `Password` varchar(10) DEFAULT NULL,
  `ID_Karyawan` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Username`),
  KEY `ID_Karyawan` (`ID_Karyawan`),
  CONSTRAINT `karyawan.akunlogin_ibfk_1` FOREIGN KEY (`ID_Karyawan`) REFERENCES `karyawan` (`ID_Karyawan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `karyawan.akunlogin` */

insert  into `karyawan.akunlogin`(`Username`,`Password`,`ID_Karyawan`) values ('ADMIN','admin','KRY0000001'),('KASIR','kasir','KRY0000002');

/*Table structure for table `relasi_pesantiket` */

DROP TABLE IF EXISTS `relasi_pesantiket`;

CREATE TABLE `relasi_pesantiket` (
  `ID_Tiket` varchar(10) NOT NULL,
  `ID_Struk` varchar(10) DEFAULT NULL,
  `ID_Jadwal` varchar(10) DEFAULT NULL,
  `ID_Karyawan` varchar(10) DEFAULT NULL,
  `Judul_Film` varchar(50) DEFAULT NULL,
  `Tanggal` date DEFAULT NULL,
  `Jam` time DEFAULT NULL,
  `Harga` double DEFAULT NULL,
  `Studio` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`ID_Tiket`),
  KEY `ID_Jadwal` (`ID_Jadwal`),
  KEY `ID_Karyawan` (`ID_Karyawan`),
  KEY `ID_Struk` (`ID_Struk`),
  CONSTRAINT `relasi_pesantiket_ibfk_1` FOREIGN KEY (`ID_Jadwal`) REFERENCES `jadwal` (`ID_Jadwal`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `relasi_pesantiket_ibfk_2` FOREIGN KEY (`ID_Karyawan`) REFERENCES `karyawan` (`ID_Karyawan`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `relasi_pesantiket_ibfk_3` FOREIGN KEY (`ID_Struk`) REFERENCES `strukpembayaran` (`ID_Struk`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `relasi_pesantiket` */

/*Table structure for table `relasi_pesantiket.kursi` */

DROP TABLE IF EXISTS `relasi_pesantiket.kursi`;

CREATE TABLE `relasi_pesantiket.kursi` (
  `ID_Tiket` varchar(10) DEFAULT NULL,
  `Kursi` varchar(3) DEFAULT NULL,
  KEY `ID_Tiket` (`ID_Tiket`),
  CONSTRAINT `relasi_pesantiket.kursi_ibfk_1` FOREIGN KEY (`ID_Tiket`) REFERENCES `relasi_pesantiket` (`ID_Tiket`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `relasi_pesantiket.kursi` */

/*Table structure for table `strukpembayaran` */

DROP TABLE IF EXISTS `strukpembayaran`;

CREATE TABLE `strukpembayaran` (
  `ID_Struk` varchar(10) NOT NULL COMMENT 'id_struk',
  `Tanggal_Beli` date DEFAULT NULL,
  `Jam_Beli` time DEFAULT NULL,
  `Jumlah_Item` int(11) DEFAULT NULL,
  `Total` decimal(10,0) DEFAULT NULL,
  `Diskon` decimal(10,0) DEFAULT NULL,
  `Total_Bayar` decimal(10,0) DEFAULT NULL,
  `Payment` decimal(10,0) DEFAULT NULL,
  `Change` decimal(10,0) DEFAULT NULL,
  `Nama_Kasir` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`ID_Struk`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `strukpembayaran` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
