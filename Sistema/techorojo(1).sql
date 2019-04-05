-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-10-2015 a las 08:56:16
-- Versión del servidor: 10.0.17-MariaDB
-- Versión de PHP: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `techorojo`
--
CREATE DATABASE IF NOT EXISTS `techorojo` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `techorojo`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
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
('18044006-2', 'Kevin', 'Pino ', 88888888, 'Comercio 999', 'kpinobravo@gmail.com'),
('12121211-0', 'asdasd', 'asdasd', 23234, 'asdsadsa', 'sdfsdfdsf@asdsad.com'),
('11111111-1', 'asdsadasdasdsad', 'asdasdasdasd', 99999999, 'sdfsdfdfdsfsf', 'sdfdsfsdf'),
('34534543-4', 'sdfsdfsdfsdf', 'sdfsdfdsf', 345345345, 'dsfgdsfsdf', 'dfgdsfsdf'),
('12222222-5', 'szxcvxzcxzczx', 'zxczxczxczxczx', 435453453, 'sdcvscsxczc', 'zulu@zulu.zulku'),
('12222223-3', 'sdfsdgg', 'dsfgsfgdfg', 564546456, '456456546546', 'fgsgfgdfg@dfsaf.xxx'),
('12323131-7', 'qsdasdasd', 'asdasda', 42344, '234324234', 'wsdfasdfsd@shdgh.hdgzx');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobante`
--

CREATE TABLE `comprobante` (
  `Numero` varchar(8) NOT NULL,
  `Cliente` varchar(10) NOT NULL,
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

INSERT INTO `comprobante` (`Numero`, `Cliente`, `Total`, `PagadoCon`, `Vuelto`, `Fecha`, `Hora`, `Vendedor`, `Sucursal`) VALUES
('00000001', '18044006-2', 355332, 360000, 4668, '25-10-2015', '10:28:40 PM', 'Trilazo trol', 'Casa Matriz'),
('00000002', '12121211-0', 435, 500, 65, '25-10-2015', '10:30:22 PM', 'Trilazo trol', 'Casa Matriz'),
('00000003', '11111111-1', 999, 2000, 1001, '25-10-2015', '10:30:41 PM', 'Trilazo trol', 'Casa Matriz'),
('00000004', '12222222-5', 345342, 350000, 4658, '25-10-2015', '10:31:03 PM', 'Trilazo trol', 'Casa Matriz'),
('00000005', '11111111-1', 870, 1000, 130, '25-10-2015', '10:31:17 PM', 'Trilazo trol', 'Casa Matriz'),
('00000006', '34534543-4', 1740, 2000, 260, '25-10-2015', '10:31:48 PM', 'Trilazo trol', 'Casa Matriz'),
('00000007', '11111111-1', 435345, 440000, 4655, '25-10-2015', '10:32:15 PM', 'Trilazo trol', 'Casa Matriz'),
('00000008', '34534543-4', 435345, 440000, 4655, '25-10-2015', '10:32:37 PM', 'Trilazo trol', 'Casa Matriz'),
('00000009', '18044006-2', 690684, 700000, 9316, '25-10-2015', '10:32:56 PM', 'Trilazo trol', 'Casa Matriz'),
('00000010', '18044006-2', 534543, 550000, 15457, '25-10-2015', '10:33:20 PM', 'Trilazo trol', 'Casa Matriz'),
('00000011', '18044006-2', 89910, 90000, 90, '26-10-2015', '01:04:11 AM', 'Trilazo trol', 'Casa Matriz'),
('00000012', '34534543-4', 4350, 4500, 150, '26-10-2015', '10:17:14 PM', 'Trilazo trol', 'Casa Matriz');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controldiag`
--

