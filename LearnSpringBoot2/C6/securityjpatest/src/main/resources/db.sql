INSERT INTO `tb_role` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `tb_role` VALUES (2, 'ROLE_DBA');
INSERT INTO `tb_role` VALUES (3, 'ROLE_USER');

INSERT INTO `tb_user` VALUES (1, 'admin', '$2a$10$eQjDl4m6kwftad.vbPuQPu1yt07LhoPsP5cW0qx4tl8HKr1xhrBiS', '管理员');
INSERT INTO `tb_user` VALUES (2, 'fkit', '$2a$10$ISbP2yEA9hTBmcCBfZbWXe9NzXgEDiRbHLVDfuRxDESd8ZhWlhQy.', '疯狂软件');

INSERT INTO `tb_user_role` VALUES (1, 1);
INSERT INTO `tb_user_role` VALUES (1, 2);
INSERT INTO `tb_user_role` VALUES (2, 3);