package main;

import entity.Cultist;
import object.*;

import java.io.IOException;

public class AssetSetter{
    GamePanel gp;
    public int size = 200;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void treeSetter(int i, int j, int k){
        gp.obj[i] = new obj_Tree();
        gp.obj[i].worldX = j*gp.tileSizeRes;
        gp.obj[i].worldY = k*gp.tileSizeRes;
    }

    public void cultistSetter(int i, int j, int k) throws IOException {
        gp.cultist[i] = new Cultist(gp);
        gp.cultist[i].worldX = j*gp.tileSizeRes;
        gp.cultist[i].worldY = k*gp.tileSizeRes;
    }
    public void setTree(){

        treeSetter(8, 19,87);
        treeSetter(9, 28,82);
        treeSetter(10, 24,87);
        treeSetter(11, 5,85);
        treeSetter(12, 7,84);
        treeSetter(13, 34,86);
        treeSetter(14, 33,84);
        treeSetter(15, 26,88);
        treeSetter(16, 39,82);
        treeSetter(17, 19,82);
        treeSetter(18, 10,82);
        treeSetter(19, 7,80);
        treeSetter(20, 3,82);
        treeSetter(21, 22,80);
        treeSetter(22, 20,79);
        treeSetter(23, 32,81);
        treeSetter(24, 16,81);
        treeSetter(25, 6,70);
        treeSetter(26, 5,77);
        treeSetter(27, 13,77);
        treeSetter(28, 25,77);
        treeSetter(29, 30,70);
        treeSetter(30, 29,74);
        treeSetter(31, 14,74);
        treeSetter(32, 31,76);
        treeSetter(33, 13,86);
        treeSetter(34, 27,71);
        treeSetter(35, 27,75);
        treeSetter(36, 20,75);
        treeSetter(37, 18,69);
        treeSetter(38, 12,70);
        treeSetter(39, 6,76);
        treeSetter(40, 1, 62);
        treeSetter(41, 2,63);
        treeSetter(42, 14,87);
        treeSetter(43, 12,88);
        treeSetter(44, 18,86);
        treeSetter(45, 19,66);
        treeSetter(46, 28,60);
        treeSetter(47, 24,65);
        treeSetter(48, 5,65);
        treeSetter(49, 7,64);
        treeSetter(50, 34,64);
        treeSetter(51, 33,62);
        treeSetter(52, 26,65);
        treeSetter(53, 39,60);
        treeSetter(54, 19,60);
        treeSetter(55, 10,60);
        treeSetter(56, 7,58);
        treeSetter(57, 3,60);
        treeSetter(58, 22,58);
        treeSetter(59, 20,57);
        treeSetter(60, 32,59);
        treeSetter(61, 16,59);
        treeSetter(62, 6,48);
        treeSetter(63, 5,56);
        treeSetter(64, 13,56);
        treeSetter(65, 25,56);
        treeSetter(66, 30,48);
        treeSetter(67, 29,52);
        treeSetter(68, 14,52);
        treeSetter(69, 31,54);
        treeSetter(70, 13,64);
        treeSetter(71, 27,49);
        treeSetter(72, 27,53);
        treeSetter(73, 20,53);
        treeSetter(74, 18,47);
        treeSetter(75, 12,48);
        treeSetter(76, 6,54);
        treeSetter(77, 56,34);
        treeSetter(78, 1, 40);
        treeSetter(79, 2,41);
        treeSetter(80, 14,65);
        treeSetter(81, 12,66);
        treeSetter(82, 18,64);
        treeSetter(83, 68,60);
        treeSetter(84, 64,65);
        treeSetter(85, 45,65);
        treeSetter(86, 47,38);
        treeSetter(87, 74,64);
        treeSetter(88, 73,62);
        treeSetter(89, 66,65);
        treeSetter(90, 79,60);
        treeSetter(91, 59,60);
        treeSetter(92, 50,60);
        treeSetter(93, 47,58);
        treeSetter(94, 43,60);
        treeSetter(95, 62,58);
        treeSetter(96, 60,57);
        treeSetter(97, 72,59);
        treeSetter(98, 56,59);
        treeSetter(99, 46,48);
        treeSetter(100, 45,56);
        treeSetter(101, 53,56);
        treeSetter(102, 65,56);
        treeSetter(103, 70,48);
        treeSetter(104, 69,52);
        treeSetter(105, 54,52);
        treeSetter(106, 71,54);
        treeSetter(107, 53,64);
        treeSetter(108, 67,49);
        treeSetter(109, 67,53);
        treeSetter(110, 60,53);
        treeSetter(111, 58,47);
        treeSetter(112, 52,48);
        treeSetter(113, 6,54);
        treeSetter(114, 41, 40);
        treeSetter(115, 42,41);
        treeSetter(116, 54,65);
        treeSetter(117, 52,66);
        treeSetter(118, 58,64);
        treeSetter(119, 59,87);
        treeSetter(120, 68,82);
        treeSetter(121, 64,87);
        treeSetter(122, 45,85);
        treeSetter(123, 47,84);
        treeSetter(124, 74,86);
        treeSetter(125, 73,84);
        treeSetter(126, 66,87);
        treeSetter(127, 79,82);
        treeSetter(128, 59,82);
        treeSetter(129, 50,82);
        treeSetter(130, 47,80);
        treeSetter(131, 43,82);
        treeSetter(132, 62,80);
        treeSetter(133, 60,79);
        treeSetter(134, 72,81);
        treeSetter(135, 56,81);
        treeSetter(136, 46,70);
        treeSetter(137, 45,77);
        treeSetter(138, 53,77);
        treeSetter(139, 65,77);
        treeSetter(140, 70,70);
        treeSetter(141, 69,74);
        treeSetter(142, 54,74);
        treeSetter(143, 71,76);
        treeSetter(144, 53,86);
        treeSetter(145, 67,71);
        treeSetter(146, 67,75);
        treeSetter(147, 60,75);
        treeSetter(148, 58,69);
        treeSetter(149, 52,70);
        treeSetter(150, 46,76);
        treeSetter(151, 41, 62);
        treeSetter(152, 42,63);
        treeSetter(153, 54,87);

        treeSetter(154, 19,40);
        treeSetter(155, 28,35);
        treeSetter(156, 24,47);
        treeSetter(157, 5,38);
        treeSetter(158, 7,37);
        treeSetter(159, 34,39);
        treeSetter(160, 33,37);
        treeSetter(161, 26,46);
        treeSetter(162, 39,35);
        treeSetter(163, 19,35);
        treeSetter(164, 10,35);
        treeSetter(165, 7,33);
        treeSetter(166, 3,35);
        treeSetter(167, 22,33);
        treeSetter(168, 20,32);
        treeSetter(169, 32,34);
        treeSetter(170, 16,34);
        treeSetter(171, 6,23);
        treeSetter(172, 5,40);
        treeSetter(173, 13,37);
        treeSetter(174, 25,37);
        treeSetter(175, 30,23);
        treeSetter(176, 29,27);
        treeSetter(177, 14,27);
        treeSetter(178, 31,28);
        treeSetter(179, 13,39);
        treeSetter(180, 27,24);
        treeSetter(181, 27,28);
        treeSetter(182, 20,28);
        treeSetter(183, 18,22);
        treeSetter(184, 12,23);
        treeSetter(185, 6,29);
        treeSetter(186, 1, 15);
        treeSetter(187, 2,16);
        treeSetter(188, 14,47);
        treeSetter(189, 12,41);
        treeSetter(190, 18,39);

        treeSetter(191, 75,70);
        treeSetter(192, 80,76);
        treeSetter(193, 81, 89);
        treeSetter(194,  78,74);
        treeSetter(195, 76,87);
        treeSetter(196, 74,88);
        treeSetter(197, 72,86);
        treeSetter(198, 39, 40);
        treeSetter(199, 41, 52);
        treeSetter(200, 35, 45);
        treeSetter(201, 37, 76);
        treeSetter(202, 43, 70);
        treeSetter(203, 46, 78);

        treeSetter(204, 19,15);
        treeSetter(205, 28,10);
        treeSetter(206, 24,22);
        treeSetter(207, 5,13);
        treeSetter(208, 7,12);
        treeSetter(209, 34,14);
        treeSetter(210, 33,12);
        treeSetter(211, 26,21);
        treeSetter(212, 39,10);
        treeSetter(213, 19,10);
        treeSetter(214, 10,10);
        treeSetter(215, 7,8);
        treeSetter(216, 3,10);
        treeSetter(217, 22,8);
        treeSetter(218, 20,7);
        treeSetter(219, 32,9);
        treeSetter(220, 16,9);
        treeSetter(221, 7,2);
        treeSetter(222, 5,15);
        treeSetter(223, 13,12);
        treeSetter(224, 25,12);
        treeSetter(225, 78,4);
        treeSetter(226, 29,2);
        treeSetter(227, 53,2);
        treeSetter(228, 31,3);
        treeSetter(229, 13,14);
        treeSetter(230, 27,1);
        treeSetter(231, 27,3);
        treeSetter(232, 29,5);
        treeSetter(233, 18,3);
        treeSetter(234, 70,2);
        treeSetter(235, 6,4);
        treeSetter(236, 1, 10);
        treeSetter(237, 2,9);
        treeSetter(238, 14,22);
        treeSetter(239, 12,16);
        treeSetter(240, 18,14);
        treeSetter(241, 35,72);
        treeSetter(242, 38,67);
        treeSetter(243, 64,46);
        treeSetter(244, 69,42);
        treeSetter(245, 74,39);
        treeSetter(246, 68,35);
        treeSetter(247, 59,39);
        treeSetter(248, 53,40);
        treeSetter(249, 64,34);

        treeSetter(250, 46,34);
        treeSetter(251, 51,36);
        treeSetter(252, 56,28);
        treeSetter(253, 49,27);
        treeSetter(254, 59,27);
        treeSetter(255, 70,29);
        treeSetter(256, 77,32);
        treeSetter(257, 74,26);
        treeSetter(258, 75,24);
        treeSetter(259, 60,19);
        treeSetter(260, 63,19);
        treeSetter(261, 51,23);

        treeSetter(262, 39,15);
        treeSetter(263, 48,10);
        treeSetter(264, 44,22);
        treeSetter(265, 35,13);
        treeSetter(266, 37,12);
        treeSetter(267, 54,14);
        treeSetter(268, 53,12);
        treeSetter(269, 46,21);
        treeSetter(270, 59,10);
        treeSetter(271, 59,10);
        treeSetter(272, 50,13);
        treeSetter(273, 37,8);
        treeSetter(274, 33,10);
        treeSetter(275, 44,8);
        treeSetter(276, 42,7);
        treeSetter(277, 52,9);
        treeSetter(278, 36,9);
        treeSetter(279, 36,2);
        treeSetter(280, 35,15);
        treeSetter(281, 33,12);
        treeSetter(282, 45,12);
        treeSetter(283, 59,7);
        treeSetter(284, 29,2);
        treeSetter(285, 20,2);
        treeSetter(286, 51,3);
        treeSetter(287, 63,14);
        treeSetter(288, 47,1);
        treeSetter(289, 37,3);
        treeSetter(290, 40,5);
        treeSetter(291, 58,3);
        treeSetter(292, 40,2);
        treeSetter(293, 56,4);
        treeSetter(294, 51, 10);
        treeSetter(295, 42,9);
        treeSetter(296, 34,22);
        treeSetter(297, 32,16);
        treeSetter(298, 38,14);
        treeSetter(299, 55,72);
        treeSetter(300, 58,67);
        treeSetter(301, 54,46);
        treeSetter(302, 59,42);
        treeSetter(303, 44,39);
        treeSetter(304, 58,35);
        treeSetter(305, 49,39);
        treeSetter(306, 73,40);
        treeSetter(307, 74,34);
        treeSetter(308, 70,14);
        treeSetter(309, 64,40);
        treeSetter(310, 42,45);
        treeSetter(311, 73,19);
        treeSetter(312, 65,27);
        treeSetter(313, 77,13);
        treeSetter(314, 37,28);
        treeSetter(315, 44,29);
        treeSetter(316, 36,49);
        treeSetter(317, 46,16);
        treeSetter(318, 13,30);
        treeSetter(319, 39,20);
        treeSetter(320, 66,9);
        treeSetter(321, 62,23);
        treeSetter(322, 63,31);
        treeSetter(323, 19,17);
        treeSetter(324, 39,24);
        treeSetter(325, 52,31);
        treeSetter(326, 77, 44);
        for(int i = 0; i<=84;i++){
            treeSetter(i+326, i, 0);
        }
        for(int i = 1; i<=93;i++){
            treeSetter(i+326+84, 0, i);
        }
        for(int i = 1; i<=93;i++){
            treeSetter(i+326+84+93, 84, i);
        }















//        treeSetter(37, 6,16);
//        treeSetter(38, 6,16);
//        treeSetter(39, 6,16);
//        treeSetter(40, 6,16);





    }

