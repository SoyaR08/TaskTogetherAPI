-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 10-03-2025 a las 13:17:22
-- Versión del servidor: 8.1.0
-- Versión de PHP: 8.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `TaskTogether`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Comentario`
--

CREATE TABLE `Comentario` (
  `id` int NOT NULL,
  `content` varchar(140) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `userId` int DEFAULT NULL,
  `taskId` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Historial`
--

CREATE TABLE `Historial` (
  `id` int NOT NULL,
  `projectId` int DEFAULT NULL,
  `userId` int DEFAULT NULL,
  `changeDescription` varchar(255) DEFAULT NULL,
  `changeDate` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Miembros`
--

CREATE TABLE `Miembros` (
  `projectId` int NOT NULL,
  `userId` int NOT NULL,
  `userRol` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `Miembros`
--

INSERT INTO `Miembros` (`projectId`, `userId`, `userRol`) VALUES
(1, 1, 'PROJECTADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Proyecto`
--

CREATE TABLE `Proyecto` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(300) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `user_creator` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `Proyecto`
--

INSERT INTO `Proyecto` (`id`, `name`, `description`, `start_date`, `end_date`, `user_creator`) VALUES
(1, 'Terminar la API', 'Debo terminar el crud de las otras entidades y hacer las pruebas', '2025-03-03', '2025-03-10', 1),
(2, 'Probar el add projects', 'Primera prueba', '2025-03-03', '2025-03-10', 1),
(3, 'Probar el add projects', 'Segunda prueba', '2025-03-03', '2025-03-10', 1),
(4, 'Probar DTO', 'Probando el DTO de añadir proyectos', '2025-03-03', '2025-03-10', 1),
(5, 'Probar DTO 2.0', 'Probando el DTO de añadir proyectos', '2025-03-03', '2025-03-10', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Tarea`
--

CREATE TABLE `Tarea` (
  `id` int NOT NULL,
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  `status` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `priority` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `projectId` int DEFAULT NULL,
  `userId` int DEFAULT NULL
) ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario`
--

CREATE TABLE `Usuario` (
  `id` int NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `role` varchar(50) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `email` varchar(125) NOT NULL,
  `password` varchar(255) NOT NULL,
  `job` varchar(50) DEFAULT NULL
) ;

--
-- Volcado de datos para la tabla `Usuario`
--

INSERT INTO `Usuario` (`id`, `nombre`, `role`, `address`, `email`, `password`, `job`) VALUES
(1, 'Rafa Navarro', 'GEN_ADMIN', 'C/ Development 8', 'rafa140200@gmail.com', '$2a$10$SaKxV9VawHVATtWzX2dS.eVFCkYuuIOGO8kV4TYwgpE/AeGXBKucG', 'Frontend Developer'),
(3, 'Rafa2', 'USER', 'Home', '12345678@gmail.com', '$2a$10$NoJyq2iLBrxIb7sCbc/sVe9VFOeeTtHre6SmkA2cUbPDl6VJ/wIc2', 'Lawyer'),
(6, 'Jose María', 'GEN_ADMIN', 'Calle de la república', '12@gmail.com', '$2a$10$xf00icv.0/pHd53nqe.E3.ztUF5yJgOOw.CUcRHsc4v2EwzXlWenS', 'Programador'),
(9, 'Jose María2', 'USER', 'Calle de la república', '121212@gmail.com', '$2a$10$ixBp1UkVhXLub4q6eHIygurGD1HZOjLdofvDcJmIUQ3TxjwrNgVtG', 'Programador');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Comentario`
--
ALTER TABLE `Comentario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1_COMENTARIO` (`userId`),
  ADD KEY `FK2_COMENTARIO` (`taskId`);

--
-- Indices de la tabla `Historial`
--
ALTER TABLE `Historial`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1_HISTORIAL` (`projectId`),
  ADD KEY `FK2_HISTORIAL` (`userId`);

--
-- Indices de la tabla `Miembros`
--
ALTER TABLE `Miembros`
  ADD PRIMARY KEY (`projectId`,`userId`),
  ADD KEY `FK2_Miembros` (`userId`),
  ADD KEY `projectId` (`projectId`);

--
-- Indices de la tabla `Proyecto`
--
ALTER TABLE `Proyecto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_PROYECTO` (`user_creator`);

--
-- Indices de la tabla `Tarea`
--
ALTER TABLE `Tarea`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1_TAREA` (`projectId`),
  ADD KEY `FK2_TAREA` (`userId`);

--
-- Indices de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Comentario`
--
ALTER TABLE `Comentario`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Historial`
--
ALTER TABLE `Historial`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Proyecto`
--
ALTER TABLE `Proyecto`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `Tarea`
--
ALTER TABLE `Tarea`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Comentario`
--
ALTER TABLE `Comentario`
  ADD CONSTRAINT `FK1_COMENTARIO` FOREIGN KEY (`userId`) REFERENCES `Usuario` (`id`),
  ADD CONSTRAINT `FK2_COMENTARIO` FOREIGN KEY (`taskId`) REFERENCES `Tarea` (`id`);

--
-- Filtros para la tabla `Historial`
--
ALTER TABLE `Historial`
  ADD CONSTRAINT `FK1_HISTORIAL` FOREIGN KEY (`projectId`) REFERENCES `Proyecto` (`id`),
  ADD CONSTRAINT `FK2_HISTORIAL` FOREIGN KEY (`userId`) REFERENCES `Usuario` (`id`);

--
-- Filtros para la tabla `Miembros`
--
ALTER TABLE `Miembros`
  ADD CONSTRAINT `FK1_Miembros` FOREIGN KEY (`projectId`) REFERENCES `Proyecto` (`id`),
  ADD CONSTRAINT `FK2_Miembros` FOREIGN KEY (`userId`) REFERENCES `Usuario` (`id`);

--
-- Filtros para la tabla `Proyecto`
--
ALTER TABLE `Proyecto`
  ADD CONSTRAINT `FK_PROYECTO` FOREIGN KEY (`user_creator`) REFERENCES `Usuario` (`id`);

--
-- Filtros para la tabla `Tarea`
--
ALTER TABLE `Tarea`
  ADD CONSTRAINT `FK1_TAREA` FOREIGN KEY (`projectId`) REFERENCES `Proyecto` (`id`),
  ADD CONSTRAINT `FK2_TAREA` FOREIGN KEY (`userId`) REFERENCES `Usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
