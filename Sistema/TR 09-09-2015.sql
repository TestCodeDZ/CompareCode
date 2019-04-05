-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 09-09-2015 a las 12:26:24
-- Versión del servidor: 5.1.41
-- Versión de PHP: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `techorojo`
--
CREATE DATABASE `techorojo` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
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
  `Correo` varchar(40) NOT NULL,
  PRIMARY KEY (`RUT`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `clientes`
--


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

--
-- Volcar la base de datos para la tabla `controldiag`
--


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
-- Volcar la base de datos para la tabla `desperfectos`
--

INSERT INTO `desperfectos` (`ID`, `Descripcion`, `Costo`) VALUES
('D001', 'Cambio de rueda', 6000),
('D002', 'Falta de Aire', 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detallediag`
--

CREATE TABLE IF NOT EXISTS `detallediag` (
  `ID_Detalle` int(10) NOT NULL AUTO_INCREMENT,
  `Cod_Desp` int(10) NOT NULL,
  `NumDiag` int(10) NOT NULL,
  `Desc_Desperfecto` int(4) NOT NULL,
  `Cantidad` int(2) NOT NULL,
  `PrecioUnitario` int(10) NOT NULL,
  `PrecioTotal` int(7) NOT NULL,
  `Estado` int(3) NOT NULL,
  PRIMARY KEY (`ID_Detalle`),
  KEY `Desperfectos` (`Desc_Desperfecto`),
  KEY `Estado` (`Estado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `detallediag`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadodiag`
--

CREATE TABLE IF NOT EXISTS `estadodiag` (
  `ID` int(1) NOT NULL AUTO_INCREMENT,
  `Estado` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Estado` (`Estado`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `estadodiag`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estadoreparacion`
--

CREATE TABLE IF NOT EXISTS `estadoreparacion` (
  `ID` int(3) NOT NULL AUTO_INCREMENT,
  `EstadoReparacion` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Volcar la base de datos para la tabla `estadoreparacion`
--


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
-- Volcar la base de datos para la tabla `marcas`
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
-- Volcar la base de datos para la tabla `sucursal`
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
-- Volcar la base de datos para la tabla `tipousuario`
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
  `ID` varchar(4) NOT NULL,
  `Nombres` varchar(50) NOT NULL,
  `Apellidos` varchar(50) NOT NULL,
  `TipoUsuario` int(1) NOT NULL,
  `Usuario` char(30) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Sucursal` int(2) NOT NULL,
  UNIQUE KEY `Usuario` (`Usuario`),
  KEY `TipoUsuario` (`TipoUsuario`),
  KEY `Sucursal` (`Sucursal`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `usuarios`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE IF NOT EXISTS `vehiculo` (
  `Patente` varchar(6) NOT NULL,
  `Marca` int(4) NOT NULL,
  `Modelo` int(11) NOT NULL,
  `Año` int(4) NOT NULL,
  `Color` char(12) NOT NULL,
  `Dueño` varchar(10) NOT NULL,
  PRIMARY KEY (`Patente`),
  KEY `Marcas` (`Marca`),
  KEY `Dueño` (`Dueño`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `vehiculo`
--


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
