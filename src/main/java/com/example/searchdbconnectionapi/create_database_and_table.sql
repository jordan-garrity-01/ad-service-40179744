CREATE TABLE `ads` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `keyword` varchar(254) NOT NULL,
  `advert` text NOT NULL
);

ALTER TABLE `ads`
  ADD PRIMARY KEY (`advertid`);