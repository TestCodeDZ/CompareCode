-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-11-2015 a las 20:40:42
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `techorojo`
--
CREATE DATABASE IF NOT EXISTS `techorojo` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `techorojo`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `RUT` varchar(10) NOT NULL,
  `Nombres` varchar(50) NOT NULL,
  `Apellidos` varchar(50) NOT NULL,
  `Contacto` int(15) NOT NULL,
  `Direccion` varchar(30) NOT NULL,
  `Correo` varchar(40) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`RUT`, `Nombres`, `Apellidos`, `Contacto`, `Direccion`, `Correo`) VALUES
('18044006-2', 'kevinazo', 'pino', 83387891, 'xcfgdfzxgdzfg', 'kpino.zulucorp@hotmail.com'),
('18700990-1', 'Ignacio', 'Carrasco', 83228112, 'asdasdasd', 'nicooabarca@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobante`
--

CREATE TABLE IF NOT EXISTS `comprobante` (
  `Numero` varchar(8) NOT NULL,
  `Cliente` varchar(10) NOT NULL,
  `ModoPago` varchar(30) NOT NULL,
  `Total` int(7) NOT NULL,
  `PagadoCon` int(7) NOT NULL,
  `Vuelto` int(6) NOT NULL,
  `Fecha` varchar(10) NOT NULL,
  `Hora` varchar(11) NOT NULL,
  `Vendedor` char(100) NOT NULL,
  `Sucursal` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `comprobante`
--

INSERT INTO `comprobante` (`Numero`, `Cliente`, `ModoPago`, `Total`, `PagadoCon`, `Vuelto`, `Fecha`, `Hora`, `Vendedor`, `Sucursal`) VALUES
('00000001', '18044006-2', 'Efectivo', 3980, 4000, 20, '06-11-2015', '00:30:45 AM', 'null null', 'Casa Matriz'),
('00000002', '18700990-1', 'Cheque', 19900, 19900, 0, '06-11-2015', '00:44:27 AM', 'null null', 'Casa Matriz'),
('00000003', '18044006-2', 'Cheque', 19900, 19900, 0, '06-11-2015', '02:11:46 AM', 'null null', 'Casa Matriz');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controldiag`
--

CREATE TABLE IF NOT EXISTS `controldiag` (
  `ID_Diagnostico` varchar(8) NOT NULL,
  `Patente` varchar(6) NOT NULL,
  `RUTCliente` varchar(10) NOT NULL,
  `Mecanico` char(100) NOT NULL,
  `F_ing_Diagnostico` varchar(10) NOT NULL,
  `F_Ent_Diag` varchar(10) NOT NULL,
  `Repuestos` varchar(255) NOT NULL,
  `Total` int(7) NOT NULL,
  `Estado_Diag` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `controldiag`
--

INSERT INTO `controldiag` (`ID_Diagnostico`, `Patente`, `RUTCliente`, `Mecanico`, `F_ing_Diagnostico`, `F_Ent_Diag`, `Repuestos`, `Total`, `Estado_Diag`) VALUES
('00000001', 'DH2732', '18700990-1', 'zulu Corporation', '05-11-2015', '05-11-2015', 'asdfasdsadasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd', 396000, 'Aceptado'),
('00000002', 'aaaa22', '18044006-2', 'zulu Corporation', '09-11-2015', '09-11-2015', 'revision motor arreglo de ruedas y cambio de aceite', 655990, 'Aceptado'),
('00000003', 'aaaa22', '18044006-2', 'zulu Corporation', '12-11-2015', '13-11-2015', 'cambio de ruedas xD', 99000, 'Aceptado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `desperfectos`
--

CREATE TABLE IF NOT EXISTS `desperfectos` (
  `ID` varchar(10) NOT NULL,
  `Descripcion` varchar(30) NOT NULL,
  `Costo` int(7) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `desperfectos`
--

INSERT INTO `desperfectos` (`ID`, `Descripcion`, `Costo`) VALUES
('CD0001', 'Cambio de Ruedas', 99000),
('CD0002', 'falla motor', 250000),
('CD0003', 'Cambio de Aceite', 9990);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallecomprobante`
--

CREATE TABLE IF NOT EXISTS `detallecomprobante` (
  `NumComp` varchar(8) NOT NULL,
  `CodPro` varchar(10) NOT NULL,
  `DescProducto` varchar(50) NOT NULL,
  `Cantidad` int(4) NOT NULL,
  `PrecioUnitario` int(6) NOT NULL,
  `PrecioTotal` int(7) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detallecomprobante`
--

INSERT INTO `detallecomprobante` (`NumComp`, `CodPro`, `DescProducto`, `Cantidad`, `PrecioUnitario`, `PrecioTotal`) VALUES
('00000001', 'CI0001', 'nada', 2, 1990, 3980),
('00000002', 'CI0001', 'nada', 10, 1990, 19900),
('00000003', 'CI0001', 'nada', 10, 1990, 19900);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallediag`
--

CREATE TABLE IF NOT EXISTS `detallediag` (
  `ID_Diag` varchar(8) NOT NULL,
  `Cod_Desp` varchar(10) NOT NULL,
  `Desc_Desperfecto` varchar(50) NOT NULL,
  `Cantidad` int(1) NOT NULL,
  `PrecioDesp` int(7) NOT NULL,
  `Ptotal` int(7) NOT NULL,
  `Estado` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detallediag`
--

INSERT INTO `detallediag` (`ID_Diag`, `Cod_Desp`, `Desc_Desperfecto`, `Cantidad`, `PrecioDesp`, `Ptotal`, `Estado`) VALUES
('00000001', 'CD0001', 'Cambio de Ruedas', 4, 99000, 396000, 'Reparado'),
('00000002', 'CD0001', 'Cambio de Ruedas', 4, 99000, 396000, 'Reparado'),
('00000002', 'CD0002', 'falla motor', 1, 250000, 250000, 'Reparado'),
('00000002', 'CD0003', 'Cambio de Aceite', 1, 9990, 9990, 'Reparado'),
('00000003', 'CD0001', 'Cambio de Ruedas', 1, 99000, 99000, 'Reparado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadodiag`
--

CREATE TABLE IF NOT EXISTS `estadodiag` (
`ID` int(1) NOT NULL,
  `Estado` varchar(15) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estadodiag`
--

INSERT INTO `estadodiag` (`ID`, `Estado`) VALUES
(1, 'Ingresado'),
(2, 'Aceptado'),
(3, 'Rechazado'),
(4, 'Reparado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadoreparacion`
--

CREATE TABLE IF NOT EXISTS `estadoreparacion` (
`ID` int(3) NOT NULL,
  `EstadoReparacion` varchar(30) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estadoreparacion`
--

INSERT INTO `estadoreparacion` (`ID`, `EstadoReparacion`) VALUES
(1, 'En Taller'),
(2, 'En Reparación'),
(3, 'Reparado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumos`
--

CREATE TABLE IF NOT EXISTS `insumos` (
  `Codigo` varchar(10) NOT NULL,
  `Descripcion` varchar(50) NOT NULL,
  `Precio` int(6) NOT NULL,
  `Stock` int(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `insumos`
--

INSERT INTO `insumos` (`Codigo`, `Descripcion`, `Precio`, `Stock`) VALUES
('CI0001', 'nada', 1990, 58);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE IF NOT EXISTS `marcas` (
  `ID` varchar(10) NOT NULL,
  `NombreMarca` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `marcas`
--

INSERT INTO `marcas` (`ID`, `NombreMarca`) VALUES
('MV0001', 'audi'),
('MV0002', 'chevrolet'),
('MV0003', 'Nissan'),
('MV0004', 'Mitsubishi'),
('MV0005', 'Chevron'),
('MV0006', 'Samsung'),
('MV0007', 'Tata'),
('MV0008', 'FIAT'),
('MV0009', 'LADA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modopago`
--

CREATE TABLE IF NOT EXISTS `modopago` (
`IDMP` int(11) NOT NULL,
  `Desc_MP` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `modopago`
--

INSERT INTO `modopago` (`IDMP`, `Desc_MP`) VALUES
(1, 'Cheque'),
(2, 'Efectivo'),
(3, 'Tarjeta de Crédito');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagoreparaciones`
--

CREATE TABLE IF NOT EXISTS `pagoreparaciones` (
  `NumDiag` varchar(8) NOT NULL,
  `ModoPago` varchar(30) NOT NULL,
  `TotalReparacion` int(7) NOT NULL,
  `PagadoCon` int(7) NOT NULL,
  `Vuelto` int(6) NOT NULL,
  `FechaPago` varchar(10) NOT NULL,
  `HoraPago` varchar(8) NOT NULL,
  `RecibidoPor` char(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursal`
--

CREATE TABLE IF NOT EXISTS `sucursal` (
`ID_Suc` int(2) NOT NULL,
  `NombreSucursal` varchar(20) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `sucursal`
--

INSERT INTO `sucursal` (`ID_Suc`, `NombreSucursal`) VALUES
(1, 'Casa Matriz'),
(2, 'Susursal 1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE IF NOT EXISTS `tipousuario` (
`IDTU` int(1) NOT NULL,
  `TipoUser` varchar(20) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`IDTU`, `TipoUser`) VALUES
(1, 'Dueño'),
(2, 'Jefe de Taller'),
(3, 'Mecánico'),
(4, 'Secretaria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
`IDTB` int(11) NOT NULL,
  `ID` varchar(10) NOT NULL,
  `Nombres` varchar(50) NOT NULL,
  `Apellidos` varchar(50) NOT NULL,
  `TipoUsuario` int(1) NOT NULL,
  `Usuario` char(30) NOT NULL,
  `Password` varchar(128) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`IDTB`, `ID`, `Nombres`, `Apellidos`, `TipoUsuario`, `Usuario`, `Password`) VALUES
(1, 'US0002', 'Kevinazo', 'Vinazo', 2, 'Kev.Vina', 'b8bdee49ce093cea2154bd379ecc81ce124287b1bd74b830ed72bb3df559ceeb79c591ee749c20aec0898abb8c32ef9abc218227b6285c2ce794456cd9aeadac'),
(2, 'US0001', 'kevin', 'pino', 1, 'kev.pino', 'bcdcbdbc75bb95dbf89cdeca4c2497f56183a324fccc07097701f403211771faa8380fc3816f26cb33ae7a1616a9bc6815375216886f51d0b539ab19f843e985'),
(3, 'US0003', 'zulu', 'Corporation', 3, 'zul.Corp', 'fe4523a0585e418805ad5b0a0ee506d7a4cdee402c6072073447dd7abdcc44d70d9e2317297511d4a46dcd88bd29ab411ef8e42a159f143a0c92735af071580d'),
(4, 'US0004', 'Darth', 'ZuluCorp', 4, 'Dar.Zulu', 'eef2684d52163733cfa44958f613edb468ddafda6b0b17b644d6c266f69a37a9304602282206a5ff6a1f69a705ff645bca6120ccbbf7dbd3e9f72618ab7a16e8'),
(6, 'US0005', 'dfgdgdgfdg', 'dfgsdfgdfgdfg', 2, 'dfg.dfgs', 'c6a57fcfbe66b57c3d233411a9ece8eb36fa5c73c5cea55201901a8ee21534bcc4b7e08590c99f2dc4db70c06bb4a2252a629047d7f9cff69349d0ba4322dd6c');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE IF NOT EXISTS `vehiculo` (
  `Patente` varchar(6) NOT NULL,
  `Marca` varchar(50) NOT NULL,
  `Modelo` varchar(50) NOT NULL,
  `Año` int(4) NOT NULL,
  `Color` char(12) NOT NULL,
  `Dueño` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`Patente`, `Marca`, `Modelo`, `Año`, `Color`, `Dueño`) VALUES
('aaaa22', 'chevrolet', 'CORSA', 1999, 'caca', '18044006-2'),
('DH2732', 'audi', 'a4', 2001, 'blanco', '18700990-1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
 ADD PRIMARY KEY (`RUT`);

--
-- Indices de la tabla `comprobante`
--
ALTER TABLE `comprobante`
 ADD PRIMARY KEY (`Numero`);

--
-- Indices de la tabla `controldiag`
--
ALTER TABLE `controldiag`
 ADD PRIMARY KEY (`ID_Diagnostico`), ADD KEY `EDiag` (`Estado_Diag`), ADD KEY `Mecanico` (`Mecanico`);

--
-- Indices de la tabla `desperfectos`
--
ALTER TABLE `desperfectos`
 ADD PRIMARY KEY (`ID`), ADD UNIQUE KEY `Descripcion` (`Descripcion`), ADD UNIQUE KEY `ID` (`ID`), ADD KEY `Descripcion_2` (`Descripcion`);

--
-- Indices de la tabla `detallediag`
--
ALTER TABLE `detallediag`
 ADD KEY `Desperfectos` (`Desc_Desperfecto`), ADD KEY `Estado` (`Estado`);

--
-- Indices de la tabla `estadodiag`
--
ALTER TABLE `estadodiag`
 ADD PRIMARY KEY (`ID`), ADD UNIQUE KEY `Estado` (`Estado`);

--
-- Indices de la tabla `estadoreparacion`
--
ALTER TABLE `estadoreparacion`
 ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `insumos`
--
ALTER TABLE `insumos`
 ADD PRIMARY KEY (`Codigo`), ADD UNIQUE KEY `Descripcion` (`Descripcion`);

--
-- Indices de la tabla `marcas`
--
ALTER TABLE `marcas`
 ADD UNIQUE KEY `NombreMarca` (`NombreMarca`), ADD UNIQUE KEY `ID` (`ID`);

--
-- Indices de la tabla `modopago`
--
ALTER TABLE `modopago`
 ADD PRIMARY KEY (`IDMP`);

--
-- Indices de la tabla `sucursal`
--
ALTER TABLE `sucursal`
 ADD PRIMARY KEY (`ID_Suc`), ADD UNIQUE KEY `Sucursal` (`NombreSucursal`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
 ADD PRIMARY KEY (`IDTU`), ADD UNIQUE KEY `TipoUsuario` (`TipoUser`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
 ADD PRIMARY KEY (`IDTB`), ADD UNIQUE KEY `Usuario` (`Usuario`), ADD KEY `TipoUsuario` (`TipoUsuario`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
 ADD PRIMARY KEY (`Patente`), ADD KEY `Marcas` (`Marca`), ADD KEY `Dueño` (`Dueño`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `estadodiag`
--
ALTER TABLE `estadodiag`
MODIFY `ID` int(1) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `estadoreparacion`
--
ALTER TABLE `estadoreparacion`
MODIFY `ID` int(3) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `modopago`
--
ALTER TABLE `modopago`
MODIFY `IDMP` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `sucursal`
--
ALTER TABLE `sucursal`
MODIFY `ID_Suc` int(2) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
MODIFY `IDTU` int(1) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
MODIFY `IDTB` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
