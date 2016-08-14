#!/usr/bin/python3

def show_magicians(magicians):
    for magician in magicians: 
        print(magician.title())

def make_great(magicians):
    for i in range(len(magicians)):
        magicians[i] = "the Great " + magicians[i]


magicians = ['david copperfield', 'val valentino', 'harry houdini', 'david blaine', 'criss angel']
make_great(magicians)
show_magicians(magicians)
