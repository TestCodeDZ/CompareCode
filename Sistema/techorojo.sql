-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 03-12-2015 a las 19:07:45
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`RUT`, `Nombres`, `Apellidos`, `Contacto`, `Direccion`, `Correo`) VALUES
('12367908-3', 'Patricia', 'Lopez', 72665925, 'Concha y Toro 135 Peumo', 'carrasco.lopez.igna@gmail.com'),
('9756201-6', 'Anibal', 'Abarca', 66377257, 'Caupolican 84 Coinco', 'nicooabarca@gmail.com');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `comprobante`
--

INSERT INTO `comprobante` (`Numero`, `Cliente`, `ModoPago`, `Total`, `PagadoCon`, `Vuelto`, `Fecha`, `Hora`, `Vendedor`, `Sucursal`) VALUES
('00000001', '10879164-0', 'Efectivo', 5000, 5000, 0, '26-11-2015', '07:29:35 PM', 'Darth ZuluCorp', 'Casa Matriz'),
('00000002', '10879164-0', 'Efectivo', 40000, 40000, 0, '03-12-2015', '03:25:01 AM', 'Pilar Sordo', 'Casa Matriz');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `controldiag`
--

INSERT INTO `controldiag` (`ID_Diagnostico`, `Patente`, `RUTCliente`, `Mecanico`, `F_ing_Diagnostico`, `F_Ent_Diag`, `Repuestos`, `Total`, `Estado_Diag`) VALUES
('00000013', 'DD8248', '10879164-0', 'Ignacio Carrasco', '02-12-2015', '03-12-2015', 'Cambio de Filtro de Aceite', 9990, 'Reparado'),
('00000014', 'LL6601', '12367908-3', 'Ignacio Carrasco', '03-12-2015', '04-12-2015', 'Cambio de Neumatico\nCambio de Correa Dist', 55500, 'Rechazado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `desperfectos`
--

CREATE TABLE IF NOT EXISTS `desperfectos` (
  `ID` varchar(10) NOT NULL,
  `Descripcion` varchar(30) NOT NULL,
  `Costo` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `desperfectos`
--

INSERT INTO `desperfectos` (`ID`, `Descripcion`, `Costo`) VALUES
('CD0003', 'Cambio de Aceite', 9990),
('CD0004', 'Cambio de Neumático', 20000),
('CD0005', 'Correa de Distribución', 15500),
('CD0006', 'Junta de la Culata', 30000),
('CD0007', 'Inyectores', 35000),
('CD0008', 'Embrague', 50000),
('CD0009', 'Pastillas de Freno', 15000),
('CD0010', 'Cambio de Turbo', 100000),
('CD0011', 'Aire Acondicionado', 30000),
('CD0012', 'Balanceo', 20000),
('CD0013', 'Suspensión', 25000);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detallecomprobante`
--

INSERT INTO `detallecomprobante` (`NumComp`, `CodPro`, `DescProducto`, `Cantidad`, `PrecioUnitario`, `PrecioTotal`) VALUES
('00000001', 'CI0004', 'Aceite de Relleno', 1, 5000, 5000),
('00000002', 'CI0002', 'Llanta', 1, 30000, 30000),
('00000002', 'CI0004', 'Aceite de Relleno', 2, 5000, 10000);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detallediag`
--

INSERT INTO `detallediag` (`ID_Diag`, `Cod_Desp`, `Desc_Desperfecto`, `Cantidad`, `PrecioDesp`, `Ptotal`, `Estado`) VALUES
('00000001', 'CD0001', 'Cambio de Ruedas', 4, 99000, 396000, 'Reparado'),
('00000002', 'CD0001', 'Cambio de Ruedas', 4, 99000, 396000, 'Reparado'),
('00000002', 'CD0002', 'falla motor', 1, 250000, 250000, 'Reparado'),
('00000002', 'CD0003', 'Cambio de Aceite', 1, 9990, 9990, 'Reparado'),
('00000003', 'CD0001', 'Cambio de Ruedas', 1, 99000, 99000, 'Reparado'),
('00000004', 'CD0001', 'Cambio de Ruedas', 1, 99000, 99000, 'Reparado'),
('00000004', 'CD0002', 'falla motor', 1, 250000, 250000, 'Reparado'),
('00000004', 'CD0003', 'Cambio de Aceite', 1, 9990, 9990, 'Reparado'),
('00000005', 'CD0001', 'Cambio de Ruedas', 1, 99000, 99000, 'Reparado'),
('00000006', 'CD0001', 'Cambio de Ruedas', 4, 99000, 396000, 'Reparado'),
('00000007', 'CD0004', 'Cambio de Neumático', 1, 20000, 20000, 'Reparado'),
('00000007', 'CD0004', 'Cambio de Neumático', 4, 20000, 80000, 'Reparado'),
('00000008', 'CD0005', 'Correa de Distribución', 1, 15500, 15500, 'Reparado'),
('00000008', 'CD0004', 'Cambio de Neumático', 1, 20000, 20000, 'Reparado'),
('00000008', 'CD0005', 'Correa de Distribución', 1, 15500, 15500, 'Reparado'),
('00000008', 'CD0004', 'Cambio de Neumático', 2, 20000, 40000, 'Reparado'),
('00000009', 'CD0005', 'Correa de Distribución', 1, 15500, 15500, 'Reparado'),
('00000010', 'CD0004', 'Cambio de Neumático', 2, 20000, 40000, 'Reparado'),
('00000010', 'CD0005', 'Correa de Distribución', 1, 15500, 15500, 'Reparado'),
('00000011', 'CD0005', 'Correa de Distribución', 1, 15500, 15500, 'Reparado'),
('00000012', 'CD0005', 'Correa de Distribución', 1, 15500, 15500, 'En Taller'),
('00000012', 'CD0008', 'Embrague', 1, 50000, 50000, 'En Taller'),
('00000013', 'CD0003', 'Cambio de Aceite', 1, 9990, 9990, 'Reparado'),
('00000014', 'CD0004', 'Cambio de Neumático', 2, 20000, 40000, 'En Taller'),
('00000014', 'CD0005', 'Correa de Distribución', 1, 15500, 15500, 'En Taller');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadodiag`
--

CREATE TABLE IF NOT EXISTS `estadodiag` (
`ID` int(1) NOT NULL,
  `Estado` varchar(15) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estadodiag`
--

INSERT INTO `estadodiag` (`ID`, `Estado`) VALUES
(2, 'Aceptado'),
(5, 'Finalizado'),
(1, 'Ingresado'),
(3, 'Rechazado'),
(4, 'Reparado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadoreparacion`
--

CREATE TABLE IF NOT EXISTS `estadoreparacion` (
`ID` int(3) NOT NULL,
  `EstadoReparacion` varchar(30) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `insumos`
--

INSERT INTO `insumos` (`Codigo`, `Descripcion`, `Precio`, `Stock`) VALUES
('CI0001', 'Aceite Shell 2L', 4990, 234),
('CI0002', 'Llanta', 30000, 11),
('CI0003', 'Kit de Emergencia', 67000, 4),
('CI0004', 'Aceite de Relleno', 5000, 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE IF NOT EXISTS `marcas` (
  `ID` varchar(10) NOT NULL,
  `NombreMarca` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `HoraPago` varchar(11) NOT NULL,
  `RecibidoPor` char(50) NOT NULL,
  `Sucursal` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pagoreparaciones`
--

INSERT INTO `pagoreparaciones` (`NumDiag`, `ModoPago`, `TotalReparacion`, `PagadoCon`, `Vuelto`, `FechaPago`, `HoraPago`, `RecibidoPor`, `Sucursal`) VALUES
('00000001', 'Tarjeta de Crédito', 396000, 396000, 0, '26-11-2015', '03:55:15 AM', 'Darth ZuluCorp', 'Casa Matriz'),
('00000002', 'Efectivo', 655990, 660000, 4010, '26-11-2015', '04:02:56 AM', 'Darth ZuluCorp', 'Casa Matriz'),
('00000004', 'Tarjeta de Crédito', 20000, 20000, 0, '26-11-2015', '04:06:48 AM', 'Darth ZuluCorp', 'Casa Matriz'),
('00000003', 'Tarjeta de Crédito', 99000, 99000, 0, '26-11-2015', '04:07:05 AM', 'Darth ZuluCorp', 'Casa Matriz'),
('00000005', 'Efectivo', 99000, 100000, 1000, '26-11-2015', '06:07:45 PM', 'Darth ZuluCorp', 'Casa Matriz'),
('00000006', 'Efectivo', 396000, 400000, 4000, '26-11-2015', '06:16:21 PM', 'Darth ZuluCorp', 'Casa Matriz'),
('00000007', 'Efectivo', 20000, 20000, 0, '26-11-2015', '06:48:44 PM', 'Darth ZuluCorp', 'Casa Matriz'),
('00000007', 'Efectivo', 100000, 120000, 20000, '26-11-2015', '07:27:49 PM', 'Darth ZuluCorp', 'Casa Matriz'),
('00000008', 'Efectivo', 91000, 100000, 9000, '30-11-2015', '07:04:35 PM', 'Pilar Sordo', 'Casa Matriz'),
('00000009', 'Efectivo', 15500, 20000, 4500, '02-12-2015', '03:32:06 PM', 'Pilar Sordo', 'Casa Matriz');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursal`
--

CREATE TABLE IF NOT EXISTS `sucursal` (
`ID_Suc` int(2) NOT NULL,
  `NombreSucursal` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`IDTB`, `ID`, `Nombres`, `Apellidos`, `TipoUsuario`, `Usuario`, `Password`) VALUES
(1, 'US0002', 'Nicolas', 'Abarca', 2, 'Nic.Abar', 'fe4523a0585e418805ad5b0a0ee506d7a4cdee402c6072073447dd7abdcc44d70d9e2317297511d4a46dcd88bd29ab411ef8e42a159f143a0c92735af071580d'),
(2, 'US0001', 'Kevin', 'Pino', 1, 'Kev.Pino', 'fe4523a0585e418805ad5b0a0ee506d7a4cdee402c6072073447dd7abdcc44d70d9e2317297511d4a46dcd88bd29ab411ef8e42a159f143a0c92735af071580d'),
(3, 'US0003', 'Ignacio', 'Carrasco', 3, 'Ign.Carr', 'fe4523a0585e418805ad5b0a0ee506d7a4cdee402c6072073447dd7abdcc44d70d9e2317297511d4a46dcd88bd29ab411ef8e42a159f143a0c92735af071580d'),
(4, 'US0004', 'Pilar', 'Sordo', 4, 'Pil.Sord', 'fe4523a0585e418805ad5b0a0ee506d7a4cdee402c6072073447dd7abdcc44d70d9e2317297511d4a46dcd88bd29ab411ef8e42a159f143a0c92735af071580d');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`Patente`, `Marca`, `Modelo`, `Año`, `Color`, `Dueño`) VALUES
('aaaa22', 'chevrolet', 'CORSA', 1999, 'verde', '18044006-2'),
('aaaa23', 'chevrolet', 'CORSA', 2005, 'rojo', '9756201-6'),
('DD8248', 'Nissan', 'V16', 1995, 'Rojo', '10879164-0'),
('DH2732', 'audi', 'a4', 2001, 'blanco', '18700990-1'),
('KH1104', 'FIAT', '360', 2005, 'rojo', '18700990-1'),
('LL6601', 'LADA', 'Samara', 1985, 'Negro', '12367908-3'),
('lx6601', 'Mitsubishi', 'Lancer', 2005, 'Azul', '18649216-1'),
('lz6601', 'Nissan', 'V16', 2001, 'plomo', '18333845-5'),
('SP8325', 'Mitsubishi', 'Lancer', 1993, 'Blanco', '9756201-6'),
('XZ7712', 'chevrolet', 'Corsa', 2002, 'Plomo', '18333846-3');

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
MODIFY `IDTB` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
