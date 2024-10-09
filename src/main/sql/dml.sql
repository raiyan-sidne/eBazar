INSERT INTO `bid` (`id`, `user_product_id`, `user_id`, `price`) VALUES
(1, 1, 6, 93),
(2, 6, 6, 8500),
(3, 9, 6, 200000),
(4, 6, 2, 9000);

INSERT INTO `chat` (`id`, `user1_user2_id`, `user_id`, `date`, `text`) VALUES
(1, 2, 3, '2021-04-17', 'hello'),
(2, 2, 3, '2021-04-18', 'Hello again'),
(4, 2, 2, '2021-04-18', 'Hi'),
(5, 2, 2, '2021-04-18', 'Hi again'),
(8, 2, 2, '2021-04-18', 'How are you?'),
(9, 2, 3, '2021-04-18', 'Fine. and you?'),
(10, 2, 2, '2021-04-18', 'me too');

INSERT INTO `history` (`id`, `user_id`, `user_product_id`, `status`, `date`, `text`) VALUES
(1, 4, 1, 'approved', '2021-04-15', NULL),
(2, 5, 12, 'rejected', '2021-04-17', 'Seems fake as no valid proof was given.'),
(3, 5, 9, 'approved', '2021-04-17', NULL),
(4, 5, 6, 'approved', '2021-04-17', NULL),
(5, 5, 10, 'rejected', '2021-04-17', 'Name and product doesn\'t match.');

INSERT INTO `image` (`id`, `path`) VALUES
(2, '1618476224106_635102399_Raiyan_Category_A_1.jpg'),
(3, '1618476438163_137136221_1.jpg'),
(4, '1618476565385_336185256_download.jpeg'),
(5, '1618476565386_137749756_download2.jpeg'),
(13, '1618647762380_726342212_66a97029305bd4013fd4330c54f1bcc1.jpg'),
(14, '1618648424215_146150476_how-was-this-breed-created.jpg'),
(15, '1618648424215_457631845_dd77f441aaec1b3fbc776330d436844f.jpg'),
(16, '1618648424215_717051660_q0mfdt9zz5241.jpg'),
(17, '1618648817627_903017472_c709258235cbff884228724cba50b61f.jpg'),
(18, '1618648817627_950621055_Cute-Persian-Kitten-e1537133479384.jpg'),
(19, '1618648817627_204390565_cat-kitten-blue-eyes-wallpaper.jpg'),
(20, '1618649139020_395080608_bd68cdb2d0fc51c4e73c7a9db313cff9.jpg'),
(21, '1618649139021_252213899_kawasaki-ninja-400-36.jpg'),
(22, '1618649139021_912206998_kawasaki-ninja-h2r.jpg'),
(23, '1618649369704_39246458_images.jpeg'),
(24, '1618649369704_836029268_img_2986_0.jpg'),
(25, '1618649635006_564455365_images (2).jpeg'),
(26, '1618649635006_995166239_images (1).jpeg'),
(27, '1618649635006_185510100_download (2).jpeg'),
(28, '1618649635007_103115600_a.jpg'),
(29, '1618650734890_947502243_anime-naruto-itachi-uchiha-wallpaper-preview.jpg'),
(30, '1618650853947_443215915_the-starry-night-18891-e1420799723156.jpg'),
(31, '1618651000969_543025316_bpl--300x174.jpg'),
(32, '1618651000969_769174941_61fqONfrFCL._SY879_.jpg'),
(33, '1618651000969_188749652_8ee797780d31752bd380ef33560d7216.jpg'),
(34, '1618651336819_690350579_220px-Urval_av_de_bocker_som_har_vunnit_Nordiska_radets_litteraturpris_under_de_50_ar_som_priset_funnits_(2).jpg'),
(35, '1618651336819_929971721_best-sales-books-4.jpg'),
(36, '1618651336819_316029379_2560.jpg'),
(37, '1618651542633_626244226_download (3).jpeg'),
(38, '1618651542633_933334289_61ryVJLDlFL._AC_SX569_.jpg'),
(39, '1618651542633_648195880_138763-games-review-sony-ps4-pro-review-image1-gcolf3ytme.jpg'),
(40, '1618651826436_309716855_93cc1839136771.5606a8bd9b59b.jpg');

