#!/usr/bin/python3
# color_spiral.py
"""Draws a color spiral
"""
import turtle

# You can choose between 2 and 6 sides for some cool shapes!
SIDES = eval(input("Enter a number of sides between 2 and 6: "))
COLORS = ["red", "yellow", "blue", "orange", "green", "purple"]

t = turtle.Pen()
turtle.bgcolor("black")
for x in range(360):
    t.pencolor(COLORS[x%SIDES])
    t.forward(x * 3 / SIDES + x)
    t.left(360 / SIDES + 1)
    t.width(x * SIDES / 200)
    t.left(90)
