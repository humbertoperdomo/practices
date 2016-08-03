#!/usr/bin/python3

banned_users = ['andrew', 'carolina', 'david']
user = 'marie'
game_active = True
can_edit = False

if user not in banned_users:
    print(user.title() + ", you can post a response if you wish.")
