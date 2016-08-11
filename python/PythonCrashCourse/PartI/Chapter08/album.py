#!/usr/bin/python3

def make_album(artist, title, number_of_tracks=''):
    album = {'artist': artist, 'title': title}
    if number_of_tracks:
        album['number_of_tracks'] =  number_of_tracks 
    return album

new_album = make_album('jack white', 'lazaretto')
print(new_album)
new_album = make_album('jake bugg', 'on my one', '11')
print(new_album)
new_album = make_album('die antwoord', 'donker mag', '16')
print(new_album)
