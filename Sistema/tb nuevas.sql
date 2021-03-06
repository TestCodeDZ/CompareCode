-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-10-2015 a las 06:51:04
-- Versión del servidor: 5.6.16
-- Versión de PHP: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `techorojo`
--

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
  `Correo` varchar(40) NOT NULL,
  PRIMARY KEY (`RUT`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`RUT`, `Nombres`, `Apellidos`, `Contacto`, `Direccion`, `Correo`) VALUES
('18044006-2', 'Kevin', 'Pino', 88888888, 'Comercio 666', 'kpinobravo@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprobante`
--

CREATE TABLE IF NOT EXISTS `comprobante` (
  `Numero` int(8) NOT NULL,
  `Cliente` varchar(10) NOT NULL,
  `Total` int(7) NOT NULL,
  `Fecha` varchar(10) NOT NULL,
  PRIMARY KEY (`Numero`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `controldiag`
--

CREATE TABLE IF NOT EXISTS `controldiag` (
  `ID_Diagnostico` int(11) NOT NULL AUTO_INCREMENT,
  `Patente` varchar(6) NOT NULL,
  `RUTCliente` varchar(10) NOT NULL,
  `Mecanico` int(3) NOT NULL,
  `F_ing_Diagnostico` varchar(10) NOT NULL,
  `F_Ent_Diag` varchar(10) NOT NULL,
  `Repuestos` varchar(255) NOT NULL,
  `Cotizacion` int(10) NOT NULL,
  `Estado_Diag` int(1) NOT NULL,
  PRIMARY KEY (`ID_Diagnostico`),
  KEY `EDiag` (`Estado_Diag`),
  KEY `Mecanico` (`Mecanico`),
  KEY `Cot` (`Cotizacion`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `desperfectos`
--

CREATE TABLE IF NOT EXISTS `desperfectos` (
  `ID` varchar(10) NOT NULL,
  `Descripcion` varchar(30) NOT NULL,
  `Costo` int(7) NOT NULL,
  UNIQUE KEY `Descripcion` (`Descripcion`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `desperfectos`
--

INSERT INTO `desperfectos` (`ID`, `Descripcion`, `Costo`) VALUES
('D001', 'Cambio de rueda', 6000),
('D002', 'Falta de Aire', 500);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallecomprobante`
--

CREATE TABLE IF NOT EXISTS `detallecomprobante` (
  `NumComp` int(8) NOT NULL,
  `CodPro` int(10) NOT NULL,
  `DescProducto` varchar(50) NOT NULL,
  `Cantidad` int(4) NOT NULL,
  `PrecioUnitario` int(6) NOT NULL,
  `PrecioTotal` int(7) NOT NULL,
  PRIMARY KEY (`NumComp`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallediag`
--

CREATE TABLE IF NOT EXISTS `detallediag` (
  `ID_Detalle` int(10) NOT NULL AUTO_INCREMENT,
  `Cod_Desp` int(10) NOT NULL,
  `NumDiag` int(10) NOT NULL,
  `Desc_Desperfecto` varchar(50) NOT NULL,
  `Cantidad` int(2) NOT NULL,
  `PrecioUnitario` int(10) NOT NULL,
  `PrecioTotal` int(7) NOT NULL,
  `Estado` int(3) NOT NULL,
  PRIMARY KEY (`ID_Detalle`),
  KEY `Desperfectos` (`Desc_Desperfecto`),
  KEY `Estado` (`Estado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadodiag`
--

CREATE TABLE IF NOT EXISTS `estadodiag` (
  `ID` int(1) NOT NULL AUTO_INCREMENT,
  `Estado` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Estado` (`Estado`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

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

CREATE TABLE IF NOT EXISTS `estadoreparacion` (
  `ID` int(3) NOT NULL AUTO_INCREMENT,
  `EstadoReparacion` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumos`
--

CREATE TABLE IF NOT EXISTS `insumos` (
  `Codigo` varchar(10) NOT NULL,
  `Descripcion` varchar(50) NOT NULL,
  `Precio` int(6) NOT NULL,
  `Stock` int(4) NOT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `insumos`
--

INSERT INTO `insumos` (`Codigo`, `Descripcion`, `Precio`, `Stock`) VALUES
('I001', 'asdasd', 435234, 2342),
('I002', 'sdfsdfsdf', 343543, 4543),
('I003', 'sdf', 435, 3),
('I004', 'sdfdsfdsf', 435435, 3454);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE IF NOT EXISTS `marcas` (
  `ID` varchar(4) NOT NULL,
  `NombreMarca` varchar(50) NOT NULL,
  UNIQUE KEY `NombreMarca` (`NombreMarca`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `marcas`
--

INSERT INTO `marcas` (`ID`, `NombreMarca`) VALUES
('M001', 'Mitsubishi'),
('M002', 'Nissan');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursal`
--

CREATE TABLE IF NOT EXISTS `sucursal` (
  `ID_Suc` int(2) NOT NULL AUTO_INCREMENT,
  `NombreSucursal` varchar(20) NOT NULL,
  PRIMARY KEY (`ID_Suc`),
  UNIQUE KEY `Sucursal` (`NombreSucursal`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

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
  `IDTU` int(1) NOT NULL AUTO_INCREMENT,
  `TipoUser` varchar(20) NOT NULL,
  PRIMARY KEY (`IDTU`),
  UNIQUE KEY `TipoUsuario` (`TipoUser`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

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
  `IDTB` int(11) NOT NULL AUTO_INCREMENT,
  `ID` varchar(4) NOT NULL,
  `Nombres` varchar(50) NOT NULL,
  `Apellidos` varchar(50) NOT NULL,
  `TipoUsuario` int(1) NOT NULL,
  `Usuario` char(30) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Sucursal` int(2) NOT NULL,
  PRIMARY KEY (`IDTB`),
  UNIQUE KEY `Usuario` (`Usuario`),
  KEY `TipoUsuario` (`TipoUsuario`),
  KEY `Sucursal` (`Sucursal`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`IDTB`, `ID`, `Nombres`, `Apellidos`, `TipoUsuario`, `Usuario`, `Password`, `Sucursal`) VALUES
(2, 'U002', 'Kevinazo', 'Vinazo', 2, 'Kev.Vina', '1391', 2),
(3, 'U001', 'kevin', 'pino', 1, 'kev.pino', '2734', 1),
(4, 'U003', 'zulu', 'Corporation', 3, 'zul.Corp', '1111', 1),
(5, 'U004', 'Trilazo', 'trol', 4, 'Tri.trol', '4049', 1),
(6, 'U005', '12331', '11213', 1, '123.1121', '6896', 1);

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
  `Dueño` varchar(10) NOT NULL,
  PRIMARY KEY (`Patente`),
  KEY `Marcas` (`Marca`),
  KEY `Dueño` (`Dueño`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`Patente`, `Marca`, `Modelo`, `Año`, `Color`, `Dueño`) VALUES
('sfdsd2', 'Mitsubishi', 'lancer', 2000, 'Negro', '18044006-2'),
('SSSS22', 'Nissan', 'V15', 1998, 'Azùl', '18044006-2');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
