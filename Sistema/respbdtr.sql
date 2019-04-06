-- MySQL dump 10.16  Distrib 10.1.8-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: techorojo
-- ------------------------------------------------------
-- Server version	10.1.8-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `RUT` varchar(10) NOT NULL,
  `Nombres` varchar(50) NOT NULL,
  `Apellidos` varchar(50) NOT NULL,
  `Contacto` int(15) NOT NULL,
  `Direccion` varchar(30) NOT NULL,
  `Correo` varchar(40) NOT NULL,
  PRIMARY KEY (`RUT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES ('18044006-2','kevinazo','pino',83387891,'xcfgdfzxgdzfg','kpino.zulucorp@hotmail.com'),('18700990-1','Ignacio','Carrasco',83228112,'asdasdasd','kpino.zulucorp@hotmail.com');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comprobante`
--

DROP TABLE IF EXISTS `comprobante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comprobante` (
  `Numero` varchar(8) NOT NULL,
  `Cliente` varchar(10) NOT NULL,
  `ModoPago` varchar(30) NOT NULL,
  `Total` int(7) NOT NULL,
  `PagadoCon` int(7) NOT NULL,
  `Vuelto` int(6) NOT NULL,
  `Fecha` varchar(10) NOT NULL,
  `Hora` varchar(11) NOT NULL,
  `Vendedor` char(100) NOT NULL,
  `Sucursal` varchar(20) NOT NULL,
  PRIMARY KEY (`Numero`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comprobante`
--

LOCK TABLES `comprobante` WRITE;
/*!40000 ALTER TABLE `comprobante` DISABLE KEYS */;
/*!40000 ALTER TABLE `comprobante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `controldiag`
--

DROP TABLE IF EXISTS `controldiag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `controldiag` (
  `ID_Diagnostico` varchar(8) NOT NULL,
  `Patente` varchar(6) NOT NULL,
  `RUTCliente` varchar(10) NOT NULL,
  `Mecanico` char(100) NOT NULL,
  `F_ing_Diagnostico` varchar(10) NOT NULL,
  `F_Ent_Diag` varchar(10) NOT NULL,
  `Repuestos` varchar(255) NOT NULL,
  `Total` int(7) NOT NULL,
  `Estado_Diag` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_Diagnostico`),
  KEY `EDiag` (`Estado_Diag`),
  KEY `Mecanico` (`Mecanico`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `controldiag`
--

LOCK TABLES `controldiag` WRITE;
/*!40000 ALTER TABLE `controldiag` DISABLE KEYS */;
INSERT INTO `controldiag` VALUES ('00000001','DH2732','18700990-1','zulu Corporation','05-11-2015','05-11-2015','asdfasdsadasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd',396000,'Reparado'),('00000002','aaaa22','18044006-2','zulu Corporation','09-11-2015','09-11-2015','revision motor arreglo de ruedas y cambio de aceite',655990,'Reparado'),('00000003','aaaa22','18044006-2','zulu Corporation','12-11-2015','13-11-2015','cambio de ruedas xD',99000,'Reparado'),('00000004','aaaa22','18044006-2','zulu Corporation','16-11-2015','16-11-2015','xczxzzczxczxczcx',358990,'Rechazado');
/*!40000 ALTER TABLE `controldiag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `desperfectos`
--

DROP TABLE IF EXISTS `desperfectos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `desperfectos` (
  `ID` varchar(10) NOT NULL,
  `Descripcion` varchar(30) NOT NULL,
  `Costo` int(7) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Descripcion` (`Descripcion`),
  UNIQUE KEY `ID` (`ID`),
  KEY `Descripcion_2` (`Descripcion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desperfectos`
--

LOCK TABLES `desperfectos` WRITE;
/*!40000 ALTER TABLE `desperfectos` DISABLE KEYS */;
INSERT INTO `desperfectos` VALUES ('CD0001','Cambio de Ruedas',99000),('CD0002','falla motor',250000),('CD0003','Cambio de Aceite',9990);
/*!40000 ALTER TABLE `desperfectos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallecomprobante`
--

DROP TABLE IF EXISTS `detallecomprobante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallecomprobante` (
  `NumComp` varchar(8) NOT NULL,
  `CodPro` varchar(10) NOT NULL,
  `DescProducto` varchar(50) NOT NULL,
  `Cantidad` int(4) NOT NULL,
  `PrecioUnitario` int(6) NOT NULL,
  `PrecioTotal` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallecomprobante`
--

LOCK TABLES `detallecomprobante` WRITE;
/*!40000 ALTER TABLE `detallecomprobante` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallecomprobante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallediag`
--

DROP TABLE IF EXISTS `detallediag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallediag` (
  `ID_Diag` varchar(8) NOT NULL,
  `Cod_Desp` varchar(10) NOT NULL,
  `Desc_Desperfecto` varchar(50) NOT NULL,
  `Cantidad` int(1) NOT NULL,
  `PrecioDesp` int(7) NOT NULL,
  `Ptotal` int(7) NOT NULL,
  `Estado` varchar(20) NOT NULL,
  KEY `Desperfectos` (`Desc_Desperfecto`),
  KEY `Estado` (`Estado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallediag`
--

LOCK TABLES `detallediag` WRITE;
/*!40000 ALTER TABLE `detallediag` DISABLE KEYS */;
INSERT INTO `detallediag` VALUES ('00000001','CD0001','Cambio de Ruedas',4,99000,396000,'Reparado'),('00000002','CD0001','Cambio de Ruedas',4,99000,396000,'Reparado'),('00000002','CD0002','falla motor',1,250000,250000,'Reparado'),('00000002','CD0003','Cambio de Aceite',1,9990,9990,'Reparado'),('00000003','CD0001','Cambio de Ruedas',1,99000,99000,'Reparado'),('00000004','CD0001','Cambio de Ruedas',1,99000,99000,'Reparado'),('00000004','CD0002','falla motor',1,250000,250000,'Reparado'),('00000004','CD0003','Cambio de Aceite',1,9990,9990,'Reparado');
/*!40000 ALTER TABLE `detallediag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadodiag`
--

DROP TABLE IF EXISTS `estadodiag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadodiag` (
  `ID` int(1) NOT NULL AUTO_INCREMENT,
  `Estado` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Estado` (`Estado`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadodiag`
--

LOCK TABLES `estadodiag` WRITE;
/*!40000 ALTER TABLE `estadodiag` DISABLE KEYS */;
INSERT INTO `estadodiag` VALUES (2,'Aceptado'),(1,'Ingresado'),(3,'Rechazado'),(4,'Reparado');
/*!40000 ALTER TABLE `estadodiag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadoreparacion`
--

DROP TABLE IF EXISTS `estadoreparacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadoreparacion` (
  `ID` int(3) NOT NULL AUTO_INCREMENT,
  `EstadoReparacion` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadoreparacion`
--

LOCK TABLES `estadoreparacion` WRITE;
/*!40000 ALTER TABLE `estadoreparacion` DISABLE KEYS */;
INSERT INTO `estadoreparacion` VALUES (1,'En Taller'),(2,'En Reparación'),(3,'Reparado');
/*!40000 ALTER TABLE `estadoreparacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insumos`
--

DROP TABLE IF EXISTS `insumos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `insumos` (
  `Codigo` varchar(10) NOT NULL,
  `Descripcion` varchar(50) NOT NULL,
  `Precio` int(6) NOT NULL,
  `Stock` int(4) NOT NULL,
  PRIMARY KEY (`Codigo`),
  UNIQUE KEY `Descripcion` (`Descripcion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insumos`
--

LOCK TABLES `insumos` WRITE;
/*!40000 ALTER TABLE `insumos` DISABLE KEYS */;
INSERT INTO `insumos` VALUES ('CI0001','czxczc',4990,234);
/*!40000 ALTER TABLE `insumos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcas`
--

DROP TABLE IF EXISTS `marcas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `marcas` (
  `ID` varchar(10) NOT NULL,
  `NombreMarca` varchar(50) NOT NULL,
  UNIQUE KEY `NombreMarca` (`NombreMarca`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcas`
--

LOCK TABLES `marcas` WRITE;
/*!40000 ALTER TABLE `marcas` DISABLE KEYS */;
INSERT INTO `marcas` VALUES ('MV0001','audi'),('MV0002','chevrolet'),('MV0003','Nissan'),('MV0004','Mitsubishi'),('MV0005','Chevron'),('MV0006','Samsung'),('MV0007','Tata'),('MV0008','FIAT'),('MV0009','LADA');
/*!40000 ALTER TABLE `marcas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `modopago`
--

DROP TABLE IF EXISTS `modopago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modopago` (
  `IDMP` int(11) NOT NULL AUTO_INCREMENT,
  `Desc_MP` varchar(30) NOT NULL,
  PRIMARY KEY (`IDMP`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `modopago`
--

LOCK TABLES `modopago` WRITE;
/*!40000 ALTER TABLE `modopago` DISABLE KEYS */;
INSERT INTO `modopago` VALUES (1,'Cheque'),(2,'Efectivo'),(3,'Tarjeta de Crédito');
/*!40000 ALTER TABLE `modopago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagoreparaciones`
--

DROP TABLE IF EXISTS `pagoreparaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pagoreparaciones` (
  `NumDiag` varchar(8) NOT NULL,
  `ModoPago` varchar(30) NOT NULL,
  `TotalReparacion` int(7) NOT NULL,
  `PagadoCon` int(7) NOT NULL,
  `Vuelto` int(6) NOT NULL,
  `FechaPago` varchar(10) NOT NULL,
  `HoraPago` varchar(8) NOT NULL,
  `RecibidoPor` char(50) NOT NULL,
  `Sucursal` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagoreparaciones`
--

LOCK TABLES `pagoreparaciones` WRITE;
/*!40000 ALTER TABLE `pagoreparaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagoreparaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sucursal` (
  `ID_Suc` int(2) NOT NULL AUTO_INCREMENT,
  `NombreSucursal` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_Suc`),
  UNIQUE KEY `Sucursal` (`NombreSucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
INSERT INTO `sucursal` VALUES (1,'Casa Matriz'),(2,'Susursal 1');
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipousuario`
--

DROP TABLE IF EXISTS `tipousuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipousuario` (
  `IDTU` int(1) NOT NULL AUTO_INCREMENT,
  `TipoUser` varchar(20) NOT NULL,
  PRIMARY KEY (`IDTU`),
  UNIQUE KEY `TipoUsuario` (`TipoUser`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipousuario`
--

LOCK TABLES `tipousuario` WRITE;
/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
INSERT INTO `tipousuario` VALUES (1,'Dueño'),(2,'Jefe de Taller'),(3,'Mecánico'),(4,'Secretaria');
/*!40000 ALTER TABLE `tipousuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `IDTB` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(10) NOT NULL,
  `Nombres` varchar(50) NOT NULL,
  `Apellidos` varchar(50) NOT NULL,
  `TipoUsuario` int(1) NOT NULL,
  `Usuario` char(30) NOT NULL,
  `Password` varchar(128) NOT NULL,
  PRIMARY KEY (`IDTB`),
  UNIQUE KEY `Usuario` (`Usuario`),
  KEY `TipoUsuario` (`TipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'US0002','Kevinazo','Vinazo',2,'Kev.Vina','b8bdee49ce093cea2154bd379ecc81ce124287b1bd74b830ed72bb3df559ceeb79c591ee749c20aec0898abb8c32ef9abc218227b6285c2ce794456cd9aeadac'),(2,'US0001','kevin','pino',1,'kev.pino','bcdcbdbc75bb95dbf89cdeca4c2497f56183a324fccc07097701f403211771faa8380fc3816f26cb33ae7a1616a9bc6815375216886f51d0b539ab19f843e985'),(3,'US0003','zulu','Corporation',3,'zul.Corp','fe4523a0585e418805ad5b0a0ee506d7a4cdee402c6072073447dd7abdcc44d70d9e2317297511d4a46dcd88bd29ab411ef8e42a159f143a0c92735af071580d'),(4,'US0004','Darth','ZuluCorp',4,'Dar.Zulu','eef2684d52163733cfa44958f613edb468ddafda6b0b17b644d6c266f69a37a9304602282206a5ff6a1f69a705ff645bca6120ccbbf7dbd3e9f72618ab7a16e8'),(6,'US0005','dfgdgdgfdg','dfgsdfgdfgdfg',2,'dfg.dfgs','c6a57fcfbe66b57c3d233411a9ece8eb36fa5c73c5cea55201901a8ee21534bcc4b7e08590c99f2dc4db70c06bb4a2252a629047d7f9cff69349d0ba4322dd6c');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehiculo` (
  `Patente` varchar(6) NOT NULL,
  `Marca` varchar(50) NOT NULL,
  `Modelo` varchar(50) NOT NULL,
  `Año` int(4) NOT NULL,
  `Color` char(12) NOT NULL,
  `Dueño` varchar(10) NOT NULL,
  PRIMARY KEY (`Patente`),
  KEY `Marcas` (`Marca`),
  KEY `Dueño` (`Dueño`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES ('aaaa22','chevrolet','CORSA',1999,'verde','18044006-2'),('DH2732','audi','a4',2001,'blanco','18700990-1');
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-26  2:17:42
