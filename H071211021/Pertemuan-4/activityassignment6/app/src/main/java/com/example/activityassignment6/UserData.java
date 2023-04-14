package com.example.activityassignment6;

import java.util.ArrayList;

public class UserData {
    private static String[] username = {
            "Luffy",
            "Zoro",
            "Nami",
            "Usopp",
            "Sanji",
            "Chopper",
            "Robin",
            "Franky",
            "Brook",
            "Jimbei",
            "Vivi"
    };
    private static String[] imageUri = {
            "https://static.wikia.nocookie.net/onepiece/images/e/e9/Luffy_Missing_a_Tooth.png/revision/latest?cb=20180304032559",
            "https://static.wikia.nocookie.net/onepiece/images/8/8e/Roronoa_Zoro_Pre_Timeskip_Portrait.png/revision/latest?cb=20220812202526",
            "https://static.wikia.nocookie.net/onepiece/images/2/26/Nami_Portrait.png/revision/latest/scale-to-width-down/119?cb=20211209030803",
            "https://static.wikia.nocookie.net/onepiece/images/9/97/Usopp_Portrait.png/revision/latest/scale-to-width-down/119?cb=20190821161843",
            "https://static.wikia.nocookie.net/onepiece/images/0/0c/Sanji_Portrait.png/revision/latest/scale-to-width-down/119?cb=20230204061747",
            "https://static.wikia.nocookie.net/onepiece/images/8/84/Tony_Tony_Chopper_Portrait.png/revision/latest/scale-to-width-down/119?cb=20191225101353",
            "https://static.wikia.nocookie.net/onepiece/images/9/90/Nico_Robin_Post_Timeskip_Portrait.png/revision/latest/scale-to-width-down/119?cb=20200823171357",
            "https://static.wikia.nocookie.net/onepiece/images/a/a8/Franky_Portrait.png/revision/latest/scale-to-width-down/119?cb=20200426030735",
            "https://static.wikia.nocookie.net/onepiece/images/3/37/Brook_Portrait.png/revision/latest/scale-to-width-down/119?cb=20200426030609",
            "https://static.wikia.nocookie.net/onepiece/images/f/f7/Jinbe_Portrait.png/revision/latest/scale-to-width-down/119?cb=20200426030056",
            "https://static.wikia.nocookie.net/onepiece/images/3/39/Nefertari_Vivi_Portrait.png/revision/latest/scale-to-width-down/119?cb=20180629074747"
    };
    private static String[] message = {
            "Halo saya Luffy",
            "Halo saya Zoro",
            "Halo saya Nami",
            "Halo saya Usopp",
            "Halo saya Sanji",
            "Halo saya Chopper",
            "Halo saya Robin",
            "Halo saya Franky",
            "Halo saya Brook",
            "Halo saya Jimbei",
            "Halo saya Vivi"
    };
    private static String[] time = {
            "22.22",
            "22.22",
            "22.22",
            "22.22",
            "22.22",
            "22.22",
            "22.22",
            "22.22",
            "22.22",
            "22.22",
            "22.22"
    };

    private static String[] status = {
            "Halo saya Luffy",
            "Halo saya Zoro",
            "Halo saya Nami",
            "Halo saya Usopp",
            "Halo saya Sanji",
            "Halo saya Chopper",
            "Halo saya Robin",
            "Halo saya Franky",
            "Halo saya Brook",
            "Halo saya Jimbei",
            "Halo saya Vivi"
    };

    private static String[] statusDate = {
            "12 April, 2023",
            "11 April, 2023",
            "10 April, 2023",
            "9 April, 2023",
            "8 April, 2023",
            "7 April, 2023",
            "6 April, 2023",
            "5 April, 2023",
            "4 April, 2023",
            "3 April, 2023",
            "2 April, 2023",
    };
    private static String[] phoneNumber = {
            "+62 852-1111-1111",
            "+62 852-1111-1112",
            "+62 852-1111-1113",
            "+62 852-1111-1114",
            "+62 852-1111-1115",
            "+62 852-1111-1116",
            "+62 852-1111-1117",
            "+62 852-1111-1118",
            "+62 852-1111-1119",
            "+62 852-1111-1110",
            "+62 852-1111-1120"
    };

    static ArrayList<User> getUserData(){
        ArrayList<User> list = new ArrayList<>();
        for (int i = 0; i < username.length; i++){
            User user = new User();
            Chat lastChat = new Chat();
            lastChat.setMessage(message[i]);
            lastChat.setTime(time[i]);
            user.setUsername(username[i]);
            user.setImageUri(imageUri[i]);
            user.setStatus(status[i]);
            user.setStatusDate(statusDate[i]);
            user.setPhoneNumber(phoneNumber[i]);
            user.setLastChat(lastChat);
            list.add(user);
        }
        return list;
    }
}
