-- phpMyAdmin SQL Dump
-- version 4.5.0.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-10-2015 a las 08:52:58
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `desperfectos`
--

CREATE TABLE `desperfectos` (
  `ID` varchar(10) NOT NULL,
  `Descripcion` varchar(30) NOT NULL,
  `Costo` int(7) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
(1, 'US0002', 'Kevinazo', 'Vinazo', 2, 'Kev.Vina', '1391'),
(2, 'US0017', 'kevin', 'pino', 1, 'kev.pino', '4220'),
(3, 'US0003', 'zulu', 'Corporation', 3, 'zul.Corp', '1111'),
(4, 'US0018', 'Darth', 'ZuluCorp', 3, 'Dar.Zulu', '1964');

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
  MODIFY `IDTB` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
