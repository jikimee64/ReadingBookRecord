package com.soap.objects.chapter11;

import com.soap.objects.chapter10.bad3.Playlist;
import com.soap.objects.chapter10.bad3.Song;

//합성관계로 풀어도 Playlist와 PersonalPlaylist를 함께 수정해야 함
//그래도 상속보단 나은 방법이다 왜? Playlist를 변경하더라도 파급효과를 최대한 PersonalPlaylist 내부로 캡슐화 가능해서
public class PersonalPlaylist {
    private Playlist playlist = new Playlist();

    public void append(Song song){
        playlist.append(song);
    }

    public void remove(Song song){
        playlist.getTracks().remove(song);
        playlist.getSingers().remove(song.getSinger());
    }

}