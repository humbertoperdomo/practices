#!/usr/bin/python3

current_users = ['anonymous', 'webmaster', 'admin', 'dbadmin', 'humberto']

new_users = ['guest', 'WEBMASTER', 'postgres', 'humberto', 'elliot']

for new_user in new_users:
    if new_user.lower() in current_users:
        print("You need to enter a new username")
    else:
        print("Username " + new_user + " is available.")

