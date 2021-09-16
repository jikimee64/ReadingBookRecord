package com.soap.objects.object10.bad3;

public class PersonalPlaylist extends Playlist{
    public void remove(Song song){
        getTracks().remove(song);
        getSingers().remove(song.getSinger()); //부모 클래스에 인스턴스가 추가될경우 관리 필요
    }
}