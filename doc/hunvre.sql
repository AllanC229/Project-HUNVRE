-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : lun. 23 fév. 2026 à 10:12
-- Version du serveur : 8.0.30
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `hunvre`
--

-- --------------------------------------------------------

--
-- Structure de la table `carte`
--

CREATE TABLE `carte` (
  `id_carte` int UNSIGNED NOT NULL,
  `recto` char(2) COLLATE utf8mb4_general_ci NOT NULL,
  `couleur` varchar(7) COLLATE utf8mb4_general_ci NOT NULL,
  `valeur` int NOT NULL,
  `ref_visuel` int UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `contrainte`
--

CREATE TABLE `contrainte` (
  `id_contrainte` int UNSIGNED NOT NULL,
  `valeur` int DEFAULT NULL,
  `couleur` varchar(7) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `main` int DEFAULT NULL,
  `defausse` int DEFAULT NULL,
  `ref_visuel` int UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `deck_carte`
--

CREATE TABLE `deck_carte` (
  `ref_utilisateur` int UNSIGNED NOT NULL,
  `ref_carte` int UNSIGNED NOT NULL,
  `quantite` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `deck_joker`
--

CREATE TABLE `deck_joker` (
  `ref_utilisateur` int UNSIGNED NOT NULL,
  `ref_joker` int UNSIGNED NOT NULL,
  `quantite` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `deck_mod`
--

CREATE TABLE `deck_mod` (
  `ref_utilisateur` int UNSIGNED NOT NULL,
  `ref_mod` int UNSIGNED NOT NULL,
  `quantite` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `joker`
--

CREATE TABLE `joker` (
  `id_joker` int UNSIGNED NOT NULL,
  `nom` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `multi` float DEFAULT NULL,
  `jetons` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `modificateur`
--

CREATE TABLE `modificateur` (
  `id_mod` int UNSIGNED NOT NULL,
  `nom` varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  `valeur` int DEFAULT NULL,
  `couleur` varchar(7) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `rarete` varchar(6) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id_utilisateur` int UNSIGNED NOT NULL,
  `nom` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `mdp` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(6) COLLATE utf8mb4_general_ci NOT NULL,
  `blinde` int DEFAULT NULL,
  `score` int UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `visuel`
--

CREATE TABLE `visuel` (
  `id_visuel` int UNSIGNED NOT NULL,
  `verso` char(2) COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `carte`
--
ALTER TABLE `carte`
  ADD PRIMARY KEY (`id_carte`),
  ADD KEY `ref_visuel` (`ref_visuel`);

--
-- Index pour la table `contrainte`
--
ALTER TABLE `contrainte`
  ADD PRIMARY KEY (`id_contrainte`),
  ADD KEY `ref_visuel` (`ref_visuel`);

--
-- Index pour la table `deck_carte`
--
ALTER TABLE `deck_carte`
  ADD PRIMARY KEY (`ref_utilisateur`,`ref_carte`),
  ADD KEY `ref_carte` (`ref_carte`);

--
-- Index pour la table `deck_joker`
--
ALTER TABLE `deck_joker`
  ADD PRIMARY KEY (`ref_utilisateur`,`ref_joker`),
  ADD KEY `ref_joker` (`ref_joker`);

--
-- Index pour la table `deck_mod`
--
ALTER TABLE `deck_mod`
  ADD PRIMARY KEY (`ref_utilisateur`,`ref_mod`),
  ADD KEY `ref_mod` (`ref_mod`);

--
-- Index pour la table `joker`
--
ALTER TABLE `joker`
  ADD PRIMARY KEY (`id_joker`);

--
-- Index pour la table `modificateur`
--
ALTER TABLE `modificateur`
  ADD PRIMARY KEY (`id_mod`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id_utilisateur`);

--
-- Index pour la table `visuel`
--
ALTER TABLE `visuel`
  ADD PRIMARY KEY (`id_visuel`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `carte`
--
ALTER TABLE `carte`
  MODIFY `id_carte` int UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `contrainte`
--
ALTER TABLE `contrainte`
  MODIFY `id_contrainte` int UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `joker`
--
ALTER TABLE `joker`
  MODIFY `id_joker` int UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `modificateur`
--
ALTER TABLE `modificateur`
  MODIFY `id_mod` int UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id_utilisateur` int UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `visuel`
--
ALTER TABLE `visuel`
  MODIFY `id_visuel` int UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `carte`
--
ALTER TABLE `carte`
  ADD CONSTRAINT `carte_ibfk_1` FOREIGN KEY (`ref_visuel`) REFERENCES `visuel` (`id_visuel`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `contrainte`
--
ALTER TABLE `contrainte`
  ADD CONSTRAINT `contrainte_ibfk_1` FOREIGN KEY (`ref_visuel`) REFERENCES `visuel` (`id_visuel`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `deck_carte`
--
ALTER TABLE `deck_carte`
  ADD CONSTRAINT `deck_carte_ibfk_1` FOREIGN KEY (`ref_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `deck_carte_ibfk_2` FOREIGN KEY (`ref_carte`) REFERENCES `carte` (`id_carte`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `deck_joker`
--
ALTER TABLE `deck_joker`
  ADD CONSTRAINT `deck_joker_ibfk_1` FOREIGN KEY (`ref_joker`) REFERENCES `joker` (`id_joker`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `deck_joker_ibfk_2` FOREIGN KEY (`ref_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Contraintes pour la table `deck_mod`
--
ALTER TABLE `deck_mod`
  ADD CONSTRAINT `deck_mod_ibfk_1` FOREIGN KEY (`ref_mod`) REFERENCES `modificateur` (`id_mod`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `deck_mod_ibfk_2` FOREIGN KEY (`ref_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