    public void setCultists() throws IOException {
        for(int i = 0; i<=42; i++){
            cultistSetter(i, ((i+1)*2), 93);
        }
    }
    public void setGun(){
        gp.obj[1] = new obj_Gun();
        gp.obj[1].worldX = -100*gp.tileSizeRes;
        gp.obj[1].worldY = -100*gp.tileSizeRes;
    }
    public void setCar(){
        gp.obj[7] = new obj_Car();
        gp.obj[7].worldX = 17*gp.tileSizeRes;
        gp.obj[7].worldY = 4290;
    }
    public void setWater(){
        gp.obj[3] = new obj_Water();
        gp.obj[3].worldX = 8*gp.tileSizeRes;
        gp.obj[3].worldY = 18*gp.tileSizeRes;
    }

    public void setShed(){
        gp.obj[4] = new obj_Shed();
        gp.obj[4].worldX = 22*gp.tileSizeRes;
        gp.obj[4].worldY = 12*gp.tileSizeRes;
    }

    public void setWell(){
        gp.obj[4] = new obj_Well();
        gp.obj[4].worldX = 29*gp.tileSizeRes;
        gp.obj[4].worldY = 42*gp.tileSizeRes;
    }

    public void setStones(){
        gp.obj[2] = new obj_Stones();
        gp.obj[2].worldX = 10*gp.tileSizeRes;
        gp.obj[2].worldY = 4*gp.tileSizeRes;
    }

    public void setBones(){
        gp.obj[5] = new obj_Bones();
        gp.obj[5].worldX = 71*gp.tileSizeRes;
        gp.obj[5].worldY = 6*gp.tileSizeRes;
    }

    public void setSac(){
        gp.obj[6] = new obj_Sac(gp);
        gp.obj[6].worldX = 75*gp.tileSizeRes;
        gp.obj[6].worldY = 54*gp.tileSizeRes;
    }
}
