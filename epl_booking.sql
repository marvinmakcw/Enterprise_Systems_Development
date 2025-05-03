-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2023 at 06:21 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `epl_booking`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `venueId` int(11) NOT NULL,
  `memberId` int(11) NOT NULL,
  `startTime` varchar(255) NOT NULL,
  `endTime` varchar(255) NOT NULL,
  `status` enum('pending','accept','reject','cancel') NOT NULL DEFAULT 'pending',
  `listId` int(11) NOT NULL,
  `requestTime` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id`, `date`, `venueId`, `memberId`, `startTime`, `endTime`, `status`, `listId`, `requestTime`) VALUES
(1, '2023-05-02', 1, 1, '09:00', '11:00', 'pending', 2, '2023-04-28 11:24:59'),
(2, '2023-05-01', 2, 1, '09:00', '11:00', 'pending', 2, '2023-04-28 11:25:50'),
(3, '2023-05-05', 1, 1, '13:00', '14:00', 'pending', 1, '2023-04-28 11:29:15'),
(4, '2023-04-29', 1, 1, '09:00', '09:00', 'pending', 1, '2023-04-28 11:33:42'),
(5, '2023-04-29', 1, 1, '09:00', '09:00', 'pending', 1, '2023-04-28 12:03:30'),
(6, '2023-04-29', 3, 1, '09:00', '09:00', 'pending', 1, '2023-04-28 12:08:43'),
(7, '2023-04-30', 2, 1, '09:00', '10:00', 'pending', 1, '2023-04-28 12:10:03'),
(8, '2023-05-05', 1, 1, '16:00', '17:00', 'pending', 1, '2023-04-28 12:10:15');

-- --------------------------------------------------------

--
-- Table structure for table `guest`
--

CREATE TABLE `guest` (
  `guestId` int(11) NOT NULL,
  `listId` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `memberId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `guest`
--

INSERT INTO `guest` (`guestId`, `listId`, `name`, `email`, `memberId`) VALUES
(1, 1, 'Marvin', 'marvin@gmail.com', 1),
(2, 1, 'Oscar', 'oscar@gmail.com', 1),
(3, 2, 'Alex', 'alex@gmail.com', 1),
(4, 2, 'Amy', 'amy@gmail.com', 1),
(5, 2, 'Ben', 'ben@gmail.com', 1),
(6, 2, 'Candy', 'candy@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('m','s','a') NOT NULL,
  `phone_no` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `role`, `phone_no`) VALUES
(1, 'marvin', '123', 'm', 61234567),
(2, 'oscar', '123', 's', 51234567),
(3, 'jesse', '123', 'a', 91234567);

-- --------------------------------------------------------

--
-- Table structure for table `venue`
--

CREATE TABLE `venue` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `capacity` int(11) NOT NULL,
  `location` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `person_ic` int(11) NOT NULL,
  `booking_fee` int(11) NOT NULL,
  `status` enum('available','booked') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `venue`
--

INSERT INTO `venue` (`id`, `name`, `type`, `capacity`, `location`, `description`, `person_ic`, `booking_fee`, `status`) VALUES
(1, 'Tuen Mun IVE', 'Sports Hall', 200, 'Tuen Mun', 'Tuen Mun IVE Sports Hall', 2, 180, 'available'),
(2, 'Sha Tin IVE', 'Classroom', 30, 'Sha Tin', 'Sha Tin IVE Classroom', 2, 20, 'available'),
(3, 'Tsing Yi IVE', 'Classroom', 30, 'Tsing Yi', 'Tsing Yi IVE Classroom', 2, 30, 'available'),
(4, 'Lee Wai Lee IVE', 'Lecture', 120, 'Lee Wai Lee', 'Lee Wai Lee IVE Lecture', 2, 120, 'available'),
(5, 'Chai Wan IVE', 'Lecture', 100, 'Chai Wan IVE', 'Chai Wan IVE Lecture', 2, 90, 'available');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_venueId` (`venueId`),
  ADD KEY `FK_memberId` (`memberId`);

--
-- Indexes for table `guest`
--
ALTER TABLE `guest`
  ADD PRIMARY KEY (`guestId`,`listId`),
  ADD KEY `FK_id` (`memberId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `venue`
--
ALTER TABLE `venue`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_person_ic` (`person_ic`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `venue`
--
ALTER TABLE `venue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `FK_memberId` FOREIGN KEY (`memberId`) REFERENCES `venue` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `guest`
--
ALTER TABLE `guest`
  ADD CONSTRAINT `FK_id` FOREIGN KEY (`memberId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `venue`
--
ALTER TABLE `venue`
  ADD CONSTRAINT `FK_person_ic` FOREIGN KEY (`person_ic`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