INSERT INTO `product` (`id`, `name`, `is_used`, `price`, `description`, `date`) VALUES
(1, 'Laptop 1', 0, 100, 'Test product \r\nversion 1', '2021-04-15'),
(2, 'Golden Retriever', 0, 10000, 'Born 3 months ago.', '2021-04-17'),
(3, 'Persian Cat', 0, 10000, 'Born 2 months ago.', '2021-04-17'),
(4, 'Kawasaki Ninja', 1, 2000000, 'Used Gently. Bought 2 years ago. \r\n\r\nIn good condition.\r\nWill provide helmet.', '2021-04-17'),
(5, 'Tesla', 1, 3000000, 'Super Fast Charging.\r\nFull charge in 20 minutes. Build quality is too good.\r\nNot even a single scratch.', '2021-04-17'),
(6, 'Digital Watch', 0, 5000, 'Never Used. Bought from Evaly :p', '2021-04-17'),
(7, 'Starry Night Painting (Remake)', 0, 45000, 'Oil and brush Painted.', '2021-04-17'),
(8, 'Signed Bat', 1, 50000, 'Signed by Shakib Al Hasan. Authentic product.', '2021-04-17'),
(9, 'Book Set', 1, 5000, 'All book are in good condition.\r\n<br>Name of the books-<br>\r\n1. abc\r\n2. xyz\r\n3. pqr<br>\r\n4. def<br>\r\n5. bla bla bla', '2021-04-17'),
(10, 'Ps4', 1, 12000, 'Used over 2 years. Will provide 2 games for free.', '2021-04-17');

INSERT INTO `product_image` (`product_id`, `image_id`) VALUES
(1, 4),
(1, 5),
(2, 14),
(2, 15),
(2, 16),
(3, 17),
(3, 18),
(3, 19),
(4, 20),
(4, 21),
(4, 22),
(5, 23),
(5, 24),
(6, 25),
(6, 26),
(6, 27),
(6, 28),
(7, 30),
(8, 31),
(8, 32),
(8, 33),
(9, 34),
(9, 35),
(9, 36),
(10, 37),
(10, 38),
(10, 39);

INSERT INTO `user` (`id`, `name`, `email`, `password`, `phone`, `role`) VALUES
(2, 'user2', 'user2@email.com', '0000000000000000000000000000202cb962ac59075b964b07152d234b70', '01234567891', 'user'),
(3, 'user1', 'user1@email.com', '0000000000000000000000000000202cb962ac59075b964b07152d234b70', '01234567891', 'user'),
(4, 'admin1', 'admin1@email.com', '0000000000000000000000000000202cb962ac59075b964b07152d234b70', '01234567890', 'admin'),
(5, 'admin2', 'admin2@email.com', '0000000000000000000000000000202cb962ac59075b964b07152d234b70', '01234567892', 'admin'),
(6, 'user3', 'user3@email.com', '0000000000000000000000000000202cb962ac59075b964b07152d234b70', '01234567894', 'user');

INSERT INTO `user1_user2` (`id`, `user_id1`, `user_id2`) VALUES
(2, 3, 2);

INSERT INTO `user_image` (`user_id`, `image_id`) VALUES
(3, 13),
(6, 29),
(5, 40);

INSERT INTO `user_product` (`id`, `user_id`, `product_id`, `status`) VALUES
(1, 3, 1, 'sold'),
(2, 6, 1, 'offered'),
(5, 6, 1, 'bought'),
(6, 3, 2, 'posted'),
(7, 3, 3, 'pending'),
(8, 2, 4, 'pending'),
(9, 2, 5, 'posted'),
(10, 2, 6, 'rejected'),
(11, 6, 7, 'pending'),
(12, 6, 8, 'rejected'),
(13, 6, 9, 'pending'),
(14, 6, 10, 'pending'),
(15, 6, 2, 'offered'),
(16, 6, 5, 'offered'),
(17, 2, 2, 'offered');
COMMIT;
