-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-09-2016 a las 03:58:19
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `feriatec`
--
CREATE DATABASE IF NOT EXISTS `feriatec` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `feriatec`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conferencia`
--

CREATE TABLE IF NOT EXISTS `conferencia` (
  `id_conferencia` int(11) NOT NULL,
  `titulo` varchar(50) NOT NULL,
  `conferencista` varchar(50) NOT NULL,
  PRIMARY KEY (`id_conferencia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `conferencia`
--

INSERT INTO `conferencia` (`id_conferencia`, `titulo`, `conferencista`) VALUES
(1010, 'El QR EN NUESTROS TIEMPOS', 'PROF. ISMAEL'),
(1020, 'EL CODE BAR EN TODO EL MUNDO ', 'ALUMNO.ED MTZ'),
(20103, 'salome', 'UPN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `participante`
--

CREATE TABLE IF NOT EXISTS `participante` (
  `idUsuario` char(8) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Correo` varchar(50) NOT NULL,
  `Contrasena` varchar(15) NOT NULL,
  `Universidad` varchar(30) NOT NULL,
  `perfil` int(2) NOT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `participante`
--

INSERT INTO `participante` (`idUsuario`, `Nombre`, `Correo`, `Contrasena`, `Universidad`, `perfil`) VALUES
('01010', 'prueba10', 'prueba@gmail.com', '123', 'UTL', 1),
('1000', 'Lalillo', 'lalo@gmail.com', '123', 'utl', 1),
('100010', 'Angel Martinez Espinoza', 'angel@gmail.com', '123', 'UTL', 1),
('140000', 'Leocadio Olivares', 'leocadio@gmail.com', '123', 'UTL', 1),
('14002001', 'isma2', 'isma2@gmail.com', '123', 'UTL', 1),
('14002010', 'Raul Bravo Leon', 'raulito@gmail.com', '123', 'UTL', 1),
('14002020', 'Aaron Fonseca', 'aaron@gmail.com', '123', 'UTL', 1),
('14002030', 'Gerson Lopez', 'gerson@gmail.com', '123', 'UTL', 1),
('14002040', 'Ismael ', 'isma@gmail.com', '123', 'UTL', 1),
('14002050', 'Felipe Quiroz ', 'felipe@gmail.com', '123', 'UTL', 1),
('14002060', 'Daniel Sanita Juarez', 'sanita@gmail.com', '123', 'UTL', 1),
('14002070', 'Abraham Alvarado Garcia ', 'abraham@gmail.com', '123', 'UTL', 1),
('14002080', 'Eduardo Martinez Espinoza', 'eduardo.martinez.2117@gmail.com', 'Eduardo1991', 'UTL', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro`
--

CREATE TABLE IF NOT EXISTS `registro` (
  `id_registro` int(8) NOT NULL AUTO_INCREMENT,
  `idUsuarioReg` char(8) NOT NULL,
  `id_conferenciaReg` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  PRIMARY KEY (`id_registro`),
  KEY `FK_IDUSU` (`idUsuarioReg`),
  KEY `FK_IDCON` (`id_conferenciaReg`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29233 ;

--
-- Volcado de datos para la tabla `registro`
--

INSERT INTO `registro` (`id_registro`, `idUsuarioReg`, `id_conferenciaReg`, `fecha`, `hora`) VALUES
(29212, '14002080', 1010, '2015-10-28', '06:43:22'),
(29213, '14002080', 1020, '2015-10-28', '06:45:17'),
(29226, '140000', 1010, '2015-10-28', '06:49:50'),
(29227, '140000', 1020, '2015-10-28', '06:50:17'),
(29228, '14002010', 1020, '2015-10-28', '06:50:49'),
(29229, '14002020', 1010, '2015-10-28', '07:19:20'),
(29230, '14002010', 1010, '2015-10-29', '01:02:08'),
(29231, '14002010', 1010, '2015-10-29', '01:02:52'),
(29232, '01010', 1010, '2015-10-29', '01:03:59');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `registro`
--
ALTER TABLE `registro`
  ADD CONSTRAINT `FK_IDCON` FOREIGN KEY (`id_conferenciaReg`) REFERENCES `conferencia` (`id_conferencia`),
  ADD CONSTRAINT `FK_IDUSU` FOREIGN KEY (`idUsuarioReg`) REFERENCES `participante` (`idUsuario`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
