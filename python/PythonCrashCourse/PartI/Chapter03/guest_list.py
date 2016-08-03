#!/usr/bin/python3

guests = ['jair', 'kurt cobain', 'jack white']

print("Hello " + guests[0].title() + ", I would like to invite you for dinner.")
print("Hello " + guests[1].title() + ", I would like to invite you for dinner.")
print("Hello " + guests[2].title() + ", I would like to invite you for dinner.")
print("Number of guests: " + str(len(guests)))

print("\n" + guests[1].title() + " is out.")

guests[1] = "jake bugg"

print("\nHello " + guests[0].title() + ", I would like to invite you for dinner.")
print("Hello " + guests[1].title() + ", I would like to invite you for dinner.")
print("Hello " + guests[2].title() + ", I would like to invite you for dinner.")
print("Number of guests: " + str(len(guests)))

print('\nHello guys, I just found a bigger table so, I will invite some other friends.')

guests.insert (0, 'Ruben Albarran')
guests.insert (2, 'Juan Son')
guests.append ('Steve Wozniak')

print("\nHello " + guests[0].title() + ", I would like to invite you for dinner.")
print("Hello " + guests[1].title() + ", I would like to invite you for dinner.")
print("Hello " + guests[2].title() + ", I would like to invite you for dinner.")
print("Hello " + guests[3].title() + ", I would like to invite you for dinner.")
print("Hello " + guests[4].title() + ", I would like to invite you for dinner.")
print("Hello " + guests[5].title() + ", I would like to invite you for dinner.")
print("Number of guests: " + str(len(guests)))

print ("\nI'm so sorry guys, my new table won't arrive on time for dinner so, I only can invite 2 of you")

print("\nI'm sorry " + guests.pop() + ". I'm afraid that you can't come to dinner")
print("I'm sorry " + guests.pop() + ". I'm afraid that you can't come to dinner")
print("I'm sorry " + guests.pop() + ". I'm afraid that you can't come to dinner")
print("I'm sorry " + guests.pop() + ". I'm afraid that you can't come to dinner")

print("\nHello " + guests[0].title() + ", I would like to invite you for dinner.")
print("Hello " + guests[1].title() + ", I would like to invite you for dinner.")
print("Number of guests: " + str(len(guests)) + "\n")

del guests[0]
del guests[0]

print(guests)
print("Number of guests: " + str(len(guests)))
