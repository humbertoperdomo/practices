#!/usr/bin/python3

while True:
    name = input('Who are you?\n')
    if name != 'Joe':
        continue
    password = input('Hello, Joe. What is the password? (It is a fish.)\n')
    if password == 'swordfish':
        break
print('Access granted.')
