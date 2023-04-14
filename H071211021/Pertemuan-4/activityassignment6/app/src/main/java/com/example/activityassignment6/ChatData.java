package com.example.activityassignment6;

import java.util.ArrayList;

public class ChatData {
    private static String[] message = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras vel.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec congue velit congue semper porta. Donec orci dui, fermentum at finibus at, porta nec nulla. Duis tristique pulvinar nulla, eget elementum risus imperdiet quis. Nunc mattis efficitur auctor. Aliquam suscipit risus vel orci sollicitudin euismod. Pellentesque nisi lectus, varius ut mauris eu, ullamcorper fermentum lectus. Interdum et malesuada fames ac ante ipsum primis in faucibus.",
            "Quisque consectetur odio rutrum orci sollicitudin, id feugiat nibh suscipit. Vivamus tempor tellus non leo finibus varius tristique eu erat. Phasellus orci sapien, semper vel varius quis, pharetra eget ipsum. ",
            "Phasellus ut enim porttitor, placerat nunc ac, dictum ante. Donec eu magna malesuada, placerat ligula ut, vehicula urna. Aliquam egestas eu elit a consequat. Fusce id placerat leo. Curabitur id viverra diam, sed bibendum magna.",
            "Maecenas vitae massa hendrerit, tempus erat at, scelerisque leo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vivamus egestas nunc vel justo viverra, a gravida ante accumsan. Etiam a convallis erat. Duis sit amet felis vitae magna fermentum condimentum vel nec eros. ",
            "Curabitur posuere elit."
    };
    private  static String[] time = {
            "14.15",
            "14.17",
            "18.09",
            "19.16",
            "19.50",
            "21.12"
    };
    static ArrayList<Chat> getChatData(){
        ArrayList<Chat> list = new ArrayList<>();
        for (int i = 0; i < message.length; i++){
            Chat chat = new Chat();
            chat.setMessage(message[i]);
            chat.setTime(time[i]);
            list.add(chat);
        }
        return list;
    }
}
