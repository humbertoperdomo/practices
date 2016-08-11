#!/usr/bin/python3

def make_album(artist, title, number_of_tracks=''):
    album = {'artist': artist, 'title': title}
    if number_of_tracks:
        album['number_of_tracks'] =  number_of_tracks 
    return album

complete_list = []
while True:
    print("Enter the album information.")
    print("(enter 'q' to quit any time)")
    
    artist_n = input("Artist name: ")
    if artist_n == 'q':
        break

    album_t = input("Album title: ")
    if album_t == 'q':
        break

    complete_list.append(make_album(title=album_t, artist=artist_n))

print("List of all entered albums")
for album in complete_list:
    print(album)