CREATE TABLE `controldiag` (
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
('00000001', 'sfdsd2', '18044006-2', 'zulu Corporation', '25-10-2015', '25-10-2015', 'bcvxbvbxcvb', 4374234, 'Ingresado'),
('00000002', 'ssss44', '12121211-0', 'zulu Corporation', '25-10-2015', '25-10-2015', 'morsa', 200000, 'Aceptado'),
('00000003', 'ssss44', '12121211-0', 'zulu Corporation', '26-10-2015', '26-10-2015', 'dfgdfgdfgdfgdfgdfgdfgdfgdfgdfgfddfgdfgdfgfdgdfgdfgdfgdfgdfgdfgdfgdfgdfgdfgdfgdfgdfgfdgdfgdfgfdgdfgdfgfdgdfgdfgfdgdfgdfgfdgdfsdfdsfsdfsdfsdfsdfsdfdsfdsfsdfdsfdsfdfgsdfgdfgsfdgfdsgsdfgdfsfgdssgdfgfdgsdfggsfdgsgfdgdfgscvcxzvckvnxckxchxsxihchhzxchzxhchzchxzcx', 200000, 'Ingresado'),
('00000004', 'SSSS22', '18044006-2', 'zulu Corporation', '26-10-2015', '26-10-2015', 'vzxcvzxcvcxv', 45990, 'Ingresado'),
('00000005', 'SSSS22', '18044006-2', 'zulu Corporation', '26-10-2015', '26-10-2015', 'sdxcxzczxc', 2342342, 'Ingresado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `desperfectos`
--

CREATE TABLE `desperfectos` (
  `ID` varchar(10) NOT NULL,
  `Descripcion` varchar(30) NOT NULL,
  `Costo` int(7) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `desperfectos`
--

INSERT INTO `desperfectos` (`ID`, `Descripcion`, `Costo`) VALUES
('CD0001', 'cambio de rueda', 4590),
('CD0002', 'morssss', 19900),
('CD0003', 'asdasda', 2342342),
('CD0004', 'qwedqdsad', 4324234),
('CD0005', 'qsdasdasd', 324234),
('CD0006', 'wfdwsdfsdf', 234234),
('CD0007', 'sdfsdf', 34234),
('CD0008', 'werwdfwdf', 2342344),
('CD0009', 'wdfsdf', 3243242),
('CD0010', 'wedfsdf', 2342343),
('CD0011', 'asdasd', 34234),
('CD0012', 'edfsdfsdfsdf', 324),
('CD0013', 'fdsgfg', 345435),
('CD0014', 'cacacaca', 4352342),
('CD0015', 'fbsdfsvg', 1),
('CD0016', 'vbn', 5675),
('CD0017', 'CXVX', 3453);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallecomprobante`
--

CREATE TABLE `detallecomprobante` (
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
('00000001', 'CI0001', 'xczvzxcvcxzv', 1, 9990, 9990),
('00000001', 'CI0003', 'dwfsdf', 1, 345342, 345342),
('00000002', 'CI0002', 'sdfsdf', 1, 435, 435),
('00000003', 'CI0012', 'csvvxc', 1, 999, 999),
('00000004', 'CI0003', 'dwfsdf', 1, 345342, 345342),
('00000005', 'CI0002', 'sdfsdf', 2, 435, 870),
('00000006', 'CI0002', 'sdfsdf', 4, 435, 1740),
('00000007', 'CI0004', 'zxcxzczx', 1, 435345, 435345),
('00000008', 'CI0006', 'sdfsdf', 1, 435345, 435345),
('00000009', 'CI0003', 'dwfsdf', 2, 345342, 690684),
('00000010', 'CI0005', 'dfsdf', 1, 534543, 534543),
('00000011', 'CI0001', 'xczvzxcvcxzv', 9, 9990, 89910),
('00000012', 'CI0002', 'sdfsdf', 10, 435, 4350);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallediag`
--

CREATE TABLE `detallediag` (
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
('00000001', 'CD0001', 'Cambio de rueda', 1, 50000, 50000, 'En Taller'),
('00000001', 'CD0004', 'qwedqdsad', 1, 4324234, 4324234, 'En Taller'),
('00000002', 'CD0001', 'Cambio de rueda', 4, 50000, 200000, 'En Taller'),
('00000003', 'CD0001', 'Cambio de rueda', 4, 50000, 200000, 'En Taller'),
('00000004', 'CD0001', 'Cambio de ruedasss', 1, 45990, 45990, 'En Taller'),
('00000005', 'CD0003', 'asdasda', 1, 2342342, 2342342, 'En Taller');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadodiag`
--

CREATE TABLE `estadodiag` (
  `ID` int(1) NOT NULL,
  `Estado` varchar(15) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estadodiag`
--

INSERT INTO `estadodiag` (`ID`, `Estado`) VALUES
(1, 'Ingresado'),
(2, 'Aceptado'),
(3, 'Rechazado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadoreparacion`
--

CREATE TABLE `estadoreparacion` (
  `ID` int(3) NOT NULL,
  `EstadoReparacion` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumos`
--

CREATE TABLE `insumos` (
  `Codigo` varchar(10) NOT NULL,
  `Descripcion` varchar(50) NOT NULL,
  `Precio` int(6) NOT NULL,
  `Stock` int(4) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `insumos`
--

INSERT INTO `insumos` (`Codigo`, `Descripcion`, `Precio`, `Stock`) VALUES
('CI0001', 'nada', 99990, 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE `marcas` (
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
-- Estructura de tabla para la tabla `sucursal`
--

CREATE TABLE `sucursal` (
  `ID_Suc` int(2) NOT NULL,
  `NombreSucursal` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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

CREATE TABLE `tipousuario` (
  `IDTU` int(1) NOT NULL,
  `TipoUser` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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

CREATE TABLE `usuarios` (
  `IDTB` int(11) NOT NULL,
  `ID` varchar(10) NOT NULL,
  `Nombres` varchar(50) NOT NULL,
  `Apellidos` varchar(50) NOT NULL,
  `TipoUsuario` int(1) NOT NULL,
  `Usuario` char(30) NOT NULL,
  `Password` varchar(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`IDTB`, `ID`, `Nombres`, `Apellidos`, `TipoUsuario`, `Usuario`, `Password`) VALUES
(2, 'US0002', 'Kevinazo', 'Vinazo', 2, 'Kev.Vina', '1391'),
(19, 'US0017', 'kevin', 'pino', 1, 'kev.pino', '4220'),
(4, 'US0003', 'zulu', 'Corporation', 3, 'zul.Corp', '1111'),
(5, 'US0004', 'Trilazo', 'trol', 4, 'Tri.trol', '4049'),
(6, 'US0005', '12331', '11213', 1, '123.1121', '6896'),
(7, 'US0006', 'sdfdfsdfas', 'asdasdsad', 2, 'sdf.asda', '2469'),
(8, 'US0007', 'zxcxzcxzc', 'zxcxzcxzc', 2, 'zxc.zxcx', '1229'),
(9, 'US0008', 'kevinazo', 'Trolazo', 2, 'kev.Trol', '4321'),
(10, 'US0009', 'vcbsxcv', 'zxcvxzvcxvzxv', 1, 'vcb.zxcv', '4321'),
(11, 'US0010', 'dvfvxcvx', 'sdfdfsdfds', 4, 'dvf.sdfd', '2871'),
(12, 'US0011', 'asdasd', 'asdasdsad', 3, 'asd.asda', '2584'),
(13, 'US0012', 'dasdsadsd', 'asdasdasdsad', 2, 'das.asda', '6587'),
(14, 'US0013', 'sadasdasd', 'asdasdasdasdsad', 2, 'sad.asda', '4636'),
(15, 'US0014', 'sdfsdf', 'sdfsdfdsf', 2, 'sdf.sdfs', '5232'),
(16, 'US0015', 'asdfg', 'asdfdfg', 2, 'asd.asdf', '8939'),
(17, 'US0016', 'asdasdsad', 'sadsdss', 1, 'asd.sads', '1881');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
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
('sfdsd2', 'Mitsubishi', 'lancer', 2000, 'Negro', '18044006-2'),
('SSSS22', 'Nissan', 'V15', 1998, 'Azùl', '18044006-2'),
('ssss44', 'audi', 'A4', 2005, 'rojo', '12121211-0'),
('sdcsdc', 'chevrolet', 'morsa', 1999, 'axczxczxc', '11111111-1'),
('ssss21', 'Nissan', 'v16', 1999, 'negro', '12121211-0'),
('asdf11', 'Nissan', 'v16', 1999, 'negro', '12121211-0');

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
  ADD PRIMARY KEY (`ID_Diagnostico`),
  ADD KEY `EDiag` (`Estado_Diag`),
  ADD KEY `Mecanico` (`Mecanico`);

--
-- Indices de la tabla `desperfectos`
--
ALTER TABLE `desperfectos`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Descripcion` (`Descripcion`),
  ADD UNIQUE KEY `ID` (`ID`),
  ADD KEY `Descripcion_2` (`Descripcion`);

--
-- Indices de la tabla `detallediag`
--
ALTER TABLE `detallediag`
  ADD KEY `Desperfectos` (`Desc_Desperfecto`),
  ADD KEY `Estado` (`Estado`);

--
-- Indices de la tabla `estadodiag`
--
ALTER TABLE `estadodiag`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Estado` (`Estado`);

--
-- Indices de la tabla `estadoreparacion`
--
ALTER TABLE `estadoreparacion`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `insumos`
--
ALTER TABLE `insumos`
  ADD PRIMARY KEY (`Codigo`),
  ADD UNIQUE KEY `Descripcion` (`Descripcion`);

--
-- Indices de la tabla `marcas`
--
ALTER TABLE `marcas`
  ADD UNIQUE KEY `NombreMarca` (`NombreMarca`),
  ADD UNIQUE KEY `ID` (`ID`);

--
-- Indices de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD PRIMARY KEY (`ID_Suc`),
  ADD UNIQUE KEY `Sucursal` (`NombreSucursal`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`IDTU`),
  ADD UNIQUE KEY `TipoUsuario` (`TipoUser`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`IDTB`),
  ADD UNIQUE KEY `Usuario` (`Usuario`),
  ADD KEY `TipoUsuario` (`TipoUsuario`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`Patente`),
  ADD KEY `Marcas` (`Marca`),
  ADD KEY `Dueño` (`Dueño`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `estadodiag`
--
ALTER TABLE `estadodiag`
  MODIFY `ID` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `estadoreparacion`
--
ALTER TABLE `estadoreparacion`
  MODIFY `ID` int(3) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  MODIFY `ID_Suc` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `IDTU` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `IDTB` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
